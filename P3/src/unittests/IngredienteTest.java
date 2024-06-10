/**
 * Test unitario de la clase Ingrediente
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import static src.informacion.ElementoNutricional.*;
import static src.comida.TipoIngrediente.*;
import static src.informacion.Alergeno.*;
import src.informacion.*;
import src.comida.*;

public class IngredienteTest {

	@Test
	public void test_crearIngrediente() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalPeso(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		assertEquals(ingrediente.getNombre(), "Pollo");
		assertEquals(ingrediente.getTipoEnum(), CARNE);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(CALORIAS), 100.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(CARBOHIDRATOS), 20.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(GRASA_TOTAL), 10.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(GRASA_SATURADA), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(PROTEINA), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(AZUCARES), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(FIBRA), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(SODIO), 5.0, 0.0);
	}

	@Test
	public void test_crearIngrediente2() {
		Ingrediente ingrediente = new Ingrediente("Caldo", "Caldo", new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		assertEquals(ingrediente.getNombre(), "Caldo");
		assertEquals(ingrediente.getTipo(), "Caldo");
		assertNull(ingrediente.getTipoEnum());
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(CALORIAS), 100.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(CARBOHIDRATOS), 20.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(GRASA_TOTAL), 10.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(GRASA_SATURADA), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(PROTEINA), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(AZUCARES), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(FIBRA), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(SODIO), 5.0, 0.0);
	}

	@Test
	public void test_TieneAlergenos() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		ingrediente.tieneAlergenos(GLUTEN, Alergeno.HUEVO);
		assertTrue(ingrediente.getAlergenos().contains(GLUTEN));
		assertTrue(ingrediente.getAlergenos().contains(Alergeno.HUEVO));
		assertFalse(ingrediente.getAlergenos().contains(Alergeno.LACTOSA));
	}

	@Test
	public void test_InfoFichero() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		ingrediente.tieneAlergenos(GLUTEN, Alergeno.HUEVO);
		assertEquals(ingrediente.getInfoFichero(), "INGREDIENTE_UNIDAD;Pollo;CARNE;100.0;20.0;10.0;5.0;5.0;5.0;5.0;5.0;S;N;N;S");
	}
	
	@Test
	public void test_setInfoFichero() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalUnidad(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		ingrediente.setInfoFichero("INGREDIENTE_UNIDAD;Pollo;CARNE;100.0;20.0;10.0;5.0;5.0;5.0;5.0;5.0;S;N;N;S");
		assertEquals(ingrediente.getNombre(), "Pollo");
		assertEquals(ingrediente.getTipoEnum(), CARNE);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(CALORIAS), 100.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(CARBOHIDRATOS), 20.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(GRASA_TOTAL), 10.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(GRASA_SATURADA), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(PROTEINA), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(AZUCARES), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(FIBRA), 5.0, 0.0);
		assertEquals(ingrediente.getInfoNutricional().getInfoNutricional().get(SODIO), 5.0, 0.0);
		assertTrue(ingrediente.getAlergenos().contains(GLUTEN));
		assertFalse(ingrediente.getAlergenos().contains(Alergeno.LACTOSA));
		assertTrue(ingrediente.getAlergenos().contains(Alergeno.HUEVO));
	}
}
