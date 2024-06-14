/**
 * Represents a node in the blockchain network.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.blockchain.components;

import src.blockchain.exceptions.transaction.TransactionException;
import src.blockchain.mining.Block;
import src.blockchain.interfaces.*;
import src.blockchain.wallets.*;

import java.util.*;

public class Node extends AbstractConnectable {
	private List<Transaction> transactions;
	private Wallet wallet;

	/**
	 * Constructs a new Node object with the specified wallet.
	 *
	 * @param wallet the wallet associated with the node
	 */
	public Node(Wallet wallet) {
		super();
		this.wallet = wallet;
		this.transactions = new ArrayList<>();
		this.setName("Node");
	}

	/**
	 * Allows mining nodes to call getId method
	 * 
	 * @return the blockchain's component id
	 */
	public int getId() {
		return super.getId();
	}

	/**
	 * Gets the wallet associated to the node.
	 *
	 * @return the wallet associated to the node
	 */
	public Wallet getWallet() {
		return this.wallet;
	}

	/**
	 * Gets the transactions confirmed by the node
	 * 
	 * @return a list with the transactions
	 */
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	/**
	 * Checks if the node can mine blocks or not
	 * 
	 * @return False, default implementation
	 */
	public Boolean checkMining() {
		return false;
	}

	/**
	 * Starts mining a block if it is a mining block, default implementation
	 * 
	 * @param tr the transaction that activated the minign
	 */
	public void mineBlock(Transaction tr) {
		return;
	}

	/**
	 * Starts validating a block if it is a mining block, default implementation
	 * 
	 * @param block the block to be validated
	 * @param miner the miner who mined the block
	 */
	public void validateBlock(Block block, MiningNode miner) {
		return;
	}

	/**
	 * Creates a new transaction from the node's wallet to the receiver with the given public key and amount.
	 *
	 * @param wallet_pk the public key of the receiver's wallet
	 * @param amount    the amount to be transferred
	 * @return the created transaction
	 * @throws TransactionException if the amount is negative or there's not enough funds
	 */
	public Transaction createTransaction(String wallet_pk, Integer amount) throws TransactionException {
		String e = "";
		if (amount < 0)
			e = "Negative transfer attempt: ";
		if (amount > this.wallet.getBalance())
			e = "Insufficient funds: ";
		if (!e.equals(""))
			throw new TransactionException(e, this.wallet.getPublicKey(), wallet_pk, amount);
		return new Transaction(this.wallet, wallet_pk, amount);
	}

	/**
	 * Creates a new transaction from the node's wallet to the specified receiver with the given amount.
	 *
	 * @param receiver the wallet of the receiver
	 * @param amount   the amount to be transferred
	 * @return the created transaction
	 * @throws TransactionException if the amount is negative or there's not enough funds
	 */
	public Transaction createTransaction(Wallet receiver, Integer amount) throws TransactionException {
		return this.createTransaction(receiver.getPublicKey(), amount);
	}

	/**
	 * Commits a transaction after the associated block was validated
	 * 
	 * @param tr 		The transaction to commit
	 * @param miner		The miner who mined the block
	 */
	public void commitTransaction(Transaction tr, Node miner) {
		this.transactions.add(tr);
		System.out.println("[" + this.fullName() + "] Commiting transaction : " + tr.getName() + " in " + this.fullName());
		System.out.println("[" + this.fullName() + "]  -> Tx details:" + tr.toString());
		if (this.wallet.getPublicKey().equals(tr.getReceiver())) {
			tr.applyTransaction(this.wallet);
			System.out.println("[" + this.fullName() + "] Applied Transaction: " + tr.toString());
			System.out.println("[" + this.fullName() + "] New wallet value: " + this.wallet.toString());
		}
	}

	/**
	 * Broadcasts the given message to the network.
	 *
	 * @param message the message to be broadcasted
	 */
	@Override
	public void broadcast(IMessage message) {
		message.process(this);	
	}

	/**
	 * Returns a string representation of the node.
	 *
	 * @return a string representation of the node
	 */
	@Override
	public String toString() {
		return this.wallet.toString() + super.toString();
	}
}
