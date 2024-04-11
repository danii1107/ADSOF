package src.blockchain.interfaces;

import src.blockchain.wallets.Transaction;
import src.blockchain.mining.Block;

public interface IMiningMethod {
	String createHash(Block block);
	Block mineBlock(Transaction transaction, Block previousConfirmedBlock, String minerKey);
}