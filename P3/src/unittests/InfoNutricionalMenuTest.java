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

	@Test
	public void test_getFactor() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		Plato plato = new Plato("Pollo con arroz");
		plato.addIngrediente(ingrediente, 1);
		Menu menu = new Menu(plato);
		assertEquals(menu.getInfoNutricional().getFactor(), 1, 0.0);
	}

	@Test
	public void test_getUnidadPeso() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		Plato plato = new Plato("Pollo con arroz");
		plato.addIngrediente(ingrediente, 1);
		Menu menu = new Menu(plato);
		assertEquals(menu.getInfoNutricional().getUnidadPeso(), "");
	}

	@Test
	public void test_toString() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		Plato plato = new Plato("Pollo con arroz");
		plato.addIngrediente(ingrediente, 1);
		Menu menu = new Menu(plato);
		assertEquals(menu.toString().substring(7, menu.toString().length()), "[Pollo con arroz]: INFORMACION NUTRICIONAL DEL MENU -> Valor energetico: 100.00 kcal, Hidratos de carbono: 20.00 g, Grasas: 10.00 g, Saturadas: 5.00 g, Proteinas: 5.00 g, Azucares: 5.00 g, Fibra: 5.00 g, Sodio: 5.00 mg.");
	}
}
