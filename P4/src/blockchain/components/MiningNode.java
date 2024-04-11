package src.blockchain.components;

import src.blockchain.wallets.Transaction;
import src.blockchain.wallets.Wallet;
import src.blockchain.network.BlockchainNetwork;
import src.blockchain.notifications.TransactionNotification;
import src.blockchain.notifications.ValidateBlockRq;
import src.blockchain.interfaces.IMessage;
import src.blockchain.mining.SimpleMining;
import src.blockchain.mining.SimpleValidate;
import src.blockchain.mining.Block;
import src.blockchain.notifications.ValidateBlockRes;

import java.util.ArrayList;
import java.util.List;

public class MiningNode extends Node {
	private List<Transaction> transactions = new ArrayList<>();
	private long computationalCapacity;
	private String name;
	private int id;
	private SimpleMining miningMethod;
	private SimpleValidate validationMethod;
	private static Block lastConfirmedBlock;

	public MiningNode(Wallet wallet, long computationalCapacity) {
		super(wallet);
		this.computationalCapacity = computationalCapacity;
		BlockchainNetwork.subId();
		this.id = BlockchainNetwork.generateId();
		if (this.id < 10) {
			this.name = "MiningNode#00" + this.id;
		} else if (this.id < 100) {
			this.name = "MiningNode#0" + this.id;
		} else {
			this.name = "MiningNode#" + this.id;
		}
	}

	public void setMiningMethod(SimpleMining miningMethod) {
		this.miningMethod = miningMethod;
	}

	public void setValidationMethod(SimpleValidate validationMethod) {
		this.validationMethod = validationMethod;
	}

	@Override
	public String fullName() {
		return this.name;
	}

	@Override
	public void broadcast(IMessage message) {
		super.broadcast(message);
		if (message instanceof TransactionNotification) {
			Transaction tr = Transaction.getTransactionByMessage(message.getMessage());
			if (tr.isConfirmed()) {
				this.transactions.add(tr);
				return;
			}
			Block block = this.miningMethod.mineBlock(tr, lastConfirmedBlock, this.getWallet().getPublicKey());
			System.out.println("[" + this.fullName() + "] Mined block: " + block);
			this.getTopParent().broadcast(new ValidateBlockRq(block.getId(), this.fullName().split("#")[1]));
		}
		if (message instanceof ValidateBlockRq) {
			if (message.getMessage().contains(this.fullName().split("#")[1])) {
				System.out.println("[" + this.fullName() + "] You cannot validate your own block");
				return;
			}
			Block block = Block.getBlockById(Long.parseLong(message.getMessage()
																		.substring(17)
																		.split(",")[0]
																		.split(":")[1]));
			Boolean valid = this.validationMethod.validate(this.miningMethod, block);
			ValidateBlockRes emittedTask = new ValidateBlockRes(block.getId(), this.fullName().split("#")[1], valid);
			System.out.println("[" + this.fullName() + "] Emitted Task: " + emittedTask.getMessage());
			this.getTopParent().broadcast(emittedTask);
		}
	}

	@Override
	public String toString() {
		return "u: " + this.getWallet().getName() + ", PK:" + this.getWallet().getPublicKey() + ", balance: " + this.getWallet().getBalance() + " | @" + fullName();
	}
}
