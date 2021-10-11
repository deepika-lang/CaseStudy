package com.deepika.presentation;

public interface DoctorPresentation {

	public String logIn();
	public void showMenu();
	boolean performMenu(int choice,String loggedId);
	void addUser();
}
