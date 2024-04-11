package src.blockchain.notifications;

import src.blockchain.interfaces.IMessage;

public class ValidateBlockRes implements IMessage {
	private long blockId;
	private String sourceId;
	private Boolean result;

	public ValidateBlockRes(long blockId, String sourceId, Boolean result) {
		this.sourceId = sourceId;
		this.blockId = blockId;
		this.result = result;
	}

	@Override
	public String getMessage() {
		return this.toString();
	}

	@Override
	public String toString() {
		return "ValidateBlockRes: <b:" + this.blockId + ", res:" + this.result + ", src:" + this.sourceId + ">";
	}
}
