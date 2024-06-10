/**
 * Clase concreta que gestiona la información nutricional de un menú
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.informacion;

import src.comida.Comida;

import java.util.Arrays;
import java.util.Map;

public class InfoNutricionalMenu extends InfoNutricional {
	
	/**
	 * Constructor que iniciliza el map de informacion nutricional del menu
	 */
	public InfoNutricionalMenu() {
		super();
	}

	/**
	 * Recalcula la info nutricional, se invoca cuando se instancia el menu con los platos
	 * @param comida Platos que componen el menu
	 */
	public void calcularInfoNutricional(Comida... comida) {
		Arrays.asList(comida).forEach(p -> {
			Map<ElementoNutricional, Double> infoComida = p.getInfoNutricional().getInfoNutricional();
			Map<ElementoNutricional, Double> infoMenu = super.getInfoNutricional();

			for (Map.Entry<ElementoNutricional, Double> entry : infoComida.entrySet()) {
				Double antiguoValor = infoMenu.get(entry.getKey());
				Double nuevoValor =  antiguoValor + entry.getValue();
				infoMenu.put(entry.getKey(), nuevoValor);
			}
		});
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
		return "INFORMACION NUTRICIONAL DEL MENU -> " + super.toString();
	}
}
