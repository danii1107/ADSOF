/**
 * Abstract factory pattern to create the pair NewMining-NewValidation.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.abstractFactory;

import src.blockchain.interfaces.*;
import src.blockchain.mining.*;

public class NewFactory implements IFactory {
	/**
	 * Instances the new mining method
	 * 
	 * @return the mining method
	 */
	@Override
    public IMiningMethod createMining() {
        return new NewMining();
    }

	/**
	 * Instances the new validation method
	 * 
	 * @return the validation method
	 */
    @Override
    public IValidateMethod createValidation() {
        return new NewValidate();
    }
}