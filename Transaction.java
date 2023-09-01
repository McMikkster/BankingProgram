package BankingApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Transaction 
{
	private String transactionType;
	private double value;
	private String id;
	private String source;
	private LocalDate dateOfTransaction;
	private ArrayList<String> usedTransactionNumbers = new ArrayList<String>();



	public Transaction(String transactionType, double value, String source, LocalDate dateOfTransaction) 
	{
		this.transactionType = transactionType;
		this.value = value;
		this.source = source;
		this.dateOfTransaction = dateOfTransaction;
		
		id = generateTransactionNumber();
	}

	public String getTransactionType() 
	{
		return transactionType;
	}

	public void setTransactionType(String transactionType) 
	{
		this.transactionType = transactionType;
	}

	public double getValue() 
	{
		return value;
	}

	public void setValue(double value) 
	{
		this.value = value;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getSource() 
	{
		return source;
	}

	public void setSource(String source) 
	{
		this.source = source;
	}

	public LocalDate getDateOfTransaction() 
	{
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDate dateOfTransaction) 
	{
		this.dateOfTransaction = dateOfTransaction;
	}

	
	public ArrayList<String> getUsedTransactionNumbers() 
	{
		return usedTransactionNumbers;
	}

	public void setUsedTransactionNumbers(ArrayList<String> usedTransactionNumbers) 
	{
		this.usedTransactionNumbers = usedTransactionNumbers;
	}

	public String generateTransactionNumber()
	{

		char[] characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		Random random = new Random();
		boolean done = false;
			

		StringBuilder id = new StringBuilder();
		
		while (!done) 
		{
		
			while (id.length() < 15) 
			{
				id.append(characters[random.nextInt(characters.length)]);
			}
				if (!usedTransactionNumbers.contains(id.toString())) 
				{
					usedTransactionNumbers.add(id.toString());
					done = true;
					}
				}

				return id.toString();
	}
	
	@Override
	public String toString() {
		return "Transaction [transactionType=" + transactionType + ", value=" + value + ", id=" + id + ", source="
				+ source + ", dateOfTransaction=" + dateOfTransaction + "] \n";
	}
	
	
}
