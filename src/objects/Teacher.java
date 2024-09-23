package objects;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dbConnection.Conn;

public class Teacher extends User {
	
// Teacher is a subclass of the super class User
// Teacher has all the attributes of User 
// and extra methods of makeSlotUnavailable() and makeSlotAvailable()

	public Teacher(int i) {
		super(i);
	}
	
	public static void makeSlotUnavailable(int i) throws SQLException {
	// change the availability a slot in both the Availability List and the table
		Conn.initializeConnection();
		Slot.slotListbyAvailability[i] = false;
		// change the availability of the slot into false in the Availability list 
		// -> save time from having to run setAvailabilityList() function
		String sql = "UPDATE Slots SET Availability = 'Occupied' WHERE ID = ?";
		// update the value of the cell in the column Availability and row ID into "Occupied"
		try {
			PreparedStatement ps = Conn.conn.prepareStatement(sql);
			ps.setInt(1, i);
			ps.executeUpdate();
			// change the Availability value of the Slot in the Slots table into "Occupied"
			Conn.conn.commit();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not change this slot into unavailable");
			System.out.println(e.getMessage() + e.getCause());
			e.printStackTrace();
		}
	}

	public static void makeSlotAvailable(int i) {
		// change the availability a slot in both the Availability List and the table
		Conn.initializeConnection();
		Slot.slotListbyAvailability[i] = true;
		// change the availability of the slot into true in the Availability list 
		// -> save time from having to run setAvailabilityList() function
		String sql = "UPDATE Slots SET Availability = 'Available' WHERE ID = ?";
		try {
			PreparedStatement ps = Conn.conn.prepareStatement(sql);
			ps.setInt(1, i);
			ps.executeUpdate();
			// change the Availability value of the Slot in the Slots table into "Available"
			Conn.conn.commit();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not change this slot into unavailable");
			e.printStackTrace();
		}
	}

}
