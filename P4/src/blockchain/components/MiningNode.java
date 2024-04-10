package src.blockchain.components;

import src.blockchain.exceptions.transaction.TransactionException;
import src.blockchain.wallets.Transaction;
import src.blockchain.wallets.Wallet;
import src.blockchain.network.BlockchainNetwork;

public class MiningNode extends Node {
	private long computationalCapacity;
	private String name;
	private int id;

	public MiningNode(Wallet wallet, long computationalCapacity) {
		super(wallet);
		this.computationalCapacity = computationalCapacity;
		BlockchainNetwork.subId();
		this.id = BlockchainNetwork.generateId();
		if (this.id < 10) {
			this.name = "MiningNode#00" + this.id;
		} else if (this.id < 100) {
			this.name = "MiningNode#0" + this.id;
		} else {
			this.name = "MiningNode#" + this.id;
		}
	}

	@Override
	public String fullName() {
		return this.name;
	}

	public Transaction createTransaction(String wallet_pk, int amount) throws TransactionException {
		return super.createTransaction(Wallet.getWalletByKey(wallet_pk), amount);
	}

	@Override
	public String toString() {
		return "u: " + this.getWallet().getName() + ", PK:" + this.getWallet().getPublicKey() + ", balance: " + this.getWallet().getBalance() + " | @" + fullName();
	}
}
