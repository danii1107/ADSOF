/**
 * Unit tests for the MiningNode class.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.mining.SimpleMining;
import src.blockchain.mining.SimpleValidate;
import src.tests.TesterMainExercise1;
import src.blockchain.interfaces.*;

public class MiningNodeTest extends TesterMainExercise1 {
	@Test
	public void testComputationalCapacity() {
		this.buildNetwork();
		assertEquals(10000, this.miningNode.getComputationalCapacity());
	}
	
	@Test
	public void testMiningMethod() {
		this.buildNetwork();
		IMiningMethod miningMethod = new SimpleMining();
		this.miningNode.setMiningMethod(miningMethod);
		assertEquals(miningMethod, this.miningNode.getMiningMethod());
	}
	
	@Test
	public void testValidationMethod() {
		this.buildNetwork();
		IValidateMethod validateMethod = new SimpleValidate();
		this.miningNode.setValidationMethod(validateMethod);
		assertEquals(validateMethod, this.miningNode.getValidationMethod());
	}
	
	@Test
	public void testCheckMining() {
		this.buildNetwork();
		assertTrue(this.miningNode.checkMining());
	}
	
	@Test
	public void testToString() {
		this.buildNetwork();
		assertEquals(this.miningNode.getWallet().toString() + " | @" + this.miningNode.fullName(), this.miningNode.toString());
	}
}
