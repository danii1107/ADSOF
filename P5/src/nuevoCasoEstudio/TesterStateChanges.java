/**
 * Tester equivalente al primer tester pero con el caso de
 * estudio de exposición
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package nuevoCasoEstudio;

import static expositions.ExpositionKind.*;

import expositions.Exposition;
import expositions.ExpositionState;
import states.ObjectStateTracker;

public class TesterStateChanges {
    protected ObjectStateTracker<Exposition, ExpositionState> regState;
    protected Exposition expo1, expo2, expo3;

    public static void main(String[] args) {
        TesterStateChanges tsc = new TesterStateChanges();
        tsc.createExpositions();
        System.out.println(tsc.regState);
        tsc.changeExpositions();
        System.out.println(tsc.regState);
    }

    protected void changeExpositions() {
        this.expo2.entraGente(15);
        this.expo3.setLleno(true);
        this.regState.updateStates();
    }

    protected void createExpositions() {
        this.regState = new ObjectStateTracker<Exposition, ExpositionState>(ExpositionState.getValues());
        regState.withState(ExpositionState.ABIERTA, e -> e.getAforoDispo() == e.getAforoMax() && !e.getLleno())
                .withState(ExpositionState.EN_PROCESO, e -> e.getAforoDispo() > 0 && e.getAforoDispo() != e.getAforoMax() && !e.getLleno())
                .withState(ExpositionState.LLENA, e -> e.getLleno() || e.getAforoDispo() <= 0)
                .elseState(ExpositionState.CERRADA);

        this.expo1 = new Exposition("Expo1", PINTURA);
        this.expo2 = new Exposition("Expo2", ESCULTURAS);
        this.expo3 = new Exposition("Expo3", AUDIOVISUAL);

        this.regState.addObjects(expo1, expo2, expo3);
    }
}
