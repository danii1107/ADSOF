/**
 * Unit tests for the BlockchaiNetwork class.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.tests.TesterMainExercise1;

public class BlockchainTest extends TesterMainExercise1 {
	
	@Test
	public void testElements() {
		this.buildNetwork();
		assertNotNull(this.network.getElements());
		assertFalse(this.network.getElements().isEmpty());
	}

	@Test
	public void testName() {
		this.buildNetwork();
		assertEquals("ADSOF blockchain", this.network.getName());
	}

	@Test
	public void testConnect() {
		this.buildNetwork();
		assertTrue(this.network.getElements().contains(this.node));
		assertTrue(this.network.getElements().contains(this.miningNode));
		assertTrue(this.network.getElements().contains(this.subnet));
	}

	@Test
	public void testParent() {
		this.buildNetwork();
		assertNull(this.network.getParent());
	}
	
	@Test
	public void testToString() {
		this.buildNetwork();
		assertTrue(this.network.toString().contains(this.network.getName() + " consists of " + this.network.getElements().size() + " elements:\n"));
	}
}
