package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;



import FileHandler.FileIO;

/**
 * Class Register A state for registering a new username and password
 */
public class Register extends State {
	// these are the regex checks for the username and password to check against
	// (currently allows all lowcase and uppcase letters, numbers, and must be
	// length of 5 - 19 characters)
	static String userNameCode = "[a-zA-Z1-9]{5,19}";
	static String passwordCode = "[a-zA-Z1-9]{5,19}";
	
	private LoginPage page;
	private JTextField username;
	private JTextField password;
	// Security question
	private JTextField security_q;
	// Security answer
	private JTextField security_a;
	private JLabel invalid;
	private boolean invalidShown = false;

	public Register(LoginPage page) {
		this.page = page;
		init();
		initTextBoxes();
		initButtons();
	}

	private static final long serialVersionUID = 3109968069917069255L;

	public void init() {
		universalSettings();
		setLayout(null);
	}

	/*
	 * Creates Login and Register buttons.
	 */
	private void initButtons() {
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				page.switchTo(Screens.LOGIN);
				username.setText("Username");
				password.setText("Password");
				security_q.setText("Security Question");
				security_a.setText("Security Answer");
			}		
		});

		back.setBounds((getSize().width / 2) - (100 / 2) - 50, 12 * (getSize().height / 19), 100, 30);
//		centerWidth(back);
		add(back);
		
		JButton register = new JButton("Register");
		
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (register(username.getText(), password.getText())) {
					/* Write to LoginCredentials using FileIO
					 */
					FileIO fileio = new FileIO();
					
					try {
						if(fileio.checkUsername(username.getText())) {
							if(!invalidShown) {
								// display wrong
								System.out.println("Username already exists.");
								initInvalid();
								invalidShown = true;
							}
						}else {
							fileio.newUserLogin(username.getText(), password.getText(), security_q.getText(), security_a.getText());
							fileio.newFile(username.getText());
							page.switchTo(Screens.LOGIN);
							username.setText("Username");
							password.setText("Password");
							security_q.setText("Security Question");
							security_a.setText("Security Answer");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
				} else {
					// Show errors.
					System.err.println("Does not meet requirements");
				}
			}
			
		});
		register.setBounds((getSize().width / 2) - (100 / 2) + 50, 12 * (getSize().height / 19), 100, 30);
//		centerWidth(register);
		add(register);
	}
	
	public void initInvalid() {
		invalid = new JLabel("Username already exists.");
		Font font = new Font("Arial", 10, 14);
		invalid.setForeground(Color.RED);
		invalid.setFont(font);
		invalid.setBounds(560, 9 * (getSize().height / 24), 500, 20);
		add(invalid);
		repaint();
		revalidate();
	}

	/*
	 * Creates username and password text fields
	 */
	private void initTextBoxes() {
		username = createTextBox("Username");
		username.setBounds(0, 8 * (getSize().height / 19), 200, 30);
		centerWidth(username);
		add(username);

		password = createTextBox("Password");
		password.setBounds(0, 9 * (getSize().height / 19), 200, 30);
		centerWidth(password);
		add(password);

		security_q = createTextBox("Security Question");
		security_q.setBounds(0, 10 * (getSize().height / 19), 200, 30);
		centerWidth(security_q);
		add(security_q);

		security_a = createTextBox("Security Answer");
		security_a.setBounds(0, 11 * (getSize().height / 19), 200, 30);
		centerWidth(security_a);
		add(security_a);

	}

	// this method is the register check that checks if the username and password
	// are using the allowed characters
	public static boolean register(String userName, String password) {

		// creating the pattern compilers for the regex string checks
		Pattern userNamePattern = Pattern.compile(userNameCode);
		Pattern passwordPattern = Pattern.compile(passwordCode);

		// this creates the matcher to use the strings that are passed into the method
		Matcher userNameMatch = userNamePattern.matcher(userName);
		Matcher passwordMatch = passwordPattern.matcher(password);

		// Check if something is typed in.
		if (userName.equals("Username") || userName.equals("Password")) {
			return false;
		}
		
		// this checks if the username fits within the regex boundaries
		if (userNameMatch.find()) {
			System.out.println("Workedusername");
		}

		// if there is a character that is not allowed by regex it will give the user
		// this error.
		else {
			System.out.println("Couldnt create username");
		}

		// this checks if the password fits within the regex boundaries
		if (passwordMatch.find()) {
			System.out.println("Workedpassword");
		}

		// if there is a character that is not allowed by regex it will give the user
		// this error.
		else {
			System.out.println("Couldnt create password");
		}

		return true;
	}

}
