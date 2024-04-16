package src.blockchain.components;

import src.blockchain.interfaces.IConnectable;

/**
 * The AbstractConnectable class is an abstract base class that implements the IConnectable interface.
 * It provides a common implementation for the fullName() method.
 */
public abstract class AbstractConnectable implements IConnectable {
	/**
	 * Returns the full name of the connectable object.
	 *
	 * @return the full name of the connectable object.
	 */
	public abstract String fullName();
}
