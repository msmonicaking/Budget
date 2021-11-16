import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends State {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3206847208968227199L;

	public Main() {
		init();
		// Temp
		JLabel test = new JLabel("MainScreen");
		centerWidth(test);
		Font f = new Font("Arial", Font.ITALIC, 100);
		test.setFont(f);
		add(test);
	}
	
	/*
	 * Set Main state settings.
	 */
	private void init() {
		setSize(1280, 720);
		setVisible(true);
	}

}
