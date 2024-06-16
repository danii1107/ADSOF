/**
 * Represents a response message for validating a block.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.notifications;

import src.blockchain.interfaces.IMessage;
import src.blockchain.mining.Block;
import src.blockchain.components.MiningNode;
import src.blockchain.components.Node;

public class ValidateBlockRq implements IMessage {
	protected MiningNode miner;
	protected String message;
	protected Block block;

	/**
	 * Constructs a new ValidateBlockRq object with the specified block ID and source ID.
	 *
	 * @param block 	the block to validate
	 * @param miner     the miner who requested the validation
	 */
	public ValidateBlockRq(Block block, MiningNode miner) {
		this.miner = miner;
		this.block = block;
		this.message = this.toString();
	}

	/**
	 * Gets the message associated with this request.
	 *
	 * @return the message as a string
	 */
	@Override
	public String getMessage() {
		return this.message;
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
			if (n.checkMining()) {
				if (n.getWallet().getPublicKey().equals(this.miner.getWallet().getPublicKey())) {
					System.out.println("[" + n.fullName() + "]" +
						" You cannot validate your own block");
					return;
				}
				n.validateBlock(this.block, this.miner);
			}
		}
		else
			System.out.println("ValidateBlockRq");
	}

	/**
	 * Returns the formatted data for a validation request
	 * 
	 * @return String with the correct format
	 */
	@Override
	public String toString() {
		return "ValidateBlockRq: <b:" + this.block.getId() + ", src:" + String.format("%03d", this.miner.getId()) + ">";
	}
}
