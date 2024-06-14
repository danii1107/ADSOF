/**
 * This exception is thrown when a connection error occurs in the blockchain network.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.blockchain.exceptions.connection;

public class ConnectionException extends RuntimeException {
	protected Integer id;

	/**
	 * Constructs a new ConnectionException for the specified 3 digit id.
	 * 
	 * @param id the id of the node
	 */
	public ConnectionException(Integer id) {
		this.id = id;
	}

	/**
	 * Returns a string representation of this ConnectionException.
	 *
	 * @return a string representation of this ConnectionException
	 */
	@Override
	public String toString() {
		return "Connection exception: Node " + String.format("%03d", this.id) + " is already connected to the network";
	}
}
