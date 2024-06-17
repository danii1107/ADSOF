/**
 * Unit tests for the Exceptions.
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.blockchain.exceptions.transaction.*;
import src.blockchain.exceptions.connection.*;
import src.blockchain.exceptions.singleton.*;


public class ExceptionTest {
	
	@Test
    public void testTransaction() {
        TransactionException exception = new TransactionException("Test exception", "me", "you", 1); 
        assertEquals("Test exceptionsource: me, receiver: you, amount: 1", exception.toString());
    }

	@Test
	public void testSingleton() {
		SingletonException exception = new SingletonException("Test exception");
		assertEquals("SingletonException: Test exception", exception.toString());
	}

	@Test
	public void testConnection() {
		ConnectionException exception = new ConnectionException(1);
		assertEquals("Connection exception: Node 001 is already connected to the network", exception.toString());
	}

	@Test
	public void testDuplicateConnection() {
		DuplicateConnectionException exception = new DuplicateConnectionException(1);
		assertEquals("Connection exception: Node 001 is connected to a different network", exception.toString());
	}
}
