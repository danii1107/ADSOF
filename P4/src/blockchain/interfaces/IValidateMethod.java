package src.blockchain.interfaces;

import src.blockchain.mining.Block;

public interface IValidateMethod {
	public boolean validate(IMiningMethod miningMethod, Block block);
}