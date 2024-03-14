import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Plato {
	private String nombre;
	private Map<Ingrediente, Integer> ingredientes;
	private Map<Plato, Integer> platos;
	private Set<String> alergenosUnicos;

	public Plato(String nombre) {
		this.nombre = nombre;
		this.ingredientes = new HashMap<>();
		this.platos = new HashMap<>();
		this.alergenosUnicos = new HashSet<>();
	}
	
	public boolean addIngrediente(Ingrediente ingrediente, int cantidad) {
		if (this.ingredientes.containsKey(ingrediente)) {
			return true;
		}
		this.ingredientes.put(ingrediente, cantidad);
		return false;
	}
	
	public boolean addPlato(Plato plato) {
		if (this.platos.containsKey(plato)) {
			return true;
		}
		this.platos.put(plato, 1);
		return false;
	}

	public List<Double> getInfoNutricional() {
		List<Double> infoNutricionalTotal = new ArrayList<>(Collections.nCopies(8, 0.0));
	
		for (Map.Entry<Ingrediente, Integer> entrada : this.ingredientes.entrySet()) {
			Ingrediente ingrediente = entrada.getKey();
			Integer cantidad = entrada.getValue();
			List<Double> infoNutricionalAux = ingrediente.getInfo().getNutrientes();
	
			for (int i = 0; i < infoNutricionalAux.size(); i++) {
				double valorActual = infoNutricionalTotal.get(i);
				double valorNutriente = infoNutricionalAux.get(i);
				
				if (ingrediente.getInfo().getTipo().equals("unidad")) {
					infoNutricionalTotal.set(i, valorActual + (valorNutriente * cantidad));
				} else if (ingrediente.getInfo().getTipo().equals("peso")) {
					infoNutricionalTotal.set(i, valorActual + (valorNutriente * (cantidad / 100.0)));
				}
			}
			if (ingrediente.getTabla() != null && !ingrediente.getTabla().isEmpty()) {
                alergenosUnicos.add(ingrediente.getTabla());
            }
		}
		for (Map.Entry<Plato, Integer> entrada : this.platos.entrySet()) {
			Plato plato = entrada.getKey();
			Integer cantidad = entrada.getValue();
			List<Double> infoNutricionalAux = plato.getInfoNutricional();
	
			for (int i = 0; i < infoNutricionalAux.size(); i++) {
				double valorActual = infoNutricionalTotal.get(i);
				double valorNutriente = infoNutricionalAux.get(i);
				
				infoNutricionalTotal.set(i, valorActual + (valorNutriente * cantidad));
			}
			alergenosUnicos.addAll(plato.alergenosUnicos);
		}
		return infoNutricionalTotal;
	}


	public String toString() {
		List<Double> infoNutricional = this.getInfoNutricional();
		StringBuilder alergenos = new StringBuilder();
		alergenos.append(String.join(", ", alergenosUnicos));
		return "[Plato] " + this.nombre + ": INFORMACION NUTRICIONAL DEL PLATO -> Valor energetico: " + String.format("%.2f", infoNutricional.get(0)) + 
			" kcal, Hidratos de Carbono: " + String.format("%.2f", infoNutricional.get(1)) + " g, Grasas: " + String.format("%.2f", infoNutricional.get(2)) +
			" g, Saturadas: " + String.format("%.2f", infoNutricional.get(3)) + " g, Proteinas: " + String.format("%.2f", infoNutricional.get(4)) + " g, Azucares: " +
			String.format("%.2f", infoNutricional.get(5)) + " g, Fibra: " + String.format("%.2f", infoNutricional.get(6)) + " g, Sodio: " + 
			String.format("%.2f", infoNutricional.get(7)) + " mg." + (alergenosUnicos.isEmpty() ? "" : String.format(" CONTIENE %s", alergenos.toString().toLowerCase()));
	}
}
