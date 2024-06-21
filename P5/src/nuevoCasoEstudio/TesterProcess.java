/**
 * Tester equivalente al 4 con el caso de estudio de exposiciones
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package nuevoCasoEstudio;

import expositions.*;
import states.Process;

public class TesterProcess extends TesterTrajectories {
    public static void main(String[] args) {
        TesterProcess tsc = new TesterProcess();
        tsc.createExpositions();
        tsc.changeExpositions();
        tsc.displayTrajectories();
        tsc.buildProcess();
    }

    @Override
    protected void changeExpositions() {
        super.changeExpositions();
        this.expo3.setLleno(true);
        this.regState.updateStates();
        this.expo2.entraGente(5);
        this.regState.updateStates();
    }

    protected void buildProcess() {
        Process<ExpositionState> expoProcess = new Process<>(ExpositionState.getValues());
        for (Exposition e : this.regState) {
            expoProcess.add(this.regState.trajectory(e));
        }
        System.out.println(expoProcess);
    }
}
