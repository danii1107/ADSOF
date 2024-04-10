package src.blockchain.wallets;
public class Transaction {
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
}
