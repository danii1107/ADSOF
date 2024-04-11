package src.blockchain.mining;

import src.blockchain.interfaces.IValidateMethod;
import src.blockchain.interfaces.IMiningMethod;

public class SimpleValidate implements IValidateMethod {
	@Override
    public boolean validate(IMiningMethod miningMethod, Block block) {
        String recalculatedHash = miningMethod.createHash(block);
        return recalculatedHash.equals(block.getHash());
    }
}
