package src.blockchain.network;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import src.blockchain.interfaces.IConnectable;
import src.blockchain.interfaces.IMessage;
import src.blockchain.components.Node;
import src.blockchain.components.Subnet;
import src.blockchain.exceptions.connection.*;

/**
 * Represents a blockchain network that implements the {@link IConnectable} interface.
 * It allows connecting various components and broadcasting messages to all connected elements.
 */
public class BlockchainNetwork implements IConnectable {
	private static int nextId = 0;
	private static Set<BlockchainNetwork> networks = new HashSet<>();
	private String name;
	private List<IConnectable> elements = new ArrayList<>();

	/**
	 * Constructs a new BlockchainNetwork with the given name.
	 *
	 * @param name the name of the blockchain network
	 */
	public BlockchainNetwork(String name) {
		this.name = name;
		networks.add(this);
	}

	/**
	 * Returns the set of all BlockchainNetwork instances.
	 *
	 * @return the set of all BlockchainNetwork instances
	 */
	public static Set<BlockchainNetwork> getNetworks() {
		return networks;
	}

	/**
	 * Returns the list of elements connected to this blockchain network.
	 *
	 * @return the list of connected elements
	 */
	public List<IConnectable> getElements() {
		return elements;
	}

	/**
	 * Generates a unique identifier for a blockchain network.
	 *
	 * @return the generated unique identifier
	 */
	public static int generateId() {
		return nextId++;
	}

	/**
	 * Decrements the nextId counter.
	 */
	public static void subId() {
		nextId--;
	}

	/**
	 * Connects a component to this blockchain network.
	 *
	 * @param component the component to connect
	 * @return the updated blockchain network
	 * @throws ConnectionException if the component is already connected
	 * @throws DuplicateConnectionException if the component is already connected to another network
	 */
	public BlockchainNetwork connect(IConnectable component) throws ConnectionException, DuplicateConnectionException {
		if (elements.contains(component)) {
			throw new ConnectionException(component);
		}
		if (component instanceof Node) {
			Set<Subnet> sn = Subnet.getSubnetworks();
			sn.forEach(s -> {
				if (s.getNodes().contains(component)) {
					subId();
					throw new DuplicateConnectionException(component);
				}
			});
		}
		networks.forEach(n -> {
			if (n.elements.contains(component)) {
				subId();
				throw new DuplicateConnectionException(component);
			}
		});
		elements.add(component);
		System.out.println(this.name + " - new peer connected: " + component);
		return this;
	}

	/**
	 * Broadcasts a message to all connected elements in the blockchain network.
	 *
	 * @param message the message to broadcast
	 */
	public void broadcast(IMessage message) {
		elements.forEach(e -> e.broadcast(message));
	}

	/**
	 * Returns the parent of this blockchain network.
	 *
	 * @return the parent of this blockchain network
	 */
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
		StringBuilder sb = new StringBuilder(name + " consists of " + elements.size() + " elements:\n");
		elements.forEach(e -> sb.append("* ").append(e).append("\n"));
		return sb.toString();
	}
}
