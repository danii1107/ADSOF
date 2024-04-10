package src.blockchain.exceptions.connection;

import src.blockchain.components.AbstractConnectable;
import src.blockchain.interfaces.IConnectable;

public class ConnectionException extends RuntimeException {
	private IConnectable e;
	
	public ConnectionException(IConnectable e) {
		this.e = e;
		toString();
	}

	@Override
	public String toString() {
		String name = ((AbstractConnectable) e).fullName().replace("#", " ").replace("MiningNode", "Node");
		return "Connection exception: " + name + " is connected to a different network";
	}
}
