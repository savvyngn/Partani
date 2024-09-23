package gui;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import com.toedter.calendar.JDateChooser;

import objects.Partani;
import objects.Partani.Subject;
import objects.Slot;

public class RequestPartaniFrame extends JFrame implements ActionListener {

	public static JFrame frame;
	private JPanel panel;
	private JLabel requestPar;
	private JTable table;
	private JLabel lblPeriod;
	private JLabel lblDay;
	private JLabel lblWeek;
	private JLabel lblChosenTimeSlot;
	private JList<String> listSlotToBeChosen;
	private JScrollPane scrollPaneSlot;
	private JButton btnRefresh;
	private JButton btnNext;
	private JLabel lblNumOfStu;
	private JLabel lblSubject;
	private JTextField txtNumOfStu;
	private JScrollPane scrollPaneSub;
	private JList<String> listOfSubjects;
	private JLabel lblNumOfTop;
	private JTextField txtNumOfTop;
	private JLabel lblTopics;
	private JTextField txtTopicList;
	private JTextField txtNote;
	private JLabel lblStartDate;
	private JLabel lblStudent;
	private JTextField txtStudent;
	private JDateChooser dateStartDate;
	private JDateChooser dateEndDate;
	private JLabel lblClarification;
	private JLabel lblNote;
	private JLabel lblEndDate;
	private JButton btnBack;

	public RequestPartaniFrame() throws Exception {
		initialize();
		LogInFrame.userActivity.push(frame);
	}

	private void initialize() throws SQLException, Exception {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setBounds(100, 100, 480, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(77, 73, 157));
		panel.setBounds(-11, 15, 480, 30);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		requestPar = new JLabel("REQUEST A PARTANI");
		requestPar.setBounds(167, 7, 146, 18);
		requestPar.setHorizontalAlignment(SwingConstants.LEADING);
		requestPar.setForeground(new Color(245, 245, 245));
		requestPar.setFont(new Font("Roboto", Font.BOLD, 15));
		panel.add(requestPar);

		Calendar cal = new GregorianCalendar();

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

		lblWeek = new JLabel();
		lblWeek.setBounds(30, 60, 69, 20);
		lblWeek.setText("Week " + cal.get(Calendar.WEEK_OF_YEAR));
		lblWeek.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblWeek.setForeground(new Color(238, 175, 18));
		frame.getContentPane().add(lblWeek);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Roboto", Font.PLAIN, 12));
		table.setRowHeight(15);
		table.setBounds(83, 82, 346, 120);
		frame.getContentPane().add(table);

		lblChosenTimeSlot = new JLabel("Chosen slot");
		lblChosenTimeSlot.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblChosenTimeSlot.setBounds(190, 215, 69, 20);
		lblChosenTimeSlot.setForeground(new Color(77, 73, 157));
		frame.getContentPane().add(lblChosenTimeSlot);

		listSlotToBeChosen = new JList<String>();
		listSlotToBeChosen.setListData(Slot.slotList);
		listSlotToBeChosen.setFont(new Font("Roboto", Font.PLAIN, 12));
		listSlotToBeChosen.setBounds(191, 178, 155, 20);
		listSlotToBeChosen.setForeground(Color.DARK_GRAY); 

		scrollPaneSlot = new JScrollPane();
		scrollPaneSlot.setBounds(270, 215, 159, 20);
		frame.getContentPane().add(scrollPaneSlot);
		scrollPaneSlot.setViewportView(listSlotToBeChosen);

		btnNext = new JButton("Next");
		btnNext.setForeground(new Color(245, 245, 245));
		btnNext.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		btnNext.setBackground(new Color(122, 180, 165));
		btnNext.setBounds(349, 430, 80, 25);
		frame.getContentPane().add(btnNext);

		lblNumOfStu = new JLabel("<html> Number of students <br> (including you, max 5)");
		lblNumOfStu.setVerticalAlignment(SwingConstants.TOP);
		lblNumOfStu.setForeground(new Color(77, 73, 157));
		lblNumOfStu.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblNumOfStu.setBounds(235, 250, 125, 36);
		frame.getContentPane().add(lblNumOfStu);

		txtNumOfStu = new JTextField();
		txtNumOfStu.setForeground(Color.DARK_GRAY);
		txtNumOfStu.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtNumOfStu.setBounds(369, 250, 60, 20);
		frame.getContentPane().add(txtNumOfStu);
		txtNumOfStu.setColumns(10);

		lblSubject = new JLabel("Subject");
		lblSubject.setForeground(new Color(77, 73, 157));
		lblSubject.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblSubject.setBounds(30, 250, 54, 20);
		frame.getContentPane().add(lblSubject);

		scrollPaneSub = new JScrollPane();
		scrollPaneSub.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneSub.setBounds(75, 250, 145, 20);
		frame.getContentPane().add(scrollPaneSub);

		listOfSubjects = new JList<String>();
		listOfSubjects.setForeground(Color.DARK_GRAY);
		listOfSubjects.setListData(Subject.subjectList);
		listOfSubjects.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listOfSubjects.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPaneSub.setViewportView(listOfSubjects);

		lblNumOfTop = new JLabel("<html> Number of topics <br> (should be less than 3)");
		lblNumOfTop.setVerticalAlignment(SwingConstants.TOP);
		lblNumOfTop.setForeground(new Color(77, 73, 157));
		lblNumOfTop.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblNumOfTop.setBounds(235, 320, 138, 30);
		frame.getContentPane().add(lblNumOfTop);

		txtNumOfTop = new JTextField();
		txtNumOfTop.setForeground(Color.DARK_GRAY);
		txtNumOfTop.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtNumOfTop.setColumns(10);
		txtNumOfTop.setBounds(369, 320, 60, 20);
		frame.getContentPane().add(txtNumOfTop);

		lblTopics =   new JLabel("List of topics");
		lblTopics.setForeground(new Color(77, 73, 157));
		lblTopics.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblTopics.setBounds(30, 320, 85, 20);
		frame.getContentPane().add(lblTopics);

		txtTopicList = new JTextField();
		txtTopicList.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtTopicList.setForeground(Color.DARK_GRAY);
		txtTopicList.setBounds(114, 320, 107, 20);
		frame.getContentPane().add(txtTopicList);
		txtTopicList.setColumns(10);

		txtNote = new JTextField();
		txtNote.setForeground(Color.DARK_GRAY);
		txtNote.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtNote.setColumns(10);
		txtNote.setBounds(128, 400, 301, 20);
		frame.getContentPane().add(txtNote);

		lblStartDate= new JLabel("Start date");
		lblStartDate.setForeground(new Color(77, 73, 157));
		lblStartDate.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblStartDate.setBounds(30, 355, 54, 20);
		frame.getContentPane().add(lblStartDate);

		dateStartDate = new JDateChooser();
		dateStartDate.setForeground(Color.DARK_GRAY);
		dateStartDate.setDateFormatString("yyyyMMdd");
		dateStartDate.setBounds(96, 355, 125, 20);
		frame.getContentPane().add(dateStartDate);

		dateEndDate = new JDateChooser();
		dateEndDate.setForeground(Color.DARK_GRAY);
		dateEndDate.setDateFormatString("yyyyMMdd");
		dateEndDate.setBounds(304, 355, 125, 20);
		frame.getContentPane().add(dateEndDate);

		lblEndDate = new JLabel("End date");
		lblEndDate.setForeground(new Color(77, 73, 157));
		lblEndDate.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblEndDate.setBounds(235, 355, 54, 20);
		frame.getContentPane().add(lblEndDate);

		lblNote = new JLabel("Additional note");
		lblNote.setForeground(new Color(77, 73, 157));
		lblNote.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblNote.setBounds(30, 400, 100, 20);
		frame.getContentPane().add(lblNote);

		lblStudent = new JLabel("Name(s) of student(s)");
		lblStudent.setForeground(new Color(77, 73, 157));
		lblStudent.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblStudent.setBounds(30, 285, 125, 20);
		frame.getContentPane().add(lblStudent);

		txtStudent = new JTextField();
		txtStudent.setForeground(Color.DARK_GRAY);
		txtStudent.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtStudent.setColumns(10);
		txtStudent.setBounds(159, 285, 270, 20);
		frame.getContentPane().add(txtStudent);

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
		btnBack.setBounds(30, 430, 80, 25);
		frame.getContentPane().add(btnBack);

		lblClarification = new JLabel("The End date needs to be after Start date; if recurrcing chooses the last date");
		lblClarification.setForeground(new Color(119, 136, 153));
		lblClarification.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblClarification.setBounds(30, 380, 399, 20);
		frame.getContentPane().add(lblClarification);
		
		Slot.setSlotList();
		Slot.setAvailabilityList();
		Slot.setSlotListbyAvailabilityGUI();
		
		btnRefresh = new JButton("Refresh Calendar");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Slot.setSlotList();
				try {
					Partani.updateInvalidPartaniIDList();
					Partani.updatePartaniIDList();
					Slot.updateAvailableSlots();
					Slot.setAvailabilityList();
					Slot.setSlotListbyAvailabilityGUI();
					btnBack.doClick();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Cannot update Availability Slots");
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Cannot update Partani Slots");
				}
			}
		});
		btnRefresh.setForeground(new Color(245, 245, 245));
		btnRefresh.setFont(new Font("Roboto Medium", Font.PLAIN, 11));
		btnRefresh.setBackground(new Color(238, 175, 18));
		btnRefresh.setBounds(30, 210, 125, 25);
		frame.getContentPane().add(btnRefresh);

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
		
		SimpleDateFormat dfDate  = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
		
		btnNext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (Slot.slotListbyAvailability[listSlotToBeChosen.getSelectedIndex()] == true && 
							dfDate.parse(((JTextField)dateStartDate.getDateEditor().getUiComponent()).getText()).before(dfDate.parse(((JTextField)dateEndDate.getDateEditor().getUiComponent()).getText()))
							// end date is after before start date
							&&
							Integer.valueOf(txtNumOfStu.getText()) <=5 &&
							Integer.valueOf(txtNumOfTop.getText()) <=3 &&
							dfDate.parse(((JTextField)dateStartDate.getDateEditor().getUiComponent()).getText()).after(now))
							// end date time (measured at 00:00:00 of that date) is after current time, measured with year, month, date and time
							{
						try {
							System.out.println(dfDate.parse(((JTextField)dateStartDate.getDateEditor().getUiComponent()).getText()));
							Partani.addPartani(
									listSlotToBeChosen.getSelectedIndex(),
									txtStudent.getText(),
									Integer.valueOf(txtNumOfStu.getText()), 
									listOfSubjects.getSelectedValue(), 
									Integer.valueOf(txtNumOfTop.getText()), 
									txtTopicList.getText(), 
									((JTextField)dateStartDate.getDateEditor().getUiComponent()).getText(), 
									((JTextField)dateEndDate.getDateEditor().getUiComponent()).getText(),
									txtNote.getText()
									);
							System.out.println(LogInFrame.currentUsername);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Connection to partani db failed");
							e1.printStackTrace();
						} 
						JOptionPane.showMessageDialog(null, "Partani successfully added");
						Slot.updateAvailableSlots();
						// with the new Partani added in, the corresponding slot whose ID is registered will be unavailable
						// because the registered end date is valid because of the if condition, the Partani is automatically valid -> no need to go through the Partani check process and update directly to the Slot availability
					}
					else {
						JOptionPane.showMessageDialog(null, "Information is invalid");
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
