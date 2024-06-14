/**
 * Abstract class representing and connectable element of the blockchain.
 * 
 * @author Daniel Birsan
 */
package src.blockchain.components;

import src.blockchain.interfaces.IConnectable;

public abstract class AbstractConnectable implements IConnectable {
	private static int nextId = 0;
	private final int id;
	private IConnectable parent;
	private String name;

	/**
	 * Constructs a new AbstractConnectable object, asigning its unique id.
	 */
	public AbstractConnectable() {
		this.id = nextId++;
		this.parent = null;
	}

	/**
	 * Returns the components id
	 * 
	 * @return int, components id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Returns the name of the connectable object.
	 * 
	 * @return String, the name of the connectable object
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the connectable object.
	 * 
	 * @param name, the name of the connectable object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the parent of the connectable object.
	 * 
	 * @return the parent of the connectable object
	 */
	@Override
	public IConnectable getParent() {
		return this.parent;
	}

	/**
	 * Sets the parent of the connectable object.
	 * 
	 * @param parent, the parent of the connectable object
	 */
	public void setParent(IConnectable parent) {
		this.parent = parent;
	}

	/**
	 * Returns the full name of the component.
	 * 
	 * @return the full name of the component
	 */
	public String fullName() {
		return this.name + "#" + String.format("%03d", this.id);
	}
	
	/**
	 * Returns the string representation of the connectable object.
	 *
	 * @return the unique id in string format
	 */
	@Override
	public String toString() {
		return " | @" + this.fullName();
	}
}
