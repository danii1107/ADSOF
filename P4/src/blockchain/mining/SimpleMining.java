package src.blockchain.mining;

import src.blockchain.interfaces.IMiningMethod;
import src.blockchain.wallets.Transaction;
import src.blockchain.utils.CommonUtils;

public class SimpleMining implements IMiningMethod {
	@Override
	public String createHash(Block block) {
		String hash = CommonUtils.sha256(block.toStringHash());
		return hash;
	}

	@Override
	public Block mineBlock(Transaction transaction, Block previousConfirmedBlock, String minerKey) {
		Block block = new Block(transaction, previousConfirmedBlock, minerKey);
		block.setBlockHash(createHash(block));
		return block;
	}
	
}
