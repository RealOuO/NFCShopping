package scut.bgooo.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PrivilegeListActivity extends Activity {
	
	private ArrayList<HashMap<String, Object>> mTempitems = null;

	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.privilegelist);

		mListView=(ListView)findViewById(R.id.privilege_listview);

		mTempitems = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("PrivilegeCost", "第" + i + "特价");
			map.put("Discount", "第" + i + "折扣");
			map.put("ProductName", "第" + i + "商品");
			map.put("Duration", "第" + i + "日期");
			mTempitems.add(map);
		}
		PrivilegeAdapter ma = new PrivilegeAdapter(this, mTempitems);
		mListView.setAdapter(ma);// 更新列表
	}

}

class PrivilegeAdapter extends BaseAdapter {

	ArrayList<HashMap<String, Object>> items;
	Context context;

	public PrivilegeAdapter(Context context,
			ArrayList<HashMap<String, Object>> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (position == this.getCount() - 1) {
			return null;
		} else {
			return items.get(position - 1);
		}
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (position == this.getCount() - 1) {
			View moreitem = LayoutInflater.from(context).inflate(
					R.layout.moreitemsview, null);
			TextView tv = (TextView) moreitem.findViewById(R.id.tvItemContent);
			tv.setText("更多");
			return moreitem;
		} else {
			View privilegeitem = null;

			if (convertView != null
					&& convertView.findViewById(R.id.tvUsername) != null) {
				Log.d("getview", "doGetView-------get TextView-----------"
						+ position);
				privilegeitem = convertView;
			} else {
				Log.d("getview", "doGetView-------new TextView-----------"
						+ position);
				// 把xml布局文件变成View对象
				privilegeitem = LayoutInflater.from(context).inflate(
						R.layout.privilegeitem, null);
			}

			vh.tvPrivilegeCost = (TextView) privilegeitem
					.findViewById(R.id.tvPrivilegeCost);
			vh.tvProductName = (TextView) privilegeitem
					.findViewById(R.id.tvProductname);
			vh.tvDiscount = (TextView) privilegeitem
					.findViewById(R.id.tvDiscount);
			vh.tvDuration = (TextView) privilegeitem
					.findViewById(R.id.tvDuration);

			vh.tvPrivilegeCost.setText(items.get(position).get("PrivilegeCost")
					.toString());
			vh.tvProductName.setText(items.get(position).get("ProductName")
					.toString());
			vh.tvDiscount.setText(items.get(position).get("Discount")
					.toString());
			vh.tvDuration.setText(items.get(position).get("Duration")
					.toString());

			return privilegeitem;

		}
	}

	private static class ViewHolder {
		TextView tvProductName;
		TextView tvPrivilegeCost;
		TextView tvDiscount;
		TextView tvDuration;
	}

	private ViewHolder vh = new ViewHolder();
}