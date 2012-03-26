package scut.bgooo.userdb;

import android.graphics.drawable.Drawable;

public class UserInfo {
	
	private String mId;
	private String mUserId;
	private String mAccessToken;
	private String mAccessSecret;
	private String mUserName;
	private byte[] mIcon;
	private String mLocation;
	private boolean isDefault = false;
	//下面是表中各个字段名
	public static String ID = "ID";
	public static String USERID = "USERID";
	public static String TOKEN = "TOKEN";
	public static String TOKENSECRET = "TOKENSECRET";
	public static String USERNAME = "USERNAME";
	public static String USERICON = "USERICON";
	public static String USERLOCATION = "LOCATION";
	public static String ISDEFAULT = "ISDEFAULT";
	public void SetId(String id) {
		mId = id;
	}
	
	public void SetUserId(String userId) {
		mUserId = userId;
	}
	
	public void SetAccessToken(String accessToken) {
		mAccessToken  = accessToken;
	}
	
	public void SetAccessSecret(String accessSecret) {
		mAccessSecret = accessSecret;
	}
	
	public void SetUserName(String userName) {
		mUserName = userName;
	}
	
	public void SetIcon(byte[] icon) {
		mIcon = icon;
	}
	
	public void SetLocation(String location) {
		mLocation = location;
	}
	
	public void SetDefault(boolean argument) {
		isDefault = argument;
	}
	public String GetId() {
		return mId;
	}
	
	public String GetUserId() {
		return mUserId;
	}
	
	public String GetAToken() {
		return mAccessToken;
	}
	
	public String GetASecret() {
		return mAccessSecret;
	}
	
	public String GetUserName() {
		return mUserName;
	}
	
	public byte[] GetIcon() {
		return mIcon;
	}
	
	public String GetLocationg() {
		return mLocation;
	}
	
	public boolean IsDefault() {
		return isDefault;
	}
}
