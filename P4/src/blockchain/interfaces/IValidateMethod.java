/**
 * The IValidateMethod interface represents a method for validating a block in a blockchain.
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es 
 */
package src.blockchain.interfaces;

import src.blockchain.mining.Block;

public interface IValidateMethod {
	/**
	 * Validates a block using the specified mining method.
	 *
	 * @param miningMethod the mining method used to mine the block
	 * @param block the block to be validated
	 * @return true if the block is valid, false otherwise
	 */
	public boolean validate(IMiningMethod miningMethod, Block block);
}