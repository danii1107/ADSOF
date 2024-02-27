/**
*
* @author Daniel Birsan daniel.birsan@estudiante.uam.es
* @author Juan José Martínez Domínguez juanjose.martinezd@estudiante.uam.es
*
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

	public List<Author> getAuthors() {
		return authors;
	}

	public int getYear() {
		return year;
	}

	public String getTitle() {
		return title;
	}

	public String getJournal() {
		return journal;
	}

	public int getVolume() {
		return volume;
	}

	public int getIssue() {
		return issue;
	}
}
