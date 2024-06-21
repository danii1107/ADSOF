/**
 * Nuevo caso de estudio, representa el seguimiento de exposiciones artísticas
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package expositions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import interfaces.IObserver;

public class Exposition {
	private String nombre;
	private ExpositionKind tipo;
	private int aforoDisponible;
	private boolean lleno = false;
	protected static List<IObserver<Exposition>> observers = new ArrayList<>();
	
	/**
	 * Constructor basico, asigna el nombre de la exposicion y el tipo
	 * @param nombre Nombre de la expo
	 * @param tipo Tipo de expo
	 */
	public Exposition(String nombre, ExpositionKind tipo) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.aforoDisponible = tipo.capacidad();
	}
	
	/**
	 * Reduce el aforo disponible
	 * @param numero numero de personas que entran a la exposicion
	 */
	public void entraGente (int numero) {		
		this.aforoDisponible-=numero;
		this.notifyy();
	}

	/**
	 * Devuelve el aforo maximo de la expo
	 * @return El aforo maximo
	 */
	public int getAforoMax() {
		return this.tipo.capacidad();
	}

	/**
	 * Devuelve el aforo disponible
	 * @return El aforo disponible
	 */
	public int getAforoDispo() {
		return this.aforoDisponible;
	}

	/**
	 * Devuelve si la expo está llena o no
	 * @return True si lo esta, false si no
	 */
	public boolean getLleno() {
		return this.lleno;
	}
	
	/**
	 * Setea si la expo esta llena o no
	 * @param b Booleano al que setear la flag
	 */
	public void setLleno(boolean b) {
		this.lleno = b;
		this.notifyy();
	}

	/**
	 * Añade un observador al subject
	 * @param o Implementación de la interfaz IObserver
	 */
	public static void withTracker(IObserver<Exposition> o) {
		observers.add(o);
	}

	/**
	 * Devuelve la lista de observadores
	 * @return Lista de observadores
	 */
	public List<IObserver<Exposition>> getObservers() {
		return observers;
	}

	/**
	 * Método del patrón observer que notifica a los observadores
	 * de que se ha producido un cambio
	 */
	private void notifyy() {
        Iterator<IObserver<Exposition>> it;
		it = observers.iterator();
		while (it.hasNext())
			it.next().update(this);
    }

	@Override
	public String toString() {
		return "Exposición: "+this.nombre;
	}

	/**
	 * Sobreescribe el método equals por defecto para hacer que un
	 * registro sea igual a otro si tiene el mismo nombre
	 * @param obj objeto al que comparar esta instancia
	 * @return booleano que indica si son iguales o no
	 */
	@Override
    public boolean equals(Object obj) {
        if (obj==this) return true;
		if (!(obj instanceof Exposition)) return false;
		Exposition vn = (Exposition) obj;
		return this.nombre.equals(vn.nombre);
    }

	/**
	 * Sobreescribe el hashcode por defecto al hashcode del nombre
	 * del registro
	 * @return el entero equivalente al hashcode
	 */
    @Override
    public int hashCode() {
        return this.nombre.hashCode();
    }
}
