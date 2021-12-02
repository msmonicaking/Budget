package States;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import FileHandler.FileIO;

/*
 * Sliding nagivation panel on the left.
 */
public class Menu extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2763300197436203685L;
	int width = 250;
	int height = 720;
	
	ArrayList<JButton> menuButtons;
	private MainPage page;
	
	public Menu(MainPage page) {
		this.page = page;
		menuButtons = new ArrayList<>();
		init();
		initButtons();
	}
	
	/*
	 * Sets Login state settings.
	 */
	private void init() {
		universalSettings();
		setLayout(null);
	}

	private void initButtons() {
		JButton home = new JButton("Home");
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				page.switchTo(Screens.HOME);
			}

		});
		menuButtons.add(home);
		
		JButton transaction = new JButton("Transaction");
		transaction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				page.switchTo(Screens.MAIN);
			}
			
		});
		menuButtons.add(transaction);
		
		int spacing = 10;
		int bHeight = 40;
		int bWidth = 200;
		
		for (int i = 0; i < menuButtons.size(); i++) {
			menuButtons.get(i).setSize(bWidth, bHeight);
			menuButtons.get(i).setLocation(0, 100 + ((bHeight + spacing) * (i + 1)));
			centerWidth(menuButtons.get(i));
			add(menuButtons.get(i));
		}
	}
	
	@Override
	protected void centerWidth(Component com) {
		int x = (width / 2) - (com.getWidth() / 2);
		com.setLocation(x, com.getY());
	}
	
	@Override
	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
		g.setColor(deepSpace);
		g.fillRect(0, 0, width, height);
	}

}
