import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Project {

	public static void main(String[] argv) {
		
//		AddMusician(new Musician("123","TEST","19092221111"));
//		AddAlbum(new Album(11,"TEST","2000-01-01","DVD","aaaaaaaa","123"));
//		AddSong(new Song("TEST SONG", "TEST AUTHOR", 11));
//		AddSong(new Song("TEST SONG222", "TEST AUTHOR222", 11));
		
//		RemoveSong("TEST SONG222", 11);
//		RemoveSong("TEST SONG", 11);
//		RemoveAlbum(11);
//		RemoveMusician("123");
		
		
	}

	public static void AddMusician(Musician m){
		Connection connection = initiateDB();
		try {

			String stm = "INSERT INTO Musician(SSN, Name, PhoneNum) VALUES (?,?,?)";
			PreparedStatement pst = connection.prepareStatement(stm);
			pst.setString(1, m.getSSN());
			pst.setString(2, m.getName());
			pst.setString(3, m.getPhone());
			pst.executeUpdate();
			System.out.println("You have added musician "+m.getName()+" with SSN="+m.getSSN()+" into the database.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement in AddMusician.");
		}

		CloseConnection(connection);

	}

	public static void RemoveMusician(String SSN){
		Connection connection = initiateDB();
		try {

			String stm = "DELETE FROM Musician WHERE ssn=?";
			PreparedStatement pst = connection.prepareStatement(stm);
			pst.setString(1, SSN);;
			pst.executeUpdate();
			System.out.println("You have removed the musician with SSN="+SSN+" from the database. \nIf this musician is a producer of an album in the database, this command will not take any effect.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement in RemoveMusician.");
		}

		CloseConnection(connection);

	}

	public static void AddSong(Song song){

		Connection connection = initiateDB();
		try {

			String stm = "INSERT INTO song(song_title, Author, Album_ID) VALUES (?,?,?)";
			PreparedStatement pst = connection.prepareStatement(stm);
			pst.setString(1, song.getTitle());
			pst.setString(2, song.getAuthor());
			pst.setInt(3, song.getAlbumID());
			pst.executeUpdate();
			System.out.println("You have added a new Song called '"+song.getTitle()+"' into the AlbumID "+song.getAlbumID()+".");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement in AddSong.");
		}

		CloseConnection(connection);

	}

	public static void RemoveSong(String title, int AlbumID){

		Connection connection = initiateDB();
		try {

			String stm = "DELETE FROM Song WHERE song_title=? AND Album_ID=?";
			PreparedStatement pst = connection.prepareStatement(stm);
			pst.setString(1, title);
			pst.setInt(2, AlbumID);
			pst.executeUpdate();
			System.out.println("You have removed the song '"+title+"' from AlbumID "+AlbumID+".");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement in RemoveSong.");
		}

		CloseConnection(connection);

	}

	public static void AddAlbum(Album album){

		Connection connection = initiateDB();
		try {

			String stm = "INSERT INTO album(Album_Id, Title, CopyrightDate, Format,AlbumIdentifier, ProducerSSN) VALUES (?,?,CAST(? AS DATE),?,?,?)";
			PreparedStatement pst = connection.prepareStatement(stm);
			pst.setInt(1, album.getAlbumID());
			pst.setString(2, album.getTitle());
			pst.setString(3, album.getCopyrightDate());
			pst.setString(4, album.getFormat());
			pst.setString(5, album.getAlbumIdentifier());
			pst.setString(6, album.getProducer());
			pst.executeUpdate();
			System.out.println("You have added a new Album into the database with AlbumID "+album.getAlbumID()+" called '"+album.getTitle()+"'.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement in AddAlbum.");
		}

		CloseConnection(connection);
	}

	public  static void RemoveAlbum(int id){


		Connection connection = initiateDB();
		try {

			String stm = "DELETE FROM album WHERE album_id=?";
			PreparedStatement pst = connection.prepareStatement(stm);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("You have removed album with AlbumID of "+id+".");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement in RemoveAlbum.");
		}

		CloseConnection(connection);
	}

	public static ArrayList<Musician> getAllMusicians(){

		ArrayList<Musician> musicians = new ArrayList<Musician>();
		Connection connection = initiateDB();
		try {

			String stm = "SELECT * FROM musician";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(stm);
			while(rs.next()){
				Musician temp = new Musician(rs.getString("ssn"), rs.getString("name"), rs.getString("phonenum"));
				musicians.add(temp);
			}
			return musicians;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement GetAllMusicians.");
		}

		CloseConnection(connection);
		return null;

	}

	public static ArrayList<Address> getAllAddresses(){

		ArrayList<Address> addresses = new ArrayList<Address>();
		Connection connection = initiateDB();
		try {

			String stm = "SELECT * FROM address";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(stm);
			while(rs.next()){
				Address temp = new Address(rs.getString("phonenum"), rs.getString("street"), rs.getString("city"), rs.getString("state"));
				addresses.add(temp);
			}
			return addresses;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement GetAllAddresses.");
		}

		CloseConnection(connection);
		return null;
	}

	public static ArrayList<Album> getAllAlbums(){

		ArrayList<Album> albumes = new ArrayList<Album>();
		Connection connection = initiateDB();
		try {

			String stm = "SELECT * FROM album";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(stm);
			while(rs.next()){
				Album temp = new Album(rs.getInt("album_id"), rs.getString("title"), rs.getDate("copyrightdate").toString(), rs.getString("format"), rs.getString("albumidentifier"), rs.getString("producerssn"));
				albumes.add(temp);
			}
			return albumes;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement GetAllalbums.");
		}

		CloseConnection(connection);
		return null;
	}

	public static ArrayList<Instrument> getAllInstruments(){

		ArrayList<Instrument> instruments = new ArrayList<Instrument>();
		Connection connection = initiateDB();
		try {

			String stm = "SELECT * FROM instrument";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(stm);
			while(rs.next()){
				Instrument temp = new Instrument(rs.getString("name"), rs.getInt("instrumentid"), rs.getString("musical_key"));
				instruments.add(temp);
			}
			return instruments;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement GetAllInstrument.");
		}

		CloseConnection(connection);
		return null;

	}

	public static ArrayList<PerformedBy> getAllPerformed(){

		ArrayList<PerformedBy> instruments = new ArrayList<PerformedBy>();
		Connection connection = initiateDB();
		try {

			String stm = "SELECT * FROM performed_by";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(stm);
			while(rs.next()){
				PerformedBy temp = new PerformedBy(rs.getString("musicianssn"), rs.getString("song_title"), rs.getInt("album_id"));
				instruments.add(temp);
			}
			return instruments;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement GetAllPerformedBy.");
		}

		CloseConnection(connection);
		return null;

	}

	public static ArrayList<Plays> getAllPlays(){

		ArrayList<Plays> instruments = new ArrayList<Plays>();
		Connection connection = initiateDB();
		try {

			String stm = "SELECT * FROM plays";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(stm);
			while(rs.next()){
				Plays temp = new Plays(rs.getString("musicianssn"), rs.getInt("instrumentid"));
				instruments.add(temp);
			}
			return instruments;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement GetAllPlays.");
		}
		CloseConnection(connection);
		return null;

	}


	public static ArrayList<Song> getAllSongs(){

		ArrayList<Song> instruments = new ArrayList<Song>();
		Connection connection = initiateDB();
		try {

			String stm = "SELECT * FROM song";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(stm);
			while(rs.next()){
				Song temp = new Song(rs.getString("song_title"), rs.getString("author"), rs.getInt("album_id"));
				instruments.add(temp);
			}
			return instruments;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create Statement GetAllSongs.");
		}

		CloseConnection(connection);
		return null;

	}

	public static Connection initiateDB(){
		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("JDBC Driver error ");
			e.printStackTrace();
			return null;

		}

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/CS4222", "postgres",
					"password");

		} catch (SQLException e) {

			System.out.println("Connection to DB Failed!");
			e.printStackTrace();
			return null;

		}

		if (connection != null) {
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;

	}

	public static void CloseConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to close connection after GetAllSongs.");
		}

	}

}
