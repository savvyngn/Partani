package gui;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dbConnection.Conn;
import objects.User;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class LogInFrame extends JFrame implements ActionListener {
// all GUI classes inherits the attribute of the java-designated JFrame class for GUI

	public static Stack<JFrame> userActivity = new Stack<JFrame>();
	// this Stack records all the frames an user opens
	// it is a Stack so when user wants to go back the program will open the last element of the Stack
	
	public static JFrame frame;
	private JLabel lblTitle;
	private static JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JButton btnSignup;
	private JButton btnLogin;
	private JLabel lblUserIcon;
	private JLabel lblPwdIcon;
	
	public static String currentUsername;
	public static String currentFullname;

	public LogInFrame() throws SQLException {
		initialize();
		userActivity.push(frame);
	}

	private void initialize() throws SQLException {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 480, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lblTitle = new JLabel("PARTANI SCHEDULE SYSTEM");
		lblTitle.setForeground(new Color(226, 116, 116));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		lblTitle.setBounds(93, 60, 277, 24);
		frame.getContentPane().add(lblTitle);

		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(248, 248, 255));
		txtUsername.setBounds(213, 110, 157, 25);
		txtUsername.setColumns(10);		
		frame.getContentPane().add(txtUsername);

		txtPassword = new JPasswordField();
		txtPassword.setBackground(new Color(248, 248, 255));
		txtPassword.setBounds(213, 150, 157, 26);
		txtPassword.setColumns(10);	
		frame.getContentPane().add(txtPassword);

		lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(77, 73, 157));
		lblUsername.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblUsername.setBounds(122, 115, 75, 19);
		frame.getContentPane().add(lblUsername);

		lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(77, 73, 157));
		lblPassword.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblPassword.setBounds(122, 155, 71, 19);
		frame.getContentPane().add(lblPassword);

		try {
			User.setUsernameList();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			User.setPasswordList();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		btnLogin = new JButton("Log in");
		btnLogin.setForeground(new Color(245, 245, 245));
		btnLogin.setBackground(new Color(226, 116, 116));
		btnLogin.setFont(new Font("Roboto", Font.BOLD, 12));
		btnLogin.setBounds(290, 200, 80, 25);
		frame.getContentPane().add(btnLogin);

		User.setUsernameList();
		User.setPasswordList();

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUsername = txtUsername.getText();
				String passwordEntered = txtPassword.getText();
				String passwordEnteredHashed = Integer.toString(passwordEntered.hashCode());
				boolean logInSuccessful = false;
				try {
					logInSuccessful = User.validUserInfo(currentUsername, passwordEnteredHashed);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (logInSuccessful == true) {
					if (currentUsername.equals("teacher")) {
						TeacherDashboardFrame window1 = new TeacherDashboardFrame();
						window1.frame.setVisible(true);
						LogInFrame.frame.setVisible(false);
					}
					else  {
						StudentDashboardFrame window2 = new StudentDashboardFrame();
						window2.frame.setVisible(true);
						LogInFrame.frame.setVisible(false);
					} 
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong username or password");
				}
			}
		});

		btnSignup = new JButton("Sign up");
		btnSignup.setForeground(new Color(245, 245, 245));
		btnSignup.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnSignup.setBackground(new Color(77, 73, 157));
		btnSignup.setBounds(93, 200, 80, 25);
		frame.getContentPane().add(btnSignup);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpFrame window = new SignUpFrame();
				window.frame.setVisible(true);
				LogInFrame.frame.setVisible(false);
			}
		});

		lblUserIcon= new JLabel("");
		Image imgUser = new ImageIcon(this.getClass().getResource("/icons8-customer-30.png")).getImage();
		Image imgUser1 = imgUser.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		lblUserIcon.setIcon(new ImageIcon(imgUser1));
		lblUserIcon.setBounds(92, 110, 25, 25);
		frame.getContentPane().add(lblUserIcon);

		lblPwdIcon = new JLabel("");
		Image imgPwd = new ImageIcon(this.getClass().getResource("/icons8-password-30.png")).getImage();
		Image imgPwd1 = imgPwd.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		lblPwdIcon.setIcon(new ImageIcon(imgPwd1));
		lblPwdIcon.setBounds(92, 150, 25, 25);
		frame.getContentPane().add(lblPwdIcon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}


