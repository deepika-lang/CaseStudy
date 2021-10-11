package com.deepika.helper;

import java.util.Scanner;

import com.deepika.bean.DoctorSchedule;

public class DoctorScheduleInputOutput {
	
	public static void displayDoctorSchedule(DoctorSchedule doctorschedule) {
		System.out.print(doctorschedule.getScheduleId() +"     "+doctorschedule.getDoctorId()+"     "+doctorschedule.getPatientId()+"     "+doctorschedule.getStartTime()+"     "+doctorschedule.getEndTime()+"     ");
	}

}
