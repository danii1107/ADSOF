package src.blockchain.notifications;

import src.blockchain.interfaces.IMessage;

public class ValidateBlockRq implements IMessage {
	private long blockId;
	private String sourceId;

	public ValidateBlockRq(long blockId, String sourceId) {
		this.sourceId = sourceId;
		this.blockId = blockId;
	}

	@Override
	public String getMessage() {
		return this.toString();
	}

	@Override
	public String toString() {
		return "ValidateBlockRq: <b:" + this.blockId + ", src:" + this.sourceId + ">";
	}
}
