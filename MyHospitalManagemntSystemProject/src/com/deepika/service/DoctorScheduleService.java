package com.deepika.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DoctorScheduleService {
	
	boolean updateSchedule(int doctorId,String slot,int patientLoggedid)throws ClassNotFoundException,SQLException, IOException;
	List<String> getComplaintList() throws ClassNotFoundException,SQLException, IOException;
	Collection<String> availableDoctors(String complaint) throws ClassNotFoundException,SQLException, IOException;
	Collection<String> calSLots(int doctorId) throws ClassNotFoundException,SQLException, IOException;
	Collection<String> getSLotsAvailable(int doctorId,Collection<String> slotList) throws ClassNotFoundException,SQLException, IOException;
	Collection<String> showPatientAppointment(String username)throws ClassNotFoundException,SQLException, IOException;
	Collection<String> showDoctorAppointment(String doctorId)throws ClassNotFoundException,SQLException, IOException;
	Collection<String> showDoctorSchedule(String loggedid)throws ClassNotFoundException,SQLException, IOException;
	boolean cancelSchedule(int scheduleId,String username) throws ClassNotFoundException,SQLException, IOException;
	boolean updateDoctorSchedule(String username, String starttime, String endtime)  throws ClassNotFoundException,SQLException, IOException;
	//boolean checkTime(String endtime) ;
	Collection<String> getSlots(String starttime,String endtime);
	
}
