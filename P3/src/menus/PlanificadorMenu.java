/**
 * Clase concreta que gestiona y planifica los menús
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.menus;

import static src.informacion.ElementoNutricional.*;
import src.informacion.*;
import src.comida.*;

import java.util.*;

public class PlanificadorMenu {
    private Map<ElementoNutricional, Double> maximos;
	private Set<Alergeno> alergenosExcluidos;
	private List<Plato> platos;

	/**
	 * Constructor que reserva memoria para las edd e inicializa la lista 
	 * de platos a considerar
	 * @param platos Platos disponibles para la planificación
	 */
    public PlanificadorMenu(List<Plato> platos) {
		this.maximos = new HashMap<>();
        this.alergenosExcluidos = new HashSet<>();
		this.platos = new ArrayList<>(platos);
	}

	/**
	 * Planifica según las limitaciones el menú que se puede preparar
	 * @param caloriasMin Mínimo de calorias del menú
	 * @param caloriasMax Máximo de calorías del menú
	 * @return El menú que se ha encontrado según la planificación
	 */
    public Menu planificar(double caloriasMin, double caloriasMax) {
		List<Plato> platos = new ArrayList<>();
		Double calorias = 0.0;

		for (Plato p : this.platos) {	
			if (p.getInfoNutricional().getInfoNutricional().get(CALORIAS) + calorias <= caloriasMax) {
				if (!this.tieneAlergenosExluidos(p.getAlergenos())) {
					if (cumpleMaximos(p)) {
						calorias += p.getInfoNutricional().getInfoNutricional().get(CALORIAS);
						platos.add(p);
					}
				}
			}
			if (calorias >= caloriasMin) break;
		}

		if (calorias < caloriasMin) return null;

		return new Menu(platos.toArray(new Plato[0]));
    }

	/**
	 * Establece un límite para un elemento nutricional
	 * @param elemento Elemento que limitar
	 * @param max Valor máximo de dicho elemento
	 * @return La propia instancia para encadenar invocaciones
	 */
    public PlanificadorMenu conMaximo(ElementoNutricional elemento, Double max) {
        this.maximos.put(elemento, max);
        return this;
    }

	/**
	 * Establece los alérgenos que no puede haber en el menú
	 * 
	 * 
	 * @param alergenos Alérgenos que deben excluirse
	 * @return La propia instancia para encadenar invocaciones
	 */
    public PlanificadorMenu sinAlergenos(Alergeno... alergenos) {
        Arrays.asList(alergenos).forEach(a -> this.alergenosExcluidos.add(a));
        return this;
    }

	/**
	 * Método privado para verificar si un plato cumple con los máximos establecidos
	 * @param plato Plato a verificar
	 * @return True si cumple, false si no
	 */
	private Boolean cumpleMaximos(Plato plato) {
		Boolean flag = true;
		Map<ElementoNutricional, Double> infoPlato = plato.getInfoNutricional().getInfoNutricional();
		for (Map.Entry<ElementoNutricional, Double> entry : this.maximos.entrySet()) {
			if (entry.getValue() < infoPlato.get(entry.getKey()))
				flag = false;
		}
		return flag;
	}

	/**
	 * Método privado para verificar si el plato tiene alérgenos que deben
	 * estar excluidos del menú
	 * @param alergenosPlato Conjunto de alérgenos del plato
	 * @return True si los contiene, false si no
	 */
	private Boolean tieneAlergenosExluidos(Set<Alergeno> alergenosPlato) {
		Boolean flag = false;
		for (Alergeno a : this.alergenosExcluidos) {
			if (alergenosPlato.contains(a))
				flag = true;
		}
		return flag;
	}
}