/**
 * Tester equivalente al segundo pero con el caso de estudio 
 * de exposiciones
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package nuevoCasoEstudio;

import static expositions.ExpositionKind.*;

import expositions.Exposition;

public class TesterRepeatedObjects extends TesterStateChanges {
    public static void main(String[] args) {
        TesterRepeatedObjects tro = new TesterRepeatedObjects();
        tro.createExpositions();
        tro.regState.addObjects(new Exposition("Expo1", PINTURA));
        System.out.println(tro.regState);
    }
}
