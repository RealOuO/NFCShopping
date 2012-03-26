package scut.bgooo.concern;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class DBHelper extends SQLiteOpenHelper {

	private static final int DB_VERSION = 5;
	private static final String DB_NAME = "nfc_scanner_product.db";
	static final String TABLE_NAME = "concern";
	static final String ID_COL = "id";
	static final String PRODUCT_ID_COL = "pid";
	static final String NAME_COL = "name";
	static final String TYPE_COL = "type";
	static final String PRICE_COL = "price";
	static final String RATING_COL = "rating";
	static final String TIMESTAMP_COL = "timestamp";
	static final String ISCOLLECTED_COL = "collected";

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID_COL
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID_COL
				+ " INTEGER, " + NAME_COL + " TEXT, " + TYPE_COL + " INTEGER, "
				+ PRICE_COL + " REAL, " + RATING_COL + " INTEGER, "
				+ TIMESTAMP_COL + " INTEGER, " + ISCOLLECTED_COL + " INTEGER);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
