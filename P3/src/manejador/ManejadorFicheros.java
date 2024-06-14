/**
 * Clase estática que lee y escribe ficheros para crear o guardar instancias
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.manejador;

import src.informacion.*;
import src.menus.Menu;
import src.comida.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class ManejadorFicheros {
	private static List<Menu> menus = new ArrayList<>();

	public static List<Menu> getMenus() {
		return menus;
	}

	/**
	 * Método estático que escribe en un fichero las instancias necesarias para crear
	 * los menús que recibe en formato csv
	 * @param nombreFichero Path al fichero
	 * @param menus Lista de menus a ser guardados en el fichero
	 */
	public static void guardarFichero(String nombreFichero, List<Menu> menus) {
		StringBuilder nuevaInfo = new StringBuilder();
		Set<String> infoExistente = cargarInformacionExistente(nombreFichero);
	
		for (Menu menu : menus) {
			for (Plato plato : menu.getPlatos()) {
				for (Comida ingrediente : plato.getComponentes().keySet()) {
					String infoIngrediente = ingrediente.getInfoFichero();
					if (infoExistente.add(infoIngrediente)) {
						nuevaInfo.append(infoIngrediente).append("\n");
					}
				}
				String infoPlato = plato.getInfoFichero();
				if (infoExistente.add(infoPlato)) {
					nuevaInfo.append(infoPlato).append("\n");
				}
			}
			String infoMenu = menu.getInfoFichero();
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

	/**
	 * Método estático que carga la información del fichero que recibe e instancia
	 * los objetos a partir de los datos del mismo
	 * @param nombreFichero Path del fichero que contiene los datos
	 */
	public static void leerFichero(String nombreFichero) {
		List<String> lineas = new ArrayList<>(cargarInformacionExistente(nombreFichero));
		
		Map<String, Comida> comida = crearIngredientes(lineas);
		comida = crearPlatos(lineas, comida);
		menus = crearMenus(lineas, comida);
	}

	/**
	 * Método privado que lee la información contenida en el fichero para evitar
	 * inserciones duplicadas y para instancias los objetos al leerlo 
	 * @param nombreFichero Path del fichero con los datos
	 * @return Conjunto con las líneas leídas del fichero
	 */
	public static Set<String> cargarInformacionExistente(String nombreFichero) {
		Set<String> infoExistente = new HashSet<>();
		try {
			List<String> lineasExistentes = Files.readAllLines(Paths.get(nombreFichero));
			infoExistente.addAll(lineasExistentes);
		} catch (IOException e) {}
		return infoExistente;
	}

	/**
	 * Método privado que instancia platos e invoca al método que asigna
	 * la información del fichero a la instancia 
	 * @param lineas Líneas leídas del fichero
	 * @return Mapa con las nuevas instancias creadas
	 */
	private static Map<String, Comida> crearIngredientes(List<String> lineas) {
		Map<String, Comida> comida = new HashMap<>();	
		Ingrediente ingrediente = null;

		for (String linea : lineas) {
			Boolean esIngrediente = false;
			TipoIngrediente tipoENum = null;
			String tipoStr = null;
			if (linea.startsWith("INGREDIENTE_PESO;")) {
				try {
					tipoENum = TipoIngrediente.valueOf(linea.split(";")[2]);
				}
				catch (IllegalArgumentException e) {
					tipoStr = linea.split(";")[2];
				}
				if (tipoENum != null) {
					ingrediente = new Ingrediente(linea.split(";")[1], tipoENum,
						new InfoNutricionalPeso(0.0, 0.0, 0.0, 0.0, 0.0 ,0.0 , 0.0, 0.0));
				} else {
					ingrediente = new Ingrediente(linea.split(";")[1], tipoStr,
						new InfoNutricionalPeso(0.0, 0.0, 0.0, 0.0, 0.0 ,0.0 , 0.0, 0.0));
				}
				esIngrediente = true;
			} else if (linea.startsWith("INGREDIENTE_UNIDAD;")) {
				try {
					tipoENum = TipoIngrediente.valueOf(linea.split(";")[2]);
				}
				catch (IllegalArgumentException e) {
					tipoStr = linea.split(";")[2];
				}
				if (tipoENum != null) {
					ingrediente = new Ingrediente(linea.split(";")[1], tipoENum,
						new InfoNutricionalUnidad(0.0, 0.0, 0.0, 0.0, 0.0 ,0.0 , 0.0, 0.0));
				} else {
					ingrediente = new Ingrediente(linea.split(";")[1], tipoStr,
						new InfoNutricionalUnidad(0.0, 0.0, 0.0, 0.0, 0.0 ,0.0 , 0.0, 0.0));
				}
				esIngrediente = true;
			}
			if (esIngrediente) {
				comida.put(linea.split(";")[1], ingrediente.setInfoFichero(linea));
			}
			esIngrediente = false;
		}

		return comida;
	}

	/**
	 * Método privado que instancia platos e invoca al método que asigna
	 * la información del fichero a la instancia
	 * @param lineas Líneas leídas del fichero
	 * @param comida Mapa con la comida ya instanciada
	 * @return El mapa que recibe modificado con las nuevas instancias
	 */
	private static Map<String, Comida> crearPlatos(List<String> lineas, Map<String, Comida> comida) {
		for (String linea : lineas) {
			if (linea.startsWith("PLATO;")) {
				Plato plato = new Plato(linea.split(";")[1]);
				comida.put(linea.split(";")[1], plato.setInfoFichero(linea, comida));
			}
		}
		
		return comida;
	}

	/**
	 * Método privado que instancia platos e invoca al método que asigna
	 * la información del fichero a la instancia
	 * @param lineas Líneas leídas del fichero
	 * @param comida Mapa con la comida ya instanciada
	 * @return Lista con los menús que se han instanciado
	 */
	private static List<Menu> crearMenus(List<String> lineas, Map<String, Comida> comida) {
		List<Menu> menus = new ArrayList<>();

		for (String linea : lineas) {
			if (linea.startsWith("MENU;")) {
				Menu menu = new Menu();
				menu.setInfoFichero(linea, comida);
				menus.add(menu);
			}
		}

		return menus;
	}
} 