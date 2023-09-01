package BankingApp;

import java.time.LocalDate;
import java.util.ArrayList;


public class Manager extends Employee
{

	private ArrayList<Teller> tellers = new ArrayList<Teller>();

	public Manager(String name, Bank bank, LocalDate dateHired)
	{
		super(name, 100000, bank, dateHired);
		bank.getManagers().add(this);		
	
	}

	public ArrayList<Teller> getTellers() 
	{
		return tellers;
	}

	public void setTellers(ArrayList<Teller> tellers) 
	{
		this.tellers = tellers;
	}

	
	public void giveRaise(String id)
	{
		
		for(Teller t : tellers)
		{
			if(t.getId().equals(id))
			{
				t.setSalary(t.getSalary() + (t.getSalary() * 0.05));
				System.out.println(t.getName() + " was given a 5% raise");
				return;
			}
		}
		
		System.out.println("Employee id not found");
	}
	
	public void hireEmployee(String  name)
	{
		Teller teller = new Teller(name, this.getBank(), LocalDate.now(), this);
	}
	
	public void fireEmployee(String id)
	{

		for(int i = 0; i < tellers.size(); i++)
		{
			if(tellers.get(i).getId().equals(id))
			{
				this.getTellers().remove(i);
			}
		}
		
		for(int i = 0; i < this.getBank().getTellers().size(); i++)
		{
			if(this.getBank().getTellers().get(i).getId().equals(id))
			{
				this.getBank().getTellers().remove(i);
				System.out.println("Employee has been fired");
				return;
			}
		}
		
		System.out.println("Employee id not found");
	}
	
	public void promoteEmployee(String id)
	{
		
		for(int i = 0; i < tellers.size(); i++)
		{
			if(tellers.get(i).getId().equals(id))
			{
				this.getBank().getManagers().add(new Manager(tellers.get(i).getName(), this.getBank(),  LocalDate.now()));
				this.getTellers().remove(i);
			}
		}
		
		for(int i = 0; i < this.getBank().getTellers().size(); i++)
		{
			if(this.getBank().getTellers().get(i).getId().equals(id))
			{
				System.out.println(this.getBank().getTellers().get(i).getName() + " has been promoted");
				this.getBank().getTellers().remove(i);	
				return;
			}
		}
		
		System.out.println("Employee not found");
	}
	
	public void transferEmployee(String tellerId, String managerId)
	{
		
		int tellerPosition = 0;
		for(int i = 0; i < tellers.size(); i++)
		{
			if(tellers.get(i).getId().equals(tellerId))
			{
				tellers.remove(i);	
				tellerPosition = i;
				
			}
		}
		
		for(Manager m :this.getBank().getManagers())
		{
			if(m.getId().equals(managerId))
			{
				m.getTellers().add(this.getBank().getTellers().get(tellerPosition));
			}
		}
	
	}

	@Override
	public String toString() {
		return "Manager [name=" + this.getName() + ", id=" + this.getId() + ", salary=" + this.getSalary() + ", tellers=" + tellers + "]";
	}
	
	
}