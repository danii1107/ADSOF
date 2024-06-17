/**
 * Unit tests for the Mining methods classes.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.utils.CommonUtils;
import src.tests.TesterMainExercise1;
import src.blockchain.wallets.*;
import src.blockchain.mining.*;

public class MiningTest extends TesterMainExercise1 {
	
	@Test
	public void testNewMining() {
		this.buildNetwork();
		NewMining mining = new NewMining();
		Block block = new Block(new Transaction(wallet1, wallet2, 10), null, "miner1");
		String hash = mining.createHash(block);
		StringBuilder reverse = new StringBuilder();
		reverse.append(block.toStringHash() + "EURO2024");
		assertEquals(reverse.reverse().toString(), hash);
	}

	@Test
	public void testSimpleMining() {
		this.buildNetwork();
		SimpleMining mining = new SimpleMining();
		Block block = new Block(new Transaction(wallet1, wallet2, 10), null, "miner1");
		String hash = mining.createHash(block);
		assertEquals(CommonUtils.sha256(block.toStringHash()), hash);
	}
}
