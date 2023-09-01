package BankingApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Bank 
{
	private String name;
	private String owner;
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private ArrayList<Manager> managers = new ArrayList<Manager>();
	private ArrayList<Teller> tellers = new ArrayList<Teller>();
	private ArrayList<ATM> ATMs = new ArrayList<ATM>();
	private ArrayList<String> usedIds = new ArrayList<String>();
	private double networth = 0;
	
	
	public Bank(String name, String owner)
	{
		this.name = name;
		this.owner = owner;
		
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getOwner() 
	{
		return owner;
	}

	public void setOwner(String owner) 
	{
		this.owner = owner;
	}

	public ArrayList<Account> getAccounts() 
	{
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) 
	{
		this.accounts = accounts;
	}

	public ArrayList<Manager> getManagers() 
	{
		return managers;
	}

	public void setManagers(ArrayList<Manager> managers) 
	{
		this.managers = managers;
	}

	public ArrayList<Teller> getTellers() 
	{
		return tellers;
	}

	public void setTellers(ArrayList<Teller> tellers) 
	{
		this.tellers = tellers;
	}
	public ArrayList<ATM> getATMs() 
	{
		return ATMs;
	}

	public void setATMs(ArrayList<ATM> aTMs) 
	{
		ATMs = aTMs;
	}

	public double getNetworth() 
	{
		return networth;
	}

	public void setNetworth(double networth) 
	{
		this.networth = networth;
	}
	
	public ArrayList<String> getUsedIds() 
	{
		return usedIds;
	}

	public void setUsedIds(ArrayList<String> usedIds) 
	{
		this.usedIds = usedIds;
	}

	public void addToNetworth(double amount)
	{
		this.networth += amount;
	}
	
	public void subtractFromNetworth(double amount)
	{
		this.networth -= amount;
	}


	public String generateId() {

		char[] characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		Random random = new Random();
		boolean done = false;

		StringBuilder id = new StringBuilder();

		while (!done) {

			while (id.length() < 15) {
				id.append(characters[random.nextInt(characters.length)]);
			}

			if (!usedIds.contains(id.toString())) {
				usedIds.add(id.toString());
				done = true;
			}
		}

		return id.toString();
	}
	
	
	public void accountsReport(LocalDate startDate, LocalDate endDate)
	{
		
		System.out.println("Accounts report:");
		
		System.out.println("\tThe total number of accounts: " + accounts.size());
		
		double totalValueOfAccounts = 0;
		for(Account a: accounts)
		{
			totalValueOfAccounts += a.getBalance();
		}
		System.out.printf("\tThe total value of all acounts: $%.2f\n", totalValueOfAccounts);
		

		ArrayList<Account> topFiveAccounts = new ArrayList<Account>();
		
		for(int i = 0; i < 5; i++)
		{
			double topValue = -1;
			Account currentHighest = null;
			
			for(Account a : accounts)
			{
				if(!topFiveAccounts.contains(a))
				{
					if(a.getBalance() > topValue)
					{
						topValue = a.getBalance();
						currentHighest = a;
					}
				}
			}
			
			topFiveAccounts.add(currentHighest);
			
		}
			
		System.out.println("\tThe top five accounts are");
		for(Account a : topFiveAccounts)
		{
			System.out.printf("\t\t %s with $%.2f\n" , a.getAccountHolderName(), a.getBalance());
		}
			
	
		
		int numOfNewAccounts = 0; 
		for(Account a : this.getAccounts())
		{
			if(a.getDateCreated().getYear() >= startDate.getYear() && a.getDateCreated().getYear() <= endDate.getYear()
					&& a.getDateCreated().getMonthValue() >= startDate.getMonthValue() && a.getDateCreated().getMonthValue() <= endDate.getMonthValue())
			{
				numOfNewAccounts++;
			}
		}
		
		System.out.println("The number of new accounts created between " + startDate + " and " + endDate + " are " + numOfNewAccounts);
	}
	
	
	public void employeesReport(LocalDate startDate, LocalDate endDate)
	{
		
		System.out.println("Employees report:");
		
		System.out.println("\tThe total number of employees: " + (managers.size() + tellers.size()));
		
		System.out.println("\t\tThe total number of tellers: " + tellers.size());
		
		System.out.println("\t\tThe total number of managers: " + managers.size());
		
		
		int numOfNewTellers = 0; 
		for(Teller t : this.getTellers())
		{
			if(t.getDateHired().getYear() >= startDate.getYear() && t.getDateHired().getYear() <= endDate.getYear()
					&& t.getDateHired().getMonthValue() >= startDate.getMonthValue() && t.getDateHired().getMonthValue() <= endDate.getMonthValue())
			{
				numOfNewTellers++;
			}
		}
		
		int numOfNewManagers = 0; 
		for(Manager m : this.getManagers())
		{
			if(m.getDateHired().getYear() >= startDate.getYear() && m.getDateHired().getYear() <= endDate.getYear()
					&& m.getDateHired().getMonthValue() >= startDate.getMonthValue() && m.getDateHired().getMonthValue() <= endDate.getMonthValue())
			{
				numOfNewManagers++;
			}
		}
		

		System.out.println("\t\tThe number of employees hired between " + startDate + " and " + endDate + " are " + (numOfNewTellers + numOfNewManagers));
		
		System.out.println("\t\tThe number of tellers hired between " + startDate + " and " + endDate + " are " + numOfNewTellers);

		System.out.println("\t\tThe number of managers hired between " + startDate + " and " + endDate + " are " + numOfNewManagers);
		
		
		
		double totalAmountOfSalaries = 0;
		for(Teller a: tellers)
		{
			totalAmountOfSalaries += a.getSalary();
		}
		for(Manager a: managers)
		{
			totalAmountOfSalaries += a.getSalary();
		}

		System.out.printf("\tThe total amount of employee salaries: $%.2f\n", totalAmountOfSalaries);
		
		
		
		int largestTeam = 0;
		String managerWithLargestTeam = managers.get(largestTeam).getName();
		

		for(Manager m : managers)
		{
			if(m.getTellers().size() > largestTeam)
			{
				largestTeam = m.getTellers().size();
				managerWithLargestTeam = m.getName();
			}
		}
	
	
		System.out.println("\tThe largest team is " + managerWithLargestTeam);
	}
	
	public void atmsReport()
	{
		System.out.println("ATMs report:");
		
		System.out.println("\tThe total number of ATMs: " + ATMs.size());
		
		int totalNumOfTransactions = 0;
		for(ATM a : ATMs)
		{
			 totalNumOfTransactions += a.getNumberOfTransactions();
		}
		
		System.out.println("\tThe total number of transactions through ATMs: " + totalNumOfTransactions);

		
		for(int i = 0; i < ATMs.size(); i++)
		{
			System.out.printf("\tATM %d currently has: $%.2f\n", i, ATMs.get(i).getCurrentAmount());
		}
		

		
		
	}
	
	public void transferMoneyToATM(String id, double amount)
	{
		for(ATM a : ATMs)
		{
			if(a.getId().equals(id))
			{
				this.subtractFromNetworth(amount);
				a.setCurrentAmount(amount + a.getCurrentAmount());
				return;
			}
			
		}
			
		System.out.println("ATM not found");
	}
	
	
	
	
	
	@Override
	public String toString() 
	{
		return "Bank [name=" + name + ", owner=" + owner + ", networth=" + networth + "]";
	}
	
	
	
}

