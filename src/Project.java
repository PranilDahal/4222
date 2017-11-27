import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Project {

	public static void main(String[] argv) {

		UseClient();

	}

	public static void UseClient(){
		System.out.println("You can choose one of the following options. Enter your choice #:\n 	#1. Display information of all the tables\n 	#2. Add Musician\n 	#3. Remove Musician\n 	"
				+ "#4. Add Album\n 	#5. Remove Album\n 	#6. Add Song\n 	#7. Remove Song\n YOUR CHOICE:");

		Scanner input = new Scanner(System.in);

		int user = input.nextInt();
		switch(user){
		
		case 1: DisplayAllInformation();
		System.out.println("----------------------------------------------------------------------------------------\n\n");
		break;
		
		case 2: AddMusician();
		System.out.println("----------------------------------------------------------------------------------------\n\n");
		break;
		
		case 3: RemoveMusician();
		System.out.println("----------------------------------------------------------------------------------------\n\n");
		break;
		
		case 6: AddSong();
		System.out.println("----------------------------------------------------------------------------------------\n\n");
		break;
		
		case 7: RemoveSong();
		System.out.println("----------------------------------------------------------------------------------------\n\n");
		break;
		
		case 4: AddAlbum();
		System.out.println("----------------------------------------------------------------------------------------\n\n");
		break;
		
		case 5: RemoveAlbum();
		System.out.println("----------------------------------------------------------------------------------------\n\n");
		break;
		
		default: 			
		}

		UseClient();


	}

	public static void DisplayAllInformation(){

		ArrayList<Musician> musicians= getAllMusicians();
		ArrayList<Address> addresses = getAllAddresses();
		ArrayList<Album> albums = getAllAlbums();
		ArrayList<Instrument> instruments = getAllInstruments();
		ArrayList<PerformedBy> performed = getAllPerformed();
		ArrayList<Plays> plays = getAllPlays();
		ArrayList<Song> songs = getAllSongs();

		System.out.println("Table 1: MUSICIAN");
		System.out.println("[");
		for(Musician m : musicians){
			System.out.println("SSN: "+m.getSSN()+" -- NAME: "+m.getName()+" -- PHONE: "+m.getPhone());
		}
		System.out.println("]\n");

		System.out.println("Table 2: ADDRESS");
		System.out.println("[");
		for(Address m : addresses){
			System.out.println("PHONENUM: "+m.getPhoneNum()+" -- STREET: "+m.getStreet()+" -- CITY: "+m.getCity()+" -- STATE: "+m.getState());
		}
		System.out.println("]\n");


		System.out.println("Table 3: ALBUM");
		System.out.println("[");
		for(Album m : albums){
			System.out.println("ALBUMID: "+m.getAlbumID()+" -- TITLE: "+m.getTitle()+" -- COPYRIGHTDATE: "+m.getCopyrightDate()+" -- FORMAT: "+m.getFormat()+" -- ALBUMIDENTIFIER: "+m.getAlbumIdentifier()+" -- PRODUCERSSN: "+m.getProducer());
		}
		System.out.println("]\n");


		System.out.println("Table 4: INSTRUMENT");
		System.out.println("[");
		for(Instrument m : instruments){
			System.out.println("ID: "+m.ID+" -- NAME: "+m.Name+" -- MUSICAL_KEY: "+m.key);
		}
		System.out.println("]\n");


		System.out.println("Table 5: PERFORMED_BY");
		System.out.println("[");
		for(PerformedBy m : performed){
			System.out.println("MUSICIANSSN: "+m.MusicianSSN+" -- SONG_TITLE: "+m.Song+" -- ALBUMID: "+m.AlbumID);
		}
		System.out.println("]\n");


		System.out.println("Table 6: PLAYS");
		System.out.println("[");
		for(Plays m : plays){
			System.out.println("MUSICIANSSN: "+m.Ssn+" -- INSTRUMENTID: "+m.InstrumentID);
		}
		System.out.println("]\n");


		System.out.println("Table 7: SONG");
		System.out.println("[");
		for(Song m : songs){
			System.out.println("SONG_TITLE: "+m.Title+" -- AUTHOR: "+m.Author+" -- ALBUMID: "+m.AlbumID);
		}
		System.out.println("]\n");


	}

	public static void AddMusician(){
		Scanner input = new Scanner(System.in);

		System.out.println("Enter a unique SSN for the new Musician:");
		String ssn = input.nextLine();
		System.out.println("Enter a name for the new Musician:");
		String name = input.nextLine();
		System.out.println("Choose a phoneNumber from the available list for "+name+"'s Address:");
		System.out.println("   [");
		for(Address s : getAllAddresses()){
			System.out.println("          "+s.getPhoneNum());
		}
		System.out.println("   ]\n");
		String phone = input.nextLine();
		Musician m = new Musician(ssn, name, phone);

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

	public static void RemoveMusician(){
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the SSN of the Musician you want to remove. Available Musicians are: ");
		System.out.println("   [");
		for(Musician m : getAllMusicians()){
			System.out.println("     SSN: "+m.getSSN()+" -- NAME: "+m.getName()+" -- PHONE: "+m.getPhone());
		}
		System.out.println("   ]\n");
		String SSN = input.nextLine();
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

	public static void AddSong(){

		Scanner input = new Scanner(System.in);

		System.out.println("Enter a TITLE for the new Song:");
		String title = input.nextLine();
		System.out.println("Enter a name for the Author of "+title+":");
		String author = input.nextLine();
		System.out.println("Choose the ID of the Album you want "+title+" to be in. Available Albums are: ");
		System.out.println("   [");
		for(Album a : getAllAlbums()){
			System.out.println("          "+"AlbumID: "+a.getAlbumID()+"   Album Title: "+a.getTitle());
		}
		System.out.println("   ]");
		System.out.println("If you want to create a brand new Album instead, Enter 0 to go to main menu. ENTER YOUR CHOICE:");
		int id = input.nextInt();
		if(id==0){
			return;
		}
		Song song = new Song(title, author, id);
		
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

	public static void RemoveSong(){

		Scanner input = new Scanner(System.in);

		System.out.println("Available Songs to delete are: ");
		System.out.println("   [");
		for(Song m : getAllSongs()){
			System.out.println("     SONG_TITLE: "+m.getTitle()+" -- AUTHOR: "+m.getAuthor()+" -- ALBUM_ID: "+m.getAlbumID());
		}
		System.out.println("   ]\n");
		System.out.println("Enter the SONG_TITLE, press ENTER, and type the ALBUM_ID of that song. Then press ENTER again to delete that song:");
		String title = input.nextLine();
		int AlbumID = input.nextInt();
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

	public static void AddAlbum(){

		Scanner input = new Scanner(System.in);

		System.out.println("Enter a unique ALBUMID for the new Album. It MUST NOT be any of the following:");
		System.out.println("   [");
		for(Album m : getAllAlbums()){
			System.out.println("     ALBUMID: "+m.getAlbumID());
		}
		System.out.println("   ]\n Enter ALBUMID:");
		String id = input.nextLine();
		System.out.println("Enter a TITLE for the new Album:");
		String title = input.nextLine();
		System.out.println("Enter the COPYRIGHT DATE of "+title+" in YYYY-MM-DD format: ");
		String date = input.nextLine();
		System.out.println("Enter the FORMAT of "+title+" (example: CD, DVD, etc..): ");
		String format = input.nextLine();
		System.out.println("Enter an identifier for "+title+". It can be any combination of letters and character: ");
		String identifier = input.nextLine();
		System.out.println("Enter the SSN of the producer. The following producers are available:");
		System.out.println("   [");
		for(Musician m : getAllMusicians()){
			System.out.println("     SSN: "+m.getSSN()+" -- NAME: "+m.getName());
		}
		System.out.println("   ]");
		System.out.println("If you want to create a brand new Producer instead, Enter 0 to go to main menu. ENTER YOUR CHOICE:");
		String ssn = input.nextLine();
		if(ssn=="0"){
			return;
		}
		Album album = new Album(Integer.parseInt(id), title, date, format, identifier, ssn);
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

	public  static void RemoveAlbum(){
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the ALBUMID the Album you want to remove. Available Albums are: ");
		System.out.println("   [");
		for(Album m : getAllAlbums()){
			System.out.println("     ALBUMID: "+m.getAlbumID()+" -- TITLE: "+m.getTitle());
		}
		System.out.println("   ]\n");
		int id = input.nextInt();

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
