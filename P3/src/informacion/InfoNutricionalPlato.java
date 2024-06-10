/**
 * Clase concreta que calcula y almacena la información nutricional de un plato
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.informacion;

import java.util.Map;

import src.comida.*;

public class InfoNutricionalPlato extends InfoNutricional {
	
	/**
	 * Constructor de la info nutricional de un plato, inicializa el mapa
	 * de elementos nutricionales a 0.0
	 */
	public InfoNutricionalPlato() {
		super();
	}

	/**
	 * Recalcula la info nutricional, se invoca cada vez que se añade un componente al plato
	 * @param comida
	 * @param cantidad
	 */
	public void calcularInfoNutricional(Comida comida, Integer cantidad) {
		Map<ElementoNutricional, Double> infoComida = comida.getInfoNutricional().getInfoNutricional();
		Map<ElementoNutricional, Double> infoPlato = super.getInfoNutricional();
		Integer factor = comida.getInfoNutricional().getFactor();

		for (Map.Entry<ElementoNutricional, Double> entry : infoComida.entrySet()) {
			Double antiguoValor = infoPlato.get(entry.getKey());
			Double nuevoValor =  antiguoValor + entry.getValue() * cantidad / factor;
			infoPlato.put(entry.getKey(), nuevoValor);
		}
	}

	/**
	 * Implementación por defecto por herencia
	 * @return 1, para evitar división entre 0
	 */
	@Override
	public Integer getFactor() {
		return 1;
	}

	/**
	 * Implementación por defecto por herencia
	 * @return ""
	 */
	@Override
	public String getUnidadPeso() {
		return "";
	}

	@Override
	public String toString() {
		return "INFORMACION NUTRICIONAL DEL PLATO -> " + super.toString();
	}
}
