package scut.bgooo.db;

import scut.bgooo.entities.Profile;
import scut.bgooo.entities.User;
import weibo4android.http.AccessToken;
import android.content.Context;
import android.content.SharedPreferences;

public class UserProfileUtil {
	// 保存用于验证用户的Access_Token
	public static void saveProfile(Context con, Profile profile) {
		SharedPreferences sp = con.getSharedPreferences("profile",
				Context.MODE_PRIVATE);
		if (profile != null) {
			sp.edit().putString("username", profile.getUsername())
					.putString("password", profile.getPassword())
					.putInt("id", profile.getId())
					.putString("lastvisitdate", profile.getLastVisitDate())
					.commit();
		} else {
			sp.edit().putString("username", null).putString("password", null)
					.putInt("id", 0).putString("lastvisitdate", null).commit();
		}
	}

	// 读取用户验证的Access Token
	public static Profile readProfile(Context con) {
		Profile at = null;
		SharedPreferences sp = con.getSharedPreferences("profile",
				Context.MODE_PRIVATE);
		String username = sp.getString("username", null);
		String password = sp.getString("password", null);
		String lastVisitedDate = sp.getString("lastvisitdate", null);
		int id = sp.getInt("id", 0);
		if (username != null && password != null) {
			at = new Profile(id, username, password, lastVisitedDate);
		}
		return at;
	}
}
