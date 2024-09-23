package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

import gui.LogInFrame;
import objects.Partani;
import objects.Slot;

public class Conn {
	public static Connection conn = null;
	// Connection is a special SQL variable type that is linked with a specific database
	// Based on the Connection, SQL statements are executed and results are returned

	public static void initializeConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:PartaniDatabase.db";
			if (conn == null) {
			conn = DriverManager.getConnection(url);
			// DriverManager.getConnection(URL) returns a connection to the URL
			conn.setAutoCommit(false);
			}
			// the if statement is to make sure the connection is opened and closed appropriately
			// to prevent the connection from being locked due to too many changes to database while running trials
		} catch (Exception e) {
			System.out.println("Error: Failed to connect to database\n" + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
	}

	public Conn()
	{
		initializeConnection();      
	}

	public static void main (String[]args) throws SQLException, ParseException {
		LogInFrame window = new LogInFrame();
		window.frame.setVisible(true);
		initializeConnection();
		Partani.updateInvalidPartaniIDList();
		Partani.updatePartaniIDList();
		Slot.updateAvailableSlots();
	}
}

