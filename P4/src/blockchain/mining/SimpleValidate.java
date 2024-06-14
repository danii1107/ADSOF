/**
 * The SimpleValidate class implements the IValidateMethod interface and provides a 
 * simple validation method for blocks in a blockchain.
 * 
 * @uthor Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.blockchain.mining;

import src.blockchain.interfaces.IValidateMethod;
import src.blockchain.interfaces.IMiningMethod;

public class SimpleValidate implements IValidateMethod {
    /**
     * Validates a block using a given mining method.
     *
     * @param miningMethod the mining method used to create the block's hash
     * @param block        the block to be validated
     * @return             true if the block is valid, false otherwise
     */
    @Override
    public boolean validate(IMiningMethod miningMethod, Block block) {
        String recalculatedHash = miningMethod.createHash(block);
        return recalculatedHash.equals(block.getHash());
    }
}
