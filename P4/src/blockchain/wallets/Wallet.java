/**
 * Represents a wallet in a blockchain system.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.wallets;

import src.blockchain.utils.CommonUtils;

public class Wallet {
	private String owner;
	private String publicKey;
	private String privateKey;
	private Integer balance;

	/**
	 * Constructs a new Wallet object with the specified owner, key, and balance.
	 *
	 * @param owner   the owner of the wallet
	 * @param key     the public key associated to the wallet
	 * @param balance the balance of the wallet
	 */
	public Wallet(String owner, String key, Integer balance) {
		this.owner = owner;
		this.publicKey = key;
		this.balance = balance;
		this.privateKey = CommonUtils.sha1(key);
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
	 * Returns the public key associated to the wallet.
	 *
	 * @return the public key of the wallet
	 */
	public String getPublicKey() {
		return this.publicKey;
	}

	/**
	 * Returns the balance of the wallet.
	 *
	 * @return the balance of the wallet
	 */
	public Integer getBalance() {
		return this.balance;
	}

	/**
	 * Sets the balance of the wallet to the specified value.
	 *
	 * @param balance the new balance of the wallet
	 */
	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	/**
	 * Returns the private key associated to the wallet.
	 *
	 * @return the private key of the wallet
	 */
	public String getPrivateKey() {
		return this.privateKey;
	}

	/**
	 * Returns a string representation of a waller
	 * 
	 * @return the formatted string
	 */
	@Override
	public String toString() {
		return "u: " + this.owner + ", PK: " + this.publicKey + ", balance: " + this.balance;
	}
}
