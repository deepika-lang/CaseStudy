package com.deepika.client;


import java.util.Scanner;

import com.deepika.presentation.DoctorPresentation;
import com.deepika.presentation.DoctorPresentationImpl;
import com.deepika.presentation.PatientPresentation;
import com.deepika.presentation.PatientPresentationImpl;

public class Client {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Welcome to Hospital Management System");
		boolean innerloop=true;
		boolean outerloop=true;
		int choice1;
		String username;
		while(outerloop) {
			System.out.println("Select the portal you want to explore\n1.Doctor\n2.Patient\n3.Exit");
			int choice=scanner.nextInt();
			switch(choice) {
				case 1: 
						innerloop=true;
						System.out.println("1.New Doctor\n2.Existing Doctor\nEnter choice");
						choice1=scanner.nextInt();
						if(choice1==1 || choice1==2) {
							DoctorPresentation docPresentation=new DoctorPresentationImpl();
							if(choice1==1) {
								docPresentation.addUser();
							}
							username=docPresentation.logIn();
							if(username!=null) {
								while(innerloop) {
									docPresentation.showMenu();
									System.out.println("Enter choice : ");
									int choice2=scanner.nextInt();
									innerloop=docPresentation.performMenu(choice2,username);
								}
							}
						}
						else {
							System.out.println("Enter valid choice");
						}
						outerloop=true;
						break;
				case 2:
					innerloop=true;
					System.out.println("1.New Patient \n2.Existing Patient\nEnter choice");
					choice1=scanner.nextInt();
					PatientPresentation patPresentation=new PatientPresentationImpl();
					if(choice1==1) {
						patPresentation.addUser();
					}
					username=patPresentation.logIn();
					if(username!=null) {
						while(innerloop) {
							patPresentation.showMenu();
							System.out.println("Enter choice : ");
							int choice2=scanner.nextInt();
							innerloop=patPresentation.performMenu(choice2,username);
						}
					}
					outerloop=true;
					break;
				case 3: System.out.println("Thanks for using Hospital Management System!!");
						outerloop=false;
						System.exit(0);
						
				default : System.out.println("Enter Valid choice");
			}
		}
	}
}
