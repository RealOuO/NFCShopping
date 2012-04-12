package scut.bgooo.weibo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import scut.bgooo.db.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

public class WeiboUserManager {

	private SQLiteDatabase db;
	private DBHelper dbHelper;

	public WeiboUserManager(Context context) {
		dbHelper = new DBHelper(context, DBHelper.DB_NAME, null,
				DBHelper.DB_VERSION);
	}

	public void Close() {
		db.close();
		// dbHelper.close();
	}

	// 获取users表中的UserID、Access Token、Access Secret的记录
	public List<WeiboUserItem> GetUserList(Boolean isSimple) {
		List<WeiboUserItem> userList = new ArrayList<WeiboUserItem>();
		db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(DBHelper.WIBO_TB_NAME, null, null, null, null,
				null, WeiboUserItem.ID + " DESC");
		cursor.moveToFirst();
		while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
			WeiboUserItem user = new WeiboUserItem();
			user.SetId(cursor.getInt(0));
			user.SetUserId(cursor.getString(1));
			user.SetAccessToken(cursor.getString(2));
			user.SetAccessSecret(cursor.getString(3));
			if (1 == Integer.parseInt(cursor.getString(6))) {
				user.SetDefault(true);
			} else {
				user.SetDefault(false);
			}
			if (!isSimple) {
				user.SetUserName(cursor.getString(4));
				user.SetLocation(cursor.getString(5));
				user.SetIcon(cursor.getBlob(7));
			}
			userList.add(user);
			cursor.moveToNext();
		}
		cursor.close();
		Close();
		return userList;
	}

	// 判断users表中的是否包含某个UserID的记录
	public Boolean HaveUserInfo(String UserId) {
		Boolean b = false;
		db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(DBHelper.WIBO_TB_NAME, null,
				WeiboUserItem.USERID + "=" + UserId, null, null, null, null);
		b = cursor.moveToFirst();
		Log.e("HaveUserInfo", b.toString());
		cursor.close();
		return b;
	}

	// 更新users表的记录，根据UserId更新用户昵称和用户图标
	public int UpdateUserInfo(String userName, Bitmap userIcon, String UserId,
			String Loaction) {
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(WeiboUserItem.USERNAME, userName);
		// BLOB类型
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		// 将Bitmap压缩成PNG编码，质量为100%存储
		userIcon.compress(Bitmap.CompressFormat.PNG, 100, os);
		// 构造SQLite的Content对象，这里也可以使用raw
		values.put(WeiboUserItem.USERICON, os.toByteArray());
		values.put(WeiboUserItem.USERLOCATION, Loaction);
		int id = db.update(DBHelper.WIBO_TB_NAME, values, WeiboUserItem.USERID
				+ "=" + UserId, null);
		Log.e("UpdateUserInfo2", id + "");
		Close();
		return id;
	}

	// 更新users表的记录，是对一个新的用户进行更新
	public int UpdateUserInfo(WeiboUserItem user) {
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(WeiboUserItem.USERID, user.GetUserId());
		values.put(WeiboUserItem.TOKEN, user.GetAToken());
		values.put(WeiboUserItem.TOKENSECRET, user.GetASecret());
		values.put(WeiboUserItem.ISDEFAULT, user.IsDefault());
		int id = db.update(DBHelper.WIBO_TB_NAME, values, WeiboUserItem.ID
				+ "=" + user.GetId(), null);
		Log.e("UpdateUserInfo", id + "");
		Close();
		return id;
	}

	// 添加users表的记录
	public long SaveUserInfo(WeiboUserItem user) {
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		Long uid = null;
		if (!HaveUserInfo(user.GetUserId())) {
			values.put(WeiboUserItem.USERID, user.GetUserId());
			values.put(WeiboUserItem.TOKEN, user.GetAToken());
			values.put(WeiboUserItem.TOKENSECRET, user.GetASecret());
			values.put(WeiboUserItem.USERNAME, user.GetUserName());
			values.put(WeiboUserItem.USERICON, user.GetIcon());
			values.put(WeiboUserItem.USERLOCATION, user.GetLocationg());
			values.put(WeiboUserItem.ISDEFAULT, user.IsDefault());
			uid = db.insert(DBHelper.WIBO_TB_NAME, WeiboUserItem.ID, values);
			Log.e("SaveUserInfo", uid + "");
		}
		Close();
		return uid;
	}

	// 删除users表的记录
	public int DelUserInfo(String UserId) {
		db = dbHelper.getWritableDatabase();
		int id = db.delete(DBHelper.WIBO_TB_NAME, WeiboUserItem.USERID + "="
				+ UserId, null);
		Log.e("DelUserInfo", id + "");
		Close();
		return id;
	}

	public void ClearUserInfo(List<WeiboUserItem> userlist) {
		for (int i = 0; i < userlist.size(); i++) {
			WeiboUserItem user = userlist.get(i);
			String userId = user.GetUserId();
			DelUserInfo(userId);
		}
	}

	public void UpdateDefault(WeiboUserItem user) {
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(WeiboUserItem.ISDEFAULT, false);
		db.update(DBHelper.WIBO_TB_NAME, values,
				WeiboUserItem.ID + "=" + user.GetId(), null);
		Close();
	}
}
