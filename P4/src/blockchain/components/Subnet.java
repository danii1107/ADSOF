package src.blockchain.components;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import src.blockchain.interfaces.IConnectable;
import src.blockchain.interfaces.IMessage;
import src.blockchain.network.BlockchainNetwork;

public class Subnet extends AbstractConnectable {
	private static Set<Subnet> subnets = new HashSet<>();
	private int id;
	private String name;
	private List<Node> nodes = new ArrayList<>();

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

	public static Set<Subnet> getSubnetworks() {
		return subnets;
	}

	public List<Node> getNodes() {
		return this.nodes;
	}

	@Override
	public String fullName() {
		return this.name;
	}

	public void broadcast(IMessage message) {
		System.out.println("[" + fullName() + "] " + message.toString());
		System.out.println("Broadcasting to " + this.nodes.size() + " nodes:");
		this.nodes.forEach(node -> node.broadcast(message));
	}

	public IConnectable getParent() {
		Set<BlockchainNetwork> networks = BlockchainNetwork.getNetworks();
		for (BlockchainNetwork network : networks) {
			if (network.getElements() != null && network.getElements().contains(this)) {
				return ((IConnectable) network);
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Node network of " + nodes.size() + " nodes: ");
		for (Node n : nodes) {
			sb.append("[").append(n).append("] ");
		}
		return sb.toString();
	}
}
