package States;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

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
	JButton logoutButton;
	private MainPage page;

	public Menu(MainPage page) {
		this.page = page;
		init();
		initButtons();
		initMouseListener();
		drawUsername();
	}

	public void initMouseListener() {
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
	
	private void drawUsername() {
		JLabel name = new JLabel(page.username);
		Font font = new Font("Arial", 0, 30);
		
		name.setFont(font);
		name.setBounds(10, 80, 190, 50);
		centerText(name);
		name.setBackground(Color.WHITE);
		name.setForeground(Color.WHITE);
		add(name);
	}

	/**
	 * Loads the buttons in the menu
	 */
	private void initButtons() {
		menuButtons = new ArrayList<>();
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

		// Add buttons to panel with specific dimensions and spacing.
		int spacing = 10;
		int bHeight = 40;
		int bWidth = 200;

		for (int i = 0; i < menuButtons.size(); i++) {
			menuButtons.get(i).setSize(bWidth, bHeight);
			menuButtons.get(i).setLocation(0, 100 + ((bHeight + spacing) * (i + 1)));
			centerWidth(menuButtons.get(i));
			add(menuButtons.get(i));
		}
		initLogoutButton();
	}

	public void initLogoutButton() {
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// save changes.
				// switch to login screen.
				logout();
			}

		});

		int bHeight = 40;
		int bWidth = 200;
		int xOff = 0;
		int yOff = 680 - bHeight;

		logoutButton.setSize(bWidth, bHeight);
		logoutButton.setLocation(xOff, yOff);
		add(logoutButton);
	}

	@Override
	protected void centerWidth(Component com) {
		int x = (showWidth / 2) - (com.getWidth() / 2);
		com.setLocation(x, com.getY());
	}
	
	/**
	 * Centers text in menu.
	 * @param com
	 */
	private void centerText(JLabel com) {
		int width = (int) com.getPreferredSize().getWidth();
		if (width >= showWidth) {
			com.setLocation(0, com.getY());
			return;
		}
		width /= 2;
		width = (showWidth / 2) - width;
		com.setLocation(width, com.getY());
	}

	@Override
	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
		g.setColor(deepSpace);
		g.fillRect(0, 0, showWidth + hiddenWidth, height);
	}

}
