/**
 * Test unitario de la enumeración ElementoNutricional
 * 
 * @autor Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static src.informacion.ElementoNutricional.*;
import src.informacion.ElementoNutricional;

import static org.junit.Assert.*;
import org.junit.Test;

public class ElementoNutricionalTest {
	
	@Test
	public void test_Calorias() {
		ElementoNutricional elemento = CALORIAS;
		assertEquals(elemento.toString(), "Valor energetico");
		assertEquals(elemento.getUnidad(), "kcal");
	}
	
	@Test
	public void test_Carbohidratos() {
		ElementoNutricional elemento = CARBOHIDRATOS;
		assertEquals(elemento.toString(), "Hidratos de carbono");
		assertEquals(elemento.getUnidad(), "g");
	}
	
	@Test
	public void test_GrasaTotal() {
		ElementoNutricional elemento = GRASA_TOTAL;
		assertEquals(elemento.toString(), "Grasas");
		assertEquals(elemento.getUnidad(), "g");
	}
	
	@Test
	public void test_GrasaSaturada() {
		ElementoNutricional elemento = GRASA_SATURADA;
		assertEquals(elemento.toString(), "Saturadas");
		assertEquals(elemento.getUnidad(), "g");
	}
	
	@Test
	public void test_Proteina() {
		ElementoNutricional elemento = PROTEINA;
		assertEquals(elemento.toString(), "Proteinas");
		assertEquals(elemento.getUnidad(), "g");
	}
	
	@Test
	public void test_Azucar() {
		ElementoNutricional elemento = AZUCARES;
		assertEquals(elemento.toString(), "Azucares");
		assertEquals(elemento.getUnidad(), "g");
	}
	
	@Test
	public void test_Fibra() {
		ElementoNutricional elemento = FIBRA;
		assertEquals(elemento.toString(), "Fibra");
		assertEquals(elemento.getUnidad(), "g");
	}
	
	@Test
	public void test_Sodio() {
		ElementoNutricional elemento = SODIO;
		assertEquals(elemento.toString(), "Sodio");
		assertEquals(elemento.getUnidad(), "mg");
	}
}
