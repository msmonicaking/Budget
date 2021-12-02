package States;

import java.awt.Component;
import java.awt.Font;
//import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
//import java.util.LinkedList;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class State extends JPanel {

	// Potential Color pallette for ButGET
	// see Java Swing google doc for visual aid
	Color teaGreen = new Color(201, 228, 202); // text color?
	Color etonBlue = new Color(135, 187, 162);
	Color steelTeal = new Color(85, 130, 139);
	Color deepSpace = new Color(59, 96, 100);
	Color charcoal = new Color(54, 73, 88); // background color currently

	Font headerFont = new Font("Arial", Font.ITALIC, 100);

	String[] monthNames = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

	/**
	 * 
	 */
	private static final long serialVersionUID = -1367216474109123645L;
	protected static StateManager sm;

	public State() {

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

}
