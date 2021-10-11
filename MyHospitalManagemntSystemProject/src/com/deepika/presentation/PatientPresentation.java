package com.deepika.presentation;

public interface PatientPresentation {
	public String logIn();
	public void showMenu();
	boolean performMenu(int choice,String loggedId);
	void addUser();
}
