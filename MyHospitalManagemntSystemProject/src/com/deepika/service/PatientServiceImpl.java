package com.deepika.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import com.deepika.bean.Patient;
import com.deepika.persistence.PatientDao;
import com.deepika.persistence.PatientDaoImpl;

public class PatientServiceImpl implements PatientService {

	private PatientDao patDao=new PatientDaoImpl();
	
	@Override
	public String checkLogIn(String username, String password)
			throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return patDao.getLoggedId(username, password);
	}
		
	@Override
	public int getPatientid(String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return patDao.getPatientid(username);
	}
	
	@Override
	public boolean addPatient(Patient Patient)throws ClassNotFoundException,SQLException, IOException{
		
		return patDao.registerRecord(Patient);
	}

	@Override
	public boolean updatePatientDetailsById(String loggedId, String columnName, String newName)throws ClassNotFoundException, SQLException, IOException, IOException {
		// TODO Auto-generated method stub
		
		return  patDao.updateRecordDetailsById(loggedId,columnName,newName);
	}
}
