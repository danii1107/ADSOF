/**
 * Apartado Extraordinaria
 * Clase generica que representa un analizador de trayectorias de estados.
 * Se utiliza para comprobar si dada una transición es válida o no.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package states;

import java.util.function.BiPredicate;
import java.util.*;

public class Analyzer<S extends Comparable<S>> {
	private Map<S, Map<S, BiPredicate<List<Transition<S>>, S>>> expresiones;
	private List<Transition<S>> invalidadas;

	/**
	 * Constructor básico, reserva memoria para el mapa de expresiones y parejas de estados
	 */
	public Analyzer() {
		this.expresiones = new LinkedHashMap<>();
		this.invalidadas = new ArrayList<>();
	}

	/**
	 * Devuelve el mapa de expresiones
	 * @return Mapa de expresiones
	 */
	public Map<S, Map<S, BiPredicate<List<Transition<S>>, S>>> getExpresiones() {
		return this.expresiones;
	}

	/**
	 * Añade una expresion al analizador
	 * @param expresion Expresión que valida la pareja de estados o no
	 * @param origen Estado origen de la pareja de estados
	 * @param destino Estado destino de la pareja de estados
	 * @return La propia instancia para encadenar invocaciones
	 */
	public Analyzer<S> addExpresion(BiPredicate<List<Transition<S>>, S> expresion, S origen, S destino) {
        if (!this.expresiones.containsKey(origen)) {
			this.expresiones.put(origen, new HashMap<>());
		}
		this.expresiones.get(origen).put(destino, expresion);
		return this;
	}

	/**
	 * EValúa las expresiones de los estados de la trayectoria recibida
	 * @param trayectoria Trayectoria a analizar
	 * @return la lista de transacciones invalidadas
	 */
	public List<Transition<S>> analyze(List<Transition<S>> trayectoria) {
        List<Transition<S>> invalidas = new ArrayList<>();
        for (int i = 1; i < trayectoria.size(); i++) {
            if (this.expresiones.containsKey(trayectoria.get(i).getOrigen()) && this.expresiones.get(trayectoria.get(i).getOrigen()).containsKey(trayectoria.get(i).getDestino())) {
                BiPredicate<List<Transition<S>>, S> aux = this.expresiones.get(trayectoria.get(i).getOrigen()).get(trayectoria.get(i).getDestino());
                if (!aux.test(trayectoria, trayectoria.get(i).getDestino())) {
                    invalidas.add(new Transition<S>(trayectoria.get(i).getOrigen(), trayectoria.get(i).getDestino()));
                }
            }
        }
		this.invalidadas.addAll(invalidas);
        return invalidas;
    }

	@Override
	public String toString() {
		return "Transiciones invalidadas: " + this.invalidadas.toString();
	}
}
