/**
 * Test unitario de la clase InfoNutricional
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.informacion.*;

public class InfoNutricionalTest {

	@Test
    public void test_crearInfo() {
        InfoNutricional info = new InfoNutricionalPeso(100.0, 20.0, 10.0, 5.0, 15.0, 5.0, 2.0, 100.0);
        assertEquals(100.0, info.getInfoNutricional().get(ElementoNutricional.CALORIAS), 0.0);
        assertEquals(20.0, info.getInfoNutricional().get(ElementoNutricional.CARBOHIDRATOS), 0.0);
        assertEquals(10.0, info.getInfoNutricional().get(ElementoNutricional.GRASA_TOTAL), 0.0);
        assertEquals(5.0, info.getInfoNutricional().get(ElementoNutricional.GRASA_SATURADA), 0.0);
        assertEquals(15.0, info.getInfoNutricional().get(ElementoNutricional.PROTEINA), 0.0);
        assertEquals(5.0, info.getInfoNutricional().get(ElementoNutricional.AZUCARES), 0.0);
        assertEquals(2.0, info.getInfoNutricional().get(ElementoNutricional.FIBRA), 0.0);
        assertEquals(100.0, info.getInfoNutricional().get(ElementoNutricional.SODIO), 0.0);
    }

	@Test
    public void test_crearInfo2() {
        InfoNutricional info = new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 15.0, 5.0, 2.0, 100.0);
        assertEquals(100.0, info.getInfoNutricional().get(ElementoNutricional.CALORIAS), 0.0);
        assertEquals(20.0, info.getInfoNutricional().get(ElementoNutricional.CARBOHIDRATOS), 0.0);
        assertEquals(10.0, info.getInfoNutricional().get(ElementoNutricional.GRASA_TOTAL), 0.0);
        assertEquals(5.0, info.getInfoNutricional().get(ElementoNutricional.GRASA_SATURADA), 0.0);
        assertEquals(15.0, info.getInfoNutricional().get(ElementoNutricional.PROTEINA), 0.0);
        assertEquals(5.0, info.getInfoNutricional().get(ElementoNutricional.AZUCARES), 0.0);
        assertEquals(2.0, info.getInfoNutricional().get(ElementoNutricional.FIBRA), 0.0);
        assertEquals(100.0, info.getInfoNutricional().get(ElementoNutricional.SODIO), 0.0);
    }

    @Test
    public void test_constructorDefecto() {
        InfoNutricional info = new InfoNutricionalPlato();
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.CALORIAS), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.CARBOHIDRATOS), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.GRASA_TOTAL), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.GRASA_SATURADA), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.PROTEINA), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.AZUCARES), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.FIBRA), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.SODIO), 0.0);
    }

	@Test
    public void test_constructorDefecto1() {
        InfoNutricional info = new InfoNutricionalMenu();
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.CALORIAS), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.CARBOHIDRATOS), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.GRASA_TOTAL), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.GRASA_SATURADA), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.PROTEINA), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.AZUCARES), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.FIBRA), 0.0);
        assertEquals(0.0, info.getInfoNutricional().get(ElementoNutricional.SODIO), 0.0);
    }

    @Test
    public void test_getFactor() {
        InfoNutricional info = new InfoNutricionalPeso(100.0, 20.0, 10.0, 5.0, 15.0, 5.0, 2.0, 100.0);
        assertEquals(100, info.getFactor().intValue());
    }

	@Test
    public void test_getFactor2() {
        InfoNutricional info = new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 15.0, 5.0, 2.0, 100.0);
        assertEquals(1, info.getFactor().intValue());
    }

    @Test
    public void test_getUnidadPeso() {
        InfoNutricional info = new InfoNutricionalPeso(100.0, 20.0, 10.0, 5.0, 15.0, 5.0, 2.0, 100.0);
        assertEquals("INGREDIENTE_PESO", info.getUnidadPeso());
    }

	@Test
    public void test_getUnidadPeso2() {
        InfoNutricional info = new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 15.0, 5.0, 2.0, 100.0);
        assertEquals("INGREDIENTE_UNIDAD", info.getUnidadPeso());
    }
}
