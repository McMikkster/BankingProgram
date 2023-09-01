package BankingApp;
import java.util.ArrayList;
import java.time.LocalDate;
public class Account 
{
	private String accountHolderName;
	private double balance;
	private ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();
	private String accountNumber;
	private Bank bank;
	private LocalDate dateCreated;
	
	
	public Account(String accountHolderName, Bank bank, double balance, LocalDate dateCreated) 
	{		
		this.bank = bank;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.dateCreated = dateCreated;
		accountNumber = bank.generateId();
	}
	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}



	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}



	public String getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;	
	}



	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	public ArrayList<Transaction> getTransactionHistory() 
	{
		return transactionHistory;
	}



	public void setTransactionHistory(ArrayList<Transaction> transactionHistory) 
	{
		this.transactionHistory = transactionHistory;
	}
	
	public LocalDate getDateCreated() 
	{
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated)
	{
		this.dateCreated = dateCreated;
	}

		
	

	public void depositMoney(double amount, Bank bank, String accountNumber)
	{	
		for(Account a : bank.getAccounts())
		{
			if(a.accountNumber.equals(accountNumber))
			{
				a.balance += amount;
				bank.addToNetworth(amount);
				return;
			}
		}
		
		System.out.println("Account not found");
		
	}
	
	public void withdrawMoney(double amount, Bank bank, String accountNumber)
	{

		if(amount > balance)
		{
			System.out.println("Insuffient funds");
			return;
		}
	
		for(Account a : bank.getAccounts())
		{
			if(a.accountNumber.equals(accountNumber))
			{
				a.balance -= amount;
				bank.subtractFromNetworth(amount);
				return;
			}
		}

		System.out.println("Account not found");
		
	}
	
	
	
	public void transferMoney(double amount, Bank recievingBank, String accountNumber1, String accountNumber2)
	{
	
		if(amount > balance)
		{
			System.out.println("Insuffient funds");
			return;
		}
		
		if(accountNumber1.equals(accountNumber2) && recievingBank.equals(bank))
		{
			System.out.println("Cannot transfer to your own account");
			return;
		}
		
		this.withdrawMoney(amount, bank, accountNumber1);

		for(Account a : recievingBank.getAccounts())
		{
			if(a.accountNumber.equals(accountNumber2))
			{
				a.depositMoney(amount, recievingBank, accountNumber2);
				return;
			}
		}
		

	}
	
	public void saveTransaction(String type, String source, double amount)
	{
		Transaction transaction = new Transaction(type, amount, source, LocalDate.now());
		transactionHistory.add(transaction);
	}


	
	@Override
	public String toString() {
		return "Account [accountHolderName=" + accountHolderName + ", accountNumber=" + accountNumber + ", balance="
				+ balance + ", transactionHistory=" + transactionHistory + "]";
	}
}

	
	

