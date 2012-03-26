package scut.bgooo.discount;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final int DB_VERSION = 5;
	private static final String DB_NAME = "nfc_scanner_product.db";
	static final String TABLE_NAME = "discount";
	static final String ID_COL = "id";
	static final String PRODUCT_ID_COL = "pid";
	static final String NAME_COL = "name";
	static final String TYPE_COL = "type";
	static final String DISCOUNT_COL = "discount";
	static final String PRICE_COL = "price";
	static final String RATING_COL = "rating";
	static final String START_TIMESTAMP_COL = "starttimestamp";
	static final String END_TIMESTAMP_COL = "endtimestamp";

	public DBHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
