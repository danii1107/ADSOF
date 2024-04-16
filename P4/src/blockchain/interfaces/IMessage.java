package src.blockchain.interfaces;

import src.blockchain.components.Node;

/**
 * The IMessage interface represents a message in a blockchain network.
 * It provides methods to get the message content and process the message.
 */
public interface IMessage {
	
	/**
	 * Gets the content of the message.
	 *
	 * @return the message content
	 */
	public String getMessage();
	
	/**
	 * Processes the message by printing a notification.
	 *
	 * @param n the node that received the message
	 */
	public default void process(Node n) {
		System.out.println("[" + n.fullName() + "]" +
				" - Received notification - Nex Tx: " +
				this.getMessage());
	}
}