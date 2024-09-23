package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import dbConnection.Conn;
import objects.User;

import javax.swing.JPanel;
import java.awt.FlowLayout;

public class SignUpFrame extends JFrame implements ActionListener {

	public static JFrame frame;
	private JLabel lblSignUp;
	private JLabel lblName;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	private JTextField txtName;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPwd;
	private JButton btnCreate;
	private JPanel panel;

	public SignUpFrame() {
		initialize();
		LogInFrame.userActivity.push(frame);
	}

	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setBounds(100, 100, 480, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(122, 180, 165));
		panel.setBounds(-11, 30, 480, 28);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblSignUp = new JLabel("SIGN UP FOR AN ACCOUNT");
		panel.add(lblSignUp);
		lblSignUp.setForeground(new Color(245, 245, 245));
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Roboto", Font.BOLD, 15));

		lblName = new JLabel("Full name");
		lblName.setForeground(new Color(226, 116, 116));
		lblName.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblName.setBounds(100, 90, 75, 19);
		frame.getContentPane().add(lblName);

		lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(226, 116, 116));
		lblUsername.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblUsername.setBounds(100, 125, 75, 19);
		frame.getContentPane().add(lblUsername);

		lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(238, 175, 18));
		lblPassword.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblPassword.setBounds(100, 160, 71, 19);
		frame.getContentPane().add(lblPassword);

		lblConfirmPassword = new JLabel("<html> Confirm <BR> password");
		lblConfirmPassword.setForeground(new Color(238, 175, 18));
		lblConfirmPassword.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		lblConfirmPassword.setBounds(100, 185, 102, 42);
		frame.getContentPane().add(lblConfirmPassword);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBackground(new Color(248, 248, 255));
		txtName.setBounds(205, 90, 155, 25);
		frame.getContentPane().add(txtName);

		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(248, 248, 255));
		txtUsername.setBounds(205, 125, 155, 25);
		txtUsername.setColumns(10);		
		frame.getContentPane().add(txtUsername);

		txtPassword = new JPasswordField();
		txtPassword.setBackground(new Color(248, 248, 255));
		txtPassword.setBounds(205, 160, 155, 25);
		txtPassword.setColumns(10);	
		frame.getContentPane().add(txtPassword);

		txtConfirmPwd = new JPasswordField();
		txtConfirmPwd.setColumns(10);
		txtConfirmPwd.setBackground(new Color(248, 248, 255));
		txtConfirmPwd.setBounds(205, 195, 155, 25);
		frame.getContentPane().add(txtConfirmPwd);

		btnCreate = new JButton("Create account");
		btnCreate.setForeground(new Color(245, 245, 245));
		btnCreate.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnCreate.setBackground(new Color(77, 73, 157));
		btnCreate.setBounds(170, 240, 125, 25);
		frame.getContentPane().add(btnCreate);

		Conn.initializeConnection();

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String username = txtUsername.getText();
				@SuppressWarnings("deprecation")
				String password = txtPassword.getText();
				@SuppressWarnings("deprecation")
				String conpwd = txtConfirmPwd.getText();

				boolean signUpValid;
				if (name!= null && username != null && password != null && conpwd != null && password.equals(conpwd)) {
					signUpValid = true;
				}
				else {
					signUpValid = false;
					JOptionPane.showMessageDialog(null, "Information is invalid");
				}

				if (signUpValid == true) {
					password = String.valueOf(User.setHashedPassword(password));
					User.addUser(name, username, password);
					LogInFrame window;
					try {
						window = new LogInFrame();
						window.frame.setVisible(true);
						SignUpFrame.frame.setVisible(false);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
