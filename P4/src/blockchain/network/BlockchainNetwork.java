/**
 * Class that represents a blockchain network.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.network;

import src.blockchain.components.AbstractConnectable;
import src.blockchain.exceptions.connection.*;
import src.blockchain.interfaces.*;

import java.util.*;

public class BlockchainNetwork implements IConnectable {
	private List<AbstractConnectable> elements = new ArrayList<>();
	private String name;

	/**
	 * Constructs a new BlockchainNetwork with the given name.
	 *
	 * @param name the name of the blockchain network
	 */
	public BlockchainNetwork(String name) {
		this.name = name;
	}

	/**
	 * Connects a component to this blockchain network.
	 *
	 * @param component the component to connect
	 * @return the updated blockchain network
	 * @throws ConnectionException if the component can't be connected
	 */
	public BlockchainNetwork connect(AbstractConnectable component) throws ConnectionException {
		if (component.getParent() != null) {
			if (component.getParent().equals(this)) {
				throw new ConnectionException(component.getId());
			}
			throw new DuplicateConnectionException(component.getId());
		}
		elements.add(component);
		component.setParent(this);
		System.out.println(this.name + " - new peer connected: " + component);
		return this;
	}

	/**
	 * Broadcasts a message to all connected elements in the blockchain network.
	 *
	 * @param message the message to broadcast
	 */
	@Override
	public void broadcast(IMessage message) {
		elements.forEach(e -> e.broadcast(message));
	}

	/**
	 * Returns the parent of this blockchain network.
	 *
	 * @return the parent of this blockchain network
	 */
	@Override
	public IConnectable getParent() {
		return null;
	}

	/**
	 * Returns a string representation of the blockchain network.
	 *
	 * @return a string representation of the blockchain network
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.name + " consists of " + this.elements.size() + " elements:\n");
		elements.forEach(e -> sb.append("* ").append(e.toString()).append("\n"));
		return sb.toString();
	}
}
