/**
 * Class that tests the new mining and validation method 
 * of the Exercise 4.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.tests;

import src.blockchain.exceptions.singleton.SingletonException;
import src.blockchain.notifications.TransactionNotification;
import src.blockchain.abstractFactory.*;
import src.blockchain.interfaces.*;

public class TesterMainExercise4 extends TesterMainExercise2 {
	public void createTransactions() {
		// create a transaction and send it to the network
		IFactory factory = null;
		try {
			// Exception, factory does not exist
			factory = SingletonFactory.getInstance();
		} catch (SingletonException e) {
			System.out.println(e);
		}
		SingletonFactory.createFactory(new NewFactory());
		factory = SingletonFactory.getInstance();
		try {
			// Only one type of factory on the whole system
			SingletonFactory.createFactory(new SimpleFactory());
		} catch (SingletonException e) {
			System.out.println(e);
		}
		
		// Mining and validate will be always instance of the same factory
		// We should forbid the mining and validation methods to be created without the factory but the tester 3 would fail
		IMiningMethod mining = factory.createMining();
		IValidateMethod validate = factory.createValidation();

		this.miningNode.setMiningMethod(mining);
		this.miningNode.setValidationMethod(validate);
		this.miningNode2.setMiningMethod(mining);
		this.miningNode2.setValidationMethod(validate);
		network.broadcast(new TransactionNotification(
				node.createTransaction(wallet2.getPublicKey(), 5)));
	}

	public static void main(String[] args) {
		TesterMainExercise4 tme = new TesterMainExercise4();
		tme.buildNetwork();
		tme.createTransactions();
		System.out.println("End of party!");
	}
}
