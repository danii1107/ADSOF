package articles;
/**
* Clase que representa un artículo
*
* @author Daniel Birsan daniel.birsan@estudiante.uam.es
*/
import java.util.List;

public class Article{
	private List<Author> authors;
	private int year, volume, issue;
	private String title, journal;

	/**
	* Constructor para `Article`. Inicializa un artículo con sus detalles básicos.
	* @param authors Lista de autores del artículo.
	* @param year Año de publicación del artículo.
	* @param title Título del artículo.
	* @param journal Nombre del journal donde se publica el artículo.
	* @param vol Volumen del journal donde se publica el artículo.
	* @param issue Número de edición del journal donde se publica el artículo.
	*/
	public Article(List<Author> authors, int year, String title, String journal, 
			       int vol, int issue) {
		this.authors = authors;
		this.year = year;
		this.title = title;
		this.journal = journal;
		this.volume = vol;
		this.issue = issue;		
	}

	/**
	 * Devuelve la lista de autores del artículo.
	 * @return Lista de autores del artículo.
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * Devuelve el año de publicación del artículo.
	 * @return Año de publicación del artículo.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Devuelve el título del artículo.
	 * @return Título del artículo.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Devuelve el nombre del journal donde se publica el artículo.
	 * @return Nombre del journal donde se publica el artículo.
	 */
	public String getJournal() {
		return journal;
	}

	/**
	 * Devuelve el volumen 
	 * @return Volumen
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * Devuelve el número de edición
	 * @return Número de edición
	 */
	public int getIssue() {
		return issue;
	}
}
