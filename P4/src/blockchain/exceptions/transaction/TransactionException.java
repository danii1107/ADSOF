package src.blockchain.exceptions.transaction;

import src.blockchain.wallets.Wallet;

/**
 * This exception is thrown when a negative transfer attempt is made in a transaction.
 */
public class TransactionException extends RuntimeException {
	private Wallet wallet;
	private Wallet receiver;
	private int amount;

	/**
	 * Constructs a new TransactionException with the specified wallet, receiver, and amount.
	 *
	 * @param wallet   the wallet from which the transfer is attempted
	 * @param receiver the wallet to which the transfer is attempted
	 * @param amount   the amount of the transfer
	 */
	public TransactionException(Wallet wallet, Wallet receiver, int amount) {
		this.wallet = wallet;
		this.receiver = receiver;
		this.amount = amount;
		toString();
	}

	/**
	 * Returns a string representation of this TransactionException.
	 *
	 * @return a string representation of this TransactionException
	 */
	@Override
	public String toString() {
		return "Negative transfer attempt: source: " + this.wallet.getPublicKey() + ", receiver: " + receiver.getPublicKey() + ", amount: " + amount;
	}
}
