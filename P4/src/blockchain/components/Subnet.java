package src.blockchain.components;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import src.blockchain.interfaces.IConnectable;
import src.blockchain.interfaces.IMessage;
import src.blockchain.network.BlockchainNetwork;
import src.blockchain.notifications.TransactionNotification;

/**
 * Represents a subnet in a blockchain network.
 */
public class Subnet extends AbstractConnectable {
	private static Set<Subnet> subnets = new HashSet<>();
	private int id;
	private String name;
	private List<Node> nodes = new ArrayList<>();

	/**
	 * Constructs a subnet with the given nodes.
	 *
	 * @param nodes The nodes to be added to the subnet.
	 */
	public Subnet(Node... nodes) {
		for (Node node : nodes) {
			this.nodes.add(node);
		}
		this.id = BlockchainNetwork.generateId();
		if (this.id < 10) {
			this.name = "Subnet#00" + this.id;
		} else if (this.id < 100) {
			this.name = "Subnet#0" + this.id;
		} else {
			this.name = "Subnet#" + this.id;
		}
		subnets.add(this);
	}

	/**
	 * Returns the set of all subnets in the blockchain network.
	 *
	 * @return The set of subnets.
	 */
	public static Set<Subnet> getSubnetworks() {
		return subnets;
	}

	/**
	 * Returns the list of nodes in the subnet.
	 *
	 * @return The list of nodes.
	 */
	public List<Node> getNodes() {
		return this.nodes;
	}

	/**
	 * Returns the full name of the subnet.
	 *
	 * @return The full name of the subnet.
	 */
	@Override
	public String fullName() {
		return this.name;
	}

	/**
	 * Broadcasts a message to all nodes in the subnet.
	 *
	 * @param message The message to be broadcasted.
	 */
	public void broadcast(IMessage message) {
		if (message instanceof TransactionNotification)
			System.out.println("[" + fullName() + "] " + message.toString());
		else
			System.out.println("[" + fullName() + "] " + message.toString().split(":")[0]);
		System.out.println("Broadcasting to " + this.nodes.size() + " nodes:");
		this.nodes.forEach(node -> node.broadcast(message));
	}

	/**
	 * Returns the parent of the subnet, which is the blockchain network it belongs to.
	 *
	 * @return The parent blockchain network.
	 */
	public IConnectable getParent() {
		Set<BlockchainNetwork> networks = BlockchainNetwork.getNetworks();
		for (BlockchainNetwork network : networks) {
			if (network.getElements() != null && network.getElements().contains(this)) {
				return ((IConnectable) network);
			}
		}
		return null;
	}

	/**
	 * Returns a string representation of the subnet.
	 *
	 * @return The string representation of the subnet.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Node network of " + nodes.size() + " nodes: ");
		for (Node n : nodes) {
			sb.append("[").append(n).append("] ");
		}
		return sb.toString();
	}
}
