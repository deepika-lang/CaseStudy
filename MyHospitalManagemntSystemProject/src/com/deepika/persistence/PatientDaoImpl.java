package com.deepika.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.deepika.bean.Doctor;
import com.deepika.bean.DoctorSchedule;
import com.deepika.bean.Patient;
import com.deepika.helper.MySqlConnection;

public class PatientDaoImpl implements PatientDao {
	public String getLoggedId(String username, String password) throws ClassNotFoundException, SQLException, IOException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		Patient patient = null;
		String loggedId=null;
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT PATIENT_NAME from PATIENT WHERE USERNAME=? AND PASSWORD=?");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password );
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getString("PATIENT_NAME");
		}
		return null;
	}


	
	@Override
	public int getPatientid(String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT PATIENTID FROM PATIENT WHERE USERNAME=?");
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		int patientId=0;
		if(resultSet.next()) {
			patientId=resultSet.getInt("PATIENTID");
		}
		return patientId;
	}

	@Override
	public boolean registerRecord(Patient patient) throws ClassNotFoundException, SQLException, IOException {
		Connection connection = MySqlConnection.getConnection();;

		
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO Patient(PATIENT_NAME,CITY,PHONE_NUMBER,HEIGHTINCMS,WEIGHTINKGS,USERNAME,PASSWORD) VALUES(?,?,?,?,?,?,?,?)");

		//preparedStatement.setString(1, patient.getPatient_id() );
		preparedStatement.setString(2, patient.getPatient_name() );
		preparedStatement.setString(3, patient.getCity() );
		preparedStatement.setString(4, patient.getPhone_number() );
		preparedStatement.setInt(5, patient.getHeight_in_cm());
		preparedStatement.setDouble(6, patient.getWeight_in_kg());
		preparedStatement.setString(7, patient.getUsername() );
		preparedStatement.setString(8, patient.getPassword() );
		int rows = preparedStatement.executeUpdate();

		if (rows > 0)
			return true;

		return false;
	}
	
	@Override
	public boolean updateRecordDetailsById(String username, String columnName, String newName) throws ClassNotFoundException, SQLException, IOException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		
		
		Statement statement=connection.createStatement();
		String query="UPDATE PATIENT SET "+columnName+"='"+newName+"' WHERE PATIENTID ='"+username+"'"; 
		int rows=statement.executeUpdate(query);

		if (rows > 0)
			return true;

		return false;
	}
	
	
}
