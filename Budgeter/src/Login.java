import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends State {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4847569599721799776L;

	public Login() {
		init();
		initButtons();
		initTextBoxes();
	}
	
	/*
	 * Sets Login state settings.
	 */
	private void init() {
		setSize(1280, 720);
		setVisible(true);
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
				sm.switchPanel(sm.mainScreen);
			}
			
		});
		login.setBounds(0, (int) (2 * getSize().height / 3.2), 100, 30);
		centerWidth(login);
		add(login);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Switch to register page.
			}
			
		});
		register.setBounds(0, (int) (2 * getSize().height / 2.9), 100, 30);
		centerWidth(register);
		add(register);
	}
	
	private void initTextBoxes() {
		JTextField username = createTextBox("Username");

		username.setBounds(0, 2 * getSize().height / 4, 150, 30);
		centerWidth(username);
		add(username);
		
		JTextField password = createTextBox("Password");
		
		password.setBounds(0, 5 * getSize().height / 9, 150, 30);
		centerWidth(password);
		add(password);
	}
	
	/**
	 * @param name: Placeholder inside text box.
	 * @return : JTextField.
	 */
	private JTextField createTextBox(String name) {
		JTextField temp = new JTextField(name);
		temp.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (temp.getText().equals(name)) {
					temp.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				temp.setText(name);
			}
			
		});
		return temp;
	}

}