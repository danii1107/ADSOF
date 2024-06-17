/**
 * Represents a transaction between wallets.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.wallets;

public class Transaction {
	private static int nextId = 0;
	private final int id;
	private final String receiverKey;
	private final String senderKey;
	private final Integer amount;
	private final Wallet sender;
	private boolean isConfirmed;

	/**
	 * Constructs a new Transaction object.
	 *
	 * @param sender   the wallet of the sender
	 * @param receiver the wallet public key of the receiver
	 * @param amount   the amount of the transaction
	 */
	public Transaction(Wallet sender, String receiver, Integer amount) {
		this.id = nextId++;
		this.senderKey = sender.getPublicKey();
		this.receiverKey = receiver;
		this.amount = amount;
		this.sender = sender;
		this.isConfirmed = false;
	}

	/**
	 * Constructs a new Transaction object.
	 *
	 * @param sender   the wallet of the sender
	 * @param receiver the wallet of the receiver
	 * @param amount   the amount of the transaction
	 */
	public Transaction(Wallet sender, Wallet receiver, Integer amount) {
		this(sender, receiver.getPublicKey(), amount);
	}
	
	/**
	 * Gets the ID of the transaction.
	 *
	 * @return the ID of the transaction
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Gets the wallet public key of the sender.
	 *
	 * @return the public key of the sender
	 */
	public String getSender() {
		return this.senderKey;
	}

	/**
	 * Gets the wallet public key of the receiver.
	 *
	 * @return the public key of the receiver
	 */
	public String getReceiver() {
		return this.receiverKey;
	}

	/**
	 * Gets the amount of the transaction.
	 *
	 * @return the amount of the transaction
	 */
	public Integer getAmount() {
		return this.amount;
	}

	/**
	 * Checks if the transaction is confirmed.
	 *
	 * @return true if the transaction is confirmed, false otherwise
	 */
	public boolean isConfirmed() {
		return this.isConfirmed;
	}

	/**
	 * Confirms the transaction.
	 */
	public void confirm() {
		this.isConfirmed = true;
	}

	/**
	 * Returns a concatenation of Tx- and the transaction's id
	 * 
	 * @return String with the correct format
	 */
	public String getName() {
		return "Tx-" + this.id;
	}

	/**
	 * Applies the transaction to the wallets.
	 * 
	 * @param receiver the wallet that will receive the amount
	 */
	public void applyTransaction(Wallet receiver) {
		sender.setBalance(sender.getBalance() - this.amount);
		receiver.setBalance(receiver.getBalance() + this.amount);
		this.confirm();
	}

	/**
	 * Returns a string representation of a transaction
	 * 
	 * @return the formatted string
	 */
	@Override
	public String toString() {
		return "Transaction " + this.id + "| from: " + this.senderKey + ", to: " + this.receiverKey + ", quantity: " + this.amount;
	}
}
