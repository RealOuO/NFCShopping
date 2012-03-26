package scut.bgooo.userdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper {

	//用来保存	UserID、Access Token、Access Secret	的表名
	    public static final String TB_NAME="users";
	
	public SqliteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	//创建表
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS "+
                TB_NAME+"("+
                UserInfo.ID+" integer primary key,"+
                UserInfo.USERID+" varchar,"+
                UserInfo.TOKEN+" varchar,"+
                UserInfo.TOKENSECRET+" varchar,"+
                UserInfo.USERNAME+" varchar,"+
                UserInfo.USERLOCATION+" varchar,"+
                UserInfo.ISDEFAULT+" boolean," +
                UserInfo.USERICON+" blob"+                  
                ")"
                );
        Log.e("Database","onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 这个用来更新数据库吧
	 * @param db
	 * @param oldColumn
	 * @param newColumn
	 * @param typeColumn
	 */
	public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn){
        try{
            db.execSQL("ALTER TABLE " +
                    TB_NAME + " CHANGE " +
                    oldColumn + " "+ newColumn +
                    " " + typeColumn
            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
