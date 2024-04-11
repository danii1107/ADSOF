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

public class BlockchainNetwork implements IConnectable {
	private static int nextId = 0;
	private static Set<BlockchainNetwork> networks = new HashSet<>();
	private String name;
	private List<IConnectable> elements = new ArrayList<>();

	public BlockchainNetwork(String name) {
		this.name = name;
		networks.add(this);
	}

	public static Set<BlockchainNetwork> getNetworks() {
		return networks;
	} 

	public List<IConnectable> getElements() {
		return elements;
	}

	public static int generateId() {
		return nextId++;
	}

	public static void subId() {
		nextId--;
	}

	public BlockchainNetwork connect(IConnectable component) {
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

	public void broadcast(IMessage message) {
		elements.forEach(e -> e.broadcast(message));
	}

	public IConnectable getParent() {
		return null;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + " consists of " + elements.size() + " elements:\n");
		elements.forEach(e -> sb.append("* ").append(e).append("\n"));
        return sb.toString();
    }
}
