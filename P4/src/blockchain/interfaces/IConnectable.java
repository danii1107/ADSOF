package src.blockchain.interfaces;

/**
 * The IConnectable interface represents an object that can connect to other objects in a network.
 */
public interface IConnectable {
	
	/**
	 * Broadcasts a message to all connected objects.
	 * 
	 * @param msg the message to be broadcasted
	 */
	public void broadcast(IMessage msg);
	
	/**
	 * Returns the parent object to which this object is connected.
	 * 
	 * @return the parent object
	 */
	public IConnectable getParent();
	
	/**
	 * Returns the top-level parent object in the connection hierarchy.
	 * 
	 * @return the top-level parent object
	 */
	public default IConnectable getTopParent() {
		IConnectable parent = getParent();
		while (parent != null) {
			if (parent.getParent() == null) {
				return parent;
			}
			parent = parent.getParent();
		}
		return parent;
	}
}