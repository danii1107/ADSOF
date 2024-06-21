/**
 * Almacena una transicion entre estados y su marca de tiempo
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package states;

import java.time.LocalDateTime;

public class Transition<S> {
	private LocalDateTime ts;
	private S destino;
	private S origen;

	/**
	 * Constructor que establece la marca de tiempo y el estado
	 * origen y destino de la transicion
	 * @param origen Estado origen de la transicion
	 * @param destino Estado destino de la transicion
	 */
	public Transition(S origen, S destino) {
		this.origen = origen;
		this.destino = destino;
		this.ts = LocalDateTime.now();
	}

	/**
	 * Constructor secundario que no tiene estado destino
	 * @param origen Estado origen de la transicion
	 */
	public Transition(S origen) {
		this(origen, null);
	}

	/**
	 * Devuelve el estado origen de la transicion
	 * @return El estado origen
	 */
	public S getOrigen() {
		return this.origen;
	}

	/**
	 * Devuelve el estado destino de la transicion
	 * @return El estado destino
	 */
	public S getDestino() {
		return this.destino;
	}

	@Override
	public String toString() {
		String base = this.destino != null ? "from: " + this.origen + " to " + this.destino : "in: " + this.origen;
		return "(" + base + " at: " + this.ts + ")"; 
	}
}
