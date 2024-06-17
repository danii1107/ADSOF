/**
 * Unit tests for the Transaction class.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.wallets.Transaction;
import src.tests.TesterMainExercise1;

public class TransactionTest extends TesterMainExercise1 {
	
	@Test
	public void testId() {
		this.buildNetwork();
		Transaction t1 = new Transaction(wallet1, wallet2, 1);
		assertNotNull(t1);
		assertNotNull(t1.getId());
	}

	@Test
	public void testSender() {
		this.buildNetwork();
		Transaction t1 = new Transaction(wallet1, wallet2, 1);
		assertNotNull(t1);
		assertNotNull(t1.getSender());
		assertEquals(wallet1.getPublicKey(), t1.getSender());
	}

	@Test
	public void testReceiver() {
		this.buildNetwork();
		Transaction t1 = new Transaction(wallet1, wallet2, 1);
		assertNotNull(t1);
		assertNotNull(t1.getReceiver());
		assertEquals(wallet2.getPublicKey(), t1.getReceiver());
	}

	@Test
	public void testAmount() {
		this.buildNetwork();
		Transaction t1 = new Transaction(wallet1, wallet2, 1);
		assertNotNull(t1);
		assertNotNull(t1.getAmount());
		assertEquals(1, t1.getAmount().intValue());
	}

	@Test
	public void testConfirmed() {
		this.buildNetwork();
		Transaction t1 = new Transaction(wallet1, wallet2, 1);
		assertNotNull(t1);
		assertNotNull(t1.isConfirmed());
		assertFalse(t1.isConfirmed());
	}

	@Test
	public void testConfirm() {
		this.buildNetwork();
		Transaction t1 = new Transaction(wallet1, wallet2, 1);
		assertNotNull(t1);
		assertNotNull(t1.isConfirmed());
		assertFalse(t1.isConfirmed());
		t1.confirm();
		assertTrue(t1.isConfirmed());
	}

	@Test
	public void testName() {
		this.buildNetwork();
		Transaction t1 = new Transaction(wallet1, wallet2, 1);
		assertNotNull(t1);
		assertEquals("Tx-" + t1.getId(), t1.getName());
	}

	@Test
	public void testApply() {
		this.buildNetwork();
		Transaction t1 = new Transaction(wallet1, wallet2, 1);
		assertNotNull(t1);
		assertEquals(10, wallet1.getBalance().intValue());
		assertEquals(100, wallet2.getBalance().intValue());
		t1.applyTransaction(wallet2);
		assertEquals(9, wallet1.getBalance().intValue());
		assertEquals(101, wallet2.getBalance().intValue());
	}
}
