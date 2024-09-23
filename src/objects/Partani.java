package objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import dbConnection.Conn;

public class Partani {
	private static int id;
	private static String slotID;
	private static String student;
	private static int numberOfStudents;
	private static String subject;
	private static int numberOfTopics;
	private static String topic;
	private static String startDate;
	private static String endDate;
	private static String note;
	private static String rcmdDuration;
	private static boolean endDateValid;

	public static Queue<String> partaniListByID = new LinkedList<String>();
	public static Queue<String> partaniListPerPersonByID = new LinkedList<String>();
	public static Queue<String> invalidPartaniIDList = new LinkedList<String>();

	public static class Subject {
		// The Subject class's relationship with the Partani class is Aggregation, not Inheritance
		// because of the difference between is-a relationship of Inheritance and has-a relationship of Aggregation	
		public static String[] subjectList = {"Math IGCSE PreDP", "Math AA DP1", "TOK DP2", 
				"Computer Science PreDP", "Computer Science DP1", "Computer Science DP2"};;
	}

	public static String getSlotID(int id) throws SQLException {
		// the slotID is private variable so it can only be accessed through the get function
		// because the data is stored in the database, this function retrieves data from the database	
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE ID = ?";
		PreparedStatement ps;
		ps = Conn.conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		return slotID = rs.getString("Slot_ID");
	}

	public static String getStudent(int id) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE ID = ?";
		// Select all column from Partani table 
		// where the value of a cell in the ID column is the parameter value below
		PreparedStatement ps;
		ps = Conn.conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		return student = rs.getString("Student");
	}

	public static int getNumberOfStudents(int id) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE ID = ?";
		PreparedStatement ps;
		ps = Conn.conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		return numberOfStudents = Integer.valueOf(rs.getString("Number_of_students"));
	}

	public static String getSubject(int id) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE ID = ?";
		PreparedStatement ps;
		ps = Conn.conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		return subject = rs.getString("Subject");
	}


	public static String getTopic(int id) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE ID = ?";
		PreparedStatement ps;
		ps = Conn.conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		return topic = rs.getString("Topic");
	}

	public static int getNumberOfTopics(int id) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE ID = ?";
		PreparedStatement ps;
		ps = Conn.conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		return numberOfTopics = Integer.valueOf(rs.getString("Number_of_topics"));
	}

	public static String getStartDate(int id) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE ID = ?";
		PreparedStatement ps;
		ps = Conn.conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		return startDate = rs.getString("Start_date");
	}

	public static String getEndDate(int id) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE ID = ?";
		PreparedStatement ps;
		ps = Conn.conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		return endDate = rs.getString("End_date");
	}

	public static String getNote(int id) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE ID = ?";
		PreparedStatement ps;
		ps = Conn.conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		return note = rs.getString("Note");
	}

	public static String setRcmdDuration (int numOfStu, String subject, int numOfTop) {
		// setting recommended time based on subject
		int timeForSubPerTop;
		if (subject.equals("TOK DP2")) {
			timeForSubPerTop = 10;
		}
		else {
			timeForSubPerTop = 15;
		}
		// for each extra student, the extra time required is 25% of the time required for one student
		// based on the assumption that the next student might have extra questions
		// hence, the total time will be a sum of a geometric sequence 
		// where  the first term = timeForSubPerTop*numOfTop, constant ratio = 0.25, number of terms = numOfStu
		rcmdDuration = String.valueOf(Math.round(numOfTop*timeForSubPerTop*(1 - Math.pow(0.25, numOfStu))/0.75));
		return rcmdDuration;
	}

	public static void addPartani (int slotID, String studentName, int numOfStudents, String subject, int numOfTopics, 
			String topic, String startDate, String endDate, String note) throws SQLException {
		// add a new Partani with all the input information except for the Partani ID which is autoincremented
		Conn.initializeConnection();
		String sql = "INSERT INTO Partani VALUES (null, '" + slotID + "', '" + studentName + "',"
				+ " " + numOfStudents + ",'" + subject + "', " + numOfTopics + ", '" + topic + "'," 
				+ " '" + startDate + "', '" + endDate +"', '" + note +"');";
		PreparedStatement ps = null;
		try {
			ps = Conn.conn.prepareStatement(sql);
			ps.executeUpdate();
			Conn.conn.commit();
		} catch (SQLException e) {
			Conn.conn.rollback();
			JOptionPane.showMessageDialog(null, "Error: Could not add Partani");
			System.out.println(e.getMessage() + e.getCause());
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	public static void setPartaniListByID() throws SQLException{
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani";
		PreparedStatement ps;
		try {
			ps = Conn.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				partaniListByID.add(rs.getString("ID"));
			} 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not set Partani list");
			System.out.println(e.getMessage() + e.getCause());
			e.printStackTrace();
		}
	}

	public static boolean checkEndDateValid (String endDate) {
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		int fmtNow = Integer.valueOf(df.format(now));
		int fmtEndDate = Integer.valueOf(endDate);
		if (fmtNow < fmtEndDate) {
			endDateValid = true;
		}
		else {
			endDateValid = false;
		}
		return endDateValid;
	}

	public static void updateInvalidPartaniIDList() {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani";
		PreparedStatement ps;
		try {
			ps = Conn.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (checkEndDateValid(rs.getString("End_date")) == false){
					invalidPartaniIDList.add(rs.getString("ID"));
				}
			} 
			System.out.println(invalidPartaniIDList);
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not add Partani");
			System.out.println(e.getMessage() + e.getCause());
			e.printStackTrace();
		}
	}

	public static void deletePartaniSlot(int partaniID) throws SQLException {
		// delete the Partani slot with the given ID
		Conn.initializeConnection();
		String sql = "DELETE FROM Partani WHERE ID = ?";
		// delete any rows in Partani table
		// where the value of the cell in the ID column is the parameter value below
		PreparedStatement ps;
		try {
			ps = Conn.conn.prepareStatement(sql);
			ps.setInt(1, partaniID);
			int rs = ps.executeUpdate();
			// this returns either the row count or 0 for SQL statements that return nothing
			if (rs == 0) {
				System.err.println("Update gone wrong");
			}
			Conn.conn.commit();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not delete Partani");
			System.out.println(e.getMessage() + e.getCause());
			e.printStackTrace();
		}
	}

	public static void updatePartaniIDList() throws SQLException{	
		String sql = "SELECT * FROM Partani";
		PreparedStatement ps = Conn.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		for (String x:invalidPartaniIDList) {
			int id = Integer.valueOf(x);
			while (rs.getString("ID").equals(id)) {
				Teacher.makeSlotAvailable(Integer.valueOf(rs.getString("Slot_ID")));
			}
				Partani.deletePartaniSlot(id);
		}
	}

	public static void setPartaniIDListPerPerson(String name) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Partani WHERE Student LIKE '%" + name+"%'";
		// filter the table by rows whose cell in the Student column has the input String name
		PreparedStatement ps = null;
		try {
			ps = Conn.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Conn.conn.commit();
			while (rs.next()) {
				partaniListPerPersonByID.add(rs.getString("ID"));
				// add all Partani IDs with the above condition
				// into the queue of Partani ID of the user with input name
			}	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not search for Partani");
			System.out.println(e.getMessage() + e.getCause());
			e.printStackTrace();
		}
	}


}
