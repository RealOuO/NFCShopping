package scut.bgooo.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import scut.bgooo.entities.Discount;
import scut.bgooo.entities.DiscountItem;
import scut.bgooo.entities.Product;
import scut.bgooo.utility.INFCActivity;
import scut.bgooo.utility.Task;
import scut.bgooo.utility.TaskHandler;
import scut.bgooo.webservice.WebServiceUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DiscountListActivity extends Activity implements INFCActivity {

	private ArrayList<HashMap<String, Object>> mTempitems = null;

	private ListView mListView;

	private View mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discountlist);
		mProgress = findViewById(R.id.progress2);
		Bundle bundle = getIntent().getBundleExtra("ID");
		int id = Integer.valueOf(bundle.getString("ID"));
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("ID", id);
		Task task = new Task(Task.GET_DISCOUNTITEM, map);
		TaskHandler.allActivity.put(DiscountListActivity.class.getSimpleName(),
				this);
		TaskHandler.addTask(task);

		mListView = (ListView) findViewById(R.id.privilege_listview);

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		String result = (String) param[0];
		if (result.equals("OK")) {
			Vector<DiscountItem> discountitem = (Vector<DiscountItem>) param[1];
			PrivilegeAdapter ma = new PrivilegeAdapter(this, discountitem);
			mListView.setAdapter(ma);
			mProgress.setVisibility(View.GONE);
		}
	}

}

class PrivilegeAdapter extends BaseAdapter {

	Vector<DiscountItem> items;
	Context context;

	public PrivilegeAdapter(Context context, Vector<DiscountItem> items) {
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
						R.layout.discountitem, null);
			}

			vh.tvPrivilegeCost = (TextView) privilegeitem
					.findViewById(R.id.tvPrivilegeCost);
			vh.tvProductName = (TextView) privilegeitem
					.findViewById(R.id.tvProductname);
			vh.tvDiscount = (TextView) privilegeitem
					.findViewById(R.id.tvDiscount);
			vh.tvDuration = (TextView) privilegeitem
					.findViewById(R.id.tvDuration);

			double cost = Double.valueOf(((Product) items.get(position)
					.getProperty(8)).getProperty(5).toString()) * 
					Double.valueOf(items.get(position).getProperty(4)
					.toString());
			vh.tvPrivilegeCost.setText(Double.toString(cost));
			

			vh.tvProductName.setText(((Product) items.get(position)
					.getProperty(8)).getProperty(4).toString());

			
			vh.tvDiscount
					.setText(items.get(position).getProperty(4).toString());
			
			String duration = items.get(position).getProperty(6).toString()
					+ "-" + items.get(position).getProperty(7).toString();
			vh.tvDuration
					.setText(duration);

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