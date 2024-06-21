/**
 * Tester equivalente al 3 pero con el caso de estudio de
 * exposiciones
 * 
 * @author DAniel Birsan daniel.birsan@estudiante.uam.es
 */
package nuevoCasoEstudio;

import java.util.*;

import expositions.*;

public class TesterTrajectories extends TesterStateChanges {
    public static void main(String[] args) {
        TesterTrajectories tsc = new TesterTrajectories();
        tsc.createExpositions();
        tsc.changeExpositions();
        tsc.displayTrajectories();
    }

    @Override
    protected void changeExpositions() {
        super.changeExpositions();
        this.expo2.entraGente(10);
        this.expo3.setLleno(true);
        this.regState.updateStates();
    }

    protected void displayTrajectories() {
        for (Exposition e : List.of(expo1, expo2, expo3))
            System.out.println(e + ": " + this.regState.trajectory(e));
    }
}
