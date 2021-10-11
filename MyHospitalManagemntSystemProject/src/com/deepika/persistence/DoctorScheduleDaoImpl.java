package com.deepika.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.deepika.helper.MySqlConnection;
import com.deepika.service.DoctorScheduleService;
import com.deepika.service.DoctorScheduleServiceImpl;

public class DoctorScheduleDaoImpl implements DoctorScheduleDao{


	@Override
	public boolean updateSchedule(int doctorid,String slottime,int patientId) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		int rows;
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO DOCTORSCHEDULE(DOCTORID,PATIENTID,SCHEDULEDATE,SLOTTIME) VALUES(?,?,?,?)");
		preparedStatement.setInt(1, doctorid );
		preparedStatement.setInt(2, patientId );
		preparedStatement.setObject(3, LocalDate.now() );
		preparedStatement.setString(4, slottime);

		rows=preparedStatement.executeUpdate();
		if(rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> getComplaintList() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		List<String> complaintList=new ArrayList<>();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT COMPLAINT FROM DOCTOR");

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			complaintList.add(resultSet.getString("COMPLAINT"));
		}
		connection.close();
		return 	complaintList;
	}

	@Override
	public Collection<String> availableDoctors(String complaint) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		Collection<String> doctorList=new ArrayList<>();
			
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT DOCTORID,DOCTOR_NAME,CITY,EDUCATION,SPECIALIZATION FROM DOCTOR WHERE COMPLAINT=? ");
		preparedStatement.setString(1, complaint);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			doctorList.add(resultSet.getString("DOCTORID"));
			doctorList.add(resultSet.getString("DOCTOR_NAME"));
			doctorList.add(resultSet.getString("CITY"));
			doctorList.add(resultSet.getString("EDUCATION"));
			doctorList.add(resultSet.getString("SPECIALIZATION"));
		}
		connection.close();
		return doctorList;
	}
	
	
	
	@Override
	public Collection<String> calSLots(int doctorID) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		Collection<String> slotList=new ArrayList<>();
		PreparedStatement preparedStatement = 
				connection.prepareStatement("select starttime,endtime from doctor where doctorid=?;");
		preparedStatement.setInt(1, doctorID);
		ResultSet resultSet = preparedStatement.executeQuery();
		DoctorScheduleService docScheSer=new DoctorScheduleServiceImpl();
		while(resultSet.next()) {
			//if(docScheSer.checkTime(resultSet.getString("ENDTIME"))) {
			slotList=docScheSer.getSlots(resultSet.getString("STARTTIME"),resultSet.getString("ENDTIME"));
			return slotList;
		}
		return null;
	}
	
	
	
	@Override
	public Collection<String> getSLotsAvailable(int doctorID,Collection<String> slotList) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		//Collection<String> checkedSlotList=new ArrayList<>();
		PreparedStatement preparedStatement = 
				connection.prepareStatement("select slottime from doctorschedule ds,doctor d where d.doctorid=? and scheduleDate=current_date();");
		preparedStatement.setInt(1, doctorID);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			slotList.remove(resultSet.getString("slottime"));
		}
		return slotList;
	}
	
	
	
	@Override
	public Collection<String> showPatientAppointment(String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		Collection<String> patientAppointments=new ArrayList<String>();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT DS.SCHEDULEID, D.DOCTOR_NAME,COMPLAINT,DS.scheduleDate,DS.SLOTTIME FROM DOCTORSCHEDULE DS ,DOCTOR D WHERE D.DOCTORID=DS.DOCTORID AND DS.PATIENTID in (select patientid from patient where username=?);");
		preparedStatement.setString(1, username);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			patientAppointments.add(resultSet.getString("SCHEDULEID"));
			patientAppointments.add(resultSet.getString("DOCTOR_NAME"));
			patientAppointments.add(resultSet.getString("COMPLAINT"));
			patientAppointments.add(resultSet.getString("SCHEDULEDATE"));
			patientAppointments.add(resultSet.getString("SLOTTIME"));
		}
		connection.close();
		return patientAppointments;
	}
	
	@Override
	public Collection<String> showDoctorAppointment(String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		Collection<String> doctorAppointments=new ArrayList<String>();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT DS.SCHEDULEID, P.PATIENT_NAME,DS.scheduleDate,DS.SLOTTIME FROM DOCTORSCHEDULE DS ,PATIENT P WHERE P.PATIENTID=DS.PATIENTID AND SCHEDULEDATE=CURRENT_DATE() AND DS.DOCTORID in (select doctorid from doctor where username=?);");
		preparedStatement.setString(1, username);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			doctorAppointments.add(resultSet.getString("SCHEDULEID"));
			doctorAppointments.add(resultSet.getString("PATIENT_NAME"));
			doctorAppointments.add(resultSet.getString("SCHEDULEDATE"));
			doctorAppointments.add(resultSet.getString("SLOTTIME"));
		}
		connection.close();
		return doctorAppointments;
	}
	
	
	@Override
	public boolean cancelAppointment(int scheduleID,String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		
		
		Statement statement=connection.createStatement();
		String query="DELETE FROM DOCTORSCHEDULE WHERE SCHEDULEID ="+scheduleID+""; 
		int rows=statement.executeUpdate(query);

		if (rows > 0)
			return true;

		return false;
	}

	@Override
	public Collection<String> showDoctorSchedule(String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		Collection<String> doctorSchedules=new ArrayList<String>();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT SLOTID, STARTTIME,ENDTIME FROM DOCTORSLOTS");

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			doctorSchedules.add(resultSet.getString("SLOTID"));
			doctorSchedules.add(resultSet.getString("STARTTIME"));
			doctorSchedules.add(resultSet.getString("ENDTIME"));
		}
		connection.close();
		return doctorSchedules;
	}
	
	
	@Override
	public boolean updateDoctorSchedule(String username, String starttime, String endtime) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		int rows;
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE DOCTOR SET STARTTIME=?,ENDTIME=? WHERE USERNAME=?");
		preparedStatement.setString(1, starttime );
		preparedStatement.setString(2, endtime );
		preparedStatement.setString(3, username );

		rows=preparedStatement.executeUpdate();
		if(rows>0) {
			return true;
		}
		return false;
	}
	
	
}
