/**
 * Clase generica que permite definir, almacenar y gestionar estados
 * para cualquier objeto
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package states;

import java.util.function.Predicate;
import java.util.*;

import interfaces.*;

public class ObjectStateTracker<T, S extends Comparable<S>> implements IObserver<T>, Iterable<T> {
	private Map<T, List<Transition<S>>> transiciones = new HashMap<>();
	private Map<S, Predicate<T>> expresiones = new LinkedHashMap<>();
	private Map<S, Set<T>> estadosActuales = new LinkedHashMap<>();
	
	/**
	 * Constructor del tracker, recibe los estados válidos y reserva
	 * memoria para el mapa que los almacena
	 * @param estados Estados válidos y posibles
	 */
	public ObjectStateTracker(S[] estados) {
		Arrays.asList(estados).forEach(e -> {
			this.estadosActuales.put(e, new LinkedHashSet<T>());
		});
	}

	/**
	 * Añade una expresión lambda ligada a un estado, si la expresión evalúa true,
	 * el objeto está en el estado asignado
	 * @param estado Estado al que asignar la expresion
	 * @param expresion Expresión lambda que determina si un objeto está en ese estado
	 * @return this para el encadenamiento de invocaciones
	 */
	public ObjectStateTracker<T, S> withState(S estado, Predicate<T> expresion) {
		if (!this.estadosActuales.keySet().contains(estado)) {
			throw new IllegalStateException("El estado debe estar en los estados validos.");
		}
		this.expresiones.put(estado, expresion);	
		return this;
	}

	/**
	 * Añade un estado por defecto usado por si ninguna expresión evalúa true,
	 * el estado siempre evalúa true
	 * @param estado Estado por defecto si ninguna expresión es true
	 * @return this para el encadenamiento de invocaciones
	 */
	public ObjectStateTracker<T, S> elseState(S estado) {
		return withState(estado, p -> true);
	}

	/**
	 * Actualiza los estados comprobando todas las expresiones lambda
	 */
	public void updateStates() {
		Map<S, Set<T>> aux = new HashMap<>();
        for (Map.Entry<S, Set<T>> entry : this.estadosActuales.entrySet()) {
            aux.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }

		for (Map.Entry<S, Set<T>> entry : aux.entrySet()) {
			for (T t : entry.getValue()) {
				this.updateState(t);
			}
		}
	}

	/**
	 * Actualiza un objeto concreto comprobando cada expresión
	 * @param t objeto a actualizar
	 */
	public void updateState(T t) {
		S actual = null;
		for (Map.Entry<S, Set<T>> entry : this.estadosActuales.entrySet()) {
			if (entry.getValue().contains(t)) {
				actual = entry.getKey();
				break;
			}
		}
		for (Map.Entry<S, Predicate<T>> entry2 : this.expresiones.entrySet()) {
			if (entry2.getValue().test(t)) {
				this.estadosActuales.get(entry2.getKey()).add(t);
				if (!actual.equals(entry2.getKey())) {
					this.estadosActuales.get(actual).remove(t);
					this.transiciones.get(t).add(new Transition<S>(actual, entry2.getKey()));
				}
				break;
			}
		}
	}

	/**
	 * Añade objetos al sistema
	 * @param r Objetos que registrar en el sistema
	 */
	public void addObjects(T... r) {
		S inicial = this.estadosActuales.keySet().iterator().next();
		Arrays.asList(r).forEach(reg -> {
			if (this.estadosActuales.get(inicial).add(reg))
				this.transiciones.put(reg, new ArrayList<Transition<S>>(List.of(new Transition<>(inicial))));
		});
	}

	/**
	 * Devuelve la lista de transiciones de estados adjunta al objeto
	 * @param t el objeto del que se quiere obtener la trayectoria
	 * @return Lista de trayectorias del objeto
	 */
	public List<Transition<S>> trajectory(T t) {
		return this.transiciones.get(t);
	}

	@Override
	public String toString() {
		return this.estadosActuales.toString();
	}

	/**
	 * Devuelve un iterador de las claves de las trayectorias
	 * @return Iterator
	 */
	@Override
	public Iterator<T> iterator() {
		return this.transiciones.keySet().iterator();
	}

	/**
	 * Método del patrón observer que se ejecuta al recibir una notificación de un objeto observado
	 */
	@Override
	public void update(T t) {
		this.updateState(t);
	}
}