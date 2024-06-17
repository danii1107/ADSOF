/**
 * Unit tests for the Subnet class.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.tests.TesterMainExercise1;

public class SubnetTest extends TesterMainExercise1 {
	
	@Test
	public void testNodes() {
		this.buildNetwork();
		assertNotNull(this.subnet.getNodes());
		assertFalse(this.subnet.getNodes().isEmpty());
	}

	@Test
	public void testAddNode() {
		this.buildNetwork();
		assertTrue(this.subnet.getNodes().contains(this.miningNode2));
	}
	
	@Test
	public void testToString() {
		this.buildNetwork();
		assertEquals("Node network of " + this.subnet.getNodes().size() + " nodes: " + this.subnet.getNodes().toString(), this.subnet.toString());
	}
}
