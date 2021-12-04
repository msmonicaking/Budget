package States;

import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
//import javax.swing.LookAndFeel;

import javax.swing.JLabel;
import javax.swing.JPanel;
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
		//setLayout(new GridLayout(1, 1));
		setLayout(null);

		makeMonthTabs();

	}
	
	/*
	 * Set Main state settings.
	 */
	private void init() {

		universalSettings();

	}

	private void makeMonthTabs() {

		JTabbedPane monthTabs = new JTabbedPane();
		
		int monthAt = 0;
		for(String aMonth : monthNames) {
			monthTabs.addTab(aMonth, new DataPane(monthAt));
			monthAt++;
		}

		centerWidth(this);
		monthTabs.setBounds(0, 0, 1265, 680);

		monthTabs.setBackground(deepSpace);
		monthTabs.setForeground(steelTeal);
		
		monthTabs.setTabPlacement(JTabbedPane.BOTTOM);

		add(monthTabs);
	}

	// The inner State, one of these per month
	class DataPane extends State {

		int monthInt;

		public DataPane(int monthInt) {

			this.monthInt = monthInt;

			universalSettings();
			setBackground(steelTeal);
			centerWidth(this);

			title();
			displayAllTransactions();

		}

		private void title() {

			JLabel header = new JLabel(monthNames[monthInt]);
			header.setFont(headerFont);

			add(header);

		}

		private void displayAllTransactions() {


			// get transactions from File
			//String[][] transactionData = 
	
			// display in a JTable
			JTable table = new JTable();
			table.setBounds(100,100, 100, 100);

			add(table);
	
		}


	}

}