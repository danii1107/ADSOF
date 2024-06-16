/**
 * Represents a mining node in the blockchain network.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.components;

import src.blockchain.notifications.ValidateBlockRes;
import src.blockchain.notifications.ValidateBlockRq;
import src.blockchain.interfaces.IValidateMethod;
import src.blockchain.interfaces.IMiningMethod;
import src.blockchain.wallets.Transaction;
import src.blockchain.wallets.Wallet;
import src.blockchain.mining.*;

import java.util.*;

public class MiningNode extends Node {
	private long computationalCapacity;
	private IMiningMethod miningMethod = null;
	private IValidateMethod validationMethod = null;
	private List<Block> blocks = new ArrayList<>();

	/**
	 * Constructs a new MiningNode object with the specified wallet and computational capacity.
	 *
	 * @param wallet                the wallet associated with the mining node
	 * @param computationalCapacity the computational capacity of the mining node
	 */
	public MiningNode(Wallet wallet, long computationalCapacity) {
		super(wallet);
		this.computationalCapacity = computationalCapacity;
		this.setName("MiningNode");
	}

	public long getComputationalCapacity() {
		return this.computationalCapacity;
	}

	/**
	 * Sets the mining method for the mining node.
	 *
	 * @param miningMethod the mining method to be set
	 */
	public void setMiningMethod(IMiningMethod miningMethod) {
		this.miningMethod = miningMethod;
	}

	/**
	 * Sets the validation method for the mining node.
	 *
	 * @param validationMethod the validation method to be set
	 */
	public void setValidationMethod(IValidateMethod validationMethod) {
		this.validationMethod = validationMethod;
	}

	/**
	 * Checks if the node can mine blocks or not
	 * 
	 * @return True, overrided implementation
	 */
	@Override
	public Boolean checkMining() {
		return true;
	}

	/**
	 * Starts mining a block
	 * 
	 * @param tr the transaction that activated the minign
	 */
	@Override
	public void mineBlock(Transaction tr) {
		if (this.miningMethod != null) {
			Block lastBlock = this.blocks.isEmpty() ? null : this.blocks.get(this.blocks.size()-1);
			Block mined = this.miningMethod.mineBlock(tr, lastBlock, this.getWallet().getPublicKey());
			System.out.println("[" + this.fullName() + "] Mined block: " + mined.toString());
			this.getTopParent().broadcast(new ValidateBlockRq(mined, this));
		}
	}

	/**
	 * Starts validating a block
	 * 
	 * @param block the block to be validated
	 * @param miner the miner who mined the block
	 */
	@Override
	public void validateBlock(Block block, MiningNode miner) {
		if (this.validationMethod != null) {
			Boolean res = this.validationMethod.validate(this.miningMethod, block);
			ValidateBlockRes response = new ValidateBlockRes(block, this, res);
			System.out.println("[" + this.fullName() + "] Emitted task: " + response.toString());
			this.getTopParent().broadcast(response);
		}
	}

	/**
	 * Returns a string representation of the mining node.
	 *
	 * @return a string representation of the mining node
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
