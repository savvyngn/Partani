package objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import dbConnection.Conn;

public class User {

	private static String fullName;
	private static String userName;
	private static String password;
	private static int hashedPassword;

	public static LinkedList<String> fullnameList = new LinkedList<String>();
	public static LinkedList<String> usernameList = new LinkedList<String>();
	public static LinkedList<String> passwordList = new LinkedList<String>();

	public User(int i) {
		fullName = fullnameList.get(i);
		userName = usernameList.get(i);
		password = passwordList.get(i);
		hashedPassword = setHashedPassword(password);
	}

	public static void setFullnameList() throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Users";
		try {
			PreparedStatement ps = Conn.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				fullnameList.add(rs.getString("Full name"));
			}

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Error: Could not update Fullname List");
			System.out.println(sqle.getMessage());
			sqle.printStackTrace();
		}
	}

	public static void setUsernameList() throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Users";
		try {
			PreparedStatement ps = Conn.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				usernameList.add(rs.getString("Username"));
			}

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Error: Could not update Username List");
			System.out.println(sqle.getMessage());
			sqle.printStackTrace();
		}
	}

	public static String getUsernameInfo(int i) throws SQLException{
		return (usernameList.get(i));
	}

	public static void setPasswordList() throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT * FROM Users";
		try {
			PreparedStatement ps = Conn.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				passwordList.add(rs.getString("Password"));
			}

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Error: Could not update Username List");
			System.out.println(sqle.getMessage());
			sqle.printStackTrace();
		}
	}

	public static String getPasswordInfo(int i) throws SQLException{
		return (passwordList.get(i));
	}

	public static void addUser(String name, String username, String hashedPwd){
	// add a new User in the database by adding a row with all the input information
		Conn.initializeConnection();
		try {
			String sql = "INSERT INTO Users values (null, '" + name + "', '" + username + "','" + hashedPwd + "');";
			// the "null" value in the beginning represents the auto-incremented ID that is automatically generated
			PreparedStatement ps = Conn.conn.prepareStatement(sql);
			ps.executeUpdate();
			Conn.conn.commit();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not add user");
			System.out.println(e.getMessage() + e.getCause());
			e.printStackTrace();
		}
	}

	public static int setHashedPassword(String password) {
	// hash all the input passwords to increase the data security of the users
		hashedPassword = password.hashCode();
		return hashedPassword;
	}	

	public static boolean validUserInfo (String name, String hashedPwd) throws SQLException {
		boolean validUserinfo = false;
		for (int i = 0; i < User.usernameList.size(); i++) {	
			if (name != null && hashedPwd != null && name.equals(User.getUsernameInfo(i)) && hashedPwd.equals(User.getPasswordInfo(i))) {
				validUserinfo = true;
				break;
			}
			else {
				validUserinfo = false;
			}
		}
		return validUserinfo;
	}

	public static String getFullnameFromUsername(String username) throws SQLException {
		Conn.initializeConnection();
		String sql = "SELECT Full_name FROM Users WHERE Username = '" + username + "' ";
		try {
			PreparedStatement ps = Conn.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Conn.conn.commit();
			fullName = rs.getString("Full_name");
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Error: Could not get Full name from Username");
			System.out.println(sqle.getMessage());
			sqle.printStackTrace();
		}
		return fullName;
	}
}
