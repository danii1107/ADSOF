/**
 * Test unitario de la clase Analyzer
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import states.Analyzer;
import states.Transition;
import java.util.ArrayList;
import java.util.List;

public class AnalyzerTest {

    @Test
    public void testAnalyze() {
        Analyzer<String> analizador = new Analyzer<>();
        analizador.addExpresion((trayectoria, destino) -> trayectoria.size() > 0 && destino.equals("B"), "A", "B");
        assertEquals(1, analizador.getExpresiones().size());
        assertEquals(1, analizador.getExpresiones().get("A").size());
    }

    @Test
    public void testAnalyze1() {
        Analyzer<String> analizador = new Analyzer<>();
        analizador.addExpresion((trayectoria, destino) -> trayectoria.size() > 0 && destino.equals("B"), "A", "B");
        analizador.addExpresion((trayectoria, destino) -> false, "B", "C");

        List<Transition<String>> trayectoria = new ArrayList<>();
        trayectoria.add(new Transition<>("A", "B"));
        trayectoria.add(new Transition<>("B", "C"));
        trayectoria.add(new Transition<>("C", "D"));

        List<Transition<String>> invalidas = analizador.analyze(trayectoria);
        assertEquals(1, invalidas.size());
        assertEquals("C", invalidas.get(0).getDestino());
    }
}