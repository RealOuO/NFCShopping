package scut.bgooo.entities;

public class Profile {

	int Id;
	String Username;
	String Password;

	public Profile(int id, String username, String password) {
		Id = id;
		Username = username;
		Password = password;
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
