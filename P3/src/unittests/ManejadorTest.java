/**
 * Test unitario de la clase ManejadorFicheros
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.manejador.ManejadorFicheros;
import src.testers.MenusTester;
import src.menus.Menu;

import java.io.File;
import java.util.*;

public class ManejadorTest extends MenusTester {

    @Test
    public void test_guardarFichero() {
		List<Menu> menus = this.crearMenus();
        ManejadorFicheros.guardarFichero("comida.txt", menus);

        File fichero = new File("comida.txt");
        
		assertTrue(fichero.exists());
		
		Set<String> lineas = ManejadorFicheros.cargarInformacionExistente("comida.txt");
		
		assertFalse(lineas.isEmpty());
		assertEquals(13, lineas.size());
    }

    @Test
    public void test_leerFichero() {
        ManejadorFicheros.leerFichero("comida.txt");

        File file = new File("comida.txt");
        assertTrue(file.exists());

        assertEquals(3, ManejadorFicheros.getMenus().size());

		List<Menu> menus = ManejadorFicheros.getMenus();
		for (Menu menu : menus) {
			assertTrue(menu.getPlatos().size() == 2 || menu.getPlatos().size() == 1);
		}
    }
}
