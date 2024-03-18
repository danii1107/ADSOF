import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ManejadorFicheros {
	private static List<Menu> menus = new ArrayList<Menu>();
	
	private static Set<String> cargarInformacionExistente(String nombreFichero) {
		Set<String> infoExistente = new HashSet<>();
		try {
			List<String> lineasExistentes = Files.readAllLines(Paths.get(nombreFichero));
			infoExistente.addAll(lineasExistentes);
		} catch (IOException e) {}
		return infoExistente;
	}

	public static String generarInfoIngrediente(Ingrediente ingrediente) {
		StringBuilder info = new StringBuilder();
		if (ingrediente.getInfo().getTipo().equals("unidad")) {
			info.append("INGREDIENTE_UNIDAD;").append(ingrediente.getNombre()).append(";");
		} else {
			info.append("INGREDIENTE_PESO;").append(ingrediente.getNombre()).append(";");
		}
		if (ingrediente.getTipo() != null) {
			info.append(ingrediente.getTipo()).append(";");
		} else if (ingrediente.getTipo2() != null) {
			info.append(ingrediente.getTipo2()).append(";");
		}
		info.append(ingrediente.mostrarInfoFichero()).append(ingrediente.alergenosFichero(ingrediente.getTabla()));
		return info.toString();
	}

	public static String generarInfoPlato(Plato plato) {
		StringBuilder info = new StringBuilder();
		info.append("PLATO;").append(plato.getNombre());
		for (Map.Entry<Plato, Integer> entrada : plato.getPlatos().entrySet()) {
			info.append(";PLATO ").append(entrada.getKey().getNombre()).append(":").append(entrada.getValue().toString());
		}
		for (Map.Entry<Ingrediente, Integer> entrada : plato.getIngredientes().entrySet()) {
			info.append(";INGREDIENTE ").append(entrada.getKey().getNombre()).append(":").append(entrada.getValue().toString());
		}
		return info.toString();
	}

	public static String generarInfoMenu(Menu menu) {
		StringBuilder info = new StringBuilder();
		info.append("MENU;").append(menu.mostrarPlatosFichero());
		return info.toString();
	}

	public static void guardarFichero(String nombreFichero, List<Menu> menus) {
		StringBuilder nuevaInfo = new StringBuilder();
		Set<String> infoExistente = cargarInformacionExistente(nombreFichero);
	
		for (Menu menu : menus) {
			for (Plato plato : menu.getPlatos()) {
				for (Ingrediente ingrediente : plato.getIngredientes().keySet()) {
					String infoIngrediente = generarInfoIngrediente(ingrediente);
					if (infoExistente.add(infoIngrediente)) {
						nuevaInfo.append(infoIngrediente).append("\n");
					}
				}
				String infoPlato = generarInfoPlato(plato);
				if (infoExistente.add(infoPlato)) {
					nuevaInfo.append(infoPlato).append("\n");
				}
			}
			String infoMenu = generarInfoMenu(menu);
			if (infoExistente.add(infoMenu)) {
				nuevaInfo.append(infoMenu).append("\n");
			}
		}
	
		if (nuevaInfo.length() > 0) {
			try (OutputStream out = new FileOutputStream(nombreFichero, true)) {
				out.write(nuevaInfo.toString().getBytes());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static Ingrediente procesarLineaIngrediente(String linea) {
		String[] partes = linea.split(";");
		String nombre = partes[1];
		String tipo = partes[0].equals("INGREDIENTE_PESO") ? "peso" : "unidad";
		Double calorias = Double.parseDouble(partes[3]);
		Double carbohidratos = Double.parseDouble(partes[4]);
		Double grasasTotales = Double.parseDouble(partes[5]);
		Double grasasSaturadas = Double.parseDouble(partes[6]);
		Double proteinas = Double.parseDouble(partes[7]);
		Double azucares = Double.parseDouble(partes[8]);
		Double fibra = Double.parseDouble(partes[9]);
		Double sodio = Double.parseDouble(partes[10]);
		
		InfoNutricional info = null;
		if (tipo.equals("peso")) {
			info = new InfoNutricionalPeso(calorias, carbohidratos, grasasTotales, grasasSaturadas, proteinas, azucares, fibra, sodio);
		} else if (tipo.equals("unidad")) {
			info = new InfoNutricionalUnidad(calorias, carbohidratos, grasasTotales, grasasSaturadas, proteinas, azucares, fibra, sodio);
		}
		
		List<Alergeno> alergenos = new ArrayList<>();
		for (int i = 11; i < 15; i++) {
			if ("S".equals(partes[i])) {
				alergenos.add(Alergeno.values()[i - 11]);
			}
		}

		Ingrediente ingrediente = new Ingrediente(nombre, tipo, info);
		ingrediente.tieneAlergenos(alergenos.toArray(new Alergeno[0]));
		
		return ingrediente;
	}
	
	private static Plato procesarLineaPlato(String linea, Map<String, Ingrediente> ingredientesMap, Map<String, Plato> platosMap) {
		String[] partes = linea.split(";");
		String nombrePlato = partes[1];
		Map<Ingrediente, Integer> ingredientesDelPlato = new HashMap<>();
		Map<Plato, Integer> platosContenidos = new HashMap<>();
		Set<String> alergenosUnicos = new HashSet<String>();
	
		for (int i = 2; i < partes.length; i++) {
			if (partes[i].startsWith("INGREDIENTE ")) {
				String[] detalleIngrediente = partes[i].split(":");
				String nombreIngrediente = detalleIngrediente[0].substring("INGREDIENTE ".length());
				Integer cantidad = Integer.parseInt(detalleIngrediente[1]);
				Ingrediente ingrediente = ingredientesMap.get(nombreIngrediente);
				if (ingrediente != null) {
					ingredientesDelPlato.put(ingrediente, cantidad);
					alergenosUnicos.add(ingrediente.getAlergenos().toString());
				}
			} else if (partes[i].startsWith("PLATO ")) {
				String[] detallePlato = partes[i].split(":");
				String nombreSubPlato = detallePlato[0].substring("PLATO ".length());
				Integer cantidad = Integer.parseInt(detallePlato[1]);
				Plato subPlato = platosMap.get(nombreSubPlato);
				if (subPlato != null) {
					platosContenidos.put(subPlato, cantidad);
					alergenosUnicos.add(subPlato.getAlergenosUnicos().toString());
				}
			}
		}
		
		Plato plato = new Plato(nombrePlato);
		plato.setIngredientes(ingredientesDelPlato);
		plato.setPlatos(platosContenidos);
	
		return plato;
	}
	
	
	private static Menu procesarLineaMenu(String linea, Map<String, Plato> platosMap) {
		String[] partes = linea.split(";");
		Plato[] platosDelMenu = new Plato[partes.length - 1];

		for (int i = 1; i < partes.length; i++) {
			Plato plato = platosMap.get(partes[i]);
			if (plato != null) {
				platosDelMenu[i - 1] = plato;
			}
		}

		Menu menu = new Menu(platosDelMenu);
		return menu;
	}

	public static void leerFichero(String nombreFichero) {
		Map<String, Ingrediente> ingredientesMap = new HashMap<>();
		Map<String, Plato> platosMap = new HashMap<>();
		List<Menu> menusList = new ArrayList<>();

		List<String> lineas = new ArrayList<>(cargarInformacionExistente(nombreFichero));

		for (String linea : lineas) {
			if (linea.startsWith("INGREDIENTE_PESO;") || linea.startsWith("INGREDIENTE_UNIDAD;")) {
				Ingrediente ingrediente = procesarLineaIngrediente(linea);
				ingredientesMap.put(ingrediente.getNombre(), ingrediente);
			}
		}

		for (String linea : lineas) {
			if (linea.startsWith("PLATO;")) {
				Plato plato = procesarLineaPlato(linea, ingredientesMap, platosMap);
				platosMap.put(plato.getNombre(), plato);
			}
		}

		for (String linea : lineas) {
			if (linea.startsWith("MENU;")) {
				Menu menu = procesarLineaMenu(linea, platosMap);
				menusList.add(menu);
			}
		}

		menus = menusList;
	}
	
	public static List<Menu> getMenus() {
		return menus;
	}
} 