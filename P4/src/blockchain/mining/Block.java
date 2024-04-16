package src.blockchain.mining;

import src.blockchain.wallets.Transaction;
import src.blockchain.utils.BlockConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Represents a block in a blockchain.
 */
public class Block {
	public static List<Block> blocks = new ArrayList<>();
	private static long counter = 0;
	private final long id;
	private final int version;
	private final int nonce;
	private final long timestamp;
	private final int difficulty;
	private final Transaction transaction;
	private boolean validated;
	private String hash;
	private Block previousBlock;
	private String minerKey;

	/**
	 * Constructs a new Block object.
	 *
	 * @param transaction   the transaction associated with the block
	 * @param previousBlock the previous block in the blockchain
	 * @param minerKey      the key of the miner who mined the block
	 */
	public Block(Transaction transaction, Block previousBlock, String minerKey) {
		this.id = counter++;
		this.version = BlockConfig.VERSION;
		this.nonce = (int) (Math.random() * 1001);
		this.timestamp = (new Date().getTime()) / 1000;
		this.difficulty = BlockConfig.DIFFICULTY;
		this.transaction = transaction;
		this.validated = false;
		this.hash = "";
		this.previousBlock = previousBlock;
		this.minerKey = minerKey;
		blocks.add(this);
	}

	/**
	 * Retrieves a block by its ID.
	 *
	 * @param id the ID of the block to retrieve
	 * @return the block with the specified ID, or null if not found
	 */
	public static Block getBlockById(long id) {
		return blocks.stream().filter(block -> block.getId() == id).findFirst().orElse(null);
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
	 * Returns a string representation of the block's hash components.
	 *
	 * @return a string representation of the block's hash components
	 */
	public String toStringHash() {
		return this.version + (this.previousBlock != null ? this.previousBlock.getHash() : BlockConfig.GENESIS_BLOCK) + this.timestamp + this.difficulty + this.nonce;
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
	 * Returns a string representation of the block.
	 *
	 * @return a string representation of the block
	 */
	@Override
	public String toString() {
		return "id:" + id +
				", v:" + version +
				", nonce:" + nonce +
				", ts:" + timestamp +
				", diff:" + difficulty +
				", hash:" + hash +
				", minerK:" + minerKey;
	}
}
