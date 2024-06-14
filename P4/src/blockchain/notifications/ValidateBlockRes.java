/**
 * Represents a response message for validating a block.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.blockchain.notifications;

import src.blockchain.components.MiningNode;
import src.blockchain.components.Node;
import src.blockchain.mining.Block;

public class ValidateBlockRes extends ValidateBlockRq {
	private Boolean result;

	/**
	 * Constructs a ValidateBlockRes object with the specified block ID, source ID, and result.
	 *
	 * @param block     the block being validated
	 * @param miner     the miner that requested the validation
	 * @param result    the result of the block validation (true if valid, false otherwise)
	 */
	public ValidateBlockRes(Block block, MiningNode miner, Boolean result) {
		super(block, miner);
		this.result = result;
		this.message = this.toString();
	}

	/**
	 * Processes the notification. If the node is null, the notification comes from a network
	 * 	and prints the message
	 * 
	 * @param n the node that processes the notification
	 */
	@Override
	public void process(Node n) {
		if (n != null) {
			System.out.println("[" + n.fullName() + "]" +
					" Received task: " + this.getMessage());
			if (this.getMessage().contains("true")) {
				n.commitTransaction(this.block.geTransaction(), this.miner);
				this.block.setValidated(true);
			}
		}
		else
			System.out.println("ValidateBlockRes");
	}

	/**
	 * Returns the formatted data for a validation response
	 * 
	 * @return String with the correct format
	 */
	@Override
	public String toString() {
		return "ValidateBlockRes: <b:" + this.block.getId() + ", res: " + this.result + ", src:" + String.format("%03d", this.miner.getId()) + ">";
	}
}
