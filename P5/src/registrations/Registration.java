/**
 * Clase que presenta un tipo de objeto que ObjectStateTracker puede gestionar
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package registrations;

import interfaces.*;

import java.util.*;

public class Registration {
	private String name, affiliation;
	private RegistrationKind kind;
	private int amountPayed;
	private boolean validated;
	protected static List<IObserver<Registration>> observers = new ArrayList<>();
	
	/**
	 * Constructor basico, asigna el nombre del registro y el tipo
	 * @param name Nombre del registro
	 * @param kind Tipo de registro
	 */
	public Registration(String name, RegistrationKind kind) {
		this.kind = kind;
		this.name = name;
	}
	
	/**
	 * Descuenta la deuda por pagar del registro 
	 * @param amount Cantidad a descontar de la deuda
	 */
	public void pay (double amount) {		
		this.amountPayed+=amount;
		this.notifyy();
	}

	/**
	 * Devuelve el dinero pagado
	 * @return El dinero que ya ha sido abonado
	 */
	public double getAmountPayed() {
		return this.amountPayed;
	}

	/**
	 * El total del importe del registro
	 * @return Dinero total del registro
	 */
	public double getTotalAmount() {
		return this.kind.getPrice();
	}

	/**
	 * Devuelve la afiliacion del registro
	 * @return La afiliacion
	 */
	public String getAffiliation() {
		return this.affiliation;
	}
	
	/**
	 * Setea la afiliacion del registro
	 * @param aff La afiliacion
	 */
	public void setAffiliation(String aff) {
		this.affiliation = aff;
		this.notifyy();
	}

	/**
	 * Devuelve si el registro está validado o no
	 * @return True si lo esta, false si no
	 */
	public boolean getValidated() {
		return this.validated;
	}
	
	/**
	 * Setea la validacion del registro a un valor
	 * @param b Booleano al que setear la validacion del registro
	 */
	public void setValidated(boolean b) {
		this.validated = b;
		this.notifyy();
	}

	/**
	 * Añade un observador al subject
	 * @param o Implementación de la interfaz IObserver
	 */
	public static void withTracker(IObserver<Registration> o) {
		observers.add(o);
	}

	/**
	 * Devuelve la lista de observadores
	 * @return Lista de observadores
	 */
	public List<IObserver<Registration>> getObservers() {
		return observers;
	}

	/**
	 * Método del patrón observer que notifica a los observadores
	 * de que se ha producido un cambio
	 */
	private void notifyy() {
        Iterator<IObserver<Registration>> it;
		it = observers.iterator();
		while (it.hasNext())
			it.next().update(this);
    }

	@Override
	public String toString() {
		return "Reg. of: "+this.name;
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
		if (!(obj instanceof Registration)) return false;
		Registration vn = (Registration) obj;
		return this.name.equals(vn.name);
    }

	/**
	 * Sobreescribe el hashcode por defecto al hashcode del nombre
	 * del registro
	 * @return el entero equivalente al hashcode
	 */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}