package src.blockchain.wallets;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a wallet in a blockchain system.
 */
public class Wallet {
	private static List<Wallet> wallets = new ArrayList<>();
	private String owner;
	private String key;
	private int balance;

	/**
	 * Constructs a new Wallet object with the specified owner, key, and balance.
	 *
	 * @param owner   the owner of the wallet
	 * @param key     the public key associated with the wallet
	 * @param balance the balance of the wallet
	 */
	public Wallet(String owner, String key, int balance) {
		this.owner = owner;
		this.key = key;
		this.balance = balance;
		wallets.add(this);
	}

	/**
	 * Retrieves a wallet object based on the provided public key.
	 *
	 * @param key the public key of the wallet to retrieve
	 * @return the wallet object with the specified public key, or null if not found
	 */
	public static Wallet getWalletByKey(String key) {
		Wallet wallet = wallets.stream().filter(w -> w.getPublicKey().equals(key)).findFirst().orElse(null);
		if (wallet != null) {
			return wallet;
		}
		return null;
	}

	/**
	 * Returns the name of the wallet owner.
	 *
	 * @return the name of the wallet owner
	 */
	public String getName() {
		return owner;
	}

	/**
	 * Returns the public key associated with the wallet.
	 *
	 * @return the public key of the wallet
	 */
	public String getPublicKey() {
		return key;
	}

	/**
	 * Returns the balance of the wallet.
	 *
	 * @return the balance of the wallet
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Sets the balance of the wallet to the specified value.
	 *
	 * @param balance the new balance of the wallet
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
