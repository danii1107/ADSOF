/**
 * Test unitario de la clase Comida
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.comida.Ingrediente;
import src.comida.Plato;
import src.comida.TipoIngrediente;
import src.informacion.Alergeno;
import src.informacion.InfoNutricionalUnidad;

public class ComidaTest {
	
	@Test
	public void test_nombre() {
		Plato plato = new Plato("Pollo con arroz");
		Ingrediente pollo = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		plato.setNombre("Pollo");
		assertEquals(pollo.getNombre(), "Pollo");
	}

	@Test
	public void test_toString() {
		Plato plato = new Plato("Pollo con arroz");
		Ingrediente pollo = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(100.0, 20.0, 10.0, 5.0, 5.0, 5.0, 5.0, 5.0));
		pollo.tieneAlergenos(Alergeno.HUEVO);
		assertFalse(plato.addIngrediente(pollo, 1));
		assertEquals(plato.toString(), "[Plato] Pollo con arroz: INFORMACION NUTRICIONAL DEL PLATO -> Valor energetico: 100.00 kcal, Hidratos de carbono: 20.00 g, Grasas: 10.00 g, Saturadas: 5.00 g, Proteinas: 5.00 g, Azucares: 5.00 g, Fibra: 5.00 g, Sodio: 5.00 mg. CONTIENE huevo");
	}
}
