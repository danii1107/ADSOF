/**
 * Clase concreta que extiende InfoNutricional, almacena la información nutricional de
 * un ingrediente por cada unidad
 *
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 *
 */

package src.informacion;

import src.comida.Comida;

public class InfoNutricionalUnidad extends InfoNutricional {
	private static final Integer factor = 1; 

	/**
     * Instancia una información nutricional por unidad y la clase padre rellena
	 * el mapa de elementos nutricionales con sus respectivas cantidades
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
	public InfoNutricionalUnidad(double calorias, double carbohidratos, double grasasTotales, double grasasSaturadas, double proteinas, double azucares, double fibra, double sodio) {
		super(calorias, carbohidratos, grasasTotales, grasasSaturadas, proteinas, azucares, fibra, sodio);
	}
	
	/**
	 * Devueve el entero por el que se usa para calcular la equivalencia de valores
	 * nutricionales por unidad
	 * 
	 * @return factor necesario para calcular la información nutricional
	 */
	@Override
    public Integer getFactor() {
        return factor;
    }

	/**
	 * Implementación por defecto por herencia
	 * @param comida Comida
	 */
	@Override
	public void calcularInfoNutricional(Comida... comida) {
		return;
	}

	/**
	 * Implementación por defecto por herencia
	 * @param c Comida
	 * @param cantidad Cantidad de la comida
	 */
	@Override
	public void calcularInfoNutricional(Comida c, Integer cantidad) {
		return;
	}

	/**
	 * Implementación para el formato correcto de la cadena
	 * @return INGREDIENTE_UNIDAD
	 */
	@Override
	public String getUnidadPeso() {
		return "INGREDIENTE_UNIDAD";
	}

	@Override
	public String toString() {
		return "INFORMACION NUTRICIONAL POR UNIDAD -> " + super.toString();
	}
}