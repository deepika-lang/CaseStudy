package com.deepika.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.deepika.bean.Doctor;
import com.deepika.helper.DoctorInputOutput;
import com.deepika.service.DoctorScheduleService;
import com.deepika.service.DoctorScheduleServiceImpl;
import com.deepika.service.PatientService;
import com.deepika.service.PatientServiceImpl;

public class DoctorSchedulePresentationImpl implements DoctorSchedulePresentation {
	private DoctorScheduleService doctorScheduleService=new DoctorScheduleServiceImpl();
	
	@Override
	public int showDoctorAvailability(String complaint) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		Collection<String> doctorList=null;
		try {
			doctorList = doctorScheduleService.availableDoctors(complaint);
			if(doctorList!=null) {
				System.out.println("ID           Name               City         Education         Specialization  ");
				int count1=0;
				for(String myApp:doctorList) {
					if(count1%5==0)
						System.out.println();
					System.out.print(myApp+"           ");
					++count1;
				}
				
				System.out.println("\nPlease enter correct id of doctor to check their available slots");
				int doctorID=scanner.nextInt();
				return doctorID;
			}
			else {	
				System.out.println("No such doctor available in the hospital");
			}
		}
		catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int SlotAvailability(int doctorId,Collection<String> slotList,String username) {
		// TODO Auto-generated method stub
		PatientService patSer=new PatientServiceImpl();
		Scanner scanner=new Scanner(System.in);
		try {
			slotList=doctorScheduleService.getSLotsAvailable(doctorId,slotList);
			if(slotList!=null) {
				for(String slot:slotList) {
					System.out.println(slot);
				}
				System.out.println("Please enter slot in hh:mm:ss format to schedule an appointment");
				String slot=scanner.next();
				int patientid=patSer.getPatientid(username);
				doctorScheduleService.updateSchedule(doctorId, slot,patientid);
			}
			else {	
				System.out.println("Sorry for the incovenience. No slots available for the doctor");
			}
		}
		catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
