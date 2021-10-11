package com.deepika.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.deepika.bean.Doctor;

public class DoctorInputOutput {
	//public static List<String> complaintList=new ArrayList<>();
	public static Doctor getDoctorData() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Doctor Name : ");
		String name=scanner.next();
		System.out.println("Enter city : ");
		String city=scanner.next(); 
		System.out.println("Enter gender : ");
		String gender=scanner.next(); 
		System.out.println("Enter education : ");
		String education=scanner.next();
		System.out.println("Enter specialization : ");
		String specialization=scanner.next();
		System.out.println("Enter organs that can be checked : ");
		System.out.println("Enter each organ separated by a comma if you can handle multiple complaints");
		String complaint=scanner.next();
		//complaintList.add(complaint);
		System.out.println("Enter Emergency Number : ");
		String emergency_number=scanner.next();
		System.out.println("Enter username : ");
		String username=scanner.next();
		System.out.println("Enter password : ");
		String password=scanner.next();
		System.out.println("Enter starttime of your schedule in hh:mm:ss format : ");
		String starttime=scanner.next();
		System.out.println("Enter endtime of your schedule in hh:mm:ss format : ");
		String endtime=scanner.next();
		Doctor doctor=new Doctor(name, city, gender,education, specialization,emergency_number,complaint,username,password,starttime,endtime);
		return doctor;
	}
	
	public static void displayDoctor(Doctor doctor) {
		System.out.print("Dr."+doctor.getDoctor_name()+"           "+doctor.getCity()+"           "+doctor.getGender()+"			"+doctor.getEducation()+"           "+doctor.getSpecialization()+"           "+doctor.getComplaint()+"            "+doctor.getEmergency_number()+"           ");
	}

//	public static List<String> getComplaintList(){
//		return complaintList;
//		
//	}
}
