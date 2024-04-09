import java.util.ArrayList;
import java.util.List;

public class Node implements Connectable {
	private static int nextId = 0;
	private String name;
	private int id;
	private Wallet wallet;
	private List<Transaction> transactions = new ArrayList<>();

	public Node(Wallet wallet) {
		this.id = nextId++;
		if (this.id < 10) {
			this.name = "Node#00" + this.id;
		} else if (this.id < 100) {
			this.name = "Node#0" + this.id;
		} else {
			this.name = "Node#" + this.id;
		}
		this.wallet = wallet;
	}

	public Wallet getWallet() {
		return this.wallet;
	}

	public String fullName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "u: " + wallet.getName() + ", PK:" + wallet.getKey() + ", balance: " + wallet.getBalance() + " | @" + fullName();
	}
}
