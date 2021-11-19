package States;
import java.awt.Component;
//import java.awt.LayoutManager;
//import java.util.LinkedList;
import java.awt.Color;

import javax.swing.JPanel;

public class State extends JPanel {

	Color backgroundColor = new Color(54, 73, 88);

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
		setBackground(backgroundColor);

	}

}
