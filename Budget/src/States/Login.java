package States;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Login extends State {
	

	
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
	}
	
	/*
	 * Sets Login state settings.
	 */
	private void init() {

		universalSettings();
		setLayout(null);

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
				sm.initMain();
				switchPanel(sm.mainScreen);
			}
			
		});

		login.setBounds(0, (int) (2 * getSize().height / 3.2), 100, 30);
		centerWidth(login);
		add(login);
		
		JButton register = new JButton("Sign up");
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				page.switchTo(LoginPage.Screens.REGISTER);
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
		JTextField username = createTextBox("Username");

		username.setBounds(0, 2 * getSize().height / 4, 200, 30);
		centerWidth(username);
		add(username);
		
		JTextField password = createTextBox("Password");
		
		password.setBounds(0, 5 * getSize().height / 9, 200, 30);
		centerWidth(password);
		add(password);

	}
	
	

}
