/**
 * Clase que maneja las frecuencias de las transiciones entre
 * estados
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package states;

import testers.AbstractTesterAnalyzer;

import java.util.*;

public class Process<S extends Comparable<S>> extends AbstractTesterAnalyzer<S> {
	private Analyzer<S> analizador;
	private Map<S, Map<S, Integer>> frecuencias = new LinkedHashMap<>();
	private Map<S, Integer> fIniciales = new HashMap<>();
	private Map<S, Integer> fFinales = new HashMap<>();

	/**
	 * Constructor, recibe un numero arbitrario de estados válidos y
	 * reserva e inicializa la memoria necesaria para los mapas 
	 * @param estados Estados validos
	 */
	public Process(S... estados) {
		Arrays.asList(estados).forEach(e -> {
			this.frecuencias.put(e, new LinkedHashMap<S, Integer>());
			this.fIniciales.put(e, 0);
			this.fFinales.put(e, 0);
		});
		this.analizador = new Analyzer<>();
		this.analizador = this.creaAnalizador();
	}

	/**
	 * Añade las trayectorias a procesar al sistema
	 * @param trayectorias Lista de trayectorias a añadir
	 */
	public void add(List<Transition<S>> trayectorias) {
		for (int i = 0; i < trayectorias.size(); i++) {
			Map<S, Integer> aux = frecuencias.get(trayectorias.get(i).getOrigen());
			aux.put(trayectorias.get(i).getDestino(), aux.getOrDefault(trayectorias.get(i).getDestino(), 0) + 1);
		}
		try {
			this.fIniciales.put(trayectorias.get(0).getOrigen(), this.fIniciales.get(trayectorias.get(0).getOrigen()) + 1);
			this.fFinales.put(trayectorias.get(trayectorias.size() - 1).getDestino(), this.fFinales.get(trayectorias.get(trayectorias.size() - 1).getDestino()) + 1);
		} catch (NullPointerException e) {}
		List<Transition<S>> invalidas = this.analizador.analyze(trayectorias);
		System.out.println(this.analizador);
		for (int i = 0; i < invalidas.size(); i++) {
			int c = 0; 
			for (int j = 0; j < invalidas.size(); j++) {
				if (invalidas.get(j).getOrigen().equals(invalidas.get(i).getOrigen()))
					c++;
			}
			if (c > 0)
				System.out.println(invalidas.get(i).getOrigen() + " numero transiciones invalidas: " + c);
		}
	}

	@Override
	public String toString() {
		StringBuilder aux = new StringBuilder();
		for (S s : this.frecuencias.keySet()) {
			aux.append(s)
				.append(" (initial ")
				.append(this.fIniciales.get(s))
				.append(" times, final ")
				.append(this.fFinales.get(s))
				.append(" times):\n");
			for (Map.Entry<S, Integer> entry : this.frecuencias.get(s).entrySet()) {
				if (entry.getKey() != null && entry.getValue() > 0) {
					aux.append("   to state ")
						.append(entry.getKey())
						.append(": ")
						.append(entry.getValue())
						.append(" times\n");
				}
			}
		}
		return aux.toString();
	}
}
