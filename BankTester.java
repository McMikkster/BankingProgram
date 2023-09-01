package BankingApp;

import java.time.LocalDate;

public class BankTester 
{
	public static void main(String [] args)
	{
		
		Bank bofa = new Bank("BOFA", "Ahmed");
		Bank chase = new Bank("Chase", "Mohammed");
		
		Manager m1 = new Manager("Ayah",  bofa, LocalDate.of(2000, 1, 11));
		Manager m2 = new Manager("Safa",  bofa,  LocalDate.of(2002, 12, 1));
		
		
		Teller t1 = new Teller ("Mohammed",  bofa,  LocalDate.of(1991, 12, 1), m1);
		Teller t2 = new Teller ("May",  bofa,  LocalDate.of(1860, 12, 1), m1);
		Teller t3 = new Teller ("Olaf",  bofa,  LocalDate.of(1860, 12, 1), m1);
		
		
		ATM bofaATM = new ATM(bofa, 3.00);
		ATM chaseATM = new ATM(chase, 3.00);
		
		
		t1.createAccount("Jack", 500);
		t1.createAccount("Daniel", 1000);
		t1.createAccount("Zach", 200);
		t1.createAccount("Jay", 450);
		t1.createAccount("Abigail", 2000);
		
		bofa.getAccounts().get(0).setDateCreated(LocalDate.of(2002, 1 , 1));
		bofa.getAccounts().get(1).setDateCreated(LocalDate.of(2006, 1 , 1));
		bofa.getAccounts().get(2).setDateCreated(LocalDate.of(2020, 1 , 1));
		bofa.getAccounts().get(3).setDateCreated(LocalDate.of(2008, 1 , 1));
		bofa.getAccounts().get(4).setDateCreated(LocalDate.of(2009, 1 , 1));
		
		bofa.accountsReport(LocalDate.of(2002, 1, 1), LocalDate.of(2009, 1, 1));
		
		m1.setDateHired(LocalDate.of(2002, 1 , 1));
		m2.setDateHired(LocalDate.of(2006, 1 , 1));
		t1.setDateHired(LocalDate.of(2020, 1 , 1));
		t2.setDateHired(LocalDate.of(2008, 1 , 1));
		
		bofa.employeesReport(LocalDate.of(2002, 1, 1), LocalDate.of(2009, 1, 1));
	
		bofa.atmsReport();
		
		m1.giveRaise(t1.getId());
		
		m1.hireEmployee("Jaden");

		m1.promoteEmployee(t1.getId());
		
		m1.transferEmployee(t2.getId(), m2.getId());
		

		Account acc1 = new Account("Zacharia", bofa, 20000, LocalDate.now());
		Account acc2 = new Account("Justin", chase, 5000, LocalDate.now());
		bofa.getAccounts().add(acc1);
		chase.getAccounts().add(acc2);
		
		t3.createTransaction(acc1.getAccountNumber(), acc1.getAccountHolderName(), bofa, "deposit", 1000);
		t3.createTransaction(acc1.getAccountNumber(), acc1.getAccountHolderName(), bofa, "withdraw", 5000);
		
		
		System.out.println("acc1 before " + acc1.getBalance());
		System.out.println("acc2 before " + acc2.getBalance());
		
		t3.createTransaction(acc1.getAccountNumber(), acc2.getAccountHolderName(), chase, "transfer", 1000);
		System.out.println("acc1 after " + acc1.getBalance());
		System.out.println("acc2 after " + acc2.getBalance());
		

		
		
		System.out.println(acc1.getTransactionHistory());
		
		t3.createAccount("Zoey", 0);

		
		t3.displayNumberOfTransactions(acc1.getAccountNumber());
		t3.displayRangeOfTransactions(acc1.getAccountNumber(), LocalDate.of(2002, 1, 1), LocalDate.now());
	
		bofaATM.withdraw(acc2.getAccountNumber(), 4000, chase);
		bofaATM.displayBalance(acc1.getAccountNumber(), bofa);
		
		t3.deleteAccount(acc1.getAccountNumber());

	}
}

