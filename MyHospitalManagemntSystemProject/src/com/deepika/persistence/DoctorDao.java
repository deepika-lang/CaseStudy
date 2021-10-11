package com.deepika.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import com.deepika.bean.Doctor;

public interface DoctorDao {
	String getLoggedId(String username, String password)throws ClassNotFoundException,SQLException, IOException, IOException;
	//String getName(String loggedId)throws ClassNotFoundException,SQLException, IOException, IOException;
	Collection<Doctor> getAllRecords()throws ClassNotFoundException,SQLException, IOException, IOException;
	String registerRecord(Doctor doctor)throws ClassNotFoundException,SQLException, IOException, IOException;
	Doctor getRecordDetailsById(String id)throws ClassNotFoundException,SQLException, IOException, IOException;
	boolean updateRecordDetailsById(String id,String columnName,String newName)throws ClassNotFoundException,SQLException, IOException, IOException;
	int getDoctorid(String username)throws ClassNotFoundException,SQLException, IOException;

}