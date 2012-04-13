package scut.bgooo.ui;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import scut.bgooo.discount.DiscountItem;
import scut.bgooo.entities.Discount;
import scut.bgooo.utility.INFCActivity;
import scut.bgooo.utility.Task;
import scut.bgooo.utility.TaskHandler;
import scut.bgooo.webservice.WebServiceUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 这个activity是为了展示不同期的优惠列表的
 * 
 * @author 肥哥
 * 
 */
public class DiscountListActivity extends Activity implements INFCActivity {

	private MyAdapter mAdapter;
	private Vector<Discount> mDiscount = null;
	private View mProgress = null;
	private ListView mListView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discountshit);
		mProgress = findViewById(R.id.progress1);
		mListView = (ListView) findViewById(R.id.discountshit_listview);
		Task task = new Task(Task.GET_DISCOUNT, null);
		TaskHandler.allActivity.put(DiscountListActivity.class.getSimpleName(),
				this);
		TaskHandler.addTask(task);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DiscountListActivity.this,
						DiscountItemListActivity.class);
				Discount discount = mDiscount.get(arg2);
				String id = discount.getProperty(1).toString();
				Bundle bundle = new Bundle();
				bundle.putString("ID", id);
				intent.putExtra("ID", bundle);
				startActivity(intent);
			}
		});

	}

	private class MyAdapter extends BaseAdapter {

		private Context mContext; // 运行上下文
		private Vector<Discount> mListItems; // 商品信息集合
		private LayoutInflater mListContainer; // 视图容器

		public MyAdapter(Context context, Vector<Discount> listItems) {
			mContext = context;
			mListItems = listItems;
			mListContainer = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mListItems.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			final int selectID = arg0; // 表示已经设置到第几个了
			ViewHolder viewHolder = null;
			if (arg1 == null) {
				viewHolder = new ViewHolder();
				// 获取list_item布局文件的视图
				arg1 = mListContainer.inflate(R.layout.discountshititem, null);
				// 获取控件对象
				viewHolder.mImageView = (ImageView) arg1
						.findViewById(R.id.discount_image);
				viewHolder.mDiscountDiscription = (TextView) arg1
						.findViewById(R.id.discount_discription);
				viewHolder.mDiscountTime = (TextView) arg1
						.findViewById(R.id.discount_time);
				// 设置控件集到arg1
				arg1.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) arg1.getTag();
			}

			Discount discount = mListItems.get(selectID);
			viewHolder.mDiscountDiscription.setText(discount.getProperty(2)
					.toString());
			String startDate=discount.getProperty(3).toString().split("T")[0];
			String endDate=discount.getProperty(4).toString().split("T")[0];
			String duration = "起始：" +startDate  + "\n"
					+ "结束：" + endDate;
			viewHolder.mDiscountTime.setText(duration);
			return arg1;
		}

		/**
		 * 每一个listitem里的东西
		 * 
		 * @author 肥哥
		 * 
		 */
		private class ViewHolder {
			public ImageView mImageView;// 优惠期图片使用
			public TextView mDiscountDiscription;// 优惠期数量
			public TextView mDiscountTime;// 优惠期时间
		}

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
			mDiscount = (Vector<Discount>) param[1];
			if (mDiscount != null) {
				mAdapter = new MyAdapter(this, mDiscount);
				mListView.setAdapter(mAdapter);
			}

			mProgress.setVisibility(View.GONE);
		}

	}
	
	
}
