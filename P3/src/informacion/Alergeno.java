/**
 * Enumeracion que representa un alergeno de un ingrediente
 *
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 *
 */

package src.informacion;

public enum Alergeno {
	GLUTEN,
	LACTOSA,
	FRUTOS_SECOS,
	HUEVO;

	@Override
	public String toString() {
		return name().toLowerCase().replace("_", " ");
	}
}