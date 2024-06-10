/**
 * Enumeracion que representa un tipo de ingrediente
 *
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 *
 */

package src.comida;

public enum TipoIngrediente {
	CARNE,
	PESCADO,
	FRUTA_VERDURA,
	LEGUMBRE,
	CEREAL,
	HUEVO,
	LACTEO;

	@Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace("ruta_verdura", "rutas y verduras");
    }
}