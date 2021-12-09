package States;

import java.awt.Color;
import FileHandler.TransactionRow;
import FileHandler.TransactionRowList;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import FileHandler.Date;
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
		protected static TransactionRowList list;
		protected static String category;
		private static JScrollPane scrollPane;
		private static State transactionList;
		private static JTextField dayTF, nameTF, costTF; 
		private static JButton addNewTransaction;
		private static JLabel title;
		static int windowWidth = 700, windowHeight = 600;

		public CategoryWindow() {
			category = "Entertainment";
			setFocusable(true);
			init();
			initTitle();
			initCloseButton();
			initScrollPane();
			initHeader();
			initFields();
			// Test method that adds to the transactionList being displayed.
//			test();
		}
		
		
//		private void test() {
//			Transaction te = new Transaction("Food", "Milk", 10.0, new Date(10, 10, 2021));
//			for (int i = 0; i < 40; i++) {
//				addTransaction(te);
//			}
//			
//		}

		private void init() {
			setBackground(steelTeal);
			setLocation(0, 0);
			setSize(windowWidth, windowHeight);
		}
		
		/**
		 * Creates the panel that shows the transactions.
		 */
		private void initScrollPane() {
			transactionList = new State();
			transactionList.setSize(600, 20);
			transactionList.setLocation(0, 0);
			transactionList.setPreferredSize(new Dimension(600, 20));
			transactionList.setLayout(null);
			transactionList.setVisible(true);
			
			scrollPane = new JScrollPane(transactionList);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportView(transactionList);
			scrollPane.setLayout(new ScrollPaneLayout());
			scrollPane.setSize(new Dimension(600, 400));
			scrollPane.setLocation(50, 150);

			add(scrollPane);
		}

		/**
		 * Prints the category name on the window.
		 */
		private void initTitle() {
			title = new JLabel(category);
			title.setForeground(Color.WHITE);
			Font font = new Font("Arial", 0, 50);
			title.setFont(font);
			title.setLocation(15, 20);
			title.setSize(450, 40);
			add(title);
		}
		
		public static void setList(TransactionRowList l) {
			list = l;
			refresh();
		}
		
		/**
		 * Set title
		 */
		
		public static void setTitle(String s) {
			title.setText(s);
		}

		/**
		 * Prints variable "list" to screen. Should be called after adding new
		 * transaction.
		 */
		static void refresh() {
			int xOff = 0;
			int yOff = 10;
			int spacing = 28;
			transactionList.removeAll();
			transactionList.setPreferredSize(new Dimension(600, 20));
			transactionList.setSize(new Dimension(600, 20));
			
			for (TransactionRow r : list.getList()) {
				r.setLocation(xOff, yOff);
				r.setForeground(charcoal);
				yOff += spacing;
				transactionList.add(r);
			}
			Dimension temp = transactionList.getSize();
			transactionList.setSize((int) temp.getWidth(), (int) (temp.getHeight() + yOff));
			transactionList.setPreferredSize(new Dimension((int) temp.getWidth(), (int) (yOff)));
		}

		/**
		 * Prints headers on top of list of transactions.
		 */
		private void initHeader() {
			Font font = new Font("Arial", 0, 25);
			JLabel day = new JLabel("Day");
			JLabel name = new JLabel("Name");
			JLabel cost = new JLabel("Cost");
			day.setFont(font);
			name.setFont(font);
			cost.setFont(font);
			day.setSize(100, 75);
			name.setSize(300, 75);
			cost.setSize(150, 75);
			day.setLocation(50, 50);
			name.setLocation(130, 50);
			cost.setLocation(getWidth() - 195, 50);
			day.setForeground(Color.white);
			name.setForeground(Color.white);
			cost.setForeground(Color.white);
			add(day);
			add(name);
			add(cost);
		}
		
		/**
		 * Initiates fields where transactions are entered.
		 * 
		 * To-Do
		 * Check if fields are valid.
		 * If valid create transaction object and add to variable "list"
		 * Also use FileIO to add to json file correspondent to user.
		 */
		private void initFields() {
			dayTF = createTextBox("Day");
			nameTF = createTextBox("Name");
			costTF = createTextBox("Cost");
			dayTF.setSize(65, 30);
			nameTF.setSize(360, 30);
			costTF.setSize(75, 30);
			dayTF.setLocation(50, 105);
			nameTF.setLocation(130, 105);
			costTF.setLocation(getWidth() - 195, 105);
			add(dayTF);
			add(nameTF);
			add(costTF);
			
			Font font = new Font("Arial", 0, 10);
			addNewTransaction = new JButton("+");
			addNewTransaction.setFont(font);
			addNewTransaction.setSize(30, 30);
			addNewTransaction.setLocation(getWidth() - 105, 105);
			addNewTransaction.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// If all fields are correct, create new transaction class then call addTransaction()
					// Clear fields if correct.
				}
				
			});
			add(addNewTransaction);
		}

		/**
		 * Closes the window.
		 */
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
