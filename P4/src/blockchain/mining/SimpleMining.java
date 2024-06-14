/**
 * Represents a simple mining method
 * 
 * @author Daniel Birsan daniel.brisan@estudiante.uam.es
 */
package src.blockchain.mining;

import src.blockchain.interfaces.IMiningMethod;
import src.blockchain.wallets.Transaction;
import src.blockchain.utils.CommonUtils;

public class SimpleMining implements IMiningMethod {
	/**
	 * Creates a hash for the given block using the SHA-256 algorithm.
	 *
	 * @param block the block for which to create the hash
	 * @return the hash value as a string
	 */
	@Override
	public String createHash(Block block) {
		String hash = CommonUtils.sha256(block.toStringHash());
		return hash;
	}

	/**
	 * Mines a new block with the given transaction, previous confirmed block,
	 * and miner key.
	 *
	 * @param transaction           the transaction to include in the new block
	 * @param previousConfirmedBlock the previous confirmed block in the blockchain
	 * @param minerKey              the key of the miner mining the block
	 * @return the newly mined block
	 */
	@Override
	public Block mineBlock(Transaction transaction, Block previousConfirmedBlock, String minerKey) {
		Block block = new Block(transaction, previousConfirmedBlock, minerKey);
		block.setBlockHash(createHash(block));
		return block;
	}
}
