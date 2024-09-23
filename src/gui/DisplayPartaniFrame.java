package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import objects.Partani;
import objects.Slot;
import objects.User;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class DisplayPartaniFrame extends JFrame implements ActionListener{

	public static JFrame frame;
	private JLabel displayPar;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JList<String> partaniListPerPerson;
	private JButton btnCheckPar;
	private JLabel lblPartaniList;
	private JLabel lblCategory;
	private JLabel lblPartaniInfo;
	private JLabel lblSlot;
	private JLabel lblStudent;
	private JLabel lblSubject;
	private JLabel lblTopic;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JLabel lblNote;
	private JLabel lblRcmdDur;
	private JButton btnNext;
	private String[] partaniArrayPerPerson;
	// the list of ID from all Partani the user has
	private int selectedPar;
	private JButton btnBack;

	public DisplayPartaniFrame() throws SQLException {
		initialize();
		LogInFrame.userActivity.push(frame);
	}
	
	private static void setPartaniListPerPerson() throws SQLException {
		Partani.partaniListPerPersonByID.clear();
		LogInFrame.currentFullname = User.getFullnameFromUsername(LogInFrame.currentUsername);
			if (LogInFrame.currentFullname.equals("Noam")){
				Partani.partaniListPerPersonByID = Partani.partaniListByID;
			}
			else {
				Partani.setPartaniIDListPerPerson(LogInFrame.currentFullname);
			}
	}
	
	private static String[] convertPartaniIDQueueToArray() throws SQLException {
		String[] partaniArrayPerPersonByID = new String[Partani.partaniListPerPersonByID.size()];
		Partani.partaniListPerPersonByID.toArray(partaniArrayPerPersonByID);
		return partaniArrayPerPersonByID;
	}

	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setBounds(100, 100, 480, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(226, 116, 116));
		panel.setBounds(-11, 15, 480, 30);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		displayPar = new JLabel("DISPLAY PARTANI");
		displayPar.setBounds(160, 7, 160, 18);
		displayPar.setHorizontalAlignment(SwingConstants.CENTER);
		displayPar.setForeground(new Color(245, 245, 245));
		displayPar.setFont(new Font("Roboto", Font.BOLD, 15));
		panel.add(displayPar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 90, 111, 80);
		frame.getContentPane().add(scrollPane);
		
		Partani.setPartaniListByID();
		setPartaniListPerPerson();
		partaniArrayPerPerson = convertPartaniIDQueueToArray();

		partaniListPerPerson = new JList<String>();
		partaniListPerPerson.setListData(partaniArrayPerPerson);
		partaniListPerPerson.setFont(new Font("Roboto", Font.PLAIN, 12));
		partaniListPerPerson.setBounds(191, 178, 155, 20);
		partaniListPerPerson.setForeground(Color.DARK_GRAY); 
		scrollPane.setViewportView(partaniListPerPerson);

		lblPartaniInfo = new JLabel("Partani Information");
		lblPartaniInfo.setForeground(new Color(122, 180, 165));
		lblPartaniInfo.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblPartaniInfo.setBounds(160, 61, 111, 20);
		frame.getContentPane().add(lblPartaniInfo);

		lblPartaniList = new JLabel("Partani list");
		lblPartaniList.setForeground(new Color(122, 180, 165));
		lblPartaniList.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblPartaniList.setBounds(30, 61, 69, 20);
		frame.getContentPane().add(lblPartaniList);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(160, 90, 266, 159);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnNext = new JButton("Next");
		btnNext.setForeground(new Color(245, 245, 245));
		btnNext.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		btnNext.setBackground(new Color(122, 180, 165));
		btnNext.setBounds(349, 430, 80, 25);
		frame.getContentPane().add(btnNext);

		lblCategory = new JLabel();
		lblCategory.setBounds(10, 10, 130, 145);
		panel_1.add(lblCategory);
		lblCategory.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblCategory.setText("<html> Slot <br> Student(s) <br> Subject <br> Topic(s) <br> Start Date <br> End Date <br> Note <br><br> Recommended duration");

		lblSlot = new JLabel("[Slot]");
		lblSlot.setForeground(new Color(77, 73, 157));
		lblSlot.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblSlot.setBounds(38, 12, 213, 20);
		panel_1.add(lblSlot);

		lblStudent = new JLabel("[Student(s)' name(s)]");
		lblStudent.setForeground(new Color(77, 73, 157));
		lblStudent.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblStudent.setBounds(73, 27, 172, 20);
		panel_1.add(lblStudent);

		lblSubject = new JLabel("[Subject]");
		lblSubject.setForeground(new Color(77, 73, 157));
		lblSubject.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblSubject.setBounds(57, 42, 194, 20);
		panel_1.add(lblSubject);

		lblTopic = new JLabel("[Topic(s)]");
		lblTopic.setForeground(new Color(77, 73, 157));
		lblTopic.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblTopic.setBounds(62, 57, 189, 20);
		panel_1.add(lblTopic);

		lblStartDate = new JLabel("[Start Date]");
		lblStartDate.setForeground(new Color(77, 73, 157));
		lblStartDate.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblStartDate.setBounds(70, 72, 122, 20);
		panel_1.add(lblStartDate);

		lblEndDate = new JLabel("[End Date]");
		lblEndDate.setForeground(new Color(77, 73, 157));
		lblEndDate.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblEndDate.setBounds(67, 88, 122, 20);
		panel_1.add(lblEndDate);

		lblNote = new JLabel("[Additional note]");
		lblNote.setForeground(new Color(77, 73, 157));
		lblNote.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblNote.setBounds(40, 102, 205, 20);
		panel_1.add(lblNote);

		lblRcmdDur = new JLabel("[Duration in minutes]");
		lblRcmdDur.setForeground(new Color(77, 73, 157));
		lblRcmdDur.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblRcmdDur.setBounds(141, 132, 122, 20);
		panel_1.add(lblRcmdDur);

		btnCheckPar = new JButton("Check Partani");
		btnCheckPar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Slot.setSlotList();
				selectedPar = Integer.valueOf(partaniListPerPerson.getSelectedValue());
				try {
					int index = Integer.valueOf(Partani.getSlotID(selectedPar));
					lblSlot.setText(Slot.slotList[index]);
					lblStudent.setText(Partani.getStudent(selectedPar));
					lblSubject.setText(Partani.getSubject(selectedPar));
					lblTopic.setText(Partani.getTopic(selectedPar));
					LocalDate startDateFmt = LocalDate.parse(Partani.getStartDate(selectedPar), DateTimeFormatter.BASIC_ISO_DATE);
					lblStartDate.setText(String.valueOf(startDateFmt));
					LocalDate endDateFmt = LocalDate.parse(Partani.getEndDate(selectedPar), DateTimeFormatter.BASIC_ISO_DATE);
					lblEndDate.setText(String.valueOf(endDateFmt));
					lblNote.setText(Partani.getNote(selectedPar));
					lblRcmdDur.setText(Partani.setRcmdDuration(Partani.getNumberOfStudents(selectedPar), Partani.getSubject(selectedPar), Partani.getNumberOfTopics(selectedPar)));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCheckPar.setForeground(new Color(245, 245, 245));
		btnCheckPar.setFont(new Font("Roboto", Font.PLAIN, 11));
		btnCheckPar.setBackground(new Color(238, 175, 18));
		btnCheckPar.setBounds(30, 186, 111, 25);
		frame.getContentPane().add(btnCheckPar);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame currentFrame = LogInFrame.userActivity.pop();
				currentFrame.setVisible(false);
				currentFrame = LogInFrame.userActivity.push(LogInFrame.userActivity.lastElement());
				currentFrame.setVisible(true);
			}
		});
		btnNext.setForeground(new Color(245, 245, 245));
		btnNext.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		btnNext.setBackground(new Color(122, 180, 165));
		btnNext.setBounds(348, 260, 80, 25);
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
		btnBack.setBounds(30, 260, 80, 25);
		frame.getContentPane().add(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
