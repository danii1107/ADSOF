/**
 * Tester que hereda de comparadortester para crear los menus e invoca los métodos
 * de ComparadorMenus e imprime los retornos para comprobar el funcionamiento de la clase
 * con 6 menus
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.testers;

import java.util.ArrayList;
import java.util.List;

import src.informacion.InfoNutricionalUnidad;
import src.informacion.ElementoNutricional;
import src.comparador.ComparadorMenus;
import src.comida.TipoIngrediente;
import src.informacion.Alergeno;
import src.comida.Ingrediente;
import src.comida.Plato;

import src.menus.*;

public class ComparadorTester2 extends ComparadorTester {
	/**
	 * Metodo auxiliar privado que instancia nuevos menús a comparar
	 * @return la lista de los menús creados
	 */
	private List<Menu> crearMenusNuevos() {
		Plato plato1 = new Plato("Pollo con arroz");
		Ingrediente pollo1 = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		plato1.add(pollo1, 1);
		plato1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CALORIAS, 500.0);
        plato1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_TOTAL, 10.0);
        plato1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.PROTEINA, 20.0);
		plato1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CARBOHIDRATOS, 15.0);
		plato1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_SATURADA, 10.0);
		plato1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.AZUCARES, 10.0);
		plato1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.FIBRA, 50.0);
		plato1.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.SODIO, 15.0);
		plato1.addAlergenos(List.of(Alergeno.FRUTOS_SECOS, Alergeno.GLUTEN));

		Plato plato2 = new Plato("Pollo con arroz");
		Ingrediente pollo2 = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		plato2.add(pollo2, 1);
		plato2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CALORIAS, 300.0);
        plato2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_TOTAL, 5.0);
        plato2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.PROTEINA, 15.0);
		plato2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CARBOHIDRATOS, 25.0);
		plato2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_SATURADA, 5.0);
		plato2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.AZUCARES, 15.0);
		plato2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.FIBRA, 100.0);
		plato2.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.SODIO, 100.0);
		plato2.addAlergenos(List.of(Alergeno.FRUTOS_SECOS, Alergeno.HUEVO));

		Plato plato3 = new Plato("Pollo con arroz");
		Ingrediente pollo3 = new Ingrediente("Pollo", TipoIngrediente.CARNE, new InfoNutricionalUnidad(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		plato3.add(pollo3, 1);
		plato3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CALORIAS, 400.0);
        plato3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_TOTAL, 8.0);
        plato3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.PROTEINA, 22.0);
		plato3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.CARBOHIDRATOS, 20.0);
		plato3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.GRASA_SATURADA, 15.0);
		plato3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.AZUCARES, 20.0);
		plato3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.FIBRA, 25.0);
		plato3.getInfoNutricional().getInfoNutricional().put(ElementoNutricional.SODIO, 5.0);
		plato3.addAlergenos(List.of(Alergeno.FRUTOS_SECOS));

		Menu menu1 = new Menu(plato1);
        Menu menu2 = new Menu(plato2);
        Menu menu3 = new Menu(plato3);
		
		return List.of(menu1, menu2, menu3);
	}
	public static void main(String[] args) {
		ComparadorTester2 tester = new ComparadorTester2();
		List<Menu> menus = new ArrayList<>();
		menus.addAll(tester.crearMenusNuevos());
		menus.addAll(tester.crearMenus());

		ComparadorMenus comparador = new ComparadorMenus(menus.toArray(new Menu[0]));

		tester.ejecutar(comparador);
	}
}