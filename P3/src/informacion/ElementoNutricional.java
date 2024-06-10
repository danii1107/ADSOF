/**
 * Enumeracion que representa un elemento nutricional de un ingrediente
 *
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 *
 */
package src.informacion;

public enum ElementoNutricional {
	CALORIAS,
	CARBOHIDRATOS,
	GRASA_TOTAL,
	GRASA_SATURADA,
	PROTEINA,
	AZUCARES,
	FIBRA,
	SODIO;

	/**
	 * Devuelve la unidad de medida de cada elemento nutricional
	 * @return Cadena con la unidad de medida
	 */
	public String getUnidad() {
		if (this == CALORIAS) {
			return "kcal";
		} else if (this == SODIO) {
			return "mg";
		} else {
			return "g";
		}
	}

	@Override
	public String toString() {
		String nombre = name().replace("CALORIAS", "VALOR ENERGETICO")
				.replace("CARBOHIDRATOS", "HIDRATOS DE CARBONO")
				.replace("GRASA_TOTAL", "GRASAS")
				.replace("GRASA_SATURADA", "SATURADAS")
				.replace("PROTEINA", "PROTEINAS");
		return nombre.charAt(0) + nombre.substring(1).toLowerCase();
	}
}
