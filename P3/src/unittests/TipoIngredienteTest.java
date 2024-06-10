/**
 * Test unitario de la enumeración TipoIngrediente
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static src.comida.TipoIngrediente.*;
import src.comida.TipoIngrediente;

import static org.junit.Assert.*;
import org.junit.Test;

public class TipoIngredienteTest {

	@Test
    public void testCarne() {
        TipoIngrediente tipo = CARNE;
        assertEquals(tipo.toString(), "Carne");
    }

    @Test
    public void testPescado() {
        TipoIngrediente tipo = PESCADO;
        assertEquals(tipo.toString(), "Pescado");
    }

	@Test
    public void testFrutaVerdura() {
        TipoIngrediente tipo = FRUTA_VERDURA;
        assertEquals(tipo.toString(), "Frutas y verduras");
    }

	@Test
    public void testLegumbre() {
        TipoIngrediente tipo = LEGUMBRE;
        assertEquals(tipo.toString(), "Legumbre");
    }

    @Test
    public void testCereal() {
        TipoIngrediente tipo = CEREAL;
        assertEquals(tipo.toString(), "Cereal");
    }

    @Test
    public void testHuevo() {
        TipoIngrediente tipo = HUEVO;
        assertEquals(tipo.toString(), "Huevo");
    }

    @Test
    public void testLacteo() {
        TipoIngrediente tipo = LACTEO;
        assertEquals(tipo.toString(), "Lacteo");
    }
}
