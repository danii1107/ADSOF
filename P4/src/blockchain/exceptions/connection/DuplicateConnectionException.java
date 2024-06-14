/**
 * Exception thrown when a duplicate connection is detected.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.blockchain.exceptions.connection;

public class DuplicateConnectionException extends ConnectionException {
	/**
	 * Constructs a new DuplicateConnectionException for the specified 3 digit id.
	 * 
	 * @param id the id of the node
	 */
	public DuplicateConnectionException(Integer id) {
		super(id);
	}

	/**
	 * Returns a string representation of the exception.
	 * 
	 * @return a string representation of the exception
	 */
	@Override
	public String toString() {
		return "Connection exception: Node " + String.format("%03d", this.id) + " is connected to a different network";
	}
}
