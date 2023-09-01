package BankingApp;
import java.time.LocalDate;
import java.util.Random;
public class Teller extends Employee
{

	Manager manager;
	
	public Teller(String name,  Bank bank, LocalDate dateHired, Manager manager) 
	{
		super(name, 50000, bank, dateHired);
		bank.getTellers().add(this);
		
		Random rand = new Random();
		
		for(Manager m : this.getBank().getManagers())
		{
			if(m.getName().equals(manager.getName()))
			{
				m.getTellers().add(this);
			}
		}

		
	}

	public Manager getManager() 
	{
		return manager;
	}

	public void setManager(Manager manager) 
	{
		this.manager = manager;
	}

	public void createTransaction(String id1, String id2, Bank bank, String transactionType, double amount)
	{
		
		
		for(Account a : this.getBank().getAccounts())
		{
			if(a.getAccountNumber().equals(id1))
			{
				if(transactionType.equals("deposit"))
				{
					a.depositMoney(amount, getBank(), id1);
					a.saveTransaction(transactionType, "Bank", amount);
				}
				
				
				if(transactionType.equals("withdraw"))
				{
					a.withdrawMoney(amount,getBank(), id1);
					a.saveTransaction(transactionType, "Bank", amount);
				}
				
				
				if(transactionType.equals("transfer"))
				{
					a.transferMoney(amount, bank, id1, id2);
					a.saveTransaction(transactionType, "Bank", amount);
				}
				
				
				return;
			}
			
		}
	

		
	}
	
	public void createAccount(String accountHolderName, double startingBalance)
	{

		this.getBank().getAccounts().add(new Account(accountHolderName, this.getBank(), startingBalance, LocalDate.now()));
		this.getBank().addToNetworth(startingBalance);
		
	}
	
	public void deleteAccount(String id)
	{
		for(Account a : this.getBank().getAccounts())
		{
			if(a.getAccountNumber().equals(id))
			{
				this.getBank().subtractFromNetworth(a.getBalance());
				this.getBank().getAccounts().remove(a);
				return;
			}
		}	
	}
	
	public void displayNumberOfTransactions(String id)
	{
			
		for(Account a : this.getBank().getAccounts())
		{
			if(a.getAccountNumber().equals(id))
			{
				System.out.println("The number of transactions: " + a.getTransactionHistory().size());
				return;
			}
		}
		System.out.println("Account not found");
	}

	public void displayRangeOfTransactions(String id, LocalDate startDate, LocalDate endDate)
	{

		for(Account a : this.getBank().getAccounts())
		{
			if(a.getAccountNumber().equals(id))
			{
				for(Transaction t : a.getTransactionHistory())
				{
					if(t.getDateOfTransaction().getYear() >= startDate.getYear() && t.getDateOfTransaction().getYear() <= endDate.getYear() 
							&& t.getDateOfTransaction().getMonthValue() >= startDate.getMonthValue() && t.getDateOfTransaction().getMonthValue() <= endDate.getMonthValue())
					{
						System.out.println(t);
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "Teller [name=" + this.getName() + ", id=" + this.getId() + ", salary=" +this.getSalary() + "]";
	}
	
}


