package rewards.model;

import java.util.Date;

public class Transaction {

	private double dollarAmount;
	private Date transactionDate;
	

	public Transaction(double inDollarAmount, Date inDate) {
		dollarAmount = inDollarAmount;
		transactionDate = inDate;
	}
	
	public double getDollarAmount() {
		return dollarAmount;
	}

	public void setDollarAmount(double dollarAmount) {
		this.dollarAmount = dollarAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}
