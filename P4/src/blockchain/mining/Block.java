package src.blockchain.mining;

import src.blockchain.wallets.Transaction;
import src.blockchain.utils.BlockConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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

	public static Block getBlockById(long id) {
		return blocks.stream().filter(block -> block.getId() == id).findFirst().orElse(null);
	}

	public long getId() {
		return this.id;
	}

	public void setBlockHash(String blockHash) {
		this.hash = blockHash;
	}

	public String getHash() {
		return this.hash;
	}

	public String toStringHash() {
		return this.version + (this.previousBlock != null ? this.previousBlock.getHash() : BlockConfig.GENESIS_BLOCK) + this.timestamp + this.difficulty + this.nonce;
	}

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
