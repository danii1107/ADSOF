/**
 * Test unitario de la clase Plato
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import static src.informacion.ElementoNutricional.*;
import src.informacion.*;
import src.comida.*;

import java.util.*;

public class PlatoTest {
	
	@Test
	public void test_crearPlato() {
		Plato plato = new Plato("Pollo con arroz");
		assertEquals(plato.getNombre(), "Pollo con arroz");
	}
	
	@Test
	public void test_addIngrediente() {
		Plato plato = new Plato("Pollo con arroz");
		Ingrediente pollo = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		assertFalse(plato.addIngrediente(pollo, 1));
		assertTrue(plato.addIngrediente(pollo, 1));
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(CALORIAS), 100.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(CARBOHIDRATOS), 20.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(GRASA_TOTAL), 10.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(GRASA_SATURADA), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(PROTEINA), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(AZUCARES), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(FIBRA), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(SODIO), 5.0, 0.0);
	}
	
	@Test
	public void test_addPlato() {
		Plato plato = new Plato("Pollo con arroz");
		Plato plato2 = new Plato("Arroz con pollo");
		Ingrediente pollo = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		assertFalse(plato2.addIngrediente(pollo, 1));
		assertFalse(plato.addPlato(plato2));
		assertTrue(plato.addPlato(plato2));
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(CALORIAS), 100.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(CARBOHIDRATOS), 20.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(GRASA_TOTAL), 10.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(GRASA_SATURADA), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(PROTEINA), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(AZUCARES), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(FIBRA), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(SODIO), 5.0, 0.0);
	}

	@Test
	public void test_tieneAlergenos() {
		Plato plato = new Plato("Pollo con arroz");
		Ingrediente pollo = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		pollo.tieneAlergenos(Alergeno.GLUTEN, Alergeno.HUEVO);
		assertFalse(plato.addIngrediente(pollo, 1));
		assertTrue(plato.getAlergenos().contains(Alergeno.GLUTEN));
		assertTrue(plato.getAlergenos().contains(Alergeno.HUEVO));
	}

	@Test
	public void test_getChild() {
		Plato plato = new Plato("Pollo con arroz");
		Ingrediente pollo = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		pollo.tieneAlergenos(Alergeno.GLUTEN, Alergeno.HUEVO);
		assertFalse(plato.addIngrediente(pollo, 1));
		assertEquals(plato.getChild(), ((Ingrediente) pollo));
	}

	@Test
	public void test_InfoFichero() {
		Plato plato = new Plato("Pollo con arroz");
		Plato plato2 = new Plato("Arroz con pollo");
		Ingrediente pollo = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		assertFalse(plato.addIngrediente(pollo, 1));
		assertFalse(plato.addPlato(plato2));
		assertFalse(plato2.addIngrediente(pollo, 1));
		assertEquals(plato.getInfoFichero(), "PLATO;Pollo con arroz;INGREDIENTE Pollo:1;PLATO Arroz con pollo");
	}
	
	@Test
	public void test_setInfoFichero() {
		Plato plato = new Plato("Pollo con arroz");
		Ingrediente pollo = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		Map<String, Comida> comida = new HashMap<>();
		comida.put("Pollo", pollo);
		plato.setInfoFichero("PLATO;Pollo con arroz;INGREDIENTE Pollo:1", comida);
		assertEquals(plato.getNombre(), "Pollo con arroz");
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(CALORIAS), 100.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(CARBOHIDRATOS), 20.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(GRASA_TOTAL), 10.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(GRASA_SATURADA), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(PROTEINA), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(AZUCARES), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(FIBRA), 5.0, 0.0);
		assertEquals(plato.getInfoNutricional().getInfoNutricional().get(SODIO), 5.0, 0.0);

		assertNull(plato.setInfoFichero("PLATO;Pollo con arroz;INGREDIENTE Pollo:1"));
	}

	@Test
	public void test_toString() {
		Plato plato = new Plato("Pollo con arroz");
		Ingrediente pollo = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		assertFalse(plato.addIngrediente(pollo, 1));
		assertEquals(plato.toString(), "[Plato] Pollo con arroz: INFORMACION NUTRICIONAL DEL PLATO -> Valor energetico: 100.00 kcal, Hidratos de carbono: 20.00 g, Grasas: 10.00 g, Saturadas: 5.00 g, Proteinas: 5.00 g, Azucares: 5.00 g, Fibra: 5.00 g, Sodio: 5.00 mg.");
	}
}
