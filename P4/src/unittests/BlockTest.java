/**
 * Unit tests for the Block class.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.wallets.Transaction;
import src.blockchain.utils.BlockConfig;
import src.tests.TesterMainExercise1;
import src.blockchain.mining.Block;

public class BlockTest extends TesterMainExercise1 {

	@Test
	public void testId() {
		this.buildNetwork();
		Block block = new Block(node.createTransaction(wallet2.getPublicKey(), 5), null, wallet2.getPublicKey());
		assertNotNull(block);
		assertNotNull(block.getId());
	}

	@Test
	public void testHash() {
		this.buildNetwork();
		Block block = new Block(node.createTransaction(wallet2.getPublicKey(), 5), null, wallet2.getPublicKey());
		assertNotNull(block);
		assertNotNull(block.getHash());
		assertEquals("", block.getHash());
		block.setBlockHash("hash");
		assertEquals("hash", block.getHash());
	}

	@Test
	public void testValidation() {
		this.buildNetwork();
		Block block = new Block(node.createTransaction(wallet2.getPublicKey(), 5), null, wallet2.getPublicKey());
		assertNotNull(block);
		assertFalse(block.getValidated());
		block.setValidated(true);
		assertTrue(block.getValidated());
	}

	@Test
	public void testTransaction() {
		this.buildNetwork();
		Transaction tr1 = node.createTransaction(wallet2.getPublicKey(), 5);
		Block block = new Block(tr1, null, wallet2.getPublicKey());
		assertNotNull(block);
		assertNotNull(block.geTransaction());
		assertEquals(tr1, block.geTransaction());
	}

	@Test
	public void testToStringHash() {
		this.buildNetwork();
		Block block = new Block(node.createTransaction(wallet2.getPublicKey(), 5), null, wallet2.getPublicKey());
		assertNotNull(block);
		assertTrue(block.toStringHash().startsWith(BlockConfig.VERSION+BlockConfig.GENESIS_BLOCK));
	}

	@Test
	public void testToString() {
		this.buildNetwork();
		Block block = new Block(node.createTransaction(wallet2.getPublicKey(), 5), null, wallet2.getPublicKey());
		assertNotNull(block);
		assertNotNull(block.toString());
		assertTrue(block.toString().contains("id:") &&
					block.toString().contains("hash:") &&
					block.toString().contains("v:") &&
					block.toString().contains("minerK: ") &&
					block.toString().contains("diff:") &&
					block.toString().contains("nonce:") &&
					block.toString().contains("ts:")
		);
	}
}
