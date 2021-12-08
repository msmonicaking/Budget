package States;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import FileHandler.Transaction;

public class CategoryOverlay extends State {

	/**
	 * 
	 */
	private static final long serialVersionUID = -862981815128451701L;
	private CategoryWindow cw = new CategoryWindow();
	protected Main page;
	
	public CategoryOverlay(Main page) {
		this.page = page;
		init();
		
		add(cw);
		centerScreen(cw);
	}
	
	private void init() {
		setBounds(50, 0, 1230, 720);
		setVisible(true);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 100));
	}

	
	
	private void centerScreen(CategoryWindow cw) {
		int x = (getWidth() / 2) - (CategoryWindow.windowWidth / 2);
		int y = (getHeight() / 2) - (CategoryWindow.windowHeight / 2);
		System.out.println(getWidth() + " " + getHeight());
		cw.setLocation(x, y);
	}
	
	
	
	
	/**
	 * 
	 */
	void drawBackground(Graphics2D g) {
		g.setColor(new Color(0, 0, 0, 110));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	static class CategoryWindow extends JComponent {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4830083721981798147L;
		static Transaction[] list;
		static String category;
		static int windowWidth = 600, windowHeight = 600;
		
		public CategoryWindow() {
			setFocusable(true);
			init();
			initCloseButton();
		}
		
		private void init() {
			setBackground(Color.WHITE);
			setLocation(0, 0);
			setSize(windowWidth, windowHeight);
		}
		
		private void initCloseButton() {
			int width = 40;
			int height = 40;
			int x = windowWidth - width;
			int y = 0;
			JButton close = new JButton("X");
			close.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					sm.mainPage.setVisibleCW(false);
				}
				
			});
			Font font = new Font("Arial", 0, 5);
			close.setFont(font);
			close.setBounds(x, y, width, height);
			add(close);
		}
		
		
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			drawWindow(g2);
		}
		
		void drawWindow(Graphics2D g) {
			g.setColor(getBackground());
			g.fillRoundRect(0, 0, windowWidth, windowHeight, 10, 10);
		}
		
	}

}
