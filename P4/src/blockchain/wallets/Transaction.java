package src.blockchain.wallets;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
	private static List<Transaction> transactions = new ArrayList<>();
	private int id;
	private static int nextId = 0;
	private Wallet sender;
	private Wallet receiver;
	private int amount;
	private boolean isConfirmed;

	public Transaction(Wallet sender, Wallet receiver, int amount) {
		this.id = nextId++;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.isConfirmed = false;
		transactions.add(this);
	}

	public static Transaction getTransactionById(int id) {
		Transaction tr = transactions.stream().filter(transaction -> transaction.getId() == id).findFirst().orElse(null);
		return tr;
	}

	public static Transaction getTransactionByMessage(String message) {
		String[] parts = message.split(" ");
		int id = Integer.parseInt(parts[1].replace("|", ""));
		return getTransactionById(id);
	}
	
	public int getId() {
		return this.id;
	}

	public Wallet getSender() {
		return this.sender;
	}

	public Wallet getRecipient() {
		return this.receiver;
	}

	public int getAmount() {
		return this.amount;
	}

	public boolean isConfirmed() {
		return this.isConfirmed;
	}

	public void confirm() {
		this.isConfirmed = true;
	}
}
