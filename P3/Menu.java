import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu {
	private List<Plato> platos;
	private Set<String> alergenosUnicos;
	private static Integer nextId = 1;
	private Integer id = 0;

	public Menu(Plato... platos) {
		this.platos = new ArrayList<>();
		for (Plato plato : platos) {
			this.platos.add(plato);
		}
		this.id = nextId++;
		this.alergenosUnicos = new HashSet<>();
	}

	public List<Double> getInfoNutricional() {
		List<Double> infoNutricionalTotal = new ArrayList<>(Collections.nCopies(8, 0.0));

		for (int i = 0; i < this.platos.size(); i++) {
			Plato plato = this.platos.get(i);
			List<Double> infoNutricionalAux = plato.getInfoNutricional();
	
			for (int j = 0; j < infoNutricionalAux.size(); j++) {
				double valorActual = infoNutricionalTotal.get(j);
				double valorNutriente = infoNutricionalAux.get(j);	
				infoNutricionalTotal.set(j, valorActual + valorNutriente);
			}
			this.alergenosUnicos = plato.getAlergenosUnicos();
		}
		return infoNutricionalTotal;
	}

	public String mostrarPlatos() {
		String platos = "";
		for (int i = 0; i < this.platos.size(); i++) {
			platos += this.platos.get(i).getNombre(); 
			if (i < this.platos.size() - 1) 
				platos += ", ";
		}
		return platos;
	}

	public String mostrarInfo() {
		List<Double> infoNutricional = this.getInfoNutricional();
		String info = "";
		info += "Valor energetico: " + String.format("%.2f", infoNutricional.get(0)) + 
			" kcal, Hidratos de Carbono: " + String.format("%.2f", infoNutricional.get(1)) + " g, Grasas: " + String.format("%.2f", infoNutricional.get(2)) +
			" g, Saturadas: " + String.format("%.2f", infoNutricional.get(3)) + " g, Proteinas: " + String.format("%.2f", infoNutricional.get(4)) + " g, Azucares: " +
			String.format("%.2f", infoNutricional.get(5)) + " g, Fibra: " + String.format("%.2f", infoNutricional.get(6)) + " g, Sodio: " + 
			String.format("%.2f", infoNutricional.get(7)) + " mg.";
		return info;
	}

	public String mostrarAlergenos() {
		String alergenos = "";
		for (Plato plato : this.platos) {
			alergenos = this.alergenosUnicos.isEmpty() ? "" : String.format(" CONTIENE %s", plato.getAlergenos());
		}
		return alergenos;
	}

	public String toString() {
		return String.format("Menu %d [", this.id) + this.mostrarPlatos() + "]: INFORMACION NUTRICIONAL DEL MENU -> " + this.mostrarInfo() + this.mostrarAlergenos();
	}
}
