/**
 * Trajectory
 * @author Daniel Birsan y Juan José Martínez Domínguez
 */
package states;

import java.util.*;

public class Trajectory<T, S> {
	private Map<T, List<String>> transiciones;
	private Map<S, List<S>> frecuencias = new LinkedHashMap<>();
	private Map<S, Integer> fIniciales = new LinkedHashMap<>();
	private Map<S, Integer> fFinales = new LinkedHashMap<>();
	private T reg = null;
	
	/**
	 * Constructor de la clase Trajectory
	 * @param estados Estados validos
	 */
	public Trajectory(S[] estados) {
		this.transiciones = new LinkedHashMap<>();
		for (S s : estados) {
			this.frecuencias.put(s, new ArrayList<S>());
			this.fIniciales.put(s, 0);
			this.fFinales.put(s, 0);
		}
	}

	/**
	 * Añade una transición
	 * @param r Transición
	 */
	public void addObjects(T r) {
		this.transiciones.put(r, new ArrayList<String>());
	}

	/**
	 * Suma le contador de la frecuencia inicial de un estado
	 * @param s
	 */
	public void inicial(S s) {
		this.fIniciales.put(s, this.fIniciales.get(s) + 1);
	}

	/**
	 * Suma le contador de la frecuencia final de un estado
	 * @param s
	 */
	public void ffinal(S s) {
		this.fFinales.put(s, this.fFinales.get(s) + 1);
	}

	public Map<T, List<String>> getTransiciones() {
		return this.transiciones;
	}

	public Map<S, List<S>> getFrecuencias() {
		return this.frecuencias;
	}

	public Map<S, Integer> getFIniciales() {
		return this.fIniciales;
	}

	public Map<S, Integer> getFFinales() {
		return this.fFinales;
	}

	/**
	 * Establece un registro para ser printeado mas tarde
	 * @param r Objeto a printear
	 * @return
	 */
	public Trajectory<T, S> auxToString(T r) {
		this.reg = r;
		return this;
	}

	@Override
	public String toString() {
		return this.transiciones.get(this.reg).toString();
	}
}