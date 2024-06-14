/**
 * Clase abstracta que implementa el componente del patrón composite
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.comida;

import src.informacion.*;

import java.util.*;

public abstract class Comida {
	private Set<Alergeno> alergenos = new LinkedHashSet<>();
	private InfoNutricional infoNutricional = null;
	private String nombre;

	/**
	 * Constructor abstracto, establece el nombre y la infonutricional del ingrediente
	 * @param nombre Nombre del ingrediente
	 * @param infoNutricional Instancia de infonutricional asociada al ingrediente
	 */
	public Comida(String nombre, InfoNutricional infoNutricional) {
		this.infoNutricional = infoNutricional;
		this.nombre = nombre;
	}

	/**
	 * Constructor abstracto para instanciar la infonutricional de un plato
	 * @param nombre Nombre del plato
	 */
	public Comida(String nombre) {
		this(nombre, new InfoNutricionalPlato());
	}

	/**
	 * Constructor abstracto para instanciar la infonutricional de un menu
	 */
	public Comida() {
		this("", new InfoNutricionalMenu());
	}

	public InfoNutricional getInfoNutricional() {
		return this.infoNutricional;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Alergeno> getAlergenos() {
		return this.alergenos;
	}

	/**
	 * Método del patrón composite que permite añadir elementos al
	 * elemento compuestos
	 * @param c Componente a añadir, plato o ingrediente
	 * @param cantidad Cantidad a añadir, si es plato cantidad=1
	 */
	public abstract void add(Comida c, Integer cantidad);

	/**
	 * Método del patrón composite que permite obtener hojas y 
	 * detectar si es una
	 */
	public abstract Comida getChild();

	/**
	 * Método para obtener la cadena que se debe introducir en el fichero
	 * @return Cadena formateada
	 */
	public abstract String getInfoFichero();

	/**
	 * Método para obtener la instancia a partir de una linea de un fichero
	 * @param linea Cadena que contiene la información necesaria para la instancai
	 * @return Instancia concreta de comida
	 */
	public abstract Comida setInfoFichero(String linea);

	/**
	 * Método para obtener la instancia a partir de una linea de un fichero
	 * @param linea Cadena que contiene la información necesaria para la instancai
	 * @param comida Mapa con la comida ya instanciada
	 * @return Instancia concreta de comida
	 */
	public abstract Comida setInfoFichero(String linea, Map<String, Comida> comida);

	/**
	 * Método para añadir y almacenar los alérgenos únicos de un plato, menu o ingrediente
	 * @param alergenos Lista de alérgenos de la comida
	 */
	public void addAlergenos(List<Alergeno> alergenos) {
		alergenos.forEach(a -> this.alergenos.add(a));
	}

	/**
	 * Método para añadir y almacenar los alérgenos únicos recibidos en forma de set
	 * @param alergenos Conjunto de alérgenos de la comida
	 */
	public void addAlergenos(Set<Alergeno> alergenos) {
		List<Alergeno> l = new ArrayList<>();
		alergenos.forEach(a -> l.add(a));
		this.addAlergenos(l);
	}

	@Override
	public String toString() {
		StringBuilder alergenosString = new StringBuilder();
		Integer nAlergenos = this.alergenos.size();
		if (nAlergenos > 0) alergenosString.append(" CONTIENE ");
		Integer c = 0;
		for (Alergeno a : this.alergenos) {
			alergenosString.append(a.toString());
			if (++c < nAlergenos) alergenosString.append(", ");
		}
		return alergenosString.toString();
	}
}
