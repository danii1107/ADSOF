/**
 * Unit tests for the Notifications classes.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.interfaces.IMessage;
import src.blockchain.wallets.Transaction;
import src.blockchain.notifications.*;
import src.tests.TesterMainExercise1;
import src.blockchain.mining.Block;

public class NotificationTest extends TesterMainExercise1 {
	
	@Test
	public void testValidateBlockRes() {
		this.buildNetwork();
		Transaction tr1 = new Transaction(wallet1, wallet2, 1);
		Block block = new Block(tr1, null, wallet2.getPublicKey());
		IMessage msg = new ValidateBlockRes(block, miningNode2, true);
		assertNotNull(msg);
		assertEquals(msg.getMessage(), "ValidateBlockRes: <b:"+block.getId()+", res: true, src:"+String.format("%03d", miningNode2.getId())+">");
	}

	@Test
	public void testValidateBlockRq() {
		this.buildNetwork();
		Transaction tr1 = new Transaction(wallet1, wallet2, 1);
		Block block = new Block(tr1, null, wallet2.getPublicKey());
		IMessage msg = new ValidateBlockRq(block, miningNode2);
		assertNotNull(msg);
		assertEquals(msg.getMessage(), "ValidateBlockRq: <b:"+block.getId()+", src:"+String.format("%03d", miningNode2.getId())+">");
	}

	@Test
	public void testTransaction() {
		this.buildNetwork();
		Transaction tr1 = new Transaction(wallet1, wallet2, 1);
		IMessage msg = new TransactionNotification(tr1);
		assertNotNull(msg);
		assertEquals(msg.getMessage(), tr1.toString());
	}
}
