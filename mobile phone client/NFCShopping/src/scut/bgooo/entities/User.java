package scut.bgooo.entities;

public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Username;
	private String Password;
	private short Gender;
	private int VisitedTimes;

	public User(int id, String username, String password, short gender,
			int visitedtimes) {
		Id = id;
		Username = username;
		Password = password;
		Gender = gender;
		VisitedTimes = visitedtimes;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public short getGender() {
		return Gender;
	}

	public void setGender(short gender) {
		Gender = gender;
	}

	public int getVisitedTimes() {
		return VisitedTimes;
	}

	public void setVisitedTimes(int visitedTimes) {
		VisitedTimes = visitedTimes;
	}

}
