package src.blockchain.components;
import java.util.ArrayList;
import java.util.List;

import src.blockchain.exceptions.transaction.TransactionException;
import src.blockchain.interfaces.IConnectable;
import src.blockchain.interfaces.IMessage;
import src.blockchain.network.BlockchainNetwork;
import src.blockchain.wallets.Transaction;
import src.blockchain.wallets.Wallet;

public class Node extends AbstractConnectable {
	private String name;
	private int id;
	private Wallet wallet;
	private List<Transaction> transactions = new ArrayList<>();

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

	public Transaction createTransaction(Wallet receiver, int amount) throws TransactionException {
		if (amount < 0)
			throw new TransactionException(this.wallet, receiver, amount);
		Transaction tr = new Transaction(this.wallet, receiver, amount);
		this.transactions.add(tr);
		return tr;
	}

	public void broadcast(IMessage message) {
		message.process(this);
	}

	public IConnectable getParent() {
		return null;
	}

	@Override
	public String toString() {
		return "u: " + wallet.getName() + ", PK:" + wallet.getPublicKey() + ", balance: " + wallet.getBalance() + " | @" + fullName();
	}
}
