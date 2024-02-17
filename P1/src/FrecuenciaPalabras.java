/**
* Esta aplicación calcula la frecuencia de longitudes de los parámetros
*
* @author Daniel Birsan daniel.birsan@estudiante.uam.es
* @author Juan José Martínez Domínguez juanjose.martinezd@estudiante.uam.es
*
*/
public class FrecuenciaPalabras {
	/**
	* Punto de entrada a la aplicación.
	* Este método imprime las longitudes y frecuencias de palabras proporcionadas por la línea de comandos
	* @param args Los argumentos de la línea de comando. Se esperan palabras, como cadenas
	*/
	public static void main(String[] args) {
		if (args.length == 0)
			System.err.println("Se espera al menos una palabra como parametro.");
		else {
			LongitudPalabras palabras = new LongitudPalabras(args);
			imprimeFrecuencias(palabras);
		}
	}
	/**
	* Imprime longitudes y frecuencias.
	* @param palabras Palabras de las que se quiere calcular las longitudes y contar las frecuencias.
	*/
	private static void imprimeFrecuencias(LongitudPalabras palabras) {
		System.out.println(palabras);
		for(int longitud : palabras.getLongitudesUnicas())
			System.out.println("Hay "+palabras.getFrecuencia(longitud)+" palabras de "+longitud+" letras.");
	}
}