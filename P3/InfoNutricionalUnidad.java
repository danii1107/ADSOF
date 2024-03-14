public class InfoNutricionalUnidad extends InfoNutricional {
	public InfoNutricionalUnidad(double calorias, double carbohidratos, double grasasTotales, double grasasSaturadas, double proteinas, double azucares, double fibra, double sodio) {
		super(calorias, carbohidratos, grasasTotales, grasasSaturadas, proteinas, azucares, fibra, sodio);
	}
	
	@Override
    public String getTipo() {
        return "unidad";
    }

	@Override
	public String toString() {
		return "INFORMACION NUTRICIONAL POR UNIDAD -> ";
	}

}