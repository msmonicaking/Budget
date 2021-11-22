package States;

import java.awt.LayoutManager;

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

	public Main() {

		init();
		monthYearSetters();

	}
	
	/*
	 * Set Main state settings.
	 */
	private void init() {

		universalSettings();

	}

	private void monthYearSetters() {

		JTabbedPane monthTabs = new JTabbedPane(SwingConstants.BOTTOM);

		for(String aMonth : monthNames) {

			monthTabs.addTab(aMonth, new DataPane());

		}

		add(monthTabs);
	}

	// The inner State, one of these per month
	class DataPane extends State {


		public DataPane() {
			universalSettings();
		}

		private void displayAllTransactions() {


			// get transactions from File
			//String[][] transactionData = 
	
			// display in a JTable
			JTable table = new JTable();
			
			add(table);
	
		}


	}

}
