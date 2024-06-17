/**
 * Unit tests for the Node class.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import src.blockchain.exceptions.transaction.TransactionException;
import src.blockchain.wallets.Transaction;
import src.tests.TesterMainExercise1;

public class NodeTest extends TesterMainExercise1 {
	@Test
	public void testId() {
		this.buildNetwork();
		assertNotNull(this.node.getId());
	}
	
	@Test
	public void testWallet() {
		this.buildNetwork();
		assertNotNull(this.node.getWallet());
		assertEquals(this.node.getWallet(), this.wallet1);
	}
	
	@Test
	public void testTransactions() {
		this.buildNetwork();
		List<Transaction> transactions = this.node.getTransactions();
		assertNotNull(transactions);
		assertEquals(0, transactions.size());
	}
	
	@Test
	public void testCheckMining() {
		this.buildNetwork();
		assertFalse(this.node.checkMining());
	}
	
	@Test
	public void testCreateTransactionKey() throws TransactionException {
		this.buildNetwork();
		Transaction transaction = this.node.createTransaction("receiver_pk", 1);
		assertNotNull(transaction);
		assertEquals(this.wallet1.getPublicKey(), transaction.getSender());
		assertEquals("receiver_pk", transaction.getReceiver());
	}
	
	@Test
	public void testCreateTransactionWallet() throws TransactionException {
		this.buildNetwork();
		Transaction transaction = this.node.createTransaction(this.wallet2, 1);
		assertNotNull(transaction);
		assertEquals(this.wallet1.getPublicKey(), transaction.getSender());
		assertEquals(this.wallet2.getPublicKey(), transaction.getReceiver());
	}
	
	@Test
	public void testCommitTransaction() {
		this.buildNetwork();
		Transaction transaction = new Transaction(this.wallet1, this.wallet3.getPublicKey(), 1);
		this.node.commitTransaction(transaction, this.miningNode2);
		List<Transaction> transactions = node.getTransactions();
		assertEquals(1, transactions.size());
		assertEquals(transaction, transactions.get(0));
	}

	@Test
	public void testToString() {
		this.buildNetwork();
		assertEquals(this.node.getWallet().toString() + " | @" + this.node.fullName(), this.node.toString());
	}
}
