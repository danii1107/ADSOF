package blockchain.wallets;

public class Wallet {
	private String owner;
	private String key;
	private int balance;

	public Wallet(String owner, String key, int balance) {
		this.owner = owner;
		this.key = key;
		this.balance = balance;
	}

	public String getName() {
		return owner;
	}

	public String getKey() {
		return key;
	}

	public int getBalance() {
		return balance;
	}

	
}
