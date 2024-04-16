package src.blockchain.wallets;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a transaction in a blockchain.
 */
public class Transaction {
	private static List<Transaction> transactions = new ArrayList<>();
	private int id;
	private static int nextId = 0;
	private Wallet sender;
	private Wallet receiver;
	private int amount;
	private boolean isConfirmed;

	/**
	 * Constructs a new Transaction object.
	 *
	 * @param sender   the wallet of the sender
	 * @param receiver the wallet of the receiver
	 * @param amount   the amount of the transaction
	 */
	public Transaction(Wallet sender, Wallet receiver, int amount) {
		this.id = nextId++;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.isConfirmed = false;
		transactions.add(this);
	}

	/**
	 * Retrieves a transaction by its ID.
	 *
	 * @param id the ID of the transaction
	 * @return the transaction with the specified ID, or null if not found
	 */
	public static Transaction getTransactionById(int id) {
		Transaction tr = transactions.stream().filter(transaction -> transaction.getId() == id).findFirst().orElse(null);
		return tr;
	}

	/**
	 * Retrieves a transaction by its message.
	 *
	 * @param message the message containing the transaction ID
	 * @return the transaction with the specified ID, or null if not found
	 */
	public static Transaction getTransactionByMessage(String message) {
		String[] parts = message.split(" ");
		int id = Integer.parseInt(parts[1].replace("|", ""));
		return getTransactionById(id);
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
	 * Gets the wallet of the sender.
	 *
	 * @return the wallet of the sender
	 */
	public Wallet getSender() {
		return this.sender;
	}

	/**
	 * Gets the wallet of the receiver.
	 *
	 * @return the wallet of the receiver
	 */
	public Wallet getRecipient() {
		return this.receiver;
	}

	/**
	 * Gets the amount of the transaction.
	 *
	 * @return the amount of the transaction
	 */
	public int getAmount() {
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
}
