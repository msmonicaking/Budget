package States;

import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
//import javax.swing.LookAndFeel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
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
		setBounds(50, 0, 1215, 680);
		makeMonthTabs();

	}
	
	/*
	 * Set Main state settings.
	 */
	private void init() {

		universalSettings();
		setLayout(null);
	}

	private void makeMonthTabs() {

		JTabbedPane monthTabs = new JTabbedPane();
//		sm.getContentPane().setLayout(new FlowLayout());
		int monthAt = 0;
		for(String aMonth : monthNames) {
			DataPane temp = new DataPane(monthAt);
			JScrollPane scrollable = new JScrollPane(temp);
			scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollable.setViewportView(temp);
			monthTabs.addTab(aMonth, scrollable);
			scrollable.setSize(new Dimension(1165, 680));
			monthAt++;
		}
		monthTabs.setBounds(0, getY(), getWidth(), getHeight());

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
			this.setBounds(0, 0, 3000, 720);
			this.setPreferredSize(new Dimension(3000, 720));
			this.setBackground(Color.WHITE);
			setVisible(true);
			title();

//			displayAllTransactions();
			
		}
		
		private void init() {
			universalSettings();
			setLayout(null);
		}

		private void title() {
			
			JLabel header = new JLabel(monthNames[monthInt]);
			header.setFont(headerFont);
			header.setLocation(0, 200);
			centerWidth(header);
			this.add(header);

		}

		private void displayAllTransactions() {

			// get transactions from File
			//String[][] transactionData = 
	
			// display in a JTable
			JTable table = new JTable();
			table.setBounds(0, 0, 1280, 720);
			this.add(table);
	
		}


	}

}