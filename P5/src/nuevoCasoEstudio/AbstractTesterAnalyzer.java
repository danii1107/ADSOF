/**
 * Tester para crear el analizador y añadir las expresiones necesarias
 * para el caso de estudio de exposiciones.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package nuevoCasoEstudio;

import expositions.ExpositionState;
import states.Analyzer;
import states.Transition;

public abstract class AbstractTesterAnalyzer<S extends Comparable<S>> extends TesterObserver {
    /**
     * Instancia y añade las expresiones al analizador
     * @return El analizador creado
     */
    protected Analyzer<ExpositionState> creaAnalizador() {
        Analyzer<ExpositionState> analizador = new Analyzer<>();
        
        analizador.addExpresion((t, destino) -> false, ExpositionState.ABIERTA, ExpositionState.EN_PROCESO)
            .addExpresion((t, destino) -> false, ExpositionState.EN_PROCESO, ExpositionState.LLENA)
            .addExpresion((t, destino) -> false, ExpositionState.LLENA, ExpositionState.CERRADA)
            .addExpresion((t, destino) -> false, ExpositionState.EN_PROCESO, ExpositionState.ABIERTA)
            .addExpresion((t, destino) -> false, ExpositionState.CERRADA, ExpositionState.LLENA)
            .addExpresion((t, destino) -> {
                for (Transition<ExpositionState> tr : t) {
                    if (tr.getDestino() == ExpositionState.LLENA) {
                        return false;
                    }
                }
                return true;
            }, ExpositionState.LLENA, ExpositionState.ABIERTA);
        
        return analizador;
    }
}
