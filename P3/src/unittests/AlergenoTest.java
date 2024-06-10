/**
 * Test unitario de la enumeración Alergeno
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static src.informacion.Alergeno.*;
import src.informacion.Alergeno;

import static org.junit.Assert.*;
import org.junit.Test;

public class AlergenoTest {

    @Test
    public void test_Gluten() {
        Alergeno alergeno = GLUTEN;
        assertEquals(alergeno.toString(), "gluten");
    }

	@Test
    public void test_Lactosa() {
        Alergeno alergeno = LACTOSA;
        assertEquals(alergeno.toString(), "lactosa");
    }

	@Test
    public void test_FrutosSecos() {
        Alergeno alergeno = FRUTOS_SECOS;
        assertEquals(alergeno.toString(), "frutos secos");
    }

    @Test
    public void test_Huevo() {
        Alergeno alergeno = HUEVO;
        assertEquals(alergeno.toString(), "huevo");
    }
}