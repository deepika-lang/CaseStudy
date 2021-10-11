package com.deepika.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Scanner;

import com.deepika.bean.Doctor;
import com.deepika.helper.DoctorInputOutput;
import com.deepika.service.DoctorScheduleService;
import com.deepika.service.DoctorScheduleServiceImpl;
import com.deepika.service.DoctorService;
import com.deepika.service.DoctorServiceImpl;

public class DoctorPresentationImpl implements DoctorPresentation {
	private DoctorService doctorService=new DoctorServiceImpl();
	private DoctorScheduleService doctorScheduleService=new DoctorScheduleServiceImpl();
	@Override
	public String logIn() {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter username:");
		String username=scanner.next();
		System.out.println("Enter password:");
		String password=scanner.next();
		String loggedId=null;
		try {
			String doctorName=doctorService.checkLogIn(username,password);
			if(doctorName!=null) {
				//String doctorName=doctorService.getName(loggedId);
				System.out.println("Hi Dr."+doctorName+"!\nWelcome to the Doctor protal. Enjoy our services");
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
		
		System.out.println("1. List All Doctors");
		System.out.println("2. Show Doctor details by id");
		System.out.println("3. Update profile");
		System.out.println("4. Show my Appointment");
		System.out.println("5. Show my schedule");
		System.out.println("6. Update my schedule");
		System.out.println("7. Logout");

	}

	@Override
	public boolean performMenu(int choice,String username) {
		Scanner scanner=new Scanner(System.in);
		boolean loop=false;
		switch(choice) {
		case 1:
			Collection<Doctor> doctor=null;
			try {
				doctor = doctorService.getAllDoctors();
			} catch (ClassNotFoundException | SQLException| IOException e) {
				e.printStackTrace();
			}
			if(doctor!=null) {
				System.out.println("Name           City         Education         Specialization         Organ        Emergency No. ");
				for(Doctor doc:doctor) {
					DoctorInputOutput.displayDoctor(doc);
					System.out.println();
				}
			}
			else {
				System.out.println("No doctors in the hospital");
			}
			loop=true;
			break;
		case 2:
			System.out.println("Enter doctor ID : ");
			String dId=scanner.next();
			Doctor doctorRecord=null;
			try {
				doctorRecord=doctorService.getDoctorDetailsById(dId);
				if(doctorRecord!=null)
					System.out.println(doctorRecord);
				else
					System.out.println("No such doctor exits\nEnter valid id");
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			loop=true;
			break;
		case 3:
			String columnName = null,newName=null;
			boolean loopbreak=true;
			System.out.println("Which fields you want to update :\n1.Name\n2.City\n3.Education\n4.Designation\n5.Organs\n6.Emergency Number\n7.Password\n8.No edits needed");
			int choice1=scanner.nextInt();
			switch(choice1) {
				case 1: columnName="DOCTOR_NAME";
							System.out.println("Enter the new name : ");
							newName=scanner.next();
							break;
				case 2: columnName="CITY";
							System.out.println("Enter the new city : ");
							newName=scanner.next();
							break;
				case 3: columnName="EDUCATION";
							System.out.println("Enter the new education : ");
							newName=scanner.next();
							break;
				case 4:	columnName="DESIGNATION";
							System.out.println("Enter the new designation : ");
							newName=scanner.next();
							break;
				case 5: columnName="COMPLAINT";
							System.out.println("Enter new organ: ");
							System.out.println("Enter each organ separated by a comma if you can handle multiple complaints");
							newName=scanner.next();
							break;
				case 6: columnName="EMERGENCY_NUMBER";
							System.out.println("Enter the new emergency number : ");
							newName=scanner.next();
							break;
				case 7: columnName="PASSWORD";
							System.out.println("Enter the new password: ");
							newName=scanner.next();
							break;
				case 8: loop=true;
						loopbreak=false;
			}
			try {
				if(loopbreak==false)
					break;
				else if(doctorService.updateDoctorDetailsById(username,columnName,newName))
					System.out.println("Updated "+columnName+" Successfully!");
				else
					System.out.println("Failed to update doctor details!");
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			loop=true;
			break;
		case 4:
			Collection<String> myAppointments=null;
			int count1=0;
			try {
				myAppointments =doctorScheduleService.showDoctorAppointment(username);
				if(myAppointments!=null) {
					System.out.print("ID	Patient name         Schedule Date	 StartTime");
					for(String myApp:myAppointments) {
						if(count1%4==0)
							System.out.println();
						System.out.print(myApp+"           ");
						++count1;
					}
					System.out.println();
				}
				else {
					System.out.println("No appointments scheduled for you");
				}
			} catch (ClassNotFoundException | SQLException| IOException e) {
				e.printStackTrace();
			}
			loop= true;
			break;
	
			
			
		case 5:
			Collection<String> mySchedule=null;
			int count2=0;
			DoctorService docSer=new DoctorServiceImpl();
			try {
				int doctorId= docSer.getDoctorid(username);
				mySchedule =doctorScheduleService.calSLots(doctorId);
				if(mySchedule!=null) {
					System.out.println("ID	    StartTime		Date");
					for(String mySch:mySchedule) {
						System.out.println(++count2+"		"+mySch+"          "+LocalDate.now());
					}
					System.out.println();
				}
			} catch (ClassNotFoundException | SQLException| IOException e) {
				e.printStackTrace();
			}
			loop= true;
			break;
			
			
			 
		case 6:
			String starttime=null,endtime=null;
			System.out.println("Your updates might affect appointments with patient.\nDo you still want to update.\n1.Yes\n2.No");
			int ch=scanner.nextInt();
			if(ch==1) {
				System.out.println("Enter new starting time of your schedule in hh:mm:ss format");
				starttime=scanner.next();
				System.out.println("Enter new ending time of your schedule in hh:mm:ss format");
				endtime=scanner.next();
				try {
					if(doctorScheduleService.updateDoctorSchedule(username, starttime, endtime)) {
						System.out.println("Updated your schedule succesfully!!");
					}
					else {
						System.out.println("Failed to update you schedule");
					}
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Your schedule remains same");
			}
			loop= true;
			break;
			
		case 7:
			System.out.println("Thanks for using doctor portal!");
			loop=false;
		
			
		default : System.out.println("Enter Valid choice");
		}
		return loop;
	}

	public void addUser() {
		Doctor doctors1=DoctorInputOutput.getDoctorData();
		DoctorSchedulePresentation docSchedulePresentation=new DoctorSchedulePresentationImpl();
		try {
			String id=doctorService.addDoctor(doctors1);
			if(id!=null) {
				System.out.println("Doctor registration Successful!");
			}
			else {
				System.out.println("Doctor Addition Failed!");
			}
		} 
		catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
