package BankingApp;

import java.time.LocalDate;

public class Employee 
{
	private String name;
	private String id;
	private double salary;
	private Bank bank;
	private LocalDate dateHired;
	
	public Employee(String name, double salary, Bank bank, LocalDate dateHired)
	{
		super();
		this.name = name;
		this.salary = salary;
		this.bank = bank;
		this.dateHired = dateHired;
	
		id = bank.generateId();
		
	}

	public String getName() 
	{
		return name;
	}


	public void setName(String name) 
	{
		this.name = name;
	}


	public String getId()
	{
		return id;
	}


	public void setId(String id)
	{
		this.id = id;
	}


	public double getSalary() 
	{
		return salary;
		
	}


	public void setSalary(double salary) 
	{
		this.salary = salary;
	}


	public Bank getBank() 
	{
		return bank;
	}


	public void setBank(Bank bank) 
	{
		this.bank = bank;
	}


	public LocalDate getDateHired() 
	{
		return dateHired;
	}


	public void setDateHired(LocalDate dateHired) 
	{
		this.dateHired = dateHired;
	}


	
	@Override
	public String toString() 
	{
		return "Employee [name=" + name + ", id=" + id + ", salary=" + salary + ", bank=" + bank + ", dateHired="
				+ dateHired + "]";
	}
	
	
	
	
	
}