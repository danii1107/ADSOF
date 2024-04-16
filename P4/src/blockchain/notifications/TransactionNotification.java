package src.blockchain.notifications;

import src.blockchain.interfaces.IMessage;
import src.blockchain.wallets.Transaction;

/**
 * Represents a notification for a transaction.
 * Implements the IMessage interface.
 */
public class TransactionNotification implements IMessage {
	private Transaction transaction;

	/**
	 * Constructs a TransactionNotification object with the given transaction.
	 * 
	 * @param transaction the transaction associated with the notification
	 */
	public TransactionNotification(Transaction transaction) {
		this.transaction = transaction;    
	}

	/**
	 * Gets the message of the notification.
	 * 
	 * @return the message of the notification
	 */
	public String getMessage() {
		return this.toString();
	}

	/**
	 * Returns a string representation of the TransactionNotification object.
	 * 
	 * @return a string representation of the TransactionNotification object
	 */
	@Override
	public String toString() {
		return "Transaction " + this.transaction.getId() + "| from: " + this.transaction.getSender().getPublicKey() + ", to: " + this.transaction.getRecipient().getPublicKey() + ", quantity: " + this.transaction.getAmount();
	}
}
