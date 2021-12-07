package States;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import FileHandler.Date;
import FileHandler.Transaction;

import javax.swing.JTabbedPane;

public class Main extends State {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3206847208968227199L;
	private MainPage page;
	private int year, month;
	JTabbedPane monthTabs;
	public Main(MainPage page) {
		this.page = page;
		LocalDateTime now = LocalDateTime.now();
		year = now.getYear();
		month = now.getMonth().getValue() - 1;
		init();
		drawYear();
		makeMonthTabs();

	}

	/**
	 * Draws the year.
	 */
	private void drawYear() {
		JLabel yearLabel = new JLabel(year + "");
		Font font = new Font("Arial", 0, 50);
		yearLabel.setFont(font);
		yearLabel.setBackground(Color.white);
		yearLabel.setForeground(Color.white);
		yearLabel.setBounds(20, 0, 200, 100);
		add(yearLabel);
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
		monthTabs.setSelectedIndex(month);
		
		// Reset scroll bar when tab is changed.
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				
				int index = sourceTabbedPane.getSelectedIndex();
				if (sourceTabbedPane.getComponentAt(index) instanceof JScrollPane) {
					((JScrollPane) sourceTabbedPane.getComponentAt(index)).getVerticalScrollBar().setValue(0);
				}
				
			}
		};
		monthTabs.addChangeListener(changeListener);
		add(monthTabs);

	}

	// The inner State, one of these per month
	class DataPane extends State {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6883981624969242952L;
		private ArrayList<CategoryBox> list = new ArrayList<>();
		int monthInt;

		public DataPane(int monthInt) {
			this.monthInt = monthInt;
			init();
			initAddCategoryField();
			title();
			loadCategories();

//			displayAllTransactions();

		}

		private void initAddCategoryField() {
			JTextField addCategory = createTextBox("   Add Category");

			addCategory.setLocation(15, 100);
			addCategory.setSize(150, 40);
			add(addCategory);
		}

		private void loadCategories() {
			CategoryBox c1 = new CategoryBox("Food");
			c1.setBackground(etonBlue);
			CategoryBox c2 = new CategoryBox("Entertainment");
			c2.setBackground(etonBlue);
			list.add(c1);
			list.add(c2);

			int yOff = 0;
			int spacing = 10;
			for (CategoryBox c : list) {
				c.setOffset(0, yOff);
				add(c);
				yOff = c.getHeight() + spacing;
			}
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

			public CategoryBox(String category) {
				this.category = category;
				loadTemp();
				init();
				setFocusSettings();
			}

			public void init() {
				setLocation(10, 150);
				setSize(800, 200);
				addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						System.out.println("clicked");
					}
				});
			}

			public void setOffset(int xOff, int yOff) {
				setLocation(getX() + xOff, getY() + yOff);
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
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				drawBox(g2);
				drawHeader(g2);
				drawItems(g2);
			}

			void drawBox(Graphics2D g) {
				if (isVisible()) {
					g.setColor(getBackground());
					g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
					Font categoryFont = new Font("Arial", 0, 50);
					g.setColor(Color.WHITE);
					g.setFont(categoryFont);
					g.drawString(category, 10, 45);
				}
			}

			void drawHeader(Graphics2D g) {
				int xOff = 10;
				int yOff = 75;
				if (isVisible()) {
					g.setColor(Color.WHITE);
					Font font = new Font("Arial", 0, 25);
					g.setFont(font);
					g.drawString("Day", xOff + 10, yOff);
					g.drawString("Name", xOff + 90, yOff);
					g.drawString("Cost", xOff + getWidth() - 130, yOff);
				}
			}

			void drawItems(Graphics2D g) {
				int xOff = 10;
				int yOff = 110;
				int spacing = 5;

				g.setColor(Color.WHITE);
				Font font = new Font("Arial", 0, 25);
				g.setFont(font);
				FontMetrics fm = g.getFontMetrics();

				int fontHeight = fm.getMaxAscent();
				spacing += fontHeight;

				for (int i = 0; i < 2 && list.size() >= 2; i++) {
					g.drawString(list.get(i).getDate().day + "", xOff + 10, spacing * i + yOff);
					g.drawString(list.get(i).getName(), xOff + 90, spacing * i + yOff);
					g.drawString(list.get(i).getPrice() + "", xOff + getWidth() - 130, spacing * i + yOff);
				}
			}

			/**
			 * Focuses to anything that is clicked.
			 */
			public void setFocusSettings() {
				setFocusable(true);
				addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseReleased(e);
						CategoryBox.this.grabFocus();
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						CategoryBox.this.grabFocus();
					}
				});
			}

		}

	}

}