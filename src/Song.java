
public class Song {

	public String Title;
	public String Author;
	public int AlbumID;
	
	public Song(String title, String author, int albumID) {
		super();
		Title = title;
		Author = author;
		AlbumID = albumID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public int getAlbumID() {
		return AlbumID;
	}

	public void setAlbumID(int albumID) {
		AlbumID = albumID;
	}
	
	
	
}
