import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Ingrediente {
	private String nombre;
	private TipoIngrediente tipo = null;
	private String tipo2 = null;
	private InfoNutricional info;
    private String tabla;

	public Ingrediente(String nombre, TipoIngrediente tipo, InfoNutricional info) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.info = info;
	}

    public Ingrediente(String nombre, String tipo2, InfoNutricional info) {
		this.nombre = nombre;
		this.tipo2 = tipo2;
		this.info = info;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoIngrediente getTipo() {
		return tipo;
	}

	public void setTipo(TipoIngrediente tipo) {
		this.tipo = tipo;
	}

    public String getTipo2() {
		return tipo2;
	}

	public void setTipo2(String tipo) {
		this.tipo2 = tipo;
	}

	public InfoNutricional getInfo() {
		return info;
	}

	public String mostrarInfoFichero() {
		return this.info.getInfoFichero();
	}
	
	public String getTabla() {
		return tabla;
	}

	public Set<String> getAlergenos() {
		Set<String> alergenosSet = new HashSet<>();
		if (tabla != null && !tabla.isEmpty()) {
			String[] alergenos = tabla.split(", ");
			Collections.addAll(alergenosSet, alergenos);
		}
		return alergenosSet;
	}

	public String alergenosFichero(String alergenosInput) {
		StringBuilder alergenosBuilder = new StringBuilder();
		for (Alergeno alg : Alergeno.values()) {
			if (alergenosInput != null && alergenosInput.contains(alg.toString())) {
				alergenosBuilder.append("S;");
			} else {
				alergenosBuilder.append("N;");
			}
		}
		if (alergenosBuilder.length() > 0) {
			alergenosBuilder.setLength(alergenosBuilder.length() - 1);
		}
		return alergenosBuilder.toString();
	}

	public Ingrediente tieneAlergenos(Alergeno... alergenos) {
		String contenidos = "";

		for (Alergeno alergeno : alergenos)
		{
			contenidos += alergeno;
			if (alergeno != alergenos[alergenos.length - 1])
				contenidos += ", ";
        }
        this.tabla = contenidos;
		return this;
	}

	@Override
	public String toString() {
		String tipoString = tipo != null ? tipo.toString() : tipo2;
		if (tipoString == "FRUTA_VERDURA") tipoString = "Frutas y verduras";
		String alergenos = tabla != null ? tabla : "";
		return "[" + tipoString.charAt(0) + tipoString.substring(1).toLowerCase() + "] " + nombre + ": " + info.toString() + 
			info.mostrarInfo(info.getCalorias(), info.getCarbohidratos(), info.getGrasasTotales(), info.getGrasasSaturadas(), 
			info.getProteinas(), info.getAzucares(), info.getFibra(), info.getSodio()) + (alergenos.isEmpty() ? "" : " CONTIENE " + alergenos.toLowerCase());
	}
}