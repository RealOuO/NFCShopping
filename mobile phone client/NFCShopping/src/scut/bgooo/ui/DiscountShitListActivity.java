package scut.bgooo.ui;

import java.util.List;
import java.util.Map;

import scut.bgooo.discount.DiscountShitItem;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 这个activity是为了展示不同期的优惠列表的
 *
 * @author 肥哥
 *
 */
public class DiscountShitListActivity extends ListActivity {

	private MyAdapter mAdapter;
	private List<DiscountShitItem> mList = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mAdapter = new MyAdapter(this, mList);
		setListAdapter(mAdapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DiscountShitListActivity.this, DiscountListActivity.class);
				startActivity(intent);
			}
		});
		
		
	}


	private class MyAdapter extends BaseAdapter {

		private Context mContext; // 运行上下文
		private List<DiscountShitItem> mListItems; // 商品信息集合
		private LayoutInflater mListContainer; // 视图容器

		public MyAdapter(Context context, List<DiscountShitItem> listItems) {
			mContext = context;
			mListItems = listItems;
			mListContainer = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 10;
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
				viewHolder.mDiscountQuantity = (TextView)arg1.findViewById(R.id.discount_quantity);
				viewHolder.mDiscountTime = (TextView)arg1.findViewById(R.id.discount_time);
				// 设置控件集到arg1
				arg1.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) arg1.getTag();
			}

		
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
			public TextView mDiscountQuantity;// 优惠期数量
			public TextView mDiscountTime;// 优惠期时间
		}

	}
}
