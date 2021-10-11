package com.deepika.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import com.deepika.bean.DoctorSchedule;
import com.deepika.bean.Patient;


public interface PatientDao {
	String getLoggedId(String username, String password)throws ClassNotFoundException,SQLException,IOException;
	//String getName(String loggedId)throws ClassNotFoundException,SQLException, IOException;
	boolean registerRecord(Patient patient)throws ClassNotFoundException,SQLException, IOException;
	boolean updateRecordDetailsById(String id,String columnName,String newName)throws ClassNotFoundException,SQLException, IOException;
	int getPatientid(String username)throws ClassNotFoundException,SQLException, IOException;
}
