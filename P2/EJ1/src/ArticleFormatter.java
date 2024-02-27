/**
*
* @author Daniel Birsan daniel.birsan@estudiante.uam.es
* @author Juan José Martínez Domínguez juanjose.martinezd@estudiante.uam.es
*
*/
import java.util.List;

public abstract class ArticleFormatter {
	protected String name;
	
	public abstract String formatAuthorsList(List<Author> authors);
	public abstract String formatReference(Article a);
	
	/**
	 * Constructor para `ArticleFormatter`. Inicializa el formateador con un nombre específico.
	 * @param formatterName Nombre del formateador APA O IEEE.
	 */
	public ArticleFormatter(String formatterName) {
		this.name = formatterName;
	}
	
	public String getName() {
		return this.name;
	}
	
	/**
	* Formatea la lista de artículos
	* @param articles Lista de artículos a formatear.
	* @return String con los artículos formateados.
	*/
	public String format(List<Article> articles) {
		String result = "";
		for (Article a : articles) 
			result+= this.formatReference(a)+"\n";
		return result;		
	}
}
