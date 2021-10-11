package com.deepika.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.deepika.persistence.DoctorScheduleDao;
import com.deepika.persistence.DoctorScheduleDaoImpl;

public class DoctorScheduleServiceImpl implements DoctorScheduleService {

	private DoctorScheduleDao docScheDao=new DoctorScheduleDaoImpl();
	
	@Override
	public boolean updateSchedule(int doctorid,String sLottime,int patientLoggedId) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docScheDao.updateSchedule( doctorid,sLottime,patientLoggedId);
	}
	@Override
	public List<String> getComplaintList() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docScheDao.getComplaintList();
	}
	@Override
	public Collection<String> availableDoctors(String complaint) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docScheDao.availableDoctors( complaint);
	}
	
	 @Override
		public  Collection<String> calSLots(int doctorID) throws ClassNotFoundException, SQLException, IOException {
			// TODO Auto-generated method stub
			return docScheDao.calSLots(doctorID); 
		}
	 
	 
	 @Override
	public Collection<String> getSLotsAvailable(int doctorID,Collection<String> slotList) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docScheDao.getSLotsAvailable(doctorID,slotList);
	}
	 
	 
	@Override
	public Collection<String> showPatientAppointment(String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docScheDao.showPatientAppointment(username);
	}

	@Override
	public Collection<String> showDoctorAppointment(String doctorId) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docScheDao.showDoctorAppointment(doctorId); 
	}
	
	@Override
	public boolean cancelSchedule(int scheduleId,String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docScheDao.cancelAppointment(scheduleId,username);
	}
	@Override
	public Collection<String> showDoctorSchedule(String loggedid) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docScheDao.showDoctorSchedule(loggedid);
	}
	
	@Override
	public boolean updateDoctorSchedule(String username, String starttime, String endtime) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docScheDao.updateDoctorSchedule(username, starttime, endtime);
	}
	
	
//    @Override
//    public boolean checkTime(String endtime) {
//    	// TODO Auto-generated method stub
//    	Date d=new Date();
//    	Time endTime=Time.valueOf(endtime);
//    	if(d.getHours() >=endTime.getHours() ) {
//    		return false;
//    	}
//    	return true;
//    }
//	
    
    @Override
    public Collection<String> getSlots(String starttime, String endtime) {
    	// TODO Auto-generated method stub
    	Collection<String> slots=new ArrayList<>();
    	SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		try {
			 Date d = df.parse(starttime);
			 int startHour=d.getHours();
			 Date d1 = df.parse(endtime);
			 int endHour=d.getHours();
			 Calendar cal = Calendar.getInstance();
	    	 cal.setTime(d);
	    	 String newTime = "";
	    	 slots.add(starttime);
	    	 int c=(endHour-startHour)*3;
	    	 while(c<8) {
	    		 cal.add(Calendar.MINUTE, 20);
	    		 newTime = df.format(cal.getTime());
	    		 slots.add(newTime);
	    		 d = df.parse(newTime);
				 cal = Calendar.getInstance();
		    	 cal.setTime(d);
		    	 c++;
	    	 }
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return slots;
    }
}
