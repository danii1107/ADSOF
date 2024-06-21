/**
 * Test unitario para la enumeración ExpositionKind
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import expositions.ExpositionKind;

public class ExpositionKindTest {

    @Test
    public void testCapacidad_Audiovisual() {
        assertEquals(50, ExpositionKind.AUDIOVISUAL.capacidad());
    }

    @Test
    public void testCapacidad_Esculturas() {
        assertEquals(20, ExpositionKind.ESCULTURAS.capacidad());
    }

    @Test
    public void testCapacidad_Pintura() {
        assertEquals(80, ExpositionKind.PINTURA.capacidad());
    }
}