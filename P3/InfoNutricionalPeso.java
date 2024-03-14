public class InfoNutricionalPeso extends InfoNutricional {
	public InfoNutricionalPeso(double calorias, double carbohidratos, double grasasTotales, double grasasSaturadas, double proteinas, double azucares, double fibra, double sodio) {
		super(calorias, carbohidratos, grasasTotales, grasasSaturadas, proteinas, azucares, fibra, sodio);
	}

	@Override
    public String getTipo() {
        return "peso";
    }

	@Override
	public String toString() {
		return "INFORMACION NUTRICIONAL POR 100 g -> ";
	}
} 