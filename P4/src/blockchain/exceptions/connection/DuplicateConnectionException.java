package src.blockchain.exceptions.connection;

import src.blockchain.interfaces.IConnectable;
import src.blockchain.components.AbstractConnectable;

/**
 * Exception thrown when a duplicate connection is detected.
 */
public class DuplicateConnectionException extends RuntimeException {
	private IConnectable e;

	/**
	 * Constructs a new DuplicateConnectionException with the specified IConnectable object.
	 * 
	 * @param e the IConnectable object that caused the exception
	 */
	public DuplicateConnectionException(IConnectable e) {
		this.e = e;
		toString();
	}

	/**
	 * Returns a string representation of the exception.
	 * 
	 * @return a string representation of the exception
	 */
	@Override
	public String toString() {
		String name = ((AbstractConnectable) e).fullName().replace("#", " ").replace("MiningNode", "Node");
		return "Connection exception: " + name + " is connected to a different network";
	}
}
