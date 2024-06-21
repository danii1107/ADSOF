/**
 * Test unitario de la clase Process
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import states.Process;
import states.Transition;
import java.util.*;

public class ProcessTest {

    @Test
    public void testAdd() {
        Process<String> process = new Process<>("A", "B", "C");

        List<Transition<String>> transitions = new ArrayList<>();
        transitions.add(new Transition<>("A", "B"));
        transitions.add(new Transition<>("B", "C"));
        transitions.add(new Transition<>("C", null));

        process.add(transitions);

        String tostring = "A (initial 1 times, final 0 times):\n" +
                "   to state B: 1 times\n" +
                "B (initial 0 times, final 0 times):\n" +
                "   to state C: 1 times\n" +
                "C (initial 0 times, final 0 times):\n";

        assertEquals(tostring, process.toString());
    }
}