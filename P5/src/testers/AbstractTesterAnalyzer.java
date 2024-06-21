/**
 * Tester para crear el analizador y añadir las expresiones necesarias
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package testers;

import static registrations.RegistrationState.*;

import registrations.RegistrationState;
import states.Analyzer;
import states.Transition;

public abstract class AbstractTesterAnalyzer<S extends Comparable<S>> extends TesterObserver {
	/**
	 * Instancia y añade las expresiones al analizador
	 * @return El analizador creado
	 */
	protected Analyzer creaAnalizador() {
		Analyzer<RegistrationState> analizador = new Analyzer<>();
		
		analizador.addExpresion((t, destino) -> false, PAYED, STARTED)
			.addExpresion((t, destino) -> false,  PAYED,  FILLED)
			.addExpresion((t, destino) -> false,  FINISHED, STARTED)
			.addExpresion((t, destino) -> false,  FINISHED, FILLED)
			.addExpresion((t, destino) -> false,  FINISHED, PAYED)
			.addExpresion((t, destino) -> false,  FINISHED, VALIDATED)
			.addExpresion((t, destino) -> false,  FINISHED, REJECTED)
			.addExpresion((t, destino) -> {
					for (Transition<RegistrationState> tr : t) {
						if (tr.getDestino() == FILLED) {
							return false;
						}
					}
					return true;
			}, FILLED, STARTED);
		
		return analizador;
	}
	
}
