/**
 * Apartado extraordinaria
 * Clase concreta que muestra y ordena los menus según un criterio
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.comparador;

import static src.informacion.ElementoNutricional.*;
import src.informacion.ElementoNutricional;
import src.informacion.Alergeno;
import src.menus.*;

import java.util.*;

public class ComparadorMenus {
	private List<Menu> menus = new ArrayList<>();

	/**
	 * Constructor del comparador, recibe un número arbitrario de menús a tener en cuenta
	 * para las comparaciones
	 * @param menus Array de menús que se van a comparar y ordenar
	 */
	public ComparadorMenus(Menu... menus) {
		Arrays.asList(menus).forEach(m -> this.menus.add(m));
	}

	/**
	 * Ordena los menús para cada elemento nutricional de menor a mayor según el valor 
	 * de la información nutricional del menú
	 * @return Mapa que enlaza cada elemento nutricional con su lista ordenada de menús
	 */
	public Map<ElementoNutricional, List<Menu>> menusElementoNutricional() {
		Map<ElementoNutricional, List<Menu>> ordenados = new LinkedHashMap<>();

		for (ElementoNutricional e : ElementoNutricional.values()) {
			List<Menu> aux = new ArrayList<>(this.menus);
			for (int i = 0; i < aux.size() - 1; i++) {
                for (int j = 0; j < aux.size() - 1 - i; j++) {
                    if (aux.get(j).getInfoNutricional().getInfoNutricional().get(e) > aux.get(j + 1).getInfoNutricional().getInfoNutricional().get(e)) {
                        Collections.swap(aux, j, j + 1);
                    }
                }
            }
			ordenados.put(e, aux);
		}

		return ordenados;
	}

	/**
	 * Ordena los menús según sus calorías de menor a mayor
	 * @return Lista con los menús ordenados
	 */
	public List<Menu> ordenaCalorias() {
		List<Menu> ordenados = new ArrayList<>(this.menus);

		for (int i = 0; i < ordenados.size() - 1; i++) {
			for (int j = 0; j < ordenados.size() - 1 - i; j++) {
				if (ordenados.get(j).getInfoNutricional().getInfoNutricional().get(CALORIAS) > ordenados.get(j + 1).getInfoNutricional().getInfoNutricional().get(CALORIAS)) {
					Collections.swap(ordenados, j, j + 1);
				}
			}
		}
	
		return ordenados;
	}

	/**
	 * Enlaza cada alérgeno con los menús que lo contienen
	 * @return Mapa que enlaza cada alérgeno con su lista de menús que lo contienen
	 */
	public Map<Alergeno, List<Menu>> tieneAlergenos() {
		Map<Alergeno, List<Menu>> ordenados = new LinkedHashMap<>();

		for (Alergeno a : Alergeno.values()) {
			List<Menu> aux = new ArrayList<>();
			for (Menu m : this.menus) {
				if (m.getAlergenos().contains(a)) {
					aux.add(m);
				}
			}
			ordenados.put(a, aux);
		}

		return ordenados;
	}
}
