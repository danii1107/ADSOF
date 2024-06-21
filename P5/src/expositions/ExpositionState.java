/**
 * Representa los estados de los objetos exposition en forma de clase en
 * luigar de como enumeración
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package expositions;

public class ExpositionState implements Comparable<ExpositionState> {
    private String nombre;
    private int prioridad;

    public static final ExpositionState ABIERTA = new ExpositionState("Abierta", 1);
    public static final ExpositionState EN_PROCESO = new ExpositionState("En Proceso", 2);
    public static final ExpositionState LLENA = new ExpositionState("Llena", 3);
    public static final ExpositionState CERRADA = new ExpositionState("Cerrada", 4);

	private static final ExpositionState[] VALUES = { ABIERTA, EN_PROCESO, LLENA, CERRADA };

    /**
     * Constructor privado para evitar la instanciación externa sin
     * los valores adecuados
     * @param nombre nombre de la expo
     * @param prioridad prioridad de la expo
     */
    private ExpositionState(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    /**
     * Devuelve el nombre de la xpo
     * @return El nombre de la exposición
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve la prioridad de la exposición
     * @return La prioridad
     */
    public int getPrioridad() {
        return this.prioridad;
    }

    /**
     * Devuelve el array que hace referencia a los estados
     * de la clase
     * @return El array de estados
     */
	public static ExpositionState[] getValues() {
		return VALUES;
	}

    /**
     * Al usar una clase en vez de una enumeración hay que sobreescribir
     * compareto por implementar comparable
     */
    @Override
    public int compareTo(ExpositionState o) {
        return Integer.compare(this.prioridad, o.prioridad);
    }

    /**
     * SObreescribimos equals para ewvitar elementos repetidos
     * @param obj objeto a comparar
     * @return true si son iguales, false si no
     */
    @Override
    public boolean equals(Object obj) {
        if (obj==this) return true;
		if (!(obj instanceof ExpositionState)) return false;
		ExpositionState vn = (ExpositionState) obj;
		return this.nombre.equals(vn.nombre);
    }

    /**
     * Al sobreescribir equals sobreescribimos hashcode
     * @return el hash de la clase
     */
    @Override
    public int hashCode() {
        return this.prioridad + nombre.hashCode();
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
