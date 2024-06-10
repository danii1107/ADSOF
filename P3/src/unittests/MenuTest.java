/**
 * Test unitario de la clase Menu
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import static src.informacion.ElementoNutricional.*;
import static src.comida.TipoIngrediente.*;
import src.informacion.*;
import src.comida.*;
import src.menus.*;

import java.util.*;

public class MenuTest {
	
	@Test
	public void test_InfoFichero() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		Plato plato = new Plato("Pollo con arroz");
		plato.addIngrediente(ingrediente, 1);
		Menu menu = new Menu(plato);
		assertEquals(menu.getInfoFichero(), "MENU;Pollo con arroz");
	}
	
	@Test
	public void test_setInfoFichero() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		ingrediente.tieneAlergenos(Alergeno.GLUTEN, Alergeno.HUEVO);
		Plato plato = new Plato("Pollo con arroz");
		plato.addIngrediente(ingrediente, 1);
		Menu menu = new Menu();
		Map<String, Comida> comida = new HashMap<>();
		comida.put("Pollo con arroz", plato);
		menu.setInfoFichero("MENU;Pollo con arroz", comida);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(CALORIAS), 100.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(CARBOHIDRATOS), 20.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(GRASA_TOTAL), 10.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(GRASA_SATURADA), 5.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(PROTEINA), 5.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(AZUCARES), 5.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(FIBRA), 5.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(SODIO), 5.0, 0.0);
		assertTrue(menu.getAlergenos().contains(Alergeno.GLUTEN));
		assertTrue(menu.getAlergenos().contains(Alergeno.HUEVO));
	}
}
