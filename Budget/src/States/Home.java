package States;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
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
		initTitle();
		initPieChart();
		initLineGraph();
		initBar();
	}

	public void init() {
		setBounds(50, 0, 1215, 680);
		setLayout(null);
		setVisible(true);
	}

	public void initTitle() {
		LocalDate currentDate = LocalDate.now();
		JLabel title = new JLabel("" + currentDate.getMonth() + " " + currentDate.getYear());
		title.setBounds(0, -100, 300, 300);
		title.setFont(new Font("Dialog", Font.BOLD, 35));
		centerWidth(title);
		add(title);
	}

	// Pie Chart
	public void initPieChart() throws Exception {
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
			if (fileIO.checkYear(date, page.username)) {
				if (fileIO.checkMonth(date, page.username)) {
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

					JFreeChart chart = ChartFactory.createPieChart3D("SPENDING DISTRIBUTION", dataset, true, true,
							false);
					PiePlot3D plot = (PiePlot3D) chart.getPlot();
					plot.setStartAngle(0);
					plot.setDirection(Rotation.CLOCKWISE);
					plot.setForegroundAlpha(0.5f);
					ChartPanel chartPanel = new ChartPanel(chart);
					chartPanel.setPreferredSize(new java.awt.Dimension(200, 200));
					chartPanel.setBounds(0, (int) (2 * getSize().height / 12), 600, 300);
					add(chartPanel, java.awt.BorderLayout.CENTER);
				}
			}
		}
	}

	// Budget Progress Bar
	public void initBar() throws Exception {
		FileIO fileIO = new FileIO();
		if (!fileIO.isEmpty(page.username)) {
			LocalDate currentDate = LocalDate.now();
			Month month = currentDate.getMonth();
			Date date = new Date(currentDate.getDayOfMonth(), month.getValue(), currentDate.getYear());
			if (fileIO.checkYear(date, page.username)) {
				if (fileIO.checkMonth(date, page.username)) {
					Transaction[] arr = fileIO.getAllTransactions(page.username, date.year, date.month);
					Double budget = fileIO.getBudget(date, page.username);
					int budgetIndex = 0;
					Map<String, Double> map = new HashMap<String, Double>();
					for (Transaction i : arr) {
						budget -= i.getPrice();
						if (map.containsKey(i.getCategory())) {
							map.put(i.getCategory(), (double) (map.get(i.getCategory()) + i.getPrice()));
						} else {
							map.put(i.getCategory(), i.getPrice());
						}
					}

					DefaultCategoryDataset result = new DefaultCategoryDataset();
					for (Map.Entry<String, Double> entry : map.entrySet()) {
						result.addValue(entry.getValue(), entry.getKey(), "Spendings");
						budgetIndex++;
					}
					if (budget > 0) {
						result.addValue(budget, "Budget left = " + budget, "Spendings");
					}

					CategoryDataset dataset = result;

					JFreeChart chart = ChartFactory.createStackedBarChart("Budget Spent", "", "", dataset,
							PlotOrientation.HORIZONTAL, isBackgroundSet(), getIgnoreRepaint(),
							getFocusTraversalKeysEnabled());
					CategoryPlot plot = chart.getCategoryPlot();
					plot.getRenderer().setSeriesPaint(budgetIndex, new Color(161, 161, 161));
					CategoryAxis axis = plot.getDomainAxis();
					axis.setLowerMargin(0);
					axis.setUpperMargin(0);
					ChartPanel chartPanel = new ChartPanel(chart);
					chartPanel.setBounds(0, (int) (2 * getSize().height / 3), 800, 200);
					chartPanel.setMaximumDrawHeight(95);
					chartPanel.setMaximumDrawWidth(600);
					centerWidth(chartPanel);
					add(chartPanel, java.awt.BorderLayout.CENTER);
				}
			}
		}
	}

	// Line Graph
	public void initLineGraph() throws Exception {
		FileIO fileIO = new FileIO();
		if (!fileIO.isEmpty(page.username)) {
			LocalDate currentDate = LocalDate.now();
			Month month = currentDate.getMonth();
			Date date = new Date(currentDate.getDayOfMonth(), month.getValue(), currentDate.getYear());
			if (fileIO.checkYear(date, page.username)) {
				double max = 0;
				DefaultCategoryDataset result = new DefaultCategoryDataset();
				Date tempDate;
				for (int i = 1; i <= 12; i++) {
					tempDate = new Date(currentDate.getDayOfMonth(), i, currentDate.getYear());
					month = Month.of(i);
					if (fileIO.checkMonth(tempDate, page.username)) {
						double totalExpenses = fileIO.getTotalExpenses(tempDate, page.username);
						double budget = fileIO.getBudget(tempDate, page.username);
						result.addValue(totalExpenses, "Monthly Expenses", month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
						result.addValue(budget, "Monthly Budget", month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
						if(totalExpenses > max) {
							max = totalExpenses;
						}
						if(budget > max) {
							max = budget;
						}
					}else {
						result.addValue(0, "Monthly Expenses", month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
						result.addValue(0, "Monthly Budget", month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
					}
				}
				CategoryDataset dataset = result;

				JFreeChart chart = ChartFactory.createLineChart("Total Expenses Vs Budget Set", "Months", "", dataset,
						PlotOrientation.VERTICAL, true, true, false);
						
				CategoryPlot plot = chart.getCategoryPlot();
				LineAndShapeRenderer renderer = new LineAndShapeRenderer();
				plot.setRenderer(renderer);
				
				NumberAxis xAxis = (NumberAxis) plot.getRangeAxis();
				xAxis.setTickUnit(new NumberTickUnit(max/5));
				
				ChartPanel chartPanel = new ChartPanel(chart);
				centerWidth(chartPanel);
				chartPanel.setBounds(650, (int) (2 * getSize().height / 12.5), 500, 350);
				add(chartPanel, java.awt.BorderLayout.CENTER);
			}
		}
	}
}