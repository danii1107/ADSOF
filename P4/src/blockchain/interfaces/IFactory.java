/**
 * Interface for the Abstract Factory pattern.
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.blockchain.interfaces;

public interface IFactory {
	/**
	 * Intsances a new mining method.
	 * @return the mining method
	 */
	IMiningMethod createMining();

	/**
	 * Instances a new validation method.
	 * @return the validation method
	 */
    IValidateMethod createValidation();
}