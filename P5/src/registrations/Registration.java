/**
 * Registration
 * @author Daniel Birsan, Juan José Martínez Domínguez y EPS
 */
package registrations;

import java.util.*;

import interfaces.*;

public class Registration {
	private String name, affiliation;
	private RegistrationKind kind;
	private int amountPayed;
	private boolean validated;
	protected static List<IObserver> observers = new ArrayList<>();
	
	/**
	 * Constructor de la clase Registration
	 * @param name Nombre de la inscripción
	 * @param kind Tipo de inscripción
	 */
	public Registration(String name, RegistrationKind kind) {
		this.kind = kind;
		this.name = name;
	}
	
	/**
	 * Paga una cantidad de dinero
	 * @param amount Cantidad a pagar
	 */
	public void pay (double amount) {		
		this.amountPayed+=amount;
		this.notifyy();
	}

	public double getAmountPayed() {
		return this.amountPayed;
	}

	public double getTotalAmount() {
		return this.kind.getPrice();
	}

	public String getAffiliation() {
		return this.affiliation;
	}
	
	public void setAffiliation(String aff) {
		this.affiliation = aff;
		this.notifyy();
	}

	public boolean getValidated() {
		return this.validated;
	}
	
	public String toString() {
		return "Reg. of: "+this.name;
	}

	public void setValidated(boolean b) {
		this.validated = b;
		this.notifyy();
	}

	/**
	 * Añade un observador
	 * @param o Implementación de la interfaz IObserver
	 */
	public static void withTracker(IObserver o) {
		observers.add(o);
	}

	/**
	 * Método del patrón observer que notifica a los observadores
	 * de que se ha producido un cambio
	 */
	private void notifyy() {
        Iterator<IObserver> it;
		it = observers.iterator();
		while (it.hasNext())
			it.next().update();
    }
}