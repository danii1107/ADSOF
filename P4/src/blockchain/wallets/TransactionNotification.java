package src.blockchain.wallets;

import src.blockchain.interfaces.IMessage;

public class TransactionNotification implements IMessage {
	private Transaction transaction;

	public TransactionNotification(Transaction transaction) {
		this.transaction = transaction;	
	}

	public String getMessage() {
		return this.toString();
	
	}

	@Override
	public String toString() {
		return "Transaction " + this.transaction.getId() + "| from: " + this.transaction.getSender().getPublicKey() + ", to: " + this.transaction.getRecipient().getPublicKey() + ", quantity: " + this.transaction.getAmount();
	}
}
