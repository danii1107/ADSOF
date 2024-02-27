/**
*
* @author Daniel Birsan daniel.birsan@estudiante.uam.es
* @author Juan José Martínez Domínguez juanjose.martinezd@estudiante.uam.es
*
*/
public class Author {
	private String lastName;
	private String name;
	
	/**
	 * Constructor para `Author`. Inicializa un autor con su nombre y apellido.
	 * @param name Nombre del autor.
	 * @param lastName Apellido del autor.
	 */
	public Author(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Devuelve las iniciales del nombre del autor.
	 * @return Iniciales del nombre del autor.
	 */
	public String getInitial() {
		return name.substring(0, 1).toUpperCase();
	}
}
