package gui;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import dbConnection.Conn;
import objects.Partani;
import objects.Slot;
import objects.Teacher;

public class ChangeAvailabilityFrame extends JFrame implements ActionListener {

	public static JFrame frame;
	private JPanel panel;
	private JLabel changeAva;
	private JTable table;
	private JLabel lblPeriod;
	private JLabel lblDay;
	private JList<String> listSlotToBeChosen;
	private JScrollPane scrollPaneSlot;
	private JButton btnNext;
	private JButton btnBack;
	private JLabel lblChosenTimeSlot;
	private JButton btnMakeAvailable;
	private JButton btnMakeUnavailable;

	public ChangeAvailabilityFrame() throws SQLException, ParseException{
		initialize();
		LogInFrame.userActivity.push(frame);
	}

	private void initialize() throws SQLException, ParseException {
		
		Partani.updateInvalidPartaniIDList();
		Partani.updatePartaniIDList();

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setBounds(100, 100, 480, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(238, 175, 18));
		panel.setBounds(-11, 15, 480, 30);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		changeAva = new JLabel("CHANGE AVAILABILITY");
		changeAva.setBounds(162, 7, 177, 18);
		changeAva.setHorizontalAlignment(SwingConstants.LEADING);
		changeAva.setForeground(new Color(245, 245, 245));
		changeAva.setFont(new Font("Roboto", Font.BOLD, 15));
		panel.add(changeAva);

		lblPeriod = new JLabel();
		lblPeriod.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblPeriod.setForeground(new Color(77, 73, 157));
		lblPeriod.setBounds(30, 77, 54, 130);
		frame.getContentPane().add(lblPeriod);
		lblPeriod.setText("<html> Period 1 <br> Period 2 <br> Period 3 <br> Period 4 <br> Period 5 <br> Period 6 <br> Period 7 <br> Period 8 <br>");

		lblDay = new JLabel();
		lblDay.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblDay.setBounds(83, 60, 346, 20);
		lblDay.setForeground(new Color(226, 116, 116));
		frame.getContentPane().add(lblDay);
		lblDay.setText("    Sunday        Monday        Tuesday     Wednesday   Thursday");

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Roboto", Font.PLAIN, 12));
		table.setRowHeight(15);
		table.setBounds(83, 82, 346, 120);
		frame.getContentPane().add(table);

		Slot.setSlotList();
		Slot.setAvailabilityList();
		Slot.setSlotListbyAvailabilityGUI();

		table.setModel(new DefaultTableModel(
				new Object[][] {
					{Slot.slotListbyAvailabilityGUI[0], Slot.slotListbyAvailabilityGUI[1], Slot.slotListbyAvailabilityGUI[2], Slot.slotListbyAvailabilityGUI[3], Slot.slotListbyAvailabilityGUI[4]},
					{Slot.slotListbyAvailabilityGUI[5], Slot.slotListbyAvailabilityGUI[6], Slot.slotListbyAvailabilityGUI[7], Slot.slotListbyAvailabilityGUI[8], Slot.slotListbyAvailabilityGUI[9]},
					{Slot.slotListbyAvailabilityGUI[10], Slot.slotListbyAvailabilityGUI[11], Slot.slotListbyAvailabilityGUI[12], Slot.slotListbyAvailabilityGUI[13], Slot.slotListbyAvailabilityGUI[14]},
					{Slot.slotListbyAvailabilityGUI[15], Slot.slotListbyAvailabilityGUI[16], Slot.slotListbyAvailabilityGUI[17], Slot.slotListbyAvailabilityGUI[18], Slot.slotListbyAvailabilityGUI[19]},
					{Slot.slotListbyAvailabilityGUI[20], Slot.slotListbyAvailabilityGUI[21], Slot.slotListbyAvailabilityGUI[22], Slot.slotListbyAvailabilityGUI[23], Slot.slotListbyAvailabilityGUI[24]},
					{Slot.slotListbyAvailabilityGUI[25], Slot.slotListbyAvailabilityGUI[26], Slot.slotListbyAvailabilityGUI[27], Slot.slotListbyAvailabilityGUI[28], Slot.slotListbyAvailabilityGUI[29]},
					{Slot.slotListbyAvailabilityGUI[30], Slot.slotListbyAvailabilityGUI[31], Slot.slotListbyAvailabilityGUI[32], Slot.slotListbyAvailabilityGUI[33], Slot.slotListbyAvailabilityGUI[34]},
					{Slot.slotListbyAvailabilityGUI[35], Slot.slotListbyAvailabilityGUI[36], Slot.slotListbyAvailabilityGUI[37], Slot.slotListbyAvailabilityGUI[38], Slot.slotListbyAvailabilityGUI[39]},
				},
				new String[] {
						"Sun", "Mon", "Tue", "Wed", "Thu"
				}
				) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		Slot.setSlotList();
		Slot.setAvailabilityList();
		Slot.setSlotListbyAvailabilityGUI();

		listSlotToBeChosen = new JList<String>();
		listSlotToBeChosen.setListData(Slot.slotList);
		listSlotToBeChosen.setFont(new Font("Roboto", Font.PLAIN, 12));
		listSlotToBeChosen.setBounds(191, 178, 155, 20);
		listSlotToBeChosen.setForeground(Color.DARK_GRAY); 

		scrollPaneSlot = new JScrollPane();
		scrollPaneSlot.setBounds(104, 210, 115, 20);
		frame.getContentPane().add(scrollPaneSlot);
		scrollPaneSlot.setViewportView(listSlotToBeChosen);

		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherDashboardFrame window = new TeacherDashboardFrame();
				window.frame.setVisible(true);	
			}
		});
		btnNext.setForeground(new Color(245, 245, 245));
		btnNext.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		btnNext.setBackground(new Color(122, 180, 165));
		btnNext.setBounds(348, 275, 80, 25);
		frame.getContentPane().add(btnNext);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame currentFrame = LogInFrame.userActivity.pop();
				currentFrame.setVisible(false);
				currentFrame = LogInFrame.userActivity.push(LogInFrame.userActivity.lastElement());
				currentFrame.setVisible(true);
			}
		});
		btnBack.setForeground(new Color(245, 245, 245));
		btnBack.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		btnBack.setBackground(new Color(226, 116, 116));
		btnBack.setBounds(30, 275, 80, 25);
		frame.getContentPane().add(btnBack);

		lblChosenTimeSlot = new JLabel("Chosen slot");
		lblChosenTimeSlot.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblChosenTimeSlot.setBounds(30, 210, 69, 20);
		lblChosenTimeSlot.setForeground(new Color(77, 73, 157));
		frame.getContentPane().add(lblChosenTimeSlot);

		btnMakeAvailable = new JButton("Make available");
		btnMakeAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Slot.slotListbyAvailability[listSlotToBeChosen.getSelectedIndex()] == false) {
						Teacher.makeSlotAvailable(listSlotToBeChosen.getSelectedIndex());
					}
					Slot.setAvailabilityList();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error: Could not change the slot's availability");
					e1.printStackTrace();
				}
			}
		});
		btnMakeAvailable.setForeground(new Color(245, 245, 245));
		btnMakeAvailable.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		btnMakeAvailable.setBackground(new Color(238, 175, 18));
		btnMakeAvailable.setBounds(230, 209, 150, 25);
		frame.getContentPane().add(btnMakeAvailable);

		btnMakeUnavailable = new JButton("Make unavailable");
		btnMakeUnavailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Slot.slotListbyAvailability[listSlotToBeChosen.getSelectedIndex()] == true) {
						Teacher.makeSlotUnavailable(listSlotToBeChosen.getSelectedIndex());
					}
					Slot.setAvailabilityList();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error: Could not change the slot's availability");
					e1.printStackTrace();
				}
			}
		});
		btnMakeUnavailable.setForeground(new Color(245, 245, 245));
		btnMakeUnavailable.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		btnMakeUnavailable.setBackground(new Color(77, 73, 157));
		btnMakeUnavailable.setBounds(230, 239, 150, 25);
		frame.getContentPane().add(btnMakeUnavailable);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
