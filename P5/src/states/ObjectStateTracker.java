/**
 * ObjectSTateTracker
 * @author Daniel Birsan y Juan José Martínez Domínguez
 */
package states;

import java.util.function.Predicate;
import java.time.LocalDateTime;
import java.util.*;

import interfaces.*;

public class ObjectStateTracker<T, S> implements IObserver, Iterable<T> {
	private List<S> estados = new ArrayList<>();
	private Map<S, Predicate<T>> expresiones;
	private S elseState;
	private Map<S ,List<T>> estadosActuales;
	private Set<String> noRepes;
	private Trajectory<T, S> trayectoria;
	
	/**
	 * Constructor de la clase ObjectStateTracker
	 * @param estados Estados posibles
	 */
	public ObjectStateTracker(S[] estados) {
		this.estados = Arrays.asList(estados);
		this.expresiones = new LinkedHashMap<>();
		this.elseState = null;
		this.estadosActuales = new LinkedHashMap<>();
		for (S s : this.estados) {
			this.estadosActuales.put(s, new ArrayList<T>());
		}
		this.noRepes = new HashSet<>();
		this.trayectoria = new Trajectory<>(estados);
	}

	/**
	 * Añade una expresión ligada a un estado
	 * @param estado Estado
	 * @param expresion Expresión lambda que determina si un objeto está en ese estado
	 * @return ObjectStateTracker
	 */
	public ObjectStateTracker<T, S> withState(S estado, Predicate<T> expresion) {
		if (this.estados.size() != 0) {
			Boolean found = false;
			for (S s : this.estados) {
				if (s.equals(estado)) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new IllegalStateException("El estado debe estar en los estados validos.");
			}
		}
		this.expresiones.put(estado, expresion);	
		return this;
	}

	/**
	 * Añade un estado por defecto
	 * @param estado Estado por defecto si ninguna expresión es true
	 * @return ObjectStateTracker
	 */
	public ObjectStateTracker<T, S> elseState(S estado) {
		if (this.estados.size() != 0) {
			Boolean found = false;
			for (S s : this.estados) {
				if (s.equals(estado)) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new IllegalStateException("El estado debe estar en los estados validos.");
			}
		}
		this.elseState = estado;
		return this;
	}

	/**
	 * Actualiza los estados comprobando todas las expresiones lambda
	 */
	public void updateStates() {
		Map<S, List<T>> nuevosEstadosActuales = new LinkedHashMap<>();
		for (S s : this.estados) {
			nuevosEstadosActuales.put(s, new ArrayList<T>());
		}
		for (Map.Entry<S, List<T>> entry : this.estadosActuales.entrySet()) {
			S estadoAntiguo = entry.getKey();
			int i = 0;
			List<T> rLista = entry.getValue();
			for (T r : rLista) {				
				S estado = null;
				for (Map.Entry<S, Predicate<T>> entry2 : this.expresiones.entrySet()) {
					if (entry2.getValue().test(r)) {
						estado = entry2.getKey();
						break;
					}
				}
				if (estado == null)
					estado = this.elseState;
				nuevosEstadosActuales.get(estado).add(r);
				if (!estadoAntiguo.equals(estado)) {
					this.trayectoria.getTransiciones().get(r).add("(from: " + estadoAntiguo + " to " + estado + " at: " + LocalDateTime.now() + ")");
					if (i == rLista.size() - 1) {
						this.trayectoria.ffinal(estado);
					}
				}
			}
		}
		this.estadosActuales = nuevosEstadosActuales;
	}

	/**
	 * Añade objetos al sistema
	 * @param r Objetos que registrar en el sistema
	 */
	public void addObjects(T... r) {
		for (T reg : r) {
			if (this.noRepes.contains(reg.toString())) {
				continue;
			} else {
				List<T> antiguosIniciales = this.estadosActuales.get(this.estados.get(0)); 
				antiguosIniciales.add(reg);
				this.estadosActuales.put(this.estados.get(0), antiguosIniciales);
				this.trayectoria.addObjects(reg);
				this.trayectoria.getTransiciones().get(reg).add("(in: " + this.estados.get(0) + " at: " + LocalDateTime.now() + ")");
				this.trayectoria.inicial(this.estados.get(0));
				this.noRepes.add(reg.toString());
			}
		}
	}

	/**
	 * Devuelve la trayectoria de un objeto
	 * @param r Objeto
	 * @return Trayectoria del objeto
	 */
	public Trajectory<T, S> trajectory(T r) {
		return this.trayectoria.auxToString(r);
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
		return this.trayectoria.getTransiciones().keySet().iterator();
	}

	/**
	 * Método del patrón observer que se ejecuta al recibir una notificación de un objeto observado
	 */
	@Override
	public void update() {
		Map<S, List<T>> nuevosEstadosActuales = new LinkedHashMap<>();
		for (S s : this.estados) {
			nuevosEstadosActuales.put(s, new ArrayList<T>());
		}
		for (Map.Entry<S, List<T>> entry : this.estadosActuales.entrySet()) {
			S estadoAntiguo = entry.getKey();
			int i = 0;
			List<T> rLista = entry.getValue();
			for (T r : rLista) {				
				S estado = null;
				for (Map.Entry<S, Predicate<T>> entry2 : this.expresiones.entrySet()) {
					if (entry2.getValue().test(r)) {
						estado = entry2.getKey();
						break;
					}
				}
				if (estado == null)
					estado = this.elseState;
				nuevosEstadosActuales.get(estado).add(r);
				if (!estadoAntiguo.equals(estado)) {
					this.trayectoria.getTransiciones().get(r).add("(from: " + estadoAntiguo + " to " + estado + " at: " + LocalDateTime.now() + ")");
					if (i == rLista.size() - 1) {
						this.trayectoria.ffinal(estado);
					}
				}
			}
		}
		this.estadosActuales = nuevosEstadosActuales;
	}
}