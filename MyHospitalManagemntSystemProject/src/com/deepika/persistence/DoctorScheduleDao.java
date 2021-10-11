package com.deepika.persistence;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.deepika.bean.Doctor;
import com.deepika.bean.DoctorSchedule;
public interface DoctorScheduleDao {
	
	boolean updateSchedule(int doctorid, String slottime, int patientLoggedid) throws ClassNotFoundException,SQLException, IOException;
	List<String> getComplaintList() throws ClassNotFoundException,SQLException, IOException;
	Collection<String> availableDoctors(String complaint) throws ClassNotFoundException,SQLException, IOException;
	Collection<String> calSLots(int doctorID) throws ClassNotFoundException,SQLException, IOException;
	Collection<String> getSLotsAvailable(int doctorID,Collection<String> slotList) throws ClassNotFoundException,SQLException, IOException;
	Collection<String> showPatientAppointment(String username)throws ClassNotFoundException,SQLException, IOException;
	Collection<String> showDoctorAppointment(String patientId)throws ClassNotFoundException,SQLException, IOException;
	Collection<String> showDoctorSchedule(String loggedid)throws ClassNotFoundException,SQLException, IOException;
	boolean cancelAppointment(int scheduleid,String username) throws ClassNotFoundException,SQLException, IOException;
	boolean updateDoctorSchedule(String username, String starttime, String endtime)  throws ClassNotFoundException,SQLException, IOException;
}
