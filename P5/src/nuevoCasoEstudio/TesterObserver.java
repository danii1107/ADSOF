/**
 * Tester equivalente al 5 con caso de estudio de exposition
 * 
 * @author DAniel Birsan daniel.birsan@estudiante.uam.es
 */
package nuevoCasoEstudio;

import expositions.Exposition;

public class TesterObserver extends TesterStateChanges {
    public static void main(String[] args) {
        TesterObserver tsc = new TesterObserver();
        tsc.createExpositions();
        Exposition.withTracker(tsc.regState);
        System.out.println(tsc.regState);
        tsc.changeExpositions();
        System.out.println(tsc.regState);
    }

    @Override
    protected void changeExpositions() {
        this.expo1.entraGente(10);
        this.expo2.entraGente(20);
    }
}
