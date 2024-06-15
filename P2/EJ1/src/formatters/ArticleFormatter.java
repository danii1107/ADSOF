package formatters;
/**
* Clase que representa un formato de artículos
*
* @author Daniel Birsan daniel.birsan@estudiante.uam.es
*/
import java.util.List;

import articles.Article;
import articles.Author;

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
	
	/**
	 * Devuelve el nombre del formateador.
	 * @return Nombre del formateador.
	 */
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
