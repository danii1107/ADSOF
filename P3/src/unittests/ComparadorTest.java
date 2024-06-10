/**
 * Test unitario de la clase Comparador
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import src.informacion.ElementoNutricional;
import src.informacion.Alergeno;
import src.comparador.*;
import src.menus.Menu;

import java.util.*;

public class ComparadorTest {
	@Test
    public void test_MenusElementoNutricional() {
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        Menu menu3 = new Menu();

        menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CALORIAS, 500.0);
        menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_TOTAL, 10.0);
        menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.PROTEINA, 20.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CARBOHIDRATOS, 30.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_SATURADA, 10.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.AZUCARES, 10.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.FIBRA, 50.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.SODIO, 15.0);

        menu2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CALORIAS, 300.0);
        menu2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_TOTAL, 5.0);
        menu2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.PROTEINA, 15.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CARBOHIDRATOS, 25.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_SATURADA, 5.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.AZUCARES, 15.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.FIBRA, 100.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.SODIO, 100.0);

        menu3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CALORIAS, 400.0);
        menu3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_TOTAL, 8.0);
        menu3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.PROTEINA, 18.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CARBOHIDRATOS, 20.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_SATURADA, 15.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.AZUCARES, 20.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.FIBRA, 25.0);
		menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.SODIO, 5.0);

        ComparadorMenus comparador = new ComparadorMenus(menu1, menu2, menu3);

        Map<ElementoNutricional, List<Menu>> ordenados = comparador.menusElementoNutricional();

        assertEquals(Arrays.asList(menu2, menu3, menu1), ordenados.get(ElementoNutricional.CALORIAS));
        assertEquals(Arrays.asList(menu2, menu3, menu1), ordenados.get(ElementoNutricional.GRASA_TOTAL));
        assertEquals(Arrays.asList(menu2, menu3, menu1), ordenados.get(ElementoNutricional.PROTEINA));
    }

    @Test
    public void test_OrdenaCalorias() {
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        Menu menu3 = new Menu();

        menu1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CALORIAS, 500.0);
        menu2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CALORIAS, 300.0);
        menu3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CALORIAS, 400.0);

        ComparadorMenus comparador = new ComparadorMenus(menu1, menu2, menu3);

        List<Menu> ordenados = comparador.ordenaCalorias();

        assertEquals(Arrays.asList(menu2, menu3, menu1), ordenados);
    }

    @Test
    public void test_TieneAlergenos() {
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        Menu menu3 = new Menu();

        menu1.getAlergenos().add(Alergeno.GLUTEN);
        menu1.getAlergenos().add(Alergeno.LACTOSA);

        menu2.getAlergenos().add(Alergeno.LACTOSA);
        menu2.getAlergenos().add(Alergeno.FRUTOS_SECOS);

        menu3.getAlergenos().add(Alergeno.GLUTEN);
        menu3.getAlergenos().add(Alergeno.HUEVO);

        ComparadorMenus comparador = new ComparadorMenus(menu1, menu2, menu3);

        Map<Alergeno, List<Menu>> ordenados = comparador.tieneAlergenos();

        assertEquals(Arrays.asList(menu1, menu3), ordenados.get(Alergeno.GLUTEN));
        assertEquals(Arrays.asList(menu1, menu2), ordenados.get(Alergeno.LACTOSA));
        assertEquals(Arrays.asList(menu2), ordenados.get(Alergeno.FRUTOS_SECOS));
        assertEquals(Arrays.asList(menu3), ordenados.get(Alergeno.HUEVO));
    }
}
