package src.blockchain.notifications;

import src.blockchain.interfaces.IMessage;

/**
 * Represents a request to validate a block in the blockchain.
 */
public class ValidateBlockRq implements IMessage {
	private long blockId;
	private String sourceId;

	/**
	 * Constructs a new ValidateBlockRq object with the specified block ID and source ID.
	 *
	 * @param blockId   the ID of the block to validate
	 * @param sourceId  the ID of the source requesting the validation
	 */
	public ValidateBlockRq(long blockId, String sourceId) {
		this.sourceId = sourceId;
		this.blockId = blockId;
	}

	/**
	 * Gets the message associated with this request.
	 *
	 * @return the message as a string
	 */
	@Override
	public String getMessage() {
		return this.toString();
	}

	/**
	 * Returns a string representation of the ValidateBlockRq object.
	 *
	 * @return a string representation of the object
	 */
	@Override
	public String toString() {
		return "ValidateBlockRq: <b:" + this.blockId + ", src:" + this.sourceId + ">";
	}
}
