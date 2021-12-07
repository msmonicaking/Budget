package States;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
//import javax.swing.LookAndFeel;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;

import FileHandler.Date;
import FileHandler.Transaction;

import javax.swing.JTabbedPane;

public class Main extends State {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3206847208968227199L;
	private MainPage page;

	public Main(MainPage page) {
		this.page = page;
		init();
		makeMonthTabs();

	}

	/*
	 * Set Main state settings.
	 */
	private void init() {

//		universalSettings();
		setLayout(null);
		setSize(1165, 680);
		setBounds(50, 0, 1215, 680);
		setBackground(charcoal);
	}

	private void makeMonthTabs() {
		int width = getWidth();
		int height = 610;
		JTabbedPane monthTabs = new JTabbedPane();

		int monthAt = 0;
		for (String aMonth : monthNames) {
			DataPane temp = new DataPane(monthAt);
			JScrollPane scrollable = new JScrollPane(temp);
			scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollable.setViewportView(temp);
			scrollable.setSize(new Dimension(width, height));
			scrollable.setLayout(new ScrollPaneLayout());

			monthTabs.addTab(aMonth, scrollable);

			monthAt++;
		}
		monthTabs.setBounds(0, 75, width, height);
		monthTabs.setBackground(deepSpace);
		monthTabs.setForeground(steelTeal);

		monthTabs.setTabPlacement(JTabbedPane.TOP);

		add(monthTabs);

	}

	// The inner State, one of these per month
	class DataPane extends State {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6883981624969242952L;
		int monthInt;

		public DataPane(int monthInt) {
			this.monthInt = monthInt;
			init();
			title();
			loadCategories();
//			displayAllTransactions();

		}

		private void loadCategories() {
			CategoryBox c1 = new CategoryBox("Food");
			c1.setBackground(etonBlue);
			add(c1);
		}

		private void init() {
			setBounds(0, 0, getWidth(), 2000);
			setPreferredSize(new Dimension(getWidth(), 2000));
			setVisible(true);
			setLayout(null);
//			setForeground(steelTeal);
			setBackground(steelTeal);
		}

		private void title() {
			JLabel header = new JLabel(monthNames[monthInt]);
			header.setFont(headerFont);
			header.setBackground(Color.WHITE);
			header.setLocation(0, 0);
			header.setBounds(10, 10, 680, 100);
			add(header);

		}

		private void displayAllTransactions() {

			// get transactions from File
			// String[][] transactionData =

			// display in a JTable
			JTable table = new JTable();
			table.setBounds(0, 0, 1280, 720);
			add(table);

		}

		class CategoryBox extends JComponent {

			/**
			 * 
			 */

			private String category = "";
			private ArrayList<Transaction> list = new ArrayList<>();
			private static final long serialVersionUID = -8324410991043185795L;

			private int categorySize = 30;

			public CategoryBox(String category) {
				this.category = category;
				loadTemp();
				init();
			}

			public void init() {
				setLocation(10, 125);
				setSize(500, 100);
				addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						System.out.println("clicked");
					}
				});
			}

			private void loadTemp() {
				for (int i = 0; i < 10; i++) {
					int day = (int) (Math.random() * 10) + 1;
					double price = (int) (Math.random() * 100);
					Transaction temp = new Transaction("Food", "test" + i, price, new Date(day, 12, 2021));
					list.add(temp);
				}
			}

			@Override
			protected void paintComponent(Graphics g) {
				drawBox((Graphics2D) g);
				drawHeader((Graphics2D) g);
			}

			void drawBox(Graphics2D g) {
				if (isVisible()) {
					g.setColor(getBackground());
					g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
					Font categoryFont = new Font("Arial", 0, categorySize);
					g.setColor(Color.WHITE);
					g.setFont(categoryFont);
					g.drawString(category, 5, 30);
				}
			}

			void drawHeader(Graphics2D g) {

			}

		}

	}

}