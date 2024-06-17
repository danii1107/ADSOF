/**
 * Unit tests for the Validation methods classes.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.wallets.Transaction;
import src.tests.TesterMainExercise1;
import src.blockchain.mining.*;

public class ValidateTest extends TesterMainExercise1 {
	
	@Test
	public void testNewValidate() {
		this.buildNetwork();
		NewMining mining = new NewMining();
		Block block = new Block(new Transaction(wallet1, wallet2, 10), null, wallet2.getPublicKey());
		block.setBlockHash(mining.createHash(block));
		NewValidate validate = new NewValidate();
		assertTrue(validate.validate(mining, block));
	}

	@Test
	public void testSimpleValidate() {
		this.buildNetwork();
		SimpleMining mining = new SimpleMining();
		Block block = new Block(new Transaction(wallet1, wallet2, 10), null, wallet2.getPublicKey());
		block.setBlockHash(mining.createHash(block));
		SimpleValidate validate = new SimpleValidate();
		assertTrue(validate.validate(mining, block));
	}
}
