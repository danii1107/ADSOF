/**
 * Test unitario para la clase de estados de las exposiciones
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import expositions.ExpositionState;

public class ExpositionStateTest {

    @Test
    public void testGetNombre() {
        ExpositionState estado = ExpositionState.ABIERTA;
        assertEquals("Abierta", estado.getNombre());
    }

    @Test
    public void testGetPrioridad() {
        ExpositionState estado = ExpositionState.EN_PROCESO;
        assertEquals(2, estado.getPrioridad());
    }

    @Test
    public void testGetValues() {
        ExpositionState[] valores = ExpositionState.getValues();
        assertEquals(4, valores.length);
        assertEquals(ExpositionState.ABIERTA, valores[0]);
        assertEquals(ExpositionState.EN_PROCESO, valores[1]);
        assertEquals(ExpositionState.LLENA, valores[2]);
        assertEquals(ExpositionState.CERRADA, valores[3]);
    }

    @Test
    public void testCompareTo() {
        ExpositionState estado1 = ExpositionState.ABIERTA;
        ExpositionState estado2 = ExpositionState.EN_PROCESO;
        assertTrue(estado1.compareTo(estado2) < 0);
        assertTrue(estado2.compareTo(estado1) > 0);
        assertEquals(0, estado1.compareTo(estado1));
    }

    @Test
    public void testEquals() {
        ExpositionState estado1 = ExpositionState.LLENA;
        ExpositionState estado2 = ExpositionState.LLENA;
        assertTrue(estado1.equals(estado2));
    }

    @Test
    public void testHashCode() {
        ExpositionState estado = ExpositionState.CERRADA;
        assertEquals("Cerrada".hashCode() + estado.getPrioridad(), estado.hashCode());
    }

    @Test
    public void testToString() {
        ExpositionState estado = ExpositionState.ABIERTA;
        assertEquals("Abierta", estado.toString());
    }
}