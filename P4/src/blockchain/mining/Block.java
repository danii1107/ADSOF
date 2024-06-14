/**
 * Represents a block in a blockchain.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.mining;

import src.blockchain.wallets.Transaction;
import src.blockchain.utils.BlockConfig;

import java.util.*;

public class Block {
	private static long nextId = 0;
	private final long id;
	private final Transaction transaction;
	private final int difficulty;
	private final int timestamp;
	private final int version;
	private final int nonce;
	private Block previousBlock;
	private boolean validated;
	private String minerKey;
	private String hash;

	/**
	 * Constructs a new Block object.
	 *
	 * @param transaction   the transaction associated with the block
	 * @param previousBlock the previous block in the blockchain
	 * @param minerKey      the key of the miner who mined the block
	 */
	public Block(Transaction transaction, Block previousBlock, String minerKey) {
		this.id = nextId++;
		this.version = BlockConfig.VERSION;
		this.nonce = (int) (Math.random() * 1001);
		this.timestamp = (int) (new Date().getTime()/1000);
		this.difficulty = BlockConfig.DIFFICULTY;
		this.transaction = transaction;
		this.validated = false;
		this.hash = "";
		this.previousBlock = previousBlock;
		this.minerKey = minerKey;
	}

	/**
	 * Gets the ID of the block.
	 *
	 * @return the ID of the block
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets the hash of the block.
	 *
	 * @param blockHash the hash to set
	 */
	public void setBlockHash(String blockHash) {
		this.hash = blockHash;
	}

	/**
	 * Gets the hash of the block.
	 *
	 * @return the hash of the block
	 */
	public String getHash() {
		return this.hash;
	}

	/**
	 * Sets the validation status of the block.
	 *
	 * @param validated the validation status to set
	 */
	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	/**
	 * Gets if the block is validated
	 * 
	 * @return true if it is, false otherwise
	 */
	public Boolean getValidated() {
		return this.validated;
	}

	/**
	 * Gets the transaction associated to the block
	 * 
	 * @return the transaction
	 */
	public Transaction geTransaction() {
		return this.transaction;
	}

	/**
	 * Returns a string representation of the block's hash components.
	 *
	 * @return a string representation of the block's hash components
	 */
	public String toStringHash() {
		return this.version + (this.previousBlock != null ? this.previousBlock.getHash() : BlockConfig.GENESIS_BLOCK) + this.timestamp + this.difficulty + this.nonce;
	}

	/**
	 * Returns a string representation of the block.
	 *
	 * @return a string representation of the block
	 */
	@Override
	public String toString() {
		return "id:" + this.id + ", v:" + this.version + ", nonce:" + this.nonce + ", ts:" + this.timestamp + ", diff:" + this.difficulty + ", hash:" + this.hash + ", minerK: " + this.minerKey;
	}
}
