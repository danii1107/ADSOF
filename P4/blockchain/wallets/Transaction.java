package blockchain.wallets;
public class Transaction {
	private String id;
	private Wallet sender;
	private Wallet receiver;
	private int amount;
	private boolean isConfirmed;

	public Transaction(Wallet sender, Wallet receiver, int amount) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}
}
