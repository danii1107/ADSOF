/**
 * 1ero de los testers apartado extraordinaria, probará con el caso de estudio
 * predefinido, registration
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package testers;

import static registrations.RegistrationState.*;

import java.util.*;

import registrations.*;
import states.Analyzer;
import states.Transition;

public class TesterAnalyze extends AbstractTesterAnalyzer<RegistrationState> {
	public Analyzer<RegistrationState> crear() {
		return this.creaAnalizador();
	}
	public static void main(String[] args) {
		TesterAnalyze tsa = new TesterAnalyze();
		Analyzer<RegistrationState> analizador = tsa.crear();

		tsa.createRegistrations();
		Registration danielBirsan = new Registration("Daniel Birsan", RegistrationKind.MEMBER);
		tsa.regState.addObjects(danielBirsan);
		danielBirsan.pay(RegistrationKind.MEMBER.getPrice());
		
		List<Transition<RegistrationState>> trayectoria = new ArrayList<>();
		tsa.changeRegistrations();

		trayectoria.add(new Transition<RegistrationState>(PAYED, STARTED));
        trayectoria.add(new Transition<RegistrationState>(PAYED, FILLED));
        trayectoria.add(new Transition<RegistrationState>(FINISHED, STARTED));
        trayectoria.add(new Transition<RegistrationState>(FINISHED, PAYED));
        trayectoria.add(new Transition<RegistrationState>(FINISHED, FILLED));
        trayectoria.add(new Transition<RegistrationState>(FINISHED, VALIDATED));
        trayectoria.add(new Transition<RegistrationState>(FINISHED, REJECTED));
        trayectoria.add(new Transition<RegistrationState>(FILLED, STARTED));
        trayectoria.add(new Transition<RegistrationState>(FILLED, STARTED));

        tsa.regState.trajectory(danielBirsan).add(new Transition<RegistrationState>(PAYED, STARTED));
        tsa.regState.trajectory(danielBirsan).add(new Transition<RegistrationState>(PAYED, FILLED));

        tsa.regState.trajectory(danielBirsan).add(new Transition<RegistrationState>(FINISHED, STARTED));
        tsa.regState.trajectory(danielBirsan).add(new Transition<RegistrationState>(FINISHED, PAYED));
        tsa.regState.trajectory(tsa.lisaMartin).add(new Transition<RegistrationState>(FINISHED, FILLED));
        tsa.regState.trajectory(tsa.lisaMartin).add(new Transition<RegistrationState>(FINISHED, VALIDATED));
        tsa.regState.trajectory(tsa.johnDoe).add(new Transition<RegistrationState>(FINISHED, REJECTED));

        tsa.regState.trajectory(tsa.annSmith).add(new Transition<RegistrationState>(FILLED, STARTED));
        tsa.regState.trajectory(tsa.johnDoe).add(new Transition<RegistrationState>(FILLED, STARTED));

		System.out.println("Transiciones invalidas: " + analizador.analyze(trayectoria));
	}
}
