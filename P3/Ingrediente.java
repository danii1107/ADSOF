public class Ingrediente {
	private String nombre;
	private TipoIngrediente tipo;
	private String tipo2;
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

	public TipoIngrediente getTipo() {
		return tipo;
	}

    public String getTipo2() {
		return tipo2;
	}

	public InfoNutricional getInfo() {
		return info;
	}
	
	public String getTabla() {
		return tabla;
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