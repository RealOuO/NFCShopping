/*
 * Copyright (C) 2012 The Team of BGOOO
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package scut.bgooo.ui;

import java.text.DecimalFormat;
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
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DiscountItemListActivity extends Activity implements INFCActivity {

	private ArrayList<HashMap<String, Object>> mTempitems = null;

	private ListView mListView;
	private View mProgress;
	
	private Vector<DiscountItem> discountitem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discountitemlist);
		mProgress = findViewById(R.id.progress2);
		Bundle bundle = getIntent().getBundleExtra("ID");
		int id = Integer.valueOf(bundle.getString("ID"));
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("ID", id);
		Task task = new Task(Task.GET_DISCOUNTITEM, map);
		TaskHandler.allActivity.put(
				DiscountItemListActivity.class.getSimpleName(), this);
		TaskHandler.addTask(task);

		mListView = (ListView) findViewById(R.id.privilege_listview);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(DiscountItemListActivity.this,ProductActivity.class);
				intent.putExtra("product", (Product)discountitem.get(arg2).getProperty(6));
				startActivity(intent);
			}
		});

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		String result = (String) param[0];
		if (result.equals("OK")) {
			discountitem = (Vector<DiscountItem>) param[1];
			if (discountitem != null) {
				PrivilegeAdapter ma = new PrivilegeAdapter(this, discountitem);
				mListView.setAdapter(ma);
			}
			mProgress.setVisibility(View.GONE);
		} else if (result.equals("IMAGE")) {
			PrivilegeAdapter pa=(PrivilegeAdapter) mListView.getAdapter();
			if (pa != null) {
				((PrivilegeAdapter) mListView.getAdapter())
						.notifyDataSetChanged();
			}
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

			vh.ProductImage = (ImageView) privilegeitem
					.findViewById(R.id.ivProductImage);

			Product product = ((Product) items.get(position).getProperty(6));
			double cost = Double.valueOf(product.getProperty(5).toString())
					* Double.valueOf(items.get(position).getProperty(4)
							.toString()) / 100;

			DecimalFormat df = new java.text.DecimalFormat("#0.00");
			vh.tvPrivilegeCost.setText(df.format(cost) + "元");

			vh.tvProductName.setText(product.getProperty(4).toString());

			vh.tvDiscount
					.setText(items.get(position).getProperty(4).toString());

			int productID = Integer.valueOf(product.getProperty(1).toString());
			if (TaskHandler.allIcon.get(productID) != null) {
				Bitmap bitmap = TaskHandler.allIcon.get(productID);
				vh.ProductImage.setImageBitmap(bitmap);
			}
			return privilegeitem;

		}
	}

	private static class ViewHolder {
		TextView tvProductName;// 商品名
		TextView tvPrivilegeCost;//
		TextView tvDiscount;// 优惠力度
		ImageView ProductImage;
	}

	private ViewHolder vh = new ViewHolder();
}