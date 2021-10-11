package com.deepika.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.deepika.bean.Patient;
import com.deepika.helper.PatientInputOutput;
import com.deepika.service.DoctorScheduleService;
import com.deepika.service.DoctorScheduleServiceImpl;
import com.deepika.service.PatientService;
import com.deepika.service.PatientServiceImpl;

public class PatientPresentationImpl implements PatientPresentation {

	private PatientService patientService=new PatientServiceImpl();
	private DoctorScheduleService doctorScheduleService=new DoctorScheduleServiceImpl();
	
	@Override
	public String logIn() {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Enter username:");
		String username=scanner.next();
		System.out.println("Enter password:");
		String password=scanner.next();
		String patientName=null;
		try {
			patientName=patientService.checkLogIn(username,password);
			if(username!=null) {
				//String patientName=patientService.getName(loggedId);
				System.out.println("Hi "+patientName+"!\nWelcome to the Patient protal. Enjoy our services");
			}
			else {
				System.out.println("Invalid Username or Password");
				return null;
			}
		} catch (ClassNotFoundException | SQLException| IOException e) {
			e.printStackTrace();
		}
		return username;
	}
	
	@Override
	public void showMenu() {
		System.out.println("1. Update profile");
		System.out.println("2. Schedule an appointment with doctor");
		System.out.println("3. Show my appointments");
		System.out.println("4. Cancel my appointments");
		System.out.println("5. Exit");

	}

	@Override
	public boolean performMenu(int choice,String username) {
		Scanner scanner=new Scanner(System.in);
		
		boolean loop=false;
		switch(choice) {
			
		case 1:
			String columnName = null,newName=null;
			boolean loopbreak=true;
			System.out.println("Which fields you want to update :\n1.Name\n2.City\n3.Phone Number\n4.Height\n5.Weight\n6.Password\n7.No edits needed");
			int choice1=scanner.nextInt();
			switch(choice1) {
				case 1: columnName="PATIENT_NAME";
							System.out.println("Enter the new name : ");
							newName=scanner.next();
							break;
				case 2: columnName="CITY";
							System.out.println("Enter the new city : ");
							newName=scanner.next();
							break;
				case 3: columnName="PHONE_NUMBER";
							System.out.println("Enter the new Phone number : ");
							newName=scanner.next();
							break;
				case 4:	columnName="HEIGHTINCMS";
							System.out.println("Enter the new height : ");
							newName=scanner.next();
							break;
					
				case 5: columnName="WEIGHTINKGS";
							System.out.println("Enter the new weight : ");
							newName=scanner.next();
							break;
				case 6: columnName="PASSWORD";
							System.out.println("Enter the new password : ");
							newName=scanner.next();
							break;
				case 7: 	loop=true;
							loopbreak=false;
			}
			try {
				if(loopbreak==false)
					break;
				else if(patientService.updatePatientDetailsById(username, columnName, newName))
					System.out.println("Updated "+columnName+" Successfully!");
				else
					System.out.println("Failed to update patient details!");
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			loop=true;
			break;
			
		
		case 2:
			try {
					DoctorSchedulePresentation doctorSchedulePresentation=new DoctorSchedulePresentationImpl();
					List<String> complaintList= doctorScheduleService.getComplaintList();
					for(int i=0;i<complaintList.size();i++) {
						System.out.println(complaintList.get(i));
					}
					System.out.println("Please enter the organ to get treated");
					String organ=scanner.next();
					int doctorID=doctorSchedulePresentation.showDoctorAvailability(organ);
					int userChoice=0;
					Collection<String> slotList=new ArrayList<>();
					slotList=doctorScheduleService.calSLots(doctorID);
					if(slotList==null) {
						System.out.println("No doctors are available currently. Proceed if you want to schedule appointment for tomorrow");
						System.out.println("1.Yes\n2.No\nEnter choice\n");
						userChoice=scanner.nextInt();
					}
					if(userChoice==0 ||userChoice==1 ){
						doctorSchedulePresentation.SlotAvailability(doctorID,slotList,username);
					}
					else {
						System.out.println("You are exiting!!");
					}
			} 
			catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			loop=true;
			break;
			
			
			
		case 3: 	Collection<String> myAppointments=null;
					int count1=0;
					try {
						myAppointments =doctorScheduleService.showPatientAppointment(username);
					} catch (ClassNotFoundException | SQLException| IOException e) {
						e.printStackTrace();
					}
					if(myAppointments!=null) {
						System.out.print("ID	Doctor name      	 Problem in       StartTime		EndTime");
						for(String myApp:myAppointments) {
							if(count1%5==0)
								System.out.println();
							System.out.print(myApp+"           ");
							++count1;
						}
						System.out.println();
					}
					else {
						System.out.println("No appointments scheduled for you");
					}
					loop= true;
					break;
			
					
		case 4: 	Collection<String> myAppointmentList=null;
					int count2=0;
					try {
						myAppointmentList =doctorScheduleService.showPatientAppointment(username);
					} catch (ClassNotFoundException | SQLException| IOException e) {
						e.printStackTrace();
					}
					if(myAppointmentList!=null) {
						System.out.print("ID	Doctor name        Complaint       StartTime		EndTime");
						
						
						for(String myApp:myAppointmentList) {
							if(count2%5==0)
								System.out.println();
							System.out.print(myApp+"           ");
							++count2;
						}
						System.out.println();
						System.out.println("Do you really want to cancel the appointment?\n1.Yes\n2.No");
						int choice2=scanner.nextInt();
						
						switch(choice2) {
							case 1: System.out.println("Enter the id of above appointments");
									int scheduleid=scanner.nextInt();
									try {
										if(doctorScheduleService.cancelSchedule(scheduleid,username)) {
											System.out.println("Your appointment cancelled successfully");
										}
									else {
										System.out.println("Entered invalid id");
									}
							} 
							catch (ClassNotFoundException | SQLException| IOException e) {
								e.printStackTrace();
							}
						}
					}
					else {
						System.out.println("No appointments scheduled for you");
					}
					loop= true;
					break;	
					
			
		case 5:
					System.out.println("Thanks for using patient portal!");
					loop=false;
		
		default : System.out.println("Enter Valid choice");
		
		}
		
		return loop;
	}
	
	@Override
	public void addUser() {
		Patient Patients1=PatientInputOutput.getPatientData();
		
		try {
			if(patientService.addPatient(Patients1))
				System.out.println("Patient Added Successfully!");
			else
				System.out.println("Patient Addition Failed!");
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

}

