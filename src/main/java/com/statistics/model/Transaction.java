package com.statistics.model;

/**
 * Model for transaction data
 *
 * @author arijit nandi
 */

public class Transaction {
	
	private Double amount;
    private Long timestamp;
    
    public Transaction() {
    }

    public Transaction(Double amount, Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }
    
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
    
	@Override
	public String toString(){
		
		return "Transaction::" +
                "amount=" + amount +
                ", timestamp=" + timestamp;
		
	}


}
