/**
 * The IMiningMethod interface represents a method to mine blocks in a blockchain.
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.interfaces;

import src.blockchain.wallets.Transaction;
import src.blockchain.mining.Block;

public interface IMiningMethod {
	
	/**
	 * Creates a hash for the given block.
	 *
	 * @param block the block for which the hash needs to be created
	 * @return the hash value as a string
	 */
	String createHash(Block block);
	
	/**
	 * Mines a new block using the given transaction, previous confirmed block, and miner key.
	 *
	 * @param transaction the transaction to be included in the new block
	 * @param previousConfirmedBlock the previous confirmed block in the blockchain
	 * @param minerKey the key of the miner mining the block
	 * @return the newly mined block
	 */
	Block mineBlock(Transaction transaction, Block previousConfirmedBlock, String minerKey);
}