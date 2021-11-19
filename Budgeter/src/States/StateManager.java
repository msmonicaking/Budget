package States;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class StateManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 977693761657240777L;
	protected JLayeredPane pane;
	protected State mainScreen;
	protected State loginScreen;

	public StateManager() throws HeadlessException {
		super("BudGET");
		State.sm = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = getLayeredPane();
		loginScreen = new Login();
		settings();
		add(loginScreen);
	}

	protected void initMain() {
		mainScreen = new Main();
	}
	
	private void settings() {
		setSize(1280, 720);
		setVisible(true);
	}
	
	

	public static void main(String[] args) {
		StateManager sm = new StateManager();
	}
}
