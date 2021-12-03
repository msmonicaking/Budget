package States;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	int showWidth = 200;
	int hiddenWidth = 50;
	int height = 720;
	boolean show = false;
	boolean pressed = false;

	ArrayList<JButton> menuButtons;
	private MainPage page;

	public Menu(MainPage page) {
		this.page = page;
		menuButtons = new ArrayList<>();
		init();
		initButtons();
		MouseAdapter ma = new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (MouseEvent.BUTTON1 == e.getButton()) {
					if (e.getX() < showWidth + hiddenWidth) {
						pressed = true;
					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (MouseEvent.BUTTON1 == e.getButton()) {
					if (pressed && !show) {
						if (e.getX() < showWidth + hiddenWidth) {
							toggleMenu();
						}
					}
				}
				pressed = false;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (show && e.getX() >= showWidth + hiddenWidth) {
					toggleMenu();
				}
			}
			
		};
		addMouseListener(ma);
		
	}

	private void toggleMenu() {
		if (show) {
			setLocation(getX() - showWidth, getY());
		} else {
			setLocation(getX() + showWidth, getY());
		}
		show = !show;
	}

	/*
	 * Sets Login state settings.
	 */
	private void init() {
		universalSettings();
		setLayout(null);
		setSize(hiddenWidth + showWidth, height);
		setLocation(0 - showWidth, getY());
	}

	private void initButtons() {
		JButton home = new JButton("Home");
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				page.switchTo(Screens.HOME);
				toggleMenu();
			}

		});
		menuButtons.add(home);

		JButton transaction = new JButton("Transaction");
		transaction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				page.switchTo(Screens.MAIN);
				toggleMenu();
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
		int x = (showWidth / 2) - (com.getWidth() / 2);
		com.setLocation(x, com.getY());
	}

	@Override
	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
		g.setColor(deepSpace);
		g.fillRect(0, 0, showWidth + hiddenWidth, height);
	}

}
