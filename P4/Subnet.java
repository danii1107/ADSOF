import java.util.ArrayList;
import java.util.List;

public class Subnet implements Connectable {
	private String id;
	private List<Node> nodes = new ArrayList<>();

	public Subnet(Node... nodes) {
		for (Node node : nodes) {
			this.nodes.add(node);
		}
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
