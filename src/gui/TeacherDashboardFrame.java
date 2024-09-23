package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TeacherDashboardFrame extends JFrame implements ActionListener{

	public static JFrame frame;
	private JPanel panel;
	private JLabel lblTeacherDash;
	private JButton btnDisplay;
	private JButton btnRequest;
	private JLabel lblNewLabel;
	private JButton btnChange;
	private JButton btnBack;

	public TeacherDashboardFrame() {
		initialize();
		LogInFrame.userActivity.push(frame);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(238, 175, 18));
		panel.setBounds(-11, 15, 480, 30);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblTeacherDash = new JLabel("TEACHER DASHBOARD");
		lblTeacherDash.setBounds(160, 7, 160, 18);
		lblTeacherDash.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherDash.setForeground(new Color(245, 245, 245));
		lblTeacherDash.setFont(new Font("Roboto", Font.BOLD, 15));
		panel.add(lblTeacherDash);

		btnRequest= new JButton("Request Partani");
		btnRequest.setHorizontalAlignment(SwingConstants.LEADING);
		btnRequest.setForeground(new Color(245, 245, 245));
		btnRequest.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnRequest.setBackground(new Color(122, 180, 165));
		btnRequest.setBounds(235, 82, 170, 50);
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
					TeacherDashboardFrame.frame.setVisible(false);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		btnDisplay = new JButton("Display Partani");
		btnDisplay.setForeground(new Color(245, 245, 245));
		btnDisplay.setHorizontalAlignment(SwingConstants.LEADING);
		btnDisplay.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnDisplay.setBackground(new Color(77, 73, 157));
		Image imgDis = new ImageIcon(this.getClass().getResource("/icons8-timeline-week-80.png")).getImage();
		Image imgDis1 = imgDis.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		btnDisplay.setIcon(new ImageIcon(imgDis1));
		btnDisplay.setBounds(235, 147, 170, 50);
		frame.getContentPane().add(btnDisplay);
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayPartaniFrame window;
				try {
					window = new DisplayPartaniFrame();
					window.frame.setVisible(true);
					TeacherDashboardFrame.frame.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 61, 185, 170);
		Image imgTea = new ImageIcon(this.getClass().getResource("/icons8-briefcase-480.png")).getImage();
		Image imgTea1 = imgTea.getScaledInstance(160, 160, Image.SCALE_DEFAULT);
		lblNewLabel.setIcon(new ImageIcon(imgTea1));
		frame.getContentPane().add(lblNewLabel);
		
		btnChange = new JButton("<html> Change <br> Availability");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeAvailabilityFrame window = null;
				try {
					window = new ChangeAvailabilityFrame();
					window.frame.setVisible(true);
					TeacherDashboardFrame.frame.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnChange.setHorizontalAlignment(SwingConstants.LEADING);
		btnChange.setForeground(new Color(245, 245, 245));
		btnChange.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnChange.setBackground(new Color(226, 116, 116));
		btnChange.setBounds(235, 212, 170, 50);
		Image imgCha = new ImageIcon(this.getClass().getResource("/icons8-training-80.png")).getImage();
		Image imgCha1 = imgCha.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		btnChange.setIcon(new ImageIcon(imgCha1));
		frame.getContentPane().add(btnChange);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherDashboardFrame.frame.setVisible(false);
				LogInFrame.frame.setVisible(true);
			}
		});
		btnBack.setForeground(new Color(245, 245, 245));
		btnBack.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		btnBack.setBackground(new Color(238,175,18));
		btnBack.setBounds(90, 237, 80, 25);
		frame.getContentPane().add(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
