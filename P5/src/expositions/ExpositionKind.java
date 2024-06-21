/**
 * Nuevo tipo de exposiciones artísticas
 *  
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package expositions;

public enum ExpositionKind {
	AUDIOVISUAL (50), ESCULTURAS (20), PINTURA (80);

	private int capacidad;

	ExpositionKind(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public int capacidad() {
		return this.capacidad;
	}	
}