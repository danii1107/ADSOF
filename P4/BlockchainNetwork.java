import java.util.List;
import java.util.ArrayList;

public class BlockchainNetwork {
	private String name;
	private List<Connectable> elements = new ArrayList<>();

	public BlockchainNetwork(String name) {
		this.name = name;
	}

	public BlockchainNetwork connect(Connectable component) {
		elements.add(component);
		System.out.println(this.name + " - new peer connected: " + component);
		return this;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + " consists of " + elements.size() + " elements:\n");
        for (Connectable e : elements) {
            sb.append("* ").append(e).append("\n");
        }
        return sb.toString();
    }
}
