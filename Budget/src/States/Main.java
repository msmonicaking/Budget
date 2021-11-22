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

	public Main() {

		init();
		//setLayout(new GridLayout());
		setLayout(null);

		monthYearSetters();

	}
	
	/*
	 * Set Main state settings.
	 */
	private void init() {

		universalSettings();

	}

	private void monthYearSetters() {

		JTabbedPane monthTabs = new JTabbedPane();

		for(String aMonth : monthNames) {
			monthTabs.addTab(aMonth, new DataPane());
		}

		//centerWidth(this);
		monthTabs.setBounds(0, 0, 1265, 680);

		monthTabs.setBackground(deepSpace);
		//monthTabs.setForeground(steelTeal);
		
		monthTabs.setTabPlacement(JTabbedPane.BOTTOM);

		add(monthTabs);
	}

	// The inner State, one of these per month
	class DataPane extends State {


		public DataPane() {

			universalSettings();
			setBackground(steelTeal);
			centerWidth(this);

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
