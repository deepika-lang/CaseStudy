package com.deepika.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import com.deepika.bean.Patient;

public interface PatientService {
	
	String checkLogIn(String username, String password)throws ClassNotFoundException,SQLException, IOException;
	//String getName(String loggedId)throws ClassNotFoundException,SQLException, IOException ;
	boolean addPatient(Patient Patient)throws ClassNotFoundException,SQLException, IOException;
	boolean updatePatientDetailsById(String loggedId,String columnName,String newName)throws ClassNotFoundException,SQLException, IOException, IOException;
	int getPatientid(String username)throws ClassNotFoundException,SQLException, IOException;
	
	
}
