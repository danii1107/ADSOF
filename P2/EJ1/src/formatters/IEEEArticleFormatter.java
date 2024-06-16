package formatters;
/**
* Clase que representa un articulo ieee
*
* @author Daniel Birsan daniel.birsan@estudiante.uam.es
*/
import java.util.List;

import articles.Article;
import articles.Author;

public class IEEEArticleFormatter extends ArticleFormatter{
    /**
	* 	Constructor para `IEEErticleFormatter`. Establece el formato de artículo a "IEEE".
	*/
    public IEEEArticleFormatter(){
		super("IEEE");
	}

	/**
	* Formatea la lista de autores según las normas IEEE.
	* @param authors Lista de autores a formatear.
	* @return String con los autores formateados según IEEE.
	*/
    @Override
	public String formatAuthorsList(List<Author> authors) {
		StringBuffer sb = new StringBuffer();
		for (Author a : authors) {
			sb.append((sb.length()>0)?", ":"");
			sb.append(a.getInitial() + ". " + a.getLastName());
		}
		sb.append((sb.length()>0)?", ":"");
		return sb.toString();
	}
	
	/**
	* Formatea la referencia de un artículo según las normas IEEE.
	* @param a Artículo a formatear su referencia.
	* @return String con la referencia del artículo formateada según IEEE.
	*/
	@Override
	public String formatReference(Article a) {
		return formatAuthorsList(a.getAuthors()) + "\"" + a.getTitle() + "\"" +
			   ", " + a.getJournal() + ", vol. " + 
			   a.getVolume() + ", no. " + a.getIssue() + ", " + a.getYear() + ".";
	}
}