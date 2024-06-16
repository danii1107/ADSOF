/**
 * Exception that is thrown when an error occurs in the Singleton pattern.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.exceptions.singleton;

public class SingletonException extends RuntimeException {
	private String e;

	/**
	 * Constructs a new SingletonException with the specified message.
	 *
	 * @param e the related exception
	 */
	public SingletonException(String e) {
		this.e = e;
	}

	/**
	 * Returns a string representation of this SingletonException.
	 *
	 * @return a string representation of this SingletonException
	 */
	@Override
	public String toString() {
		return "SingletonException: " + e;
	}
}
