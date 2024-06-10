/**
 * Test1
 * @author Daniel Birsan y Juan José Martínez Domínguez
 */
package mytests;

import static registrations.RegistrationKind.*;
import static registrations.RegistrationState.*;

import states.*;
import registrations.*;

public class Test1 {
    protected ObjectStateTracker<Registration, RegistrationState> regState;
    protected Registration annSmith, johnDoe, lisaMartin, rejected;

    public static void main(String[] args) {
        Test1 tsc = new Test1();
        tsc.createRegistrations();
        System.out.println(tsc.regState);
        tsc.changeRegistrations();
        System.out.println(tsc.regState);
        tsc.changeRegistrations2();
        System.out.println(tsc.regState);
        tsc.changeRegistrations3();
        System.out.println(tsc.regState);
    }

    /**
     * Cambia el estado de las inscripciones
     */
    protected void changeRegistrations() {
        this.annSmith.setAffiliation("University of Miskatonic"); // now it is filled
        this.johnDoe.pay(STUDENT.getPrice()); // becomes payed
		this.regState.updateStates();
    }
    
    /**
     * Cambia el estado de las inscripciones
     */
    protected void changeRegistrations2() {
        this.lisaMartin.pay(MEMBER.getPrice());
        this.johnDoe.setValidated(true);
		this.regState.updateStates();
    }

    /**
     * Cambia el estado de las inscripciones
     */
    protected void changeRegistrations3() {
        this.lisaMartin.setValidated(true);
		this.rejected.setAffiliation("University of Miskatonic"); // now it is filled
		this.rejected.pay(FULL.getPrice() - 1); // becomes payed
		this.rejected.setValidated(true);
		this.regState.updateStates();
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
        this.rejected = new Registration("Rejected", FULL);
		this.regState.addObjects(annSmith, johnDoe, lisaMartin, rejected);
	}
}
