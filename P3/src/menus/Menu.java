/**
 * Clase concreta que almacena los menus
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.menus;

import src.comida.*;
import src.informacion.InfoNutricionalMenu;

import java.util.*;

public class Menu extends Comida {
	private static Integer nextId = 0;
	private List<Plato> platos = new ArrayList<>();
	private Integer id = 0;
	
	/**
	 * Constructor que instancia un menu, asigna un id único, establece los platos
	 * y la información nutricional del menú así como sus alérgenos
	 * @param platos Los pltos que conforman el menú
	 */
	public Menu(Plato... platos) {
		super();
		this.id = ++nextId;
		Arrays.asList(platos).forEach(p -> this.platos.add(p));
		Arrays.asList(platos).forEach(p -> this.addAlergenos(p.getAlergenos()));
		((InfoNutricionalMenu) this.getInfoNutricional()).calcularInfoNutricional(platos);
	}

	public List<Plato> getPlatos() {
		return this.platos;
	}

	/**
	 * Patrón composite, método por defecto
	 * @param c Componente a añadir
	 * @param cantidad Cantidad a añadir
	 */
	@Override
	public void add(Comida c, Integer cantidad) {}

	/**
	 * Patrón composite, método por defecto
	 */
	@Override
	public Comida getChild() {
		return null;
	}

	/**
	 * Implementación del método abstracto para obtener la cadena del fichero formateada
	 * @return Cadena formateada para un menú
	 */
	@Override
	public String getInfoFichero() {
		StringBuilder info = new StringBuilder();
		info.append("MENU;");
		this.platos.forEach(p -> info.append(p.getNombre() + ";"));
		return info.toString().substring(0, info.length() - 1);
	}

	/**
	 * Implementación del método para crear la instancia a partir de una linea de un fichero
	 * @param linea Cadena que contiene la información necesaria para la instancai
     * @param comida Mapa con la comida ya instanciada
	 * @return Instancia del plato
	 */
    @Override
	public Comida setInfoFichero(String linea, Map<String, Comida> comida) {
        String[] partes = linea.split(";");
		Plato[] platosDelMenu = new Plato[partes.length - 1];

		for (int i = 1; i < partes.length; i++) {
			Plato plato = ((Plato) comida.get(partes[i]));
			if (plato != null) {
				platosDelMenu[i - 1] = plato;
			}
		}
		
		Arrays.asList(platosDelMenu).forEach(p -> this.platos.add(p));
		Arrays.asList(platosDelMenu).forEach(p -> this.addAlergenos(p.getAlergenos()));
		((InfoNutricionalMenu) this.getInfoNutricional()).calcularInfoNutricional(platosDelMenu);
        return this;
    }

	@Override
	public String toString() {
		StringBuilder platos = new StringBuilder();
		this.platos.forEach(p -> platos.append(p.getNombre() + ", "));
		return "Menu " + this.id + " [" + platos.toString().substring(0, platos.length() - 2) + "]: " + this.getInfoNutricional().toString() + super.toString();  
	}
}
