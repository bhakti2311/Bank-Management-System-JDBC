package com.braindata.bankmanagement.serviceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.braindata.bankmanagement.model.Account;
import com.braindata.bankmanagement.service.Rbi;

public class Sbi implements Rbi {
	
	Scanner sc = new Scanner(System.in);
	Account a = new Account();
	
	
	public void createTable()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root", "root");
			String query = "create table Account (accno int, name varchar(45), mobno varchar(45), aadharno varchar(45), gender varchar(45), age int, balance int )";
			Statement stmt = con.createStatement();
			stmt.execute(query);
			con.close();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			
			e.printStackTrace();
		}
	}

	@Override
	public void createAccount() 
	{
		
		System.out.println("Enter the accoun no: ");
		int accno = sc.nextInt();
		a.setAccNo(accno);
		
		System.out.println("Enter Account holder name: ");
		String name = sc.next();
		a.setName(name);
		
		System.out.println("Enter your mobile no: ");
		String mobno = sc.next();
		a.setMobNo(mobno);
		
		System.out.println("Enter your adhar no: ");
		String adharno = sc.next();
		a.setAdharNo(adharno);
		
		System.out.println("Enter your gender: ");
		String gender = sc.next();
		a.setGender(gender);
		
		System.out.println("Enter your age: ");
		int age = sc.nextInt();
		a.setAge(age);
		
		System.out.println("Enter your balance: ");
		double balance = sc.nextDouble();
		a.setBalance(balance);
		
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem","root","root");
			String query = "insert into Account values (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, a.getAccNo());
			pstmt.setString(2, a.getName());
			pstmt.setString(3, a.getMobNo());
			pstmt.setString(4, a.getAdharNo());
			pstmt.setString(5, a.getGender());
			pstmt.setInt(6, a.getAge());
			pstmt.setDouble(7, a.getBalance());
			
			pstmt.execute();
			con.close();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
		
			e.printStackTrace();
		}
		
	}

	@Override
	public void displayAllDeatils() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem","root","root");
			String query = "select * from Account";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				System.out.println("\n--------Account Details---------\n");
				System.out.println("Account number: "+rs.getInt(1));
				System.out.println("Account Holder name: "+rs.getString(2));
				System.out.println("Mobile No: "+rs.getString(3));
				System.out.println("Adhar No: "+rs.getString(4));
				System.out.println("Gender: "+rs.getString(5));
				System.out.println("Age: "+rs.getInt(6));
				System.out.println("Balance: "+rs.getInt(7));
			}
			con.close();
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void depositeMoney() 
	{
		System.out.println("Enter account number that you want to depoite Money: ");
		int accno = sc.nextInt();
		
		System.out.println("How many money you want to Deposit: ");
		int deposite = sc.nextInt();
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem","root","root");
			
			String query1 = "Select balance from Account where accno = ?";
			PreparedStatement pstmt = con.prepareStatement(query1);
			pstmt.setInt(1, accno);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				int cbalance = rs.getInt("balance");
				int nbalance = cbalance + deposite;
				
				String query2 = "update Account set balance = ? where accno =?";
				PreparedStatement prstmt = con.prepareStatement(query2);
				prstmt.setInt(1, nbalance);
				prstmt.setInt(2, accno);
				prstmt.execute();
				System.out.println("Deposite Successfully....." +nbalance);
			}
			con.close();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void withdrawal() 
	{
		
		System.out.println("Enter account no that you want withdrawal Money: ");
		int accno = sc.nextInt();
		
		System.out.println("Enter Money you want to withdrawal: ");
		int withdrawal = sc.nextInt();
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem","root","root");
			
			String query = "select balance from Account where accno = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, accno);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				int cbalance = rs.getInt("balance");
				int nbalance = cbalance - withdrawal;
				
				String query1 = "update Account set balance = ? where accno = ?";
				PreparedStatement prstmt = con.prepareStatement(query1);
				prstmt.setInt(1, nbalance);
				prstmt.setInt(2, accno);
				prstmt.execute();
				
				System.out.println("Withdrawal Successfully....\n"
						+ "Amount After Withdrawal Money: "+nbalance);
			}
			con.close();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			
			e.printStackTrace();
		}
	}

	@Override
	public void balanceCheck() 
	{
	
		System.out.println("Enter Account no for check balance: ");
		int accno = sc.nextInt();
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem","root","root");
			
			String query = "select balance from Account where accno =?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, accno);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				System.out.println("Balance is: "+rs.getInt("balance"));
			}
		
			con.close();
		} catch (ClassNotFoundException | SQLException e) 
		{
			
			e.printStackTrace();
		}
		
	}

}
