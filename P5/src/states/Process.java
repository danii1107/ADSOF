/**
 * Process
 * @author Daniel Birsan y Juan José Martínez Domínguez
 */
package states;
import java.util.*;

public class Process<S> {
	private List<S> estados = new ArrayList<>();
	private Map<S, List<S>> frecuencias = new LinkedHashMap<>();
	private Map<S, Integer> fIniciales = new LinkedHashMap<>();
	private Map<S, Integer> fFinales = new LinkedHashMap<>();

	/**
	 * Constructor de la clase Process
	 * @param estados Estados validos
	 */
	public Process(S[] estados) {
		this.estados = Arrays.asList(estados);
	}

	/**
	 * Añade una trayectoria
	 * @param trayectorias Trayectorias a añadir
	 */
	public <T> void add(Trajectory<T, S> trayectorias) {
		this.frecuencias = trayectorias.getFrecuencias();
		this.fIniciales = trayectorias.getFIniciales();
		this.fFinales = trayectorias.getFFinales();
	}

	@Override
	public String toString() {
		String aux = "";
		for (S s : this.estados) {
			aux += s + " (initial " + this.fIniciales.get(s) + " times, final " + this.fFinales.get(s) + " times):\n";
			for (S s2 : this.frecuencias.get(s)) {
				aux += "\tto state " + s2 + ": " + Collections.frequency(this.frecuencias.get(s), s2) + " times\n";
			}
		}
		return aux;
	}
}
