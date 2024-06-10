/**
 * Test3
 * @author Daniel Birsan y Juan José Martínez Domínguez
 */
package mytests;

import static registrations.RegistrationKind.*;
import static registrations.RegistrationState.*;

import java.util.*;

import states.*;
import registrations.*;

public class Test3 {
	protected ObjectStateTracker<Registration, RegistrationState> regState;
    protected Registration annSmith, johnDoe, lisaMartin;
    public static void main(String[] args) {
        Test3 tsc = new Test3();
        tsc.createRegistrations();
        tsc.changeRegistrations();
        tsc.changeRegistrations2();
        tsc.changeRegistrations3();
        tsc.displayTrajectories();
    }

    /**
     * Cambia el estado de las inscripciones
     */
    protected void changeRegistrations() {
        this.annSmith.setAffiliation("University of Miskatonic"); // now it is filled
        this.johnDoe.pay(STUDENT.getPrice()); // becomes payed
        this.johnDoe.setValidated(true);
        this.lisaMartin.setAffiliation("Arkham College");
        this.regState.updateStates();
    }

    /**
     * Cambia el estado de las inscripciones
     */
    protected void changeRegistrations2() {
        this.annSmith.pay(FULL.getPrice());
        this.johnDoe.pay(STUDENT.getPrice());
        this.regState.updateStates();
    }

    /**
     * Cambia el estado de las inscripciones
     */
    protected void changeRegistrations3() {
        this.annSmith.setValidated(true);
        this.regState.updateStates();
    }

    /**
     * Muestra las trayectorias de las inscripciones
     */
    protected void displayTrajectories() {
		for (Registration r : List.of(annSmith, johnDoe, lisaMartin))
			System.out.println(r+": "+this.regState.trajectory(r));
	}

    /**
     * Crea las inscripciones
     */
	protected void createRegistrations() {
		this.regState = new ObjectStateTracker<>(RegistrationState.values());
		regState.withState(PAYED, r -> r.getAmountPayed()==r.getTotalAmount() && !r.getValidated())
							.withState(STARTED, r -> r.getAffiliation()==null && !r.getValidated())
							.withState(FILLED, r -> r.getAffiliation()!=null && !r.getValidated())
							.withState(VALIDATED, r -> r.getAmountPayed()==0 && r.getValidated())
							.withState(FINISHED, r -> r.getAmountPayed()==r.getTotalAmount() && r.getValidated())
							.elseState(REJECTED);
		this.annSmith = new Registration("Ann Smith", FULL);
		this.johnDoe = new Registration("John Doe", STUDENT);
		this.lisaMartin = new Registration("Lisa Martin", MEMBER);
		this.regState.addObjects(annSmith, johnDoe, lisaMartin);
	}
}
