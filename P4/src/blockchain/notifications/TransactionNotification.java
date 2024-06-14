/**
 * Represents a notification for a transaction.
 * 
 * @author Daniel Birsan danile.birsan@estudiante.uam.es
 */
package src.blockchain.notifications;

import src.blockchain.interfaces.IMessage;
import src.blockchain.wallets.Transaction;
import src.blockchain.components.Node;

public class TransactionNotification implements IMessage {
	private final Transaction transaction;
	private final String message;

	/**
	 * Constructs a TransactionNotification object with the given transaction.
	 * 
	 * @param transaction the transaction associated with the notification
	 */
	public TransactionNotification(Transaction transaction) {
		this.transaction = transaction;
		this.message = transaction.toString();
	}

	/**
	 * Gets the message of the notification.
	 * 
	 * @return the message of the notification
	 */
	@Override
	public String getMessage() {
		return this.message;
	}

	/**
	 * Processes the notification. If the node is null, the notification comes from a network
	 * 	and prints the message
	 * 
	 * @param n the node that processes the notification
	 */
	@Override
	public void process(Node n) {
		if (n != null) {
			IMessage.super.process(n);
			if (n.checkMining()) {
				if (n.getTransactions().contains(this.transaction)) {
					System.out.println("[" + n.fullName() + "] Transaction already confirmed: " + this.transaction.getName());
					return;
				}
				n.mineBlock(this.transaction);
			}
		}
		else
			System.out.println(this.getMessage());
	}

	/**
	 * Overrides the default toString method.
	 * 
	 * @return notification message
	 */
	@Override
	public String toString() {
		return this.message;
	}
}
