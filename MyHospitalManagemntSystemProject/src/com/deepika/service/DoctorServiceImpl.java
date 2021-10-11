package com.deepika.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import com.deepika.bean.Doctor;
import com.deepika.persistence.DoctorDao;
import com.deepika.persistence.DoctorDaoImpl;

public class DoctorServiceImpl implements DoctorService {

	private DoctorDao docDao=new DoctorDaoImpl();
	
	@Override
	public int getDoctorid(String username) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return docDao.getDoctorid(username);
	}
	
	@Override
	public String checkLogIn(String username, String password)throws ClassNotFoundException,SQLException, IOException {
		// TODO Auto-generated method stub
		return docDao.getLoggedId(username, password);
	}
//	@Override
//	public String getName(String loggedId) throws ClassNotFoundException,SQLException, IOException {
//		// TODO Auto-generated method stub
//		return docDao.getName(loggedId);
//	}
	
	@Override
	public Collection<Doctor> getAllDoctors()throws ClassNotFoundException,SQLException, IOException {
		
		return docDao.getAllRecords();
	}

	@Override
	public String addDoctor(Doctor doctor)throws ClassNotFoundException,SQLException, IOException{
		
		return docDao.registerRecord(doctor);
	}

	 @Override
	public Doctor getDoctorDetailsById(String id) throws ClassNotFoundException, SQLException, IOException, IOException {
		// TODO Auto-generated method stub
		return docDao.getRecordDetailsById(id);
	}

	 @Override
	public boolean updateDoctorDetailsById(String loggedId,String columnName,String newName) throws ClassNotFoundException, SQLException, IOException, IOException {
		// TODO Auto-generated method stub
		 
		return docDao.updateRecordDetailsById(loggedId,columnName,newName);
	}
}
