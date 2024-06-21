/**
 * Test unitario de la clase Transition
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import states.Transition;

public class TransitionTest {

    @Test
    public void testGetOrigen() {
        Transition<String> transition = new Transition<>("A");
        assertEquals("A", transition.getOrigen());
    }

    @Test
    public void testGetDestino() {
        Transition<String> transition = new Transition<>("A", "B");
        assertEquals("B", transition.getDestino());
    }

    @Test
    public void testToString() {
        Transition<String> transition = new Transition<>("A", "B");
        assertTrue(transition.toString().contains("(from: A to B at: "));
    }

    @Test
    public void testToString2() {
        Transition<String> transition = new Transition<>("A");
        assertTrue(transition.toString().contains("(in: A at: "));
    }
}