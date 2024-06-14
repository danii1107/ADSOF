/**
 * This exception is thrown when a transaction fails, f.i., when the amount is negative.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.blockchain.exceptions.transaction;


public class TransactionException extends RuntimeException {
	private String e;
	private String sender;
	private String receiver;
	private Integer amount;

	/**
	 * Constructs a new TransactionException with the specified wallet, receiver, and amount.
	 *
	 * @param e the related exception
	 * @param sender the public key of the sender
	 * @param receiver the public key of the receiver
	 * @param amount the amount of the transaction
	 */
	public TransactionException(String e, String sender, String receiver, Integer amount) {
		this.e = e;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}

	/**
	 * Returns a string representation of this TransactionException.
	 *
	 * @return a string representation of this TransactionException
	 */
	@Override
	public String toString() {
		return this.e + "source: " + this.sender + ", receiver: " + this.receiver + ", amount: " + this.amount;
	}
}
