package States;

import java.awt.Component;
import java.awt.Font;
//import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.util.LinkedList;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class State extends JPanel {

	// Potential Color palette for ButGET
	// see Java Swing google doc for visual aid
	protected Color teaGreen = new Color(201, 228, 202); // text color?
	protected Color etonBlue = new Color(135, 187, 162);
	protected Color steelTeal = new Color(85, 130, 139);
	protected Color deepSpace = new Color(59, 96, 100);
	protected Color charcoal = new Color(54, 73, 88); // background color currently

	Font headerFont = new Font("Arial", Font.PLAIN, 100);

	String[] monthNames = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

	/**
	 * 
	 */
	private static final long serialVersionUID = -1367216474109123645L;
	protected static StateManager sm;

	public State() {
		setFocusSettings();
	}

	/*
	 * Switches screen to "panel".
	 */
	protected void switchPanel(JPanel... panel) {
//		System.out.println("switched");
		sm.pane.removeAll();
		for (JPanel p : panel) {
			sm.pane.add(p);
		}
		sm.pane.repaint();
		sm.pane.revalidate();
	}

	protected void logout() {
		sm.loginPage.logout();
	}

	protected void centerWidth(Component com) {

		int x = (getSize().width / 2) - (com.getSize().width / 2);
		com.setBounds(x, com.getY(), com.getWidth(), com.getHeight());
	}

	public void universalSettings() {

		setSize(1280, 720);
		setVisible(true);
		setBackground(charcoal);

	}
	
	/**
	 * Focuses to anything that is clicked.
	 */
	public void setFocusSettings() {
		setFocusable(true);
		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                State.this.grabFocus();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                State.this.grabFocus();
            }
        });
	}

	/**
	 * @param name: Placeholder inside text box.
	 * @return : JTextField.
	 */
	protected JTextField createTextBox(String name) {

		JTextField temp = new JTextField(name);

		// a Focus Listener changes behavior when user clicks on text box
		temp.addFocusListener(new FocusListener() {

			// defines behavior when clicked in
			@Override
			public void focusGained(FocusEvent e) {

				if (temp.getText().equals(name)) {
					temp.setText("");
				}
			}

			// defines behavior when clicked away
			@Override
			public void focusLost(FocusEvent e) {
				if (temp.getText().equals("")) {
					temp.setText(name);
				}
			}

		});
		return temp;
	}
	
		/**
		 * @param name: Placeholder inside text box.
		 * @return : JPasswordField
		 */
		protected JTextField createPassBox(String name) {

			JPasswordField temp = new JPasswordField(name);

			// a Focus Listener changes behavior when user clicks on text box
			temp.addFocusListener(new FocusListener() {

				// defines behavior when clicked in
				@Override
				public void focusGained(FocusEvent e) {
					if (temp.getPassword().length == 0) {
						temp.setText("");
					}
				}

				// defines behavior when clicked away
				@Override
				public void focusLost(FocusEvent e) {
					if (temp.getPassword().length == 0) {
						temp.setText(name);
					}
				}

			});

			return temp;
	}

}