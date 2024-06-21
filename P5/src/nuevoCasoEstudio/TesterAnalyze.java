/**
 * 2o de los testers apartado extraordinaria, probará con el caso de estudio
 * exposicion
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package nuevoCasoEstudio;

import static expositions.ExpositionState.*;

import java.util.ArrayList;
import java.util.List;

import expositions.Exposition;
import expositions.ExpositionKind;
import expositions.ExpositionState;
import states.Analyzer;
import states.Transition;

public class TesterAnalyze extends AbstractTesterAnalyzer<ExpositionState> {
    public Analyzer<ExpositionState> crear() {
        return this.creaAnalizador();
    }

    public static void main(String[] args) {
        TesterAnalyze tsa = new TesterAnalyze();
        Analyzer<ExpositionState> analizador = tsa.crear();

        tsa.createExpositions();
        Exposition danielBirsanExpo = new Exposition("Daniel Birsan Expo", ExpositionKind.PINTURA);
        tsa.regState.addObjects(danielBirsanExpo);
        danielBirsanExpo.entraGente(10);

        List<Transition<ExpositionState>> trayectoria = new ArrayList<>();
        tsa.changeExpositions();

        trayectoria.add(new Transition<ExpositionState>(ABIERTA, EN_PROCESO));
        trayectoria.add(new Transition<ExpositionState>(EN_PROCESO, LLENA));
        trayectoria.add(new Transition<ExpositionState>(LLENA, CERRADA));
        trayectoria.add(new Transition<ExpositionState>(EN_PROCESO, ABIERTA));
        trayectoria.add(new Transition<ExpositionState>(CERRADA, LLENA));

        tsa.regState.trajectory(danielBirsanExpo).add(new Transition<ExpositionState>(ABIERTA, EN_PROCESO));
        tsa.regState.trajectory(danielBirsanExpo).add(new Transition<ExpositionState>(EN_PROCESO, LLENA));

        tsa.regState.trajectory(danielBirsanExpo).add(new Transition<ExpositionState>(LLENA, CERRADA));
        tsa.regState.trajectory(danielBirsanExpo).add(new Transition<ExpositionState>(EN_PROCESO, ABIERTA));
        tsa.regState.trajectory(tsa.expo1).add(new Transition<ExpositionState>(CERRADA, LLENA));

        tsa.regState.trajectory(tsa.expo2).add(new Transition<ExpositionState>(EN_PROCESO, ABIERTA));
        tsa.regState.trajectory(tsa.expo3).add(new Transition<ExpositionState>(CERRADA, LLENA));

        System.out.println("Transiciones invalidas: " + analizador.analyze(trayectoria));
    }
}
