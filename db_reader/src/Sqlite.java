import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Sqlite {
	static Connection conn;
	static Statement stat;
	static PreparedStatement prep;
	public static void sqliteDBCreator(String table) throws Exception {
		
		System.out.println("sd_app::sqliteDBCreator     : 1" );
		
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:parking_info_2.db");
	    stat = conn.createStatement();
	    if (table.compareTo("MultiMeterPost") == 0)
	    {
	    	System.out.println("sd_app::sqliteDBCreator     : 2 " + table.toString());
	    	stat.executeUpdate("drop table if exists " + table.toString() +";");
		    stat.executeUpdate("create table " + table.toString() + "(_id INTEGER PRIMARY KEY, BlockName TEXT, Terminal_I TEXT, Lat FLOAT, Lon FLOAT);");
		    prep = conn.prepareStatement(
		      "insert into "+table.toString()+" values (?, ?, ?, ?, ?);");
		    
		    System.out.println("sd_app::sqliteDBCreator     : 2" );	
	    }
	    else if (table.compareTo("SingleHeadMeters") == 0)
	    {
	    	System.out.println("sd_app::sqliteDBCreator     : 2" + table.toString());
	    	stat.executeUpdate("drop table if exists " + table.toString() +";");
		    stat.executeUpdate("create table " + table.toString() + "(_id INTEGER PRIMARY KEY, PostId INTEGER, Note TEXT, MeterId INTEGER,  SHAPE_fid INTEGER, Lat FLOAT, Lon FLOAT);");
		    prep = conn.prepareStatement(
		      "insert into "+ table.toString() +" values (?, ?, ?, ?, ?, ?, ?);");
		    
		    System.out.println("sd_app::sqliteDBCreator     : 2" );	
	    }
	    
	}
	
	public static void sqliteAddBatch(int ObjID, String BlockName, String nvTerminal_I,
			float nvLat, float nvLon)throws Exception {

		System.out.println("ObjID     : 1 " + ObjID);
		
		prep.setInt(1, ObjID);
		prep.setString(2, BlockName);
	    prep.setString(3, nvTerminal_I);
	    prep.setFloat(4, nvLat);
	    prep.setFloat(5, nvLon);
	    prep.addBatch();

	    System.out.println("ObjID     : 2 " + ObjID);
	    conn.setAutoCommit(false);
	    prep.executeBatch();
	    conn.setAutoCommit(true);
	    System.out.println("ObjID     : 3 " + ObjID);
	}
	
	public static void sqliteAddBatch(int ObjID, int PostId, String Note, int MeterId, int SHAPE_fid,
			float nvLat, float nvLon)throws Exception {
		
		
		System.out.println("ObjID     : 1 " + ObjID);
		
		prep.setInt(1, ObjID);
		prep.setInt(2, PostId);
		prep.setString(3, Note);
		prep.setInt(4, MeterId);
		prep.setInt(5, SHAPE_fid);
	    prep.setFloat(6, nvLat);
	    prep.setFloat(7, nvLon);
	    prep.addBatch();

	    System.out.println("ObjID     : 2 " + ObjID);
	    conn.setAutoCommit(false);
	    prep.executeBatch();
	    conn.setAutoCommit(true);
	    System.out.println("ObjID     : 3 " + ObjID);
	}
	
	public static void sqlitePrintDB() throws Exception{
	    ResultSet rs = stat.executeQuery("select * from parking_info;");
	    while (rs.next()) {
	      System.out.println("_id = " + rs.getInt("_id"));
	      System.out.println("Lat = " + rs.getFloat("Lat"));
	      System.out.println("Lon = " + rs.getFloat("Lon"));
	    }
	    rs.close();
	    conn.close();
	  }

}
