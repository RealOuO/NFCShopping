package scut.bgooo.entities;

import java.util.Date;

public class Profile {

	int Id;
	String Username;
	String Password;
	String LastVisitDate;

	

	public Profile(int id, String username, String password,String lastVisitDate) {
		Id = id;
		Username = username;
		Password = password;
		LastVisitDate=lastVisitDate;
	}

	public String getLastVisitDate() {
		return LastVisitDate;
	}


	public void setLastVisitDate(String lastVisitDate) {
		LastVisitDate = lastVisitDate;
	}
	
	public int getId() {
		return Id;
	}

	public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}
}
