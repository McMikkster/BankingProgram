package BankingApp;

import java.util.Random;

public class ATM 
{
	private String id;
	private int numberOfTransactions = 0;
	private double currentAmount = 5000;
	private Bank affiliatedBank;
	private double transactionFee;
	
	public ATM(Bank affiliatedBank, double transactionFee) 
	{		
		Random rand = new Random();
		
		this.affiliatedBank = affiliatedBank;
		this.transactionFee = transactionFee / 100;

		this.setId(String.format("%04d" , rand.nextInt(1000)));
		
		for(int i = 0; i < affiliatedBank.getATMs().size(); i++)
		{
			if(affiliatedBank.getATMs().get(i).getId().equals(this.getId()))
			{
				this.setId(String.format("%04d" , rand.nextInt(1000)));
			}
		}	
		
		
		affiliatedBank.getATMs().add(this);
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}
	
	public double getCurrentAmount() 
	{
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) 
	{	
		this.currentAmount = currentAmount;
	}

	public Bank getAffiliatedBank() 
	{
		return affiliatedBank;
	}

	public void setAffiliatedBank(Bank affiliatedBank) 
	{
		this.affiliatedBank = affiliatedBank;
	}

	public double getTransactionFee()
	{
		return transactionFee;
	}

	public void setTransactionFee(double transactionFee) 
	{
		this.transactionFee = transactionFee;
	}

	public int getNumberOfTransactions()
	{
		return numberOfTransactions;
	}

	public void setNumberOfTransactions(int numberOfTransactions) 
	{
		this.numberOfTransactions = numberOfTransactions;
	}

	public void withdraw(String id, double amount, Bank bank)
	{
		
		if(amount > currentAmount)
		{
			System.out.println("Not enough money in this ATM");
			return;
		}

		
		for(Account a : bank.getAccounts())
		{
			if(a.getAccountNumber().equals(id))
			{
				
				if(!(affiliatedBank.getName().equals(bank.getName())))
				{
					double fee = amount * transactionFee;
					amount += fee;
					affiliatedBank.addToNetworth(fee);
				}
				if(amount > a.getBalance())
				{	
					System.out.println("Insufficient funds");
					return;
				}
				a.withdrawMoney(amount, bank, id);
				currentAmount -= amount;
				a.saveTransaction("withdraw", "ATM", amount);
				System.out.println("You have successfully withdrawn");
				numberOfTransactions++;
				return;
			}
		}
		
		
		
	}
	
	public void displayBalance(String id, Bank bank)
	{
		
		
		for(Account a : bank.getAccounts())
		{
			if(a.getAccountNumber().equals(id))
			{
				System.out.println(a.getBalance());
				return;
			}
		}
		
		System.out.println("ID not found");
		
		
	}

	@Override
	public String toString() {
		return "ATM [id=" + id + ", numberOfTransactions=" + numberOfTransactions + ", currentAmount=" + currentAmount
				+ ", affiliatedBank=" + affiliatedBank + ", transactionFee=" + transactionFee + "]";
	}


}
