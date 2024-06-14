/**
 * Clase concreta Leaf en el patrón Composite
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.comida;

import src.informacion.ElementoNutricional;
import src.informacion.InfoNutricional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import src.informacion.Alergeno;

public class Ingrediente extends Comida {
	private TipoIngrediente tipoEnum = null;
	private String tipo;

	/**
	 * Instancia un ingrediente con un tipo de ingrediente String
	 * @param nombre Nombre del ingrediente
	 * @param tipo	 Tipo de ingrediente en formato string
	 * @param info	 Instancia de info nutricional, ya sea por peso o por unidad
	 */
	public Ingrediente(String nombre, String tipo, InfoNutricional info) {
		super(nombre, info);
		this.tipo = tipo;
	}

	/**
	 * Instancia un ingrediente con un tipo de ingrediente String
	 * @param nombre Nombre del ingrediente
	 * @param tipo	 Tipo de ingrediente de la enumeración
	 * @param info	 Instancia de info nutricional, ya sea por peso o por unidad
	 */
	public Ingrediente(String nombre, TipoIngrediente tipo, InfoNutricional info) {
		this(nombre, tipo.toString(), info);
		this.tipoEnum = tipo;
	}

	/**
	 * Devuelve el tipo de ingrediente
	 * @return Tipo de ingrediente
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Devuelve el tipo de ingrediente de la enumeración
	 * @return Tipo de ingrediente de la enumeración
	 */
	public TipoIngrediente getTipoEnum() {
		return this.tipoEnum;
	}

	/**
	 * Añade los alérgenos al ingrediente
	 * @param alergenos Alérgenos a añadir
	 * @return El ingrediente con los alérgenos añadidos
	 */
	public Ingrediente tieneAlergenos(Alergeno... alergenos) {
		this.addAlergenos(Arrays.asList(alergenos));
		return this;
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
	 * @return Cadena formateada para un ingrediente
	 */
	@Override
	public String getInfoFichero() {
		StringBuilder info = new StringBuilder();
		info.append(this.getInfoNutricional().getUnidadPeso() + ";" + this.getNombre() + ";");
		if (this.tipoEnum != null) {
			info.append(this.tipoEnum.toString().toUpperCase().replace("FRUTAS Y VERDURAS", "FRUTA_VERDURA") + ";");
		} else {
			info.append(this.tipo + ";");
		}
		for (Map.Entry<ElementoNutricional, Double> entry : this.getInfoNutricional().getInfoNutricional().entrySet()) {
			info.append(String.format(Locale.US, "%.1f", entry.getValue()) + ";");
		}
		for (Alergeno a : Alergeno.values()) {
			if (this.getAlergenos().contains(a)) {
				info.append("S;");
			} else {
				info.append("N;");
			}
		}
		return info.toString().substring(0, info.length() - 1);
	}

	/**
	 * Implementación del método para crear la instancia a partir de una linea de un fichero
	 * @param linea Cadena que contiene la información necesaria para la instancai
	 * @return Instancia del ingrediente
	 */
    @Override
	public Comida setInfoFichero(String linea) {
		String[] partes = linea.split(";");

		int i = 3;
		for (ElementoNutricional key : this.getInfoNutricional().getInfoNutricional().keySet()) {
			this.getInfoNutricional().getInfoNutricional().put(key, Double.parseDouble(partes[i]));
			i++;
		}
		
		List<Alergeno> alergenos = new ArrayList<>();
		for (; i < 15; i++) {
			if ("S".equals(partes[i])) {
				alergenos.add(Alergeno.values()[i - 11]);
			}
		}
		this.addAlergenos(alergenos);
		
		return this;
	}

	/**
	 * Implementación por defecto por herencia
	 * @param linea Cadena que contiene la información necesaria para la instancai
	 * @param comida Mapa con la comida ya instanciada
	 * @return Instancia del ingrediente
	 */
	@Override
	public Comida setInfoFichero(String linea, Map<String, Comida> comida) {
		return null;
	}

	@Override
	public String toString() {
		return "[" + this.tipo + "] " + this.getNombre() + ": " + this.getInfoNutricional().toString() + super.toString();
	}
}