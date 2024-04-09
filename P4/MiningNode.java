public class MiningNode extends Node {
	private long computationalCapacity;
	private String name;
	private static int nextId = 0;
	private int id;

	public MiningNode(Wallet wallet, long computationalCapacity) {
		super(wallet);
		this.computationalCapacity = computationalCapacity;
		this.id = nextId++;
		if (this.id < 10) {
			this.name = "MiningNode#00" + this.id;
		} else if (this.id < 100) {
			this.name = "MiningNode#0" + this.id;
		} else {
			this.name = "MiningNode#" + this.id;
		}
	}

	public String fullName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "u: " + this.getWallet().getName() + ", PK:" + this.getWallet().getKey() + ", balance: " + this.getWallet().getBalance() + " | @" + fullName();
	}
}
