/**
 * Class used to implement the singleton pattern for the factory.
 * 
 * @author Daniel Birsan daniel.birsan@esttudiante.uam.es
 */
package src.blockchain.abstractFactory;

import src.blockchain.exceptions.singleton.SingletonException;
import src.blockchain.interfaces.IFactory;

public class SingletonFactory {
	private static IFactory factory;

	/**
	 * Private constructor to avoid external calls.
	 */
    private SingletonFactory() {}

	/**
	 * Creates the unique instance for the abstract factory, if it has not been
	 * created yet.
	 * 
	 * @param f the factory to create
	 * @throws SingletonException if the factory has already been created
	 */
    public static void createFactory(IFactory f) {
        if (factory == null) {
            factory = f;
        } else {
            throw new SingletonException("Factory is already created.");
        }
    }

	/**
	 * Returns the unique instance of the factory.
	 * 
	 * @return the factory
	 * @throws SingletonException if the factory has not been created yet
	 */
    public static IFactory getInstance() {
        if (factory == null) {
            throw new SingletonException("Factory is not created.");
        }
        return factory;
    }
}
