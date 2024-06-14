/**
 * Clase concreta que representa el Composite del patrón composite
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */

package src.comida;

import src.informacion.*;

import java.util.*;

public class Plato extends Comida {
    private Map<Comida, Integer> componentes = new LinkedHashMap<>();
    
    /**
     * Instancia un plato y reserva memoria para el mapa de componentes
     * @param nombre Nombre del plato
     */
    public Plato(String nombre) {
        super(nombre);
    }

    public Map<Comida, Integer> getComponentes() {
        return this.componentes;
    }

    /**
     * Añade un ingrediente al plato
     * @param ingrediente Ingrediente a añadir
     * @param cantidad Cantidad del ingrediente a añadir, en gramos o unidades
     * @return False si el ingrediente no está repetido, true en caso contrario
     */
    public boolean addIngrediente(Comida ingrediente, int cantidad) {
        if (this.componentes.containsKey(ingrediente)) {
            return true;
        }
        this.add(ingrediente, cantidad);
        return false;
    }
    
    /**
     * Añade un plato al plato compuesto
     * @param plato Plato a añadir
     * @return False si el plato no está repetido, true en caso contrario
     */
    public boolean addPlato(Comida plato) {
        if (this.componentes.containsKey(plato)) {
            return true;
        }
        this.add(plato, 1);
        return false;
    }
    
    /**
     * Método para añadir un composite o una leaf del patrón composite
     * @param c Ingrediente o plato a añadir
     * @param cantidad Cantidad a añadir, 1 si es un plato
     */
    @Override
    public void add(Comida c, Integer cantidad) {
        this.componentes.put(c, cantidad);
        this.addAlergenos(c.getAlergenos());
        InfoNutricional info = this.getInfoNutricional();
        info.calcularInfoNutricional(c, cantidad);
    }

    /**
	 * Patrón composite, método para obtener una hoja
	 */
	@Override
	public Comida getChild() {
		for (Map.Entry<Comida, Integer> entry : this.componentes.entrySet()) {
            if (entry.getKey() != null) {
                return entry.getKey();
            }
        }
        return null;
	}

    /**
	 * Implementación del método abstracto para obtener la cadena del fichero formateada
	 * @return Cadena formateada para un plato
	 */
    @Override
    public String getInfoFichero() {
        StringBuilder info = new StringBuilder();
        info.append("PLATO;" + this.getNombre() + ";");
        for (Map.Entry<Comida, Integer> entry : this.componentes.entrySet()) {
            if (entry.getKey().getChild() != null) {
                info.append("PLATO " + entry.getKey().getNombre() + ";");
            } else {
                info.append("INGREDIENTE " + entry.getKey().getNombre() + ":" + entry.getValue().toString() + ";");
            }

        }
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
	
		for (int i = 2; i < partes.length; i++) {
			if (partes[i].startsWith("INGREDIENTE ")) {
				String[] detalleIngrediente = partes[i].split(":");
				String nombreIngrediente = detalleIngrediente[0].substring("INGREDIENTE ".length());
				Integer cantidad = Integer.parseInt(detalleIngrediente[1]);
				Comida ingrediente = comida.get(nombreIngrediente);
				if (ingrediente != null) {
					this.addIngrediente(ingrediente, cantidad);
				}
			} else if (partes[i].startsWith("PLATO ")) {
				String[] detallePlato = partes[i].split(":");
				String nombreSubPlato = detallePlato[0].substring("PLATO ".length());
				Comida subPlato = comida.get(nombreSubPlato);
				if (subPlato != null) {
					this.addPlato(subPlato);
				}
			}
		}
	
        return this;
    }

    /**
	 * Implementación por defecto por herencia
	 * @param linea Cadena que contiene la información necesaria para la instancai
	 * @return Instancia del ingrediente
	 */
	@Override
	public Comida setInfoFichero(String linea) {
		return null;
	}

    @Override
    public String toString() {
        return "[Plato] " + this.getNombre() + ": " + this.getInfoNutricional().toString() + super.toString();
    }
}
