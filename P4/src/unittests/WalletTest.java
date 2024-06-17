/**
 * Unit tests for the Wallet class.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.utils.CommonUtils;
import src.tests.TesterMainExercise1;

public class WalletTest extends TesterMainExercise1 {
	
	@Test
	public void testName() {
		this.buildNetwork();
		assertNotNull(wallet1);
		assertNotNull(wallet1.getName());
		assertEquals("Bob", wallet1.getName());
	}

	@Test
	public void testPublicKey() {
		this.buildNetwork();
		assertNotNull(wallet1);
		assertNotNull(wallet1.getPublicKey());
		assertEquals(CommonUtils.sha1("PK-Bob"), wallet1.getPublicKey());
	}

	@Test
	public void testBalance() {
		this.buildNetwork();
		assertNotNull(wallet1);
		assertNotNull(wallet1.getBalance());
		assertEquals(10, wallet1.getBalance().intValue());
		wallet1.setBalance(20);
		assertEquals(20, wallet1.getBalance().intValue());
	}

	@Test
	public void testPrivateKey() {
		this.buildNetwork();
		assertNotNull(wallet1);
		assertNotNull(wallet1.getPrivateKey());
		assertEquals(CommonUtils.sha1(wallet1.getPublicKey()), wallet1.getPrivateKey());
	}

	@Test
	public void testToString() {
		this.buildNetwork();
		assertNotNull(wallet1);
		assertNotNull(wallet1.toString());
		assertEquals("u: Bob, PK: " + wallet1.getPublicKey() + ", balance: 10", wallet1.toString());
	}
}
