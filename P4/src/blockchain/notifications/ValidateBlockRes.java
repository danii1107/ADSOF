package src.blockchain.notifications;

import src.blockchain.interfaces.IMessage;

/**
 * Represents a response message for validating a block.
 * Implements the IMessage interface.
 */
public class ValidateBlockRes implements IMessage {
	private long blockId;
	private String sourceId;
	private Boolean result;

	/**
	 * Constructs a ValidateBlockRes object with the specified block ID, source ID, and result.
	 *
	 * @param blockId   the ID of the block being validated
	 * @param sourceId  the ID of the source of the validation request
	 * @param result    the result of the block validation (true if valid, false otherwise)
	 */
	public ValidateBlockRes(long blockId, String sourceId, Boolean result) {
		this.sourceId = sourceId;
		this.blockId = blockId;
		this.result = result;
	}

	/**
	 * Gets the message content of the ValidateBlockRes object.
	 *
	 * @return the message content as a string
	 */
	@Override
	public String getMessage() {
		return this.toString();
	}

	/**
	 * Converts the ValidateBlockRes object to a string representation.
	 *
	 * @return a string representation of the ValidateBlockRes object
	 */
	@Override
	public String toString() {
		return "ValidateBlockRes: <b:" + this.blockId + ", res:" + this.result + ", src:" + this.sourceId + ">";
	}
}
