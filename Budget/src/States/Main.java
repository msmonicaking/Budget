package States;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import FileHandler.FileIO;
import FileHandler.Date;
import FileHandler.Transaction;
import FileHandler.TransactionRow;
import FileHandler.TransactionRowList;
import States.CategoryOverlay.CategoryWindow;

import javax.swing.JTabbedPane;

public class Main extends State {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3206847208968227199L;
	private MainPage page;
	private int year, month;
	protected JLabel monthS;
	JTabbedPane monthTabs;
	FileIO userFile;
	
	public Main(MainPage page) {

		this.page = page;
		LocalDateTime now = LocalDateTime.now();
		year = now.getYear();
		month = now.getMonth().getValue() - 1;
		init();
		drawYear();
		drawMonthS();
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

	private void updateDate() {
		monthS.setText(monthNames[month]);
	}

	public int getMonth() { return month; }
	public int getYear() { return year; }
	
	/**
	 * Draws the month.
	 */
	private void drawMonthS() {
		monthS = new JLabel(monthNames[month]);
		
		Font font = new Font("Arial", 0, 50);
		monthS.setFont(font);
		monthS.setBackground(Color.white);
		monthS.setForeground(Color.white);
		monthS.setBounds(150, 0, 400, 100);
		
		add(monthS);
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
			scrollable.getVerticalScrollBar().setUnitIncrement(16);
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
					month = index;
					updateDate();
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
		JTextField addCategoryField;
		JTextField setBudgetField;
		JButton setBudgetButton;
		JButton addCategoryButton;
		JLabel currentBudget;
		int monthInt;

		public DataPane(int monthInt) {
			this.monthInt = monthInt;
			init();
			initAddCategoryField();
			initSetBudgetField();
//			title();
			loadCategories();

//			displayAllTransactions();

		}

		/**
		 * Enter new category name and add button.
		 * 
		 * To-Do 
		 * Add a new CategoryBox to "list" and refresh when adding a new category.
		 */
		private void initAddCategoryField() {
			addCategoryField = createTextBox("   Add Category");

			addCategoryField.setLocation(15, 5);
			addCategoryField.setSize(150, 40);
			add(addCategoryField);

			addCategoryButton = new JButton("+");
			addCategoryButton.setSize(40, 40);
			addCategoryButton.setLocation(175, 5);
			add(addCategoryButton);

			addCategoryButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!addCategoryField.getText().equals("   Add Category")) {
						list.add(new CategoryBox(addCategoryField.getText()));
						refresh();
					}

				}

			});

		}
		
		/**
		 * Set budget and draw budget.
		 * 
		 */
		private void initSetBudgetField() {
			setBudgetField = createTextBox("   Set Budget");

			setBudgetField.setLocation(250, 5);
			setBudgetField.setSize(150, 40);
			add(setBudgetField);

			Font font = new Font("Arial", 0, 28);
			currentBudget = new JLabel("Budget: $1000");
			currentBudget.setForeground(Color.WHITE);
			currentBudget.setFont(font);
			currentBudget.setSize(300, 40);
			currentBudget.setLocation(480, 5);
			add(currentBudget);
			
			setBudgetButton = new JButton("Set");
			setBudgetButton.setSize(60, 40);
			setBudgetButton.setLocation(410, 5);
			setBudgetButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						double temp = Double.parseDouble(setBudgetField.getText());
						currentBudget.setText("Budget: $" + temp + "");
						setBudgetField.setText("   Set Budget");
					} catch (Exception a) {
						System.err.println("Not a valid number. ");
					}
				}
				
			});
			add(setBudgetButton);

			
			setBudgetButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// FileIO set budget.

				}

			});

		}

		/**
		 * Use to refresh category boxes when updating it.
		 */
		private void refresh() {
			int xOff = 10;
			int yOff = 50;
			int spacing = 10;
			removeAll();
			add(setBudgetField);
			add(setBudgetButton);
			add(addCategoryButton);
			add(currentBudget);
			add(addCategoryField);

			addCategoryField.setText("   Add Category");
			for (CategoryBox r : list) {
				r.setLocation(xOff, yOff);
				r.setForeground(charcoal);
				yOff += spacing + r.getHeight();
				add(r);
			}
			Dimension temp = getSize();
			setSize((int) temp.getWidth(), (int) (temp.getHeight() + yOff));
			setPreferredSize(new Dimension((int) temp.getWidth(), (int) (yOff)));
		}

		private void loadCategories() {
			
			CategoryBox c1 = new CategoryBox("Groceries");
			CategoryBox c2 = new CategoryBox("Rent");
			CategoryBox c3 = new CategoryBox("Transportation");
			CategoryBox c4 = new CategoryBox("Fees");

			list.add(c1);
			list.add(c2);
			list.add(c3);
			list.add(c4);

			refresh();
		}

		private void init() {
			setBounds(0, 0, getWidth(), 2000);
			setPreferredSize(new Dimension(getWidth(), 2000));
			setVisible(true);
			setLayout(null);
//			setForeground(steelTeal);
			setBackground(steelTeal);
		}


		class CategoryBox extends JComponent {

			/**
			 * 
			 */

			private String category = "";
			private TransactionRowList list = new TransactionRowList();
			private static final long serialVersionUID = -8324410991043185795L;

			public CategoryBox(String category) {
				this.category = category;
				loadTemp();
				init();
				setFocusSettings();
			}

			public void init() {
				setLocation(10, 50);
				setSize(800, 150);
				setBackground(etonBlue);
				addMouseListener(new MouseAdapter() {
					
					/*
					 * Listens for Category Box to be clicked on, triggers opening to CategoryOverlay
					 */
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						//System.out.println("clicked");
						// Set transaction info to CategoryWindow

						for (TransactionRow r : list.getList()) {
							r.delete.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									list.getList().remove(r);
									sm.repaint();
									CategoryWindow.refresh();
								}

							});
						}
						CategoryWindow.setList(list);
						CategoryWindow.setTitle(category);
						
						page.setVisibleCW(true);
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
					list.addTransaction(new TransactionRow(temp));
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
				if (!list.getList().isEmpty()) {
				for (int i = 0; i < list.getList().size() && i < 2; i++) {
					g.drawString(list.getList().get(i).getTransaction().getDate().day + "", xOff + 10, spacing * i + yOff);
					g.drawString(list.getList().get(i).getTransaction().getName(), xOff + 90, spacing * i + yOff);
					g.drawString(list.getList().get(i).getTransaction().getPrice() + "", xOff + getWidth() - 130, spacing * i + yOff);
//					System.out.println(getHeight() + spacing);
					
				}
				}
			}

			/**
			 * Not needed.
			 */
//			public void refresh() {
//				if (list.getList().size() == 0) {
//					setSize(new Dimension(getWidth(), 90));
//					setPreferredSize(new Dimension(getWidth(), 90));
//				} else if (list.getList().size() == 1) {
//					setSize(new Dimension(getWidth(), 120));
//					setPreferredSize(new Dimension(getWidth(), 120));
//				} else {
//					setSize(new Dimension(getWidth(), 150));
//					setPreferredSize(new Dimension(getWidth(), 150));
//				}
//			}

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