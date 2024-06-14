/**
 * Clase abstracta que representa la información nutricional de un ingrediente
 *
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 *
 */

package src.informacion;

import static src.informacion.ElementoNutricional.*;
import src.comida.Comida;

import java.util.*;

public abstract class InfoNutricional {
	private Map<ElementoNutricional, Double> infoNutricional = new LinkedHashMap<>();

	/**
     * Rellena el mapa de elementos nutricionales con sus respectivas cantidades
     *
     * @param calorias          Valor energético
     * @param carbohidratos     Carbohidratos
     * @param grasasTotales     Grasas
     * @param grasasSaturadas   Grasas saturadas
     * @param proteinas         Proteina
     * @param azucares          Azucares
     * @param fibra             Fibra
     * @param sodio             Sodio
     */
	public InfoNutricional(double calorias, double carbohidratos, double grasasTotales, double grasasSaturadas, double proteinas, double azucares, double fibra, double sodio) {
		this.infoNutricional.put(CALORIAS, calorias);
		this.infoNutricional.put(CARBOHIDRATOS, carbohidratos);
		this.infoNutricional.put(GRASA_TOTAL, grasasTotales);
		this.infoNutricional.put(GRASA_SATURADA, grasasSaturadas);
		this.infoNutricional.put(PROTEINA, proteinas);
		this.infoNutricional.put(AZUCARES, azucares);
		this.infoNutricional.put(FIBRA, fibra);
		this.infoNutricional.put(SODIO, sodio);
	}

	/**
	 * Inicializa el mapa de elementos nutricionales a 0.0
	 */
	public InfoNutricional() {
		this(0.0, 0.0, 0.0, 0.0, 0.0 ,0.0 , 0.0, 0.0);
	}

	/**
	 * Devueve el entero por el que se usa para calcular la equivalencia de valores
	 * nutricionales ya sea por peso o por unidad
	 * 
	 * @return factor necesario para calcular la información nutricional
	 */
	public abstract Integer getFactor();
	
	/**
	 * Devueve el formato correcto para cada objeto
	 * 
	 * @return INGREDIENTE_PESO O INGREDIENTE_UNIDAD
	 */
	public abstract String getUnidadPeso();

	/**
	 * Recalcula la información nutricional de un plato
	 * 
	 * @param c        ingrediente del que se quiere calcular la información
	 * @param cantidad cantidad de ingrediente
	 */
	public abstract void calcularInfoNutricional(Comida c, Integer cantidad);

	/**
	 * Recalcula la información nutricional de un menú
	 * 
	 * @param comida platos que componen el menú
	 */
	public abstract void calcularInfoNutricional(Comida... comida);

	/**
	 * Devueve la información almacenada
	 * 
	 * @return mapa de valores nutricionales con sus valores
	 */
	public Map<ElementoNutricional, Double> getInfoNutricional() {
		return this.infoNutricional;
	}

	@Override
	public String toString() {
		StringBuilder info = new StringBuilder();
		for (ElementoNutricional elem : ElementoNutricional.values()) {
			info.append(elem.toString() + ": " + String.format(Locale.US, "%.2f", this.infoNutricional.get(elem)) + " " + elem.getUnidad() + ", ");
		}
		return info.toString().substring(0, info.length() - 2) + ".";
	}
}