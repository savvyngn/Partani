package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StudentDashboardFrame extends JFrame implements ActionListener{

	public static JFrame frame;
	private JPanel panel;
	private JLabel lblStudentDash;
	private JButton btnDisplay;
	private JButton btnRequest;
	private JLabel lblNewLabel;
	private JButton btnBack;

	public StudentDashboardFrame() {
		initialize();
		LogInFrame.userActivity.push(frame);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(226, 116, 116));
		panel.setBounds(-11, 15, 480, 30);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblStudentDash = new JLabel("STUDENT DASHBOARD");
		lblStudentDash.setBounds(160, 7, 160, 18);
		lblStudentDash.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentDash.setForeground(new Color(245, 245, 245));
		lblStudentDash.setFont(new Font("Roboto", Font.BOLD, 15));
		panel.add(lblStudentDash);
		Image imgChe = new ImageIcon(this.getClass().getResource("/icons8-view-schedule-30.png")).getImage();
		Image imgChe1 = imgChe.getScaledInstance(35, 35, Image.SCALE_DEFAULT);

		btnDisplay = new JButton("Display Partani");
		btnDisplay.setForeground(new Color(245, 245, 245));
		btnDisplay.setHorizontalAlignment(SwingConstants.LEADING);
		btnDisplay.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnDisplay.setBackground(new Color(77, 73, 157));
		Image imgDis = new ImageIcon(this.getClass().getResource("/icons8-timeline-week-80.png")).getImage();
		Image imgDis1 = imgDis.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		btnDisplay.setIcon(new ImageIcon(imgDis1));
		btnDisplay.setBounds(235, 110, 170, 50);
		frame.getContentPane().add(btnDisplay);
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayPartaniFrame window;
				try {
					window = new DisplayPartaniFrame();
					window.frame.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnRequest= new JButton("Request Partani");
		btnRequest.setHorizontalAlignment(SwingConstants.LEADING);
		btnRequest.setForeground(new Color(245, 245, 245));
		btnRequest.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnRequest.setBackground(new Color(238, 175, 18));
		btnRequest.setBounds(235, 180, 170, 50);
		Image imgReq = new ImageIcon(this.getClass().getResource("/icons8-calendar-plus-80.png")).getImage();
		Image imgReq1 = imgReq.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		btnRequest.setIcon(new ImageIcon(imgReq1));
		frame.getContentPane().add(btnRequest);

		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestPartaniFrame window;
				try {
					window = new RequestPartaniFrame();
					window.frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(43, 61, 163, 188);
		Image imgJake = new ImageIcon(this.getClass().getResource("/icons8-contacts-480.png")).getImage();
		Image imgJake1 = imgJake.getScaledInstance(170, 170, Image.SCALE_DEFAULT);
		lblNewLabel.setIcon(new ImageIcon(imgJake1));
		frame.getContentPane().add(lblNewLabel);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentDashboardFrame.frame.setVisible(false);
				LogInFrame.frame.setVisible(true);
			}
		});
		btnBack.setForeground(new Color(245, 245, 245));
		btnBack.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		btnBack.setBackground(new Color(122, 180, 165));
		btnBack.setBounds(90, 247, 80, 25);
		frame.getContentPane().add(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
