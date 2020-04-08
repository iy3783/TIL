package workshop.account.entity;

public class Account {
	public Account(String custId, String accId, int balance) {
		super();
		this.custId = custId;
		this.accId = accId;
		this.balance = balance;
	}

	public Account() {
	
	}

	private String custId;
	private String accId;
	private int balance;
	
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustId() {
		return  custId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public String getAccId() {
		return  accId;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getBalance() {
		return  balance;	
	}
	
	public void deposit(int amount) {
		this.balance += amount;
	}//입금
	
	public void withdraw(int amount) {
		if(balance >= amount) {
			this.balance-=amount;
		}
	}//출금
	
	
	@Override
	public String toString() {
		return "Account [custId="+ custId +", accId="+accId+", balance="+balance+"]"; 
		
	}
	
	
	
	
}
