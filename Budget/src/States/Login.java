package States;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FileHandler.FileIO;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class Login extends State {
	private JTextField username;
	private JTextField password;
	private JLabel invalid;
	private boolean isInvalid = false;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4847569599721799776L;
	private LoginPage page;
	
	public Login(LoginPage page) {
		this.page = page;
		init();
		initButtons();
		initTextBoxes();
		initInvalid();
	}
	
	/*
	 * Sets Login state settings.
	 */
	private void init() {
		universalSettings();
		setLayout(null);
	}
	
	public void initInvalid() {
		invalid = new JLabel("Invalid Username or Password.");
		Font font = new Font("Arial", 10, 14);
		invalid.setForeground(Color.RED);
		invalid.setFont(font);
		invalid.setBounds(540, 9 * (getSize().height / 19), 500, 20);
	}
	
	/*
	 * Creates Login and Register buttons.
	 */
	private void initButtons() {
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Needs to check that input meets requirements and matches login
				 * info before switching to mainScreen.
				 */
				FileIO fileio = new FileIO();
				try {
					if (fileio.checkLoginCredentials(username.getText(), password.getText())) {
						sm.initMain();
					} else {
						// display wrong
						System.out.println("Invalid");
						if (!isInvalid) {
							add(invalid);
							repaint();
							revalidate();
							isInvalid = true;
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});

		login.setBounds(0, (int) (2 * getSize().height / 3.2), 100, 30);
		centerWidth(login);
		add(login);
		
		JButton register = new JButton("Sign up");
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				page.switchTo(Screens.REGISTER);
			}
			
		});
		register.setBounds(0, (int) (2 * getSize().height / 2.9), 100, 30);
		centerWidth(register);
		add(register);
	}
	
	/*
	 * Creates username and password text fields
	 */
	private void initTextBoxes() {
		username = createTextBox("Username");

		username.setBounds(0, 2 * getSize().height / 4, 200, 30);
		centerWidth(username);
		add(username);
		
		password = createTextBox("Password");
		
		password.setBounds(0, 5 * getSize().height / 9, 200, 30);
		centerWidth(password);
		add(password);

	}
	
	

}
