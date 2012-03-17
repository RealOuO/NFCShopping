package scut.bgooo.ui;

import java.util.List;
import java.util.Map;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.concern.ConcernItemAdapter;
import scut.bgooo.concern.ConcernManager;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ConcernListActivity extends ListActivity {

	private static final String TAG = ConcernListActivity.class.getSimpleName();
	
	private ConcernItemAdapter mConcernAdapter = null;  //适配器对象
	private ConcernManager mConcernManager = null;  //数据访问对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		this.mConcernManager = new ConcernManager(this);  //创建数据访问对象
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ConcernListActivity.this,
						CommentListActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume");
		List<ConcernItem> items = mConcernManager.buildConcernItems();
		mConcernAdapter = new ConcernItemAdapter(this, items);
		setListAdapter(mConcernAdapter);
	}

	private class MyAdapter extends BaseAdapter {

		private Context mContext; // 运行上下文
		private List<Map<String, Object>> mListItems; // 商品信息集合
		private LayoutInflater mListContainer; // 视图容器

		public MyAdapter(Context context, List<Map<String, Object>> listItems) {
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
				arg1 = mListContainer.inflate(R.layout.productitem, null);
				// 获取控件对象
				viewHolder.mImageView = (ImageView) arg1
						.findViewById(R.id.goods_image);
				viewHolder.mGoodScore = (RatingBar) arg1
						.findViewById(R.id.score);
				viewHolder.mGoodsNmae = (TextView) arg1.findViewById(R.id.name);
				viewHolder.mGoodsPrice = (TextView) arg1
						.findViewById(R.id.price);

				// 设置控件集到arg1
				arg1.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) arg1.getTag();
			}
			viewHolder.mImageView.setBackgroundColor(R.drawable.logo);
			viewHolder.mGoodScore.setRating(3);
			viewHolder.mGoodsNmae.setText("肥哥牌威化饼");
			viewHolder.mGoodsPrice.setText("50000000");
			return arg1;
		}

		/**
		 * 每一个listitem里的东西
		 * 
		 * @author 肥哥
		 * 
		 */
		private class ViewHolder {
			public ImageView mImageView;// 图片用
			public TextView mGoodsNmae;// 真实商品名
			public TextView mGoodsPrice;// 真实商品价格
			public RatingBar mGoodScore;// 评份用
		}

	}
}
