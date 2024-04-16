package src.blockchain.exceptions.connection;

import src.blockchain.components.AbstractConnectable;
import src.blockchain.interfaces.IConnectable;

/**
 * This exception is thrown when a connection error occurs in the blockchain network.
 */
public class ConnectionException extends RuntimeException {
	private IConnectable e;

	/**
	 * Constructs a new ConnectionException with the specified IConnectable object.
	 *
	 * @param e the IConnectable object that caused the exception
	 */
	public ConnectionException(IConnectable e) {
		this.e = e;
		toString();
	}

	/**
	 * Returns a string representation of this ConnectionException.
	 *
	 * @return a string representation of this ConnectionException
	 */
	@Override
	public String toString() {
		String name = ((AbstractConnectable) e).fullName().replace("#", " ").replace("MiningNode", "Node");
		return "Connection exception: " + name + " is connected to a different network";
	}
}
