package src.blockchain.exceptions.connection;

import src.blockchain.interfaces.IConnectable;
import src.blockchain.components.AbstractConnectable;

public class DuplicateConnectionException extends RuntimeException {
	private IConnectable e;

	public DuplicateConnectionException(IConnectable e) {
		this.e = e;
		toString();
	}

	@Override
	public String toString() {
		String name = ((AbstractConnectable) e).fullName().replace("#", " ").replace("MiningNode", "Node");
		return "Connection exception: " + name + " is connected to a different network";
	}
}
