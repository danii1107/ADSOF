/**
 * Test unitario de la clase InfoNutricionalMenu
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.informacion.InfoNutricionalUnidad;
import src.informacion.ElementoNutricional;
import static src.comida.TipoIngrediente.*;
import src.comida.Ingrediente;
import src.comida.Plato;
import src.menus.Menu;

public class InfoNutricionalMenuTest {

	@Test
	public void test_calcularInfoNutricional() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		Plato plato = new Plato("Pollo con arroz");
		plato.addIngrediente(ingrediente, 1);
		Menu menu = new Menu(plato);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.CALORIAS), 100.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.PROTEINA), 5.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.GRASA_TOTAL), 10.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.CARBOHIDRATOS), 20.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.FIBRA), 5.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.SODIO), 5.0, 0.0);
		assertEquals(menu.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.GRASA_SATURADA), 5, 0.0);
	}
}
