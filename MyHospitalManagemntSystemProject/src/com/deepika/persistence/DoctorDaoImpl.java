package com.deepika.persistence;

import java.io.IOException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;

import com.deepika.bean.Doctor;
import com.deepika.helper.MySqlConnection;

public class DoctorDaoImpl implements DoctorDao {
	
	
	@Override
	public int getDoctorid(String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT DOCTORID FROM DOCTOR WHERE USERNAME=?");
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		int doctorId=0;
		if(resultSet.next()) {
			doctorId=resultSet.getInt("DOCTORID");
		}
		return doctorId;
	}
	
	
	@Override
	public String getLoggedId(String username, String password) throws ClassNotFoundException, SQLException, IOException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		Doctor doctor = null;
		String loggedId=null;
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT DOCTORID,DOCTOR_NAME from DOCTOR WHERE USERNAME=? AND PASSWORD=?");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password );
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getString("DOCTOR_NAME");
		}
		return null;
	}
	
//	@Override
//	public String getName(String loggedId) throws ClassNotFoundException, SQLException, IOException, IOException {
//		// TODO Auto-generated method stub
//		Connection connection = MySqlConnection.getConnection();
//		PreparedStatement preparedStatement = connection.prepareStatement("SELECT DOCTOR_NAME FROM DOCTOR WHERE DOCTORID=?");
//		preparedStatement.setString(1, loggedId);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		String doctorName=null;
//		if(resultSet.next()) {
//			doctorName=resultSet.getString("DOCTOR_NAME");
//		}
//		return doctorName;
//	}
	
	@Override
	public Collection<Doctor> getAllRecords() throws ClassNotFoundException, SQLException, IOException {
		Connection connection = MySqlConnection.getConnection();
		
		Collection<Doctor> doctors = new ArrayList<Doctor>();
		
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT DOCTOR_NAME,CITY,EDUCATION,SPECIALIZATION,COMPLAINT,EMERGENCY_NUMBER from DOCTOR");

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Doctor doctor = new Doctor();
			//doctor.setDoctor_id(resultSet.getString("DOCTORID"));
			doctor.setDoctor_name(resultSet.getString("DOCTOR_NAME"));
			doctor.setCity(resultSet.getString("CITY"));
			doctor.setEducation(resultSet.getString("EDUCATION"));
			doctor.setSpecialization(resultSet.getString("SPECIALIZATION"));
			doctor.setComplaint(resultSet.getString("COMPLAINT"));
			doctor.setEmergency_number(resultSet.getString("EMERGENCY_NUMBER"));

			doctors.add(doctor);
		}
		connection.close();
		return doctors;
	}

	@Override
	public String registerRecord(Doctor doctor) throws ClassNotFoundException, SQLException, IOException {
		
		
		
		Connection connection = MySqlConnection.getConnection();;

		
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO DOCTOR(DOCTOR_NAME,CITY,GENDER,EDUCATION,SPECIALIZATION,COMPLAINT,EMERGENCY_NUMBER,USERNAME,PASSWORD,STARTTIME,ENDTIME) VALUES(?,?,?,?,?,?,?,?,?,?,?)");

		//preparedStatement.setString(1, doctor.getDoctor_id() );
		preparedStatement.setString(1, doctor.getDoctor_name() );
		preparedStatement.setString(2, doctor.getCity() );
		preparedStatement.setString(3, doctor.getGender() );
		preparedStatement.setString(4, doctor.getEducation() );
		preparedStatement.setString(5, doctor.getSpecialization());
		preparedStatement.setString(6, doctor.getComplaint() );
		preparedStatement.setString(7,doctor.getEmergency_number() );
		preparedStatement.setString(8,doctor.getUsername() );
		preparedStatement.setString(9, doctor.getPassword() );
		preparedStatement.setString(10, doctor.getStarttime());
		preparedStatement.setString(11, doctor.getEndtime());
		int rows = preparedStatement.executeUpdate();
		if (rows > 0)
			return doctor.getDoctor_name();

		return null;
	}

	@Override
	public Doctor getRecordDetailsById(String id) throws ClassNotFoundException, SQLException, IOException {
		Connection connection = MySqlConnection.getConnection();
		Doctor doctor = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT DOCTOR_NAME,CITY,EDUCATION,SPECIALIZATION,COMPLAINT,EMERGENCY_NUMBER FROM DOCTOR where DOCTORID=?");
		preparedStatement.setString(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			doctor = new Doctor();
			//doctor.setDoctor_id(resultSet.getString("DOCTORID"));
			doctor.setDoctor_name(resultSet.getString("DOCTOR_NAME"));
			doctor.setCity(resultSet.getString("CITY"));
			doctor.setEducation(resultSet.getString("EDUCATION"));
			doctor.setSpecialization(resultSet.getString("SPECIALIZATION"));
			doctor.setComplaint(resultSet.getString("COMPLAINT"));
			doctor.setEmergency_number(resultSet.getString("EMERGENCY_NUMBER"));

		}

		return doctor;
	}
	
	@Override
	public boolean updateRecordDetailsById(String username,String columnName,String newName) throws ClassNotFoundException, SQLException,IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		
		
		Statement statement=connection.createStatement();
		String query="UPDATE DOCTOR SET "+columnName+"='"+newName+"' where username ='"+username+"'"; 
		int rows=statement.executeUpdate(query);

		if (rows > 0)
			return true;

		return false;
	}
	
}
