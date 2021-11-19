package States;
import java.awt.Component;
//import java.awt.LayoutManager;
//import java.util.LinkedList;
import java.awt.Color;

import javax.swing.JPanel;

public class State extends JPanel {

	// Potential Color pallette for ButGET
	// see Java Swing google doc for visual aid
	Color teaGreen = new Color(201, 228, 202); // text color?
	Color etonBlue = new Color(135, 187, 162);
	Color steelTeal = new Color(85, 130, 139); 
	Color deepSpace = new Color(59, 96, 100);
	Color charcoal = new Color(54, 73, 88); // background color currently

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
	protected void switchPanel(JPanel panel) {
		sm.pane.removeAll();
		sm.pane.add(panel);
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

}
