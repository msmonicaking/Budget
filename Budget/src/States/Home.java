package States;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import FileHandler.FileIO;
import FileHandler.Transaction;
import FileHandler.Date;

public class Home extends State {

	private MainPage page;

	public Home(MainPage page) throws Exception {
		this.page = page;
		init();
		initChart();
	}

	public void init() {
		setBounds(50, 0, 1215, 680);
		setLayout(null);
		setVisible(true);
	}

	public void initChart() throws Exception {
		FileIO fileIO = new FileIO();
		if (fileIO.isEmpty(page.username)) {
			JLabel banner = new JLabel("No transactions added this month.");
			banner.setBounds(0, getSize().height / 4, 300, 300);
			centerWidth(banner);
			add(banner);
		} else {
			LocalDate currentDate = LocalDate.now();
			Month month = currentDate.getMonth();
			Date date = new Date(currentDate.getDayOfMonth(), month.getValue(), currentDate.getYear());
			Transaction[] arr = fileIO.getAllTransactions(page.username, date.year, date.month);
			Map<String, Double> map = new HashMap<String, Double>();
			for (Transaction i : arr) {
				if (map.containsKey(i.getCategory())) {
					map.put(i.getCategory(), (double) (map.get(i.getCategory()) + i.getPrice()));
				} else {
					map.put(i.getCategory(), i.getPrice());
				}
			}

			DefaultPieDataset result = new DefaultPieDataset();
			for (Map.Entry<String, Double> entry : map.entrySet()) {
				result.setValue(entry.getKey(), entry.getValue());
			}

			PieDataset dataset = result;

			JFreeChart chart = ChartFactory.createPieChart3D("" + currentDate.getMonth() + " " + currentDate.getYear() + " SPENDINGS", dataset, true, true, false);
			PiePlot3D plot = (PiePlot3D) chart.getPlot();
			plot.setStartAngle(0);
			plot.setDirection(Rotation.CLOCKWISE);
			plot.setForegroundAlpha(0.5f);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new java.awt.Dimension(200, 200));
			chartPanel.setBounds(0, (int) (2 * getSize().height / 6), 600, 300);
			centerWidth(chartPanel);
			add(chartPanel, java.awt.BorderLayout.CENTER);
		}
	}
}