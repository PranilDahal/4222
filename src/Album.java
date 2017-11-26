import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;


public class Album {

	public int AlbumID;
	public String Title;
	public String CopyrightDate;
	public String Format;
	public String AlbumIdentifier;
	public String Producer;

	public Album(int albumID, String title, String copyrightDate, String format, String albumIdentifier,
			String producer) {
		super();
		AlbumID = albumID;
		Title = title;
		CopyrightDate = copyrightDate;
		Format = format;
		AlbumIdentifier = albumIdentifier;
		Producer = producer;
	}

	public int getAlbumID() {
		return AlbumID;
	}

	public void setAlbumID(int albumID) {
		AlbumID = albumID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getCopyrightDate() {
		return CopyrightDate;
	}

	public void setCopyrightDate(String copyrightDate) {
		CopyrightDate = copyrightDate;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getAlbumIdentifier() {
		return AlbumIdentifier;
	}

	public void setAlbumIdentifier(String albumIdentifier) {
		AlbumIdentifier = albumIdentifier;
	}

	public String getProducer() {
		return Producer;
	}

	public void setProducer(String producer) {
		Producer = producer;
	}


}
