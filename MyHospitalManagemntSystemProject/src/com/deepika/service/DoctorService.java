package com.deepika.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import com.deepika.bean.Doctor;

public interface DoctorService {
	String checkLogIn(String username,String password)throws ClassNotFoundException,SQLException, IOException;
	//String getName(String loggedId)throws ClassNotFoundException,SQLException, IOException ;
	Collection<Doctor> getAllDoctors()throws ClassNotFoundException,SQLException, IOException;
	String addDoctor(Doctor doctor)throws ClassNotFoundException,SQLException, IOException;
	Doctor getDoctorDetailsById(String id)throws ClassNotFoundException,SQLException, IOException, IOException;
	boolean updateDoctorDetailsById(String loggedId,String columnName,String newName)throws ClassNotFoundException,SQLException, IOException, IOException;
	int getDoctorid(String username)throws ClassNotFoundException,SQLException, IOException;
}
