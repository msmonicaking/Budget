package FileHandler;

import java.util.ArrayList;

public class TransactionRowList {
	private ArrayList<TransactionRow> list = new ArrayList<>();
	public TransactionRowList() {
		
	}
	public TransactionRowList(ArrayList<TransactionRow> list) {
		this.list = list;
	}
	
	public ArrayList<TransactionRow> getList() {
		return list;
	}
	
	public void setTransaction(ArrayList<TransactionRow> list) {
		this.list = list;
	}
	
	public void addTransaction(TransactionRow a) {
		list.add(a);
	}

}
