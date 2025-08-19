package com.braindata.bankmanagement.client;

import java.util.Scanner;

import com.braindata.bankmanagement.serviceImpl.Sbi;

public class Test {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Sbi s = new Sbi();
		
		
		do
		{
		
			System.out.println("\nEnter 1 for CreatTable.\n"
					+"Enter 2 for createAccount.\n"
					+"Enter 3 for Display Details.\n"
					+ "Enter 4 for Depoite Money.\n"
					+ "Enter 5 for withdrawal Money.\n"
					+ "Enter 6 for Check Balance.");
			int ch = sc.nextInt();
			
		
		
			switch(ch)
			{
			case 1 :
					s.createTable();
					System.out.println("Table Created Successfully....");
					break;
					
			case 2 :
					s.createAccount();
					System.out.println("Account Created Successfully....");
					break;
				
			case 3:
					s.displayAllDeatils();
					break;
					
			case 4:
					s.depositeMoney();
					break;
					
			case 5:
					s.withdrawal();
					break;
					
			case 6 : 
					s.balanceCheck();
					break;
			}
			
		}while(true);
	}
}
