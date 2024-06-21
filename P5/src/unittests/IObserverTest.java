/**
 * Test unitario para la interfaz del patrón observer
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import interfaces.IObserver;

public class IObserverTest {
    
    @Test
    public void testUpdate1() {
        IObserver<String> observer = new IObserver<String>() {
            @Override
            public void update(String t) {
                assertEquals("EURO2024", t);
            }
        };
        observer.update("EURO2024");
    }
    
    @Test
    public void testUpdate2() {
        IObserver<String> observer = new IObserver<String>() {
            @Override
            public void update(String t) {
                assertNull(t);
            }
        };
        observer.update(null);
    }
}