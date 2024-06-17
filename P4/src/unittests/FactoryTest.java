/**
 * Unit tests for the abstract factory pattern.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.abstractFactory.*;
import src.blockchain.interfaces.IFactory;

public class FactoryTest {
	
	@Test
	public void testSingleton() {
		SingletonFactory.createFactory(new NewFactory());
		assertNotNull(SingletonFactory.getInstance());
	}

	@Test
	public void testSimpleFactory() {
		IFactory factory = new SimpleFactory();
		assertNotNull(factory.createMining());
		assertNotNull(factory.createValidation());
	}

	@Test
	public void testNewFactory() {
		IFactory factory = new NewFactory();
		assertNotNull(factory.createMining());
		assertNotNull(factory.createValidation());
	}
}
