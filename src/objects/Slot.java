package objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JOptionPane;

import dbConnection.Conn;

public class Slot {

	private String day;
	private String period;
	private boolean available;

	private static String[] slotDay = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
	private static String[] slotPeriod = new String[] {"1", "2", "3", "4", "5", "6", "7", "8"};
	public static String[] slotList = new String[40];
	public static boolean[] slotListbyAvailability = new boolean[40];
	public static boolean slotIsAvailable = true;
	public static String[] slotListbyAvailabilityGUI = new String[40];

	public Slot(int i) {
		setDay(slotDay[i]);
		setPeriod(slotPeriod[i]);
		setAvailable(slotListbyAvailability[i]);
	}

	public static void setSlotList() {
	// create slotList array based on the given day and period
		int index = 0;
		for (int per = 0; per < 8; per ++) {
			for (int day = 0; day < 5; day ++) {
				slotList[index]= slotDay[day] + " " + slotPeriod[per];
				index ++;
			}
		}
	}

	public static void setAvailabilityList() throws SQLException{
	// set the values for slotListbyAvailability array by retrieving data from the database
		Conn.initializeConnection();
		String sql = "SELECT Availability FROM Slots"; // Query
		// select only the Availability column from Slots table
		try {
			PreparedStatement ps = Conn.conn.prepareStatement(sql); 
			ResultSet rs = ps.executeQuery(); 
			int index = 0;
			while (rs.next()){
			// loop over all the row until there is no next row
				if ((rs.getString("Availability")).equals("Available")) {
					slotListbyAvailability[index] = true;
				}
				else {
					slotListbyAvailability[index] = false;
				} 
				index ++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage() + e.getCause());
			e.printStackTrace();
		}
	}


	public static void updateAvailableSlots() throws ParseException, SQLException {
	// update slotListbyAvailability by filtering out the Partani whose end date has passed
	// also deleted the expired Partani
		Conn.initializeConnection();
		String sql = "SELECT Slots.ID, Slots.Availability, Partani.Slot_ID, Partani.End_date FROM Slots INNER JOIN Partani ON Slots.ID = PARTANI.Slot_ID;";
		// select both tables where Slot ID of a registered Partani is signed up
		try {
			PreparedStatement ps = Conn.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
				if (Partani.checkEndDateValid(rs.getString("End_date")) == true) {
					// check if the corresponding Partani's end date is still after current time
					// if yes then the Partani is still valid 
					slotListbyAvailability[(Integer.valueOf(rs.getString("ID")))] = false;
					Teacher.makeSlotUnavailable(Integer.valueOf(rs.getString("ID")));
					// the Slot of the Partani is occupied until invalid
				}
				else  {
					Partani.updateInvalidPartaniIDList();
					// outdated Partani's get added to the invalidPartaniList
					Partani.updatePartaniIDList();
					// all Partani's in the invalidPartaniList get deleted
					slotListbyAvailability[(Integer.valueOf(rs.getString("ID")))] = true;
					Teacher.makeSlotAvailable((Integer.valueOf(rs.getString("ID"))));
					// the Slot of the Partani is set available again
				}
		} catch (SQLException e) {
			Conn.conn.rollback();
			JOptionPane.showMessageDialog(null, "Error: Could not update available slots");
			e.printStackTrace();
		}
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public static void setSlotListbyAvailabilityGUI() {
    // convert the true/false value of the boolean array of slot availability into available/booked for interface display
		for (int index = 0; index < 40; index ++) {
			if (Slot.slotListbyAvailability[index] == false) {
				slotListbyAvailabilityGUI[index] = "Booked";
			}
			else {
				slotListbyAvailabilityGUI[index] = "Available";
			}
		}
	}
}

