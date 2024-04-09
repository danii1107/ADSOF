import java.util.HashSet;
import java.util.Set;

public class Wallet {
	private String owner;
	private String key;
	private int balance;

	public Wallet(String owner, String key, int balance) {
		this.owner = owner;
		this.key = key;
		this.balance = balance;
	}

	public String getName() {
		return owner;
	}

	public String getKey() {
		return key;
	}

	public int getBalance() {
		return balance;
	}

	
}
