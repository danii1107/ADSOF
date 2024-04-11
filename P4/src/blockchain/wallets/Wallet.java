package src.blockchain.wallets;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
	private static List<Wallet> wallets = new ArrayList<>();
	private String owner;
	private String key;
	private int balance;

	public Wallet(String owner, String key, int balance) {
		this.owner = owner;
		this.key = key;
		this.balance = balance;
		wallets.add(this);
	}

	public static Wallet getWalletByKey(String key) {
		Wallet wallet = wallets.stream().filter(w -> w.getPublicKey().equals(key)).findFirst().orElse(null);
		if (wallet != null) {
			return wallet;
		}
		return null;
	}

	public String getName() {
		return owner;
	}

	public String getPublicKey() {
		return key;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
