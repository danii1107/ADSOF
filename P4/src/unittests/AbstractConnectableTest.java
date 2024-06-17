/**
 * Unit tests for the AbstractConnectable class.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.utils.CommonUtils;
import src.blockchain.components.Node;
import src.blockchain.wallets.Wallet;
import src.tests.TesterMainExercise1;

public class AbstractConnectableTest extends TesterMainExercise1 {
	@Test
	public void testId() {
		this.buildNetwork();
		assertNotNull(this.node);
		assertNotNull(this.miningNode);
		assertNotNull(this.miningNode2);
		assertNotNull(this.subnet);
		assertNotNull(this.node.getId());
		assertNotNull(this.miningNode.getId());
		assertNotNull(this.miningNode2.getId());
		assertNotNull(this.subnet.getId());
	}
	
	@Test
	public void testName() {
		this.buildNetwork();
		assertEquals("Node", this.node.getName());
		assertEquals("MiningNode", this.miningNode.getName());
		assertEquals("MiningNode", this.miningNode2.getName());
		assertEquals("Subnet", this.subnet.getName());
		this.node.setName("Test");
		assertEquals("Test", this.node.getName());
		this.node.setName("Node");
	}
	
	@Test
	public void testFullName() {
		this.buildNetwork();
		assertEquals("Node#" + String.format("%03d", this.node.getId()), this.node.fullName());
		assertEquals("MiningNode#" + String.format("%03d", this.miningNode.getId()), this.miningNode.fullName());
		assertEquals("MiningNode#" + String.format("%03d", this.miningNode2.getId()), this.miningNode2.fullName());
		assertEquals("Subnet#" + String.format("%03d", this.subnet.getId()), this.subnet.fullName());
	}

	@Test
	public void testParent() {
		this.buildNetwork();
		assertEquals(this.network, this.node.getParent());
		assertEquals(this.network, this.subnet.getParent());
		assertEquals(this.network, this.miningNode.getParent());
		assertEquals(this.subnet, this.miningNode2.getParent());
		Wallet wallet = new Wallet("Dani", CommonUtils.sha1("PK-Dani"), 777);
		Node nodeTest = new Node(wallet);
		assertEquals(null, nodeTest.getParent());
	}
}
