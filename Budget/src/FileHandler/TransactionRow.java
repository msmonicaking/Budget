package FileHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Draws the information and remove button.
 *
 */
public class TransactionRow extends JComponent {
	private Transaction data;
	String name;
	double cost;
	Date date;
	public JButton delete;

	public TransactionRow(Transaction t) {
		data = t;
		name = t.getName();
		cost = t.getPrice();
		date = t.getDate();
		setSize(600, 30);
		initText();
		initDeleteButton();
	}

	private void initText() {
		Font font = new Font("Arial", 0, 20);
		JLabel nameL = new JLabel(name);
		nameL.setFont(font);
		nameL.setSize(300, 20);
		nameL.setLocation(90, 0);
		add(nameL);

		JLabel dateL = new JLabel(date.day + "");
		dateL.setFont(font);
		dateL.setSize(50, 20);
		dateL.setLocation(10, 0);
		add(dateL);

		JLabel costL = new JLabel(cost + "");
		costL.setFont(font);
		costL.setSize(50, 20);
		costL.setLocation(getWidth() - 140, 0);
		add(costL);
	}

	public Transaction getTransaction() {
		return data;
	}

	private void initDeleteButton() {
		delete = new JButton("-");
//				TransactionRow temp = this;
//				delete.addActionListener(new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						CategoryWindow.delTransaction(temp);
//					}
//					
//				});

		delete.setSize(20, 20);
		delete.setLocation(getWidth() - 50, 0);
		add(delete);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		drawGrid(g2);

	}

	void drawGrid(Graphics2D g) {
		g.setColor(new Color(180, 180, 180));
		g.fillRect(10, getHeight() - 8, 50, 1);
		g.fillRect(85, getHeight() - 8, 350, 1);
		g.fillRect(460, getHeight() - 8, 63, 1);
	}

}