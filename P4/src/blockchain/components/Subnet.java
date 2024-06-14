/**
 * Represents a subnet in a blockchain network.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.blockchain.components;

import src.blockchain.exceptions.connection.*;
import src.blockchain.interfaces.IMessage;

import java.util.*;

public class Subnet extends AbstractConnectable {
	private List<Node> nodes = new ArrayList<>();

	/**
	 * Constructs a subnet with the given nodes.
	 *
	 * @param nodes The nodes to be added to the subnet.
	 * @throws DuplicateConnectionException if a node is already connected to a network,
	 *  it cannot be connected to this subnet because is now being created.
	 */
	public Subnet(Node... nodes) throws DuplicateConnectionException {
		super();
		Arrays.asList(nodes).forEach(n -> {
			if (n.getParent() != null) {
				throw new DuplicateConnectionException(n.getId());
			}
			else { 
				this.nodes.add(n);
				n.setParent(this);
			}
		});
		this.setName("Subnet");
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
	 * Broadcasts a message to all nodes in the subnet.
	 *
	 * @param message The message to be broadcasted.
	 */
	@Override
	public void broadcast(IMessage message) {
		System.out.print("[" + this.fullName() + "] ");
		message.process(null);
		System.out.println("Broadcasting to " + this.nodes.size() + " nodes:");
		this.nodes.forEach(e -> e.broadcast(message));
	}

	/**
	 * Returns a string representation of the subnet.
	 *
	 * @return The string representation of the subnet.
	 */
	@Override
	public String toString() {
		return "Node network of " + nodes.size() + " nodes: " + this.nodes.toString();
	}
}
