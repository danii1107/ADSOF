package src.tests;

import src.blockchain.mining.*;
import src.blockchain.notifications.TransactionNotification;

public class TesterMainExercise3 extends TesterMainExercise2 {
	public void createTransactions() {
		// create a transaction and send it to the network
		this.miningNode.setMiningMethod(new SimpleMining());
		this.miningNode.setValidationMethod(new SimpleValidate());
		this.miningNode2.setMiningMethod(new SimpleMining());
		this.miningNode2.setValidationMethod(new SimpleValidate());
		network.broadcast(new TransactionNotification(
				node.createTransaction(wallet2.getPublicKey(), 5)));
	}

	public static void main(String[] args) {
		TesterMainExercise3 tme = new TesterMainExercise3();
		tme.buildNetwork();
		tme.createTransactions();
		System.out.println("End of party!");
	}
}