package scut.bgooo.concern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import scut.bgooo.db.DBHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 关注列表数据管理类，负责数据库的插删改查
 * 
 * @since 2012年3月16日
 * 
 * */
public final class ConcernManager {

	private static final String TAG = ConcernManager.class.getSimpleName();

	private static final int MAX_ITEMS = 100;

	private static final short COLLECTED = 1;

	private static final String[] COLUMNS = { DBHelper.ID_COL,
			DBHelper.PRODUCT_ID_COL, DBHelper.NAME_COL, DBHelper.TYPE_COL,
			DBHelper.PRICE_COL, DBHelper.RATING_COL, DBHelper.TIMESTAMP_COL,
			DBHelper.ISCOLLECTED_COL, DBHelper.BRAND_COL,
			DBHelper.LOCATION_COL, DBHelper.BARCODE_COL,
			DBHelper.SECCATEGORY_COL, DBHelper.DESCRIPTION_COL };

	private static final String[] COUNT_COLUMN = { "COUNT(1)" };

	private static final String[] ID_COL_PROJECTION = { DBHelper.ID_COL };

	private static final String[] PRODUCT_ID_COL_PROJECTION = { DBHelper.PRODUCT_ID_COL };

	private final Activity activity;

	public ConcernManager(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 判断手机中关注商品数据库数据是否为空
	 * 
	 * @return 有数据则为true， 没数据则为false
	 * 
	 * @since 2012年3月17日
	 * @author Leeforall
	 * */
	public boolean hasConcernItems() {
		Log.d(TAG, "hasConcernItems()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = helper.getReadableDatabase();
			cursor = db.query(DBHelper.CONCERN_TABLE_NAME, COUNT_COLUMN, null,
					null, null, null, null);
			cursor.moveToFirst();
			return cursor.getInt(0) > 0;
		} catch (Exception e) {
			return false;
		} finally {
			close(cursor, db);
		}
	}

	/**
	 * 查询所有浏览记录的函数
	 * 
	 * @since 2012年3月18日
	 * 
	 * @author Leeforall
	 * */
	public List<ConcernItem> buildConcernItems() {
		Log.d(TAG, "buildConcernItems()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		List<ConcernItem> items = new ArrayList<ConcernItem>();
		List<String> dateItems = new ArrayList<String>();
		try {
			db = helper.getReadableDatabase();
			cursor = db.query(DBHelper.CONCERN_TABLE_NAME, COLUMNS, null, null,
					null, null, DBHelper.TIMESTAMP_COL + " DESC");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date scanDate;
			while (cursor.moveToNext()) {
				int id = cursor.getInt(0);
				int pid = cursor.getInt(1);
				String name = cursor.getString(2);
				int type = cursor.getInt(3);
				float price = cursor.getFloat(4);
				float rating = cursor.getFloat(5);
				long timestamp = cursor.getLong(6);
				short iscollected = cursor.getShort(7);
				String brand = cursor.getString(8);
				String location = cursor.getString(9);
				String barcode = cursor.getString(10);
				String category = cursor.getString(11);
				String description = cursor.getString(12);
				// 添加时间标签的代码段
				// 用来根据刷卡的日期进行日期归类
				scanDate = new Date(timestamp);
				String date = formater.format(scanDate);
				if (!dateItems.contains(date)) {
					dateItems.add(date);
					Date tag = formater.parse(date);
					items.add(new ConcernItem(tag.getTime()));
				}
				// 这里还需要补充ConcernItem对象的具体属性才能确定
				items.add(new ConcernItem(id, pid, name, type, category, price,
						rating, brand, location, barcode, description,
						timestamp, iscollected));
			}
			return items;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return items;
		} finally {
			close(cursor, db);
		}
	}

	public List<ConcernItem> buildCollectedConcernItems() {
		Log.d(TAG, "buildCollectedConcernItems()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		List<ConcernItem> items = new ArrayList<ConcernItem>();
		List<String> dateItems = new ArrayList<String>();
		try {
			db = helper.getReadableDatabase();
			cursor = db.query(DBHelper.CONCERN_TABLE_NAME, COLUMNS,
					DBHelper.ISCOLLECTED_COL + "=?",
					new String[] { String.valueOf(COLLECTED) }, null, null,
					DBHelper.TIMESTAMP_COL + " DESC");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date scanDate;
			while (cursor.moveToNext()) {
				int id = cursor.getInt(0);
				int pid = cursor.getInt(1);
				String name = cursor.getString(2);
				int type = cursor.getInt(3);
				float price = cursor.getFloat(4);
				float rating = cursor.getFloat(5);
				long timestamp = cursor.getLong(6);
				short iscollected = cursor.getShort(7);
				String brand = cursor.getString(8);
				String location = cursor.getString(9);
				String barcode = cursor.getString(10);
				String category = cursor.getString(11);
				String description = cursor.getString(12);
				// 添加时间标签的代码段
				// 用来根据刷卡的日期进行日期归类
				scanDate = new Date(timestamp);
				String date = formater.format(scanDate);
				if (!dateItems.contains(date)) {
					dateItems.add(date);
					Date tag = formater.parse(date);
					items.add(new ConcernItem(tag.getTime()));
				}
				items.add(new ConcernItem(id, pid, name, type, category, price,
						rating, brand, location, barcode, description,
						timestamp, iscollected));
			}
			return items;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return items;
		} finally {
			close(cursor, db);
		}
	}

	public ConcernItem buildConcernItem(int number) {
		Log.d(TAG, "buildConcernItem()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = helper.getReadableDatabase();
			cursor = db.query(DBHelper.CONCERN_TABLE_NAME, COLUMNS, null, null,
					null, null, DBHelper.TIMESTAMP_COL + " DESC");
			cursor.move(number + 1);
			int id = cursor.getInt(0);
			int pid = cursor.getInt(1);
			String name = cursor.getString(2);
			int type = cursor.getInt(3);
			float price = cursor.getFloat(4);
			float rating = cursor.getFloat(5);
			long timestamp = cursor.getLong(6);
			short iscollected = cursor.getShort(7);
			String brand = cursor.getString(8);
			String location = cursor.getString(9);
			String barcode = cursor.getString(10);
			String category = cursor.getString(11);
			String description = cursor.getString(12);
			// 这里还需要补充ConcernItem对象的具体属性才能确定
			return new ConcernItem(id, pid, name, type, category, price,
					rating, brand, location, barcode, description, timestamp,
					iscollected);
		} finally {
			close(cursor, db);
		}
	}

	/**
	 * 通过id创建ConcernItem对象
	 * 
	 * @param id
	 *            item 项的id
	 * 
	 * @since 2012年3月17日
	 * 
	 * @author Leeforall
	 * */
	public ConcernItem buildConcernItemById(int id) {
		Log.d(TAG, "buildConcernItemById()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = helper.getReadableDatabase();
			cursor = db.query(DBHelper.CONCERN_TABLE_NAME, COLUMNS,
					DBHelper.ID_COL + "=?",
					new String[] { String.valueOf(id) }, null, null, null);
			int pid = cursor.getInt(1);
			String name = cursor.getString(2);
			int type = cursor.getInt(3);
			float price = cursor.getFloat(4);
			float rating = cursor.getFloat(5);
			long timestamp = cursor.getLong(6);
			short iscollected = cursor.getShort(7);
			String brand = cursor.getString(8);
			String location = cursor.getString(9);
			String barcode = cursor.getString(10);
			String category = cursor.getString(11);
			String description = cursor.getString(12);
			// 这里还需要补充ConcernItem对象的具体属性才能确定
			return new ConcernItem(id, pid, name, type, category, price,
					rating, brand, location, barcode, description, timestamp,
					iscollected);
		} finally {
			close(cursor, db);
		}
	}

	/**
	 * <p>
	 * 删除在列表里面第number个位置的项
	 * </p>
	 * */
	public void deleteConcernItem(int number) {
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = helper.getReadableDatabase();
			cursor = db.query(DBHelper.CONCERN_TABLE_NAME, ID_COL_PROJECTION,
					null, null, null, null, DBHelper.TIMESTAMP_COL + " DESC");
			cursor.move(number + 1);
			db.delete(DBHelper.CONCERN_TABLE_NAME, DBHelper.ID_COL + '='
					+ cursor.getString(0), null);
		} finally {
			close(cursor, db);
		}
	}

	/**
	 * <p>
	 * 通过关注列表项的id删除记录
	 * </p>
	 * */
	public void deleteConcernItemById(int id) {
		Log.d(TAG, "deleteConcernItemById()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		try {
			db = helper.getWritableDatabase();
			db.delete(DBHelper.CONCERN_TABLE_NAME, DBHelper.ID_COL + "=?",
					new String[] { String.valueOf(id) });
		} finally {
			close(null, db);
		}
	}

	/**
	 * <p>
	 * 删除以为记录过的相同的商品名
	 * </p>
	 * <p>
	 * 备注：这里先定位商品名，具体可能会替换为商品的条形码编号或者ID
	 * </p>
	 * 
	 * */
	@SuppressWarnings("unused")
	private void deletePrevious(String name) {
		Log.d(TAG, "deletePrevious()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		try {
			db = helper.getWritableDatabase();
			db.delete(DBHelper.CONCERN_TABLE_NAME, DBHelper.NAME_COL + "=?",
					new String[] { String.valueOf(name) });
		} finally {
			close(null, db);
		}
	}

	/**
	 * <p>
	 * 添加新的关注记录
	 * </p>
	 * <p>
	 * 1. 如果记录已经存在，则只对记录进行更新
	 * </p>
	 * <p>
	 * 2. 如果记录不存在，则插入新的数据
	 * </p>
	 * 
	 * @param item
	 *            从标签获取的新的关注记录
	 * @since 2012年3月17日
	 * @author Leeforall
	 */
	public void addConcernItem(ConcernItem item) {
		Log.d(TAG, "addConcernItem()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		Cursor cursor = null;

		ContentValues values = new ContentValues();
		// 暂时用默认值代替
		values.put(DBHelper.NAME_COL, item.getName());
		values.put(DBHelper.PRODUCT_ID_COL, item.getProductId());
		values.put(DBHelper.TYPE_COL, item.getType());
		values.put(DBHelper.PRICE_COL, item.getPrice());
		values.put(DBHelper.RATING_COL, item.getRating());
		values.put(DBHelper.BRAND_COL, item.getBrand());
		values.put(DBHelper.LOCATION_COL, item.getLocation());
		values.put(DBHelper.BARCODE_COL, item.getBarcode());
		values.put(DBHelper.TIMESTAMP_COL, item.getTimestamp());
		values.put(DBHelper.ISCOLLECTED_COL, item.getIsCollected());
		values.put(DBHelper.SECCATEGORY_COL, item.getSecCategory());
		values.put(DBHelper.DESCRIPTION_COL, item.getDescription());

		try {
			db = helper.getWritableDatabase();
			cursor = db.query(DBHelper.CONCERN_TABLE_NAME,
					PRODUCT_ID_COL_PROJECTION, DBHelper.PRODUCT_ID_COL + "=?",
					new String[] { String.valueOf(item.getProductId()) }, null,
					null, null);
			if (cursor.getCount() == 0) {
				// 插入新的关注记录
				db.insert(DBHelper.CONCERN_TABLE_NAME, DBHelper.TIMESTAMP_COL,
						values);
			} else {
				// 记录存在则更新数据
				db.update(DBHelper.CONCERN_TABLE_NAME, values,
						DBHelper.PRODUCT_ID_COL + "=?",
						new String[] { String.valueOf(item.getProductId()) });
			}
		} finally {
			close(null, db);
		}
	}

	/**
	 * 整理历史记录，超过历史记录存储的最大值则删除时间排在最后的记录
	 * 
	 * @since 2012年3月17日
	 * 
	 * @author Leeforall
	 * */
	public void trimHistory() {
		Log.d(TAG, "trimHistory()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = helper.getWritableDatabase();
			cursor = db.query(DBHelper.CONCERN_TABLE_NAME, ID_COL_PROJECTION,
					null, null, null, null, DBHelper.TIMESTAMP_COL + " DESC");
			cursor.move(MAX_ITEMS);
			while (cursor.moveToNext()) {
				db.delete(DBHelper.CONCERN_TABLE_NAME, DBHelper.ID_COL + '='
						+ cursor.getString(0), null);
			}
		} finally {
			close(cursor, db);
		}
	}

	/**
	 * <P>
	 * 清空关注列表的数据
	 * </P>
	 * 
	 * @since 2012年3月17日
	 * 
	 * @author Leeforall
	 * */
	public void clearConcern() {
		Log.d(TAG, "clearConcern()");
		SQLiteOpenHelper helper = new DBHelper(activity, DBHelper.DB_NAME,
				null, DBHelper.DB_VERSION);
		SQLiteDatabase db = null;
		try {
			db = helper.getWritableDatabase();
			db.delete(DBHelper.CONCERN_TABLE_NAME, null, null);
		} finally {
			close(null, db);
		}
	}

	/**
	 * 关闭Cursor对象和数据库操作对象
	 * 
	 * @param cursor
	 *            游标对象
	 * @param db
	 *            数据库操作对象
	 * 
	 * @since 2012年3月17日
	 * 
	 * @author Leeforall
	 * */
	private void close(Cursor cursor, SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d(TAG, "close()");
		if (cursor != null) {
			cursor.close();
		}
		if (db != null) {
			db.close();
		}
	}
}
