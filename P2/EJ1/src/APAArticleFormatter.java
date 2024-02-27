/**
*
* @author Daniel Birsan daniel.birsan@estudiante.uam.es
* @author Juan José Martínez Domínguez juanjose.martinezd@estudiante.uam.es
*
*/
import java.util.List;

public class APAArticleFormatter extends ArticleFormatter {
	/**
	* 	Constructor para `APAArticleFormatter`. Establece el formato de artículo a "APA".
	*/
	public APAArticleFormatter(){
		super("APA");
	}
	
	/**
	* Formatea la lista de autores según las normas APA.
	* @param authors Lista de autores a formatear.
	* @return String con los autores formateados según APA.
	*/
	@Override
	public String formatAuthorsList(List<Author> authors) {
		StringBuffer sb = new StringBuffer();
		for (Author a : authors) {
			sb.append((sb.length()>0)?", ":"");
			sb.append(a.getLastName() + ", "  + a.getInitial() + ".");
		}
		return sb.toString();
	}
	
	/**
	* Formatea la referencia de un artículo según las normas APA.
	* @param a Artículo a formatear su referencia.
	* @return String con la referencia del artículo formateada según APA.
	*/
	@Override
	public String formatReference(Article a) {
		return formatAuthorsList(a.getAuthors()) + " "+
			   "(" + a.getYear() + "). " + 
			   a.getTitle() + ". " + a.getJournal() + ", " +
			   a.getVolume() + "(" + a.getIssue() + ").";
	}
}
