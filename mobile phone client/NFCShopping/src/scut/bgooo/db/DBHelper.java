package scut.bgooo.db;

import scut.bgooo.weibouser.WeiboUserItem;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/**
 * 统一数据库的名字为nfc.db
 * @author 肥哥
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	// 用来保存 UserID、Access Token、Access Secret 的表名
	public static final String WIBO_TB_NAME = "users";
	public static final int DB_VERSION = 5;
	public static final String DB_NAME = "nfc.db";
	static final String DISCOUNT_TABLE_NAME = "discount";
	static final String CONCERN_TABLE_NAME = "concern";
	
	static final String ID_COL = "id";
	static final String PRODUCT_ID_COL = "pid";
	static final String NAME_COL = "name";
	static final String TYPE_COL = "type";
	static final String DISCOUNT_COL = "discount";
	static final String PRICE_COL = "price";
	static final String RATING_COL = "rating";
	static final String START_TIMESTAMP_COL = "starttimestamp";
	static final String END_TIMESTAMP_COL = "endtimestamp";	
	static final String TIMESTAMP_COL = "timestamp";
	static final String ISCOLLECTED_COL = "collected";
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	// 创建表
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS " + WIBO_TB_NAME + "("
				+ WeiboUserItem.ID + " integer primary key,"
				+ WeiboUserItem.USERID + " varchar," + WeiboUserItem.TOKEN
				+ " varchar," + WeiboUserItem.TOKENSECRET + " varchar,"
				+ WeiboUserItem.USERNAME + " varchar,"
				+ WeiboUserItem.USERLOCATION + " varchar,"
				+ WeiboUserItem.ISDEFAULT + " boolean,"
				+ WeiboUserItem.USERICON + " blob" + ")");
		
		db.execSQL("CREATE TABLE " + CONCERN_TABLE_NAME + " (" + ID_COL
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID_COL
				+ " INTEGER, " + NAME_COL + " TEXT, " + TYPE_COL + " INTEGER, "
				+ PRICE_COL + " REAL, " + RATING_COL + " REAL, "
				+ TIMESTAMP_COL + " INTEGER, " + ISCOLLECTED_COL + " INTEGER);");
		
		Log.e("Database", "onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	/**
	 * 这个用来更新数据库吧
	 * 
	 * @param db
	 * @param oldColumn
	 * @param newColumn
	 * @param typeColumn
	 */
	public void updateColumn(SQLiteDatabase db, String oldColumn,
			String newColumn, String typeColumn) {
		try {
			db.execSQL("ALTER TABLE " + WIBO_TB_NAME + " CHANGE " + oldColumn + " "
					+ newColumn + " " + typeColumn);
			
			db.execSQL("DROP TABLE IF EXISTS " + CONCERN_TABLE_NAME);
			
			onCreate(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
