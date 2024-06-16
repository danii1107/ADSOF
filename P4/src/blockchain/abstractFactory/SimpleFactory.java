/**
 * Abstract factory pattern to create the pair SImpleMining-SimpleValidation.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.abstractFactory;

import src.blockchain.interfaces.*;
import src.blockchain.mining.*;

public class SimpleFactory implements IFactory {
	/**
	 * Instances the simple mining method
	 * 
	 * @return the mining method
	 */
	@Override
    public IMiningMethod createMining() {
        return new SimpleMining();
    }

	/**
	 * Instances the simple validation method
	 * 
	 * @return the validation method
	 */
    @Override
    public IValidateMethod createValidation() {
        return new SimpleValidate();
    }
}
