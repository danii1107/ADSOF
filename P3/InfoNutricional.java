import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class InfoNutricional {
	private double calorias;
	private double carbohidratos;
	private double grasasTotales;
	private double grasasSaturadas;
	private double proteinas;
	private double azucares;
	private double fibra;
	private double sodio;

	public InfoNutricional(double calorias, double carbohidratos, double grasasTotales, double grasasSaturadas, double proteinas, double azucares, double fibra, double sodio) {
		this.calorias = calorias;
		this.carbohidratos = carbohidratos;
		this.grasasTotales = grasasTotales;
		this.grasasSaturadas = grasasSaturadas;
		this.proteinas = proteinas;
		this.azucares = azucares;
		this.fibra = fibra;
		this.sodio = sodio;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public double getCarbohidratos() {
		return carbohidratos;
	}

	public void setCarbohidratos(double carbohidratos) {
		this.carbohidratos = carbohidratos;
	}

	public double getGrasasTotales() {
		return grasasTotales;
	}

	public void setGrasasTotales(double grasasTotales) {
		this.grasasTotales = grasasTotales;
	}

	public double getGrasasSaturadas() {
		return grasasSaturadas;
	}

	public void setGrasasSaturadas(double grasasSaturadas) {
		this.grasasSaturadas = grasasSaturadas;
	}

	public double getProteinas() {
		return proteinas;
	}

	public void setProteinas(double proteinas) {
		this.proteinas = proteinas;
	}

	public double getAzucares() {
		return azucares;
	}

	public void setAzucares(double azucares) {
		this.azucares = azucares;
	}

	public double getFibra() {
		return fibra;
	}

	public void setFibra(double fibra) {
		this.fibra = fibra;
	}

	public double getSodio() {
		return sodio;
	}

	public void setSodio(double sodio) {
		this.sodio = sodio;
	}

	public abstract String getTipo();

	public List<Double> getNutrientes() {
		List<Double> nutrientes = new ArrayList<>();
		nutrientes.add(getCalorias());
		nutrientes.add(getCarbohidratos());
		nutrientes.add(getGrasasTotales());
		nutrientes.add(getGrasasSaturadas());
		nutrientes.add(getProteinas());
		nutrientes.add(getAzucares());
		nutrientes.add(getFibra());
		nutrientes.add(getSodio());
		return nutrientes;
	}

	
	public String getInfoFichero() {
		String info = String.format(Locale.US, "%.1f;%.1f;%.1f;%.1f;%.1f;%.1f;%.1f;%.1f;", 
			this.getCalorias(), 
			this.getCarbohidratos(), 
			this.getGrasasTotales(),
			this.getGrasasSaturadas(), 
			this.getProteinas(), 
			this.getAzucares(), 
			this.getFibra(), 
			this.getSodio());
		return info;
	}

	public String mostrarInfo(double calorias, double carbohidratos, double grasasTotales, double grasasSaturadas, double proteinas, 
								double azucares, double fibra, double sodio) {
		return "Valor energetico: " + String.format(Locale.US, "%.2f", calorias) + " kcal, Hidratos de Carbono: " + String.format(Locale.US, "%.2f", carbohidratos) + " g, Grasas: " + 
			String.format(Locale.US, "%.2f", grasasTotales) + " g, Saturadas: " + String.format(Locale.US, "%.2f", grasasSaturadas) + " g, Proteinas: " + String.format(Locale.US, "%.2f", proteinas) + 
			" g, Azucares: " + String.format(Locale.US, "%.2f", azucares) + " g, Fibra: " + String.format(Locale.US, "%.2f", fibra) + " g, Sodio: " + String.format(Locale.US, "%.2f", sodio) + " mg.";
	}

	public String toString(){
		return "";
	}
}