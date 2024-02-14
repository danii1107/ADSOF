import java.util.List;

public class IEEEArticleFormatter extends ArticleFormatter{
    
    public IEEEArticleFormatter(){
		super("IEEE");
	}

    @Override
	public String formatAuthorsList(List<Author> authors) {
		StringBuffer sb = new StringBuffer();
		for (Author a : authors) {
			sb.append((sb.length()>0)?", ":"");
			sb.append(a.getInitial() + ". "  + a.getLastName() + ",");
		}
		return sb.toString();
	}
	
	@Override
	public String formatReference(Article a) {
		return formatAuthorsList(a.getAuthors()) + "\"" + a.getTitle() + "\"" +
			   ", " + a.getJournal() + ", vol. " + 
			   a.getVolume() + ", no. " + a.getIssue() + ", " + a.getYear() + ".";
	}
}
