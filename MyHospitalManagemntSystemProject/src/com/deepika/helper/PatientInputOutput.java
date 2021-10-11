package com.deepika.helper;

import java.util.Scanner;

import com.deepika.bean.Patient;

public class PatientInputOutput {
	
	public static Patient getPatientData() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter your patient_name : ");
		String patient_name=scanner.next();
		System.out.println("Enter city : ");
		String city=scanner.next();
		System.out.println("Enter gender : ");
		String gender=scanner.next(); 
		System.out.println("Enter your phone Number : ");
		String phone_number=scanner.next();
		System.out.println("Enter your height in cms : ");
		int height=scanner.nextInt();
		System.out.println("Enter your weight in kgs: ");
		double weight=scanner.nextDouble();

		System.out.println("Enter username: ");
		String username=scanner.next();
		System.out.println("Enter password: ");
		String password=scanner.next();
		Patient patient=new Patient( patient_name, city,gender, phone_number, height,weight,username,password);
		return patient;
	}
	
	public static void displayPatient(Patient patient) {
		System.out.print(patient.getPatient_name()+"     "+patient.getCity()+"     "+patient.getGender()+"			"+patient.getPhone_number() +"     "+patient.getHeight_in_cm()+"     "+patient.getWeight_in_kg() +"     ");
	}

}
