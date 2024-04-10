package src.blockchain.exceptions.transaction;

import src.blockchain.wallets.Wallet;

public class TransactionException extends Exception {
	private Wallet wallet;
	private Wallet receiver;
	private int amount;

	public TransactionException(Wallet wallet, Wallet receiver, int amount) {
		this.wallet = wallet;
		this.receiver = receiver;
		this.amount = amount;
		toString();
	}

	@Override
	public String toString() {
		return "Negative transfer attempt: source: " + this.wallet.getPublicKey() + ", receiver: " + receiver.getPublicKey() + ", amount: " + amount;
	}
}
