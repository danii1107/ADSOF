/**
 * Test unitario de la clase InfoNutricionalPeso
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import static src.comida.TipoIngrediente.*;
import src.comida.Ingrediente;
import src.informacion.*;

public class InfoNutricionalPesoTest {
	@Test
	public void test_getFactor() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalPeso(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		assertEquals(ingrediente.getInfoNutricional().getFactor(), 100, 0.0);
	}

	@Test
	public void test_getUnidadPeso() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalPeso(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		assertEquals(ingrediente.getInfoNutricional().getUnidadPeso(), "INGREDIENTE_PESO");
	}

	@Test
	public void test_toString() {
		Ingrediente ingrediente = new Ingrediente("Pollo", CARNE, new InfoNutricionalPeso(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		assertEquals(ingrediente.toString(), "[Carne] Pollo: INFORMACION NUTRICIONAL POR 100 g -> Valor energetico: 100.00 kcal, Hidratos de carbono: 20.00 g, Grasas: 10.00 g, Saturadas: 5.00 g, Proteinas: 5.00 g, Azucares: 5.00 g, Fibra: 5.00 g, Sodio: 5.00 mg.");
	}
}