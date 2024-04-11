package src.blockchain.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import src.blockchain.exceptions.transaction.TransactionException;
import src.blockchain.interfaces.IConnectable;
import src.blockchain.interfaces.IMessage;
import src.blockchain.network.BlockchainNetwork;
import src.blockchain.wallets.Transaction;
import src.blockchain.notifications.TransactionNotification;
import src.blockchain.notifications.ValidateBlockRq;
import src.blockchain.notifications.ValidateBlockRes;
import src.blockchain.wallets.Wallet;

public class Node extends AbstractConnectable {
	private String name;
	private int id;
	private Wallet wallet;
	private List<Transaction> transactions = new ArrayList<>();
	private static String pendingTransaction;

	public Node(Wallet wallet) {
		this.id = BlockchainNetwork.generateId();
		if (this.id < 10) {
			this.name = "Node#00" + this.id;
		} else if (this.id < 100) {
			this.name = "Node#0" + this.id;
		} else {
			this.name = "Node#" + this.id;
		}
		this.wallet = wallet;
	}

	public Wallet getWallet() {
		return this.wallet;
	}
	
	@Override
	public String fullName() {
		return this.name;
	}

	public Transaction createTransaction(Wallet receiver, int amount) {
		if (amount < 0)
			throw new TransactionException(this.wallet, receiver, amount);
		Transaction tr = new Transaction(this.wallet, receiver, amount);
		return tr;
	}

	public Transaction createTransaction(String wallet_pk, int amount) {
		return this.createTransaction(Wallet.getWalletByKey(wallet_pk), amount);
	}

	public void broadcast(IMessage message) {
		if (message instanceof TransactionNotification) {
			pendingTransaction = message.getMessage();
			message.process(this);
			Transaction tr = Transaction.getTransactionByMessage(message.getMessage());
			if (tr.isConfirmed()) {
				this.transactions.add(tr);
				System.out.println("[" + this.fullName() + "] Transaction already confirmed: Tx-" + tr.getId());
				return;
			}
		}
		if (message instanceof ValidateBlockRq) {
			System.out.println("[" + this.fullName() + "] Received Task: " + message.getMessage());
		}
		if (message instanceof ValidateBlockRes) {
			System.out.println("[" + this.fullName() + "] Received Task: " + message.getMessage());
			if (message.getMessage().contains("true")) {
				Transaction tr = Transaction.getTransactionByMessage(pendingTransaction);
				System.out.println("[" + this.fullName() + "] Commiting transaction : Tx-" + tr.getId() + " in " + this.fullName());
				System.out.println("[" + this.fullName() + "]  -> Tx details:" + pendingTransaction);
				if (tr.getSender().getPublicKey().equals(this.wallet.getPublicKey()) || tr.getRecipient().getPublicKey().equals(this.wallet.getPublicKey())) {
					System.out.println("[" + this.fullName() + "] Applied Transaction: " + pendingTransaction);
					if (tr.getRecipient().getPublicKey().equals(this.wallet.getPublicKey())) {
						this.getWallet().setBalance(this.getWallet().getBalance() + tr.getAmount());
					} else {
						this.getWallet().setBalance(this.getWallet().getBalance() - tr.getAmount());
					}
					System.out.println("[" + this.fullName() + "] New wallet value: " + this.toString().substring(0, this.toString().indexOf(" |")));
					tr.confirm();
					this.transactions.add(tr);
				}
			}
		}
	}

	public IConnectable getParent() {
		Set<BlockchainNetwork> networks = BlockchainNetwork.getNetworks();
		for (BlockchainNetwork network : networks) {
			if (network.getElements() != null && network.getElements().contains(this)) {
				return ((IConnectable) network);
			}
		}
		Set<Subnet> subnets = Subnet.getSubnetworks();
		for (Subnet subnet : subnets) {
			if (subnet.getNodes().contains(this)) {
				return ((IConnectable) subnet);
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "u: " + wallet.getName() + ", PK:" + wallet.getPublicKey() + ", balance: " + wallet.getBalance() + " | @" + fullName();
	}
}
