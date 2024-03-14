import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManejadorFicheros {
	private static List<Menu> menus = new ArrayList<Menu>();
	
	public static void guardarFichero(String nombreFichero, List<Menu> menus) {
		try (OutputStream out = new FileOutputStream(nombreFichero)) {
			String info = "";
			for (Menu menu : menus) {
				for (Plato plato : menu.getPlatos()) {
					for (Ingrediente ingrediente : plato.getIngredientes().keySet()) {
						if (ingrediente.getInfo().getTipo().equals("unidad")) {
							info += "INGREDIENTE_UNIDAD;";
						} else {
							info += "INGREDIENTE_PESO;";
						}
						info += ingrediente.getNombre();
						info += ";";
						if (ingrediente.getTipo() != null) {
							info += ingrediente.getTipo();
							info += ";";
						} else if (ingrediente.getTipo2() != null) {
							info += ingrediente.getTipo2();
							info += ";";
						}
						info += ingrediente.mostrarInfoFichero();
						info += ingrediente.alergenosFichero(ingrediente.getTabla());
						info += "\n";
					}
					info += "PLATO;";
					info += plato.getNombre();
					for (Map.Entry<Plato, Integer> entrada : plato.getPlatos().entrySet()) {
						info += ";PLATO ";
						info += entrada.getKey().getNombre();
						info += ":";
						info += entrada.getValue().toString();
					}
					for (Map.Entry<Ingrediente, Integer> entrada : plato.getIngredientes().entrySet()) {
						info += ";INGREDIENTE ";
						info += entrada.getKey().getNombre();
						info += ":";
						info += entrada.getValue().toString();
					}
					info += "\n";
				}
				info += "MENU;";
				info += menu.mostrarPlatosFichero();
				info += "\n";
			}
			out.write(info.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerFichero(String nombreFichero) {
		try (InputStream in = new FileInputStream(nombreFichero)) {
			// guardar en atributo menus
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Menu> getMenus() {
		return menus;
	}
} 