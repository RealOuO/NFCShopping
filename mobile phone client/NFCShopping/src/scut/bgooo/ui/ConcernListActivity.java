package scut.bgooo.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.concern.ConcernItemAdapter;
import scut.bgooo.concern.ConcernManager;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author 肥哥
 * 
 * @since 2012年3月15日
 * 
 * 
 * @License: Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 * */
public class ConcernListActivity extends ListActivity {

	private static final String TAG = ConcernListActivity.class.getSimpleName();

	/**
	 * <p>
	 * 移除窗口的内部私有类，实现Runnable接口
	 * </p>
	 * <p>
	 * 参考API DEMO 的listview 的例子 list9
	 * </p>
	 * 
	 * @author Leeforall
	 * 
	 * @since 2012年3月19日
	 * */
	private final class RemoveWindow implements Runnable {
		public void run() {
			removeWindow();
		}
	}

	// RemoveWindow实例对象
	private RemoveWindow removeWindow = new RemoveWindow();
	// Handler实例对象
	private Handler handler = new Handler();
	// 具体看帮助文档
	private WindowManager windorManager;
	// 滚动时显示的提示TextView
	private TextView dialogText;
	// 显示控制量
	private boolean isShowing;
	private boolean isReady;
	// 滚动时显示的提示文字
	private String groupString = "";
	// 适配器对象
	private ConcernItemAdapter mConcernAdapter = null;
	// 数据访问对象
	private ConcernManager mConcernManager = null;
	// listview显示的数据
	private List<ConcernItem> mItems = null;

	// private TextView mEmptyTextView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		this.mConcernManager = new ConcernManager(this); // 创建数据访问对象
		mItems = mConcernManager.buildConcernItems();
		mConcernAdapter = new ConcernItemAdapter(this, mItems);
		setListAdapter(mConcernAdapter);


		getListView().setOnScrollListener(onScrollerListener);
		getListView().setOnItemClickListener(onItemClickListener);

		LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		dialogText = (TextView) inflate.inflate(R.layout.list_position, null);
		dialogText.setVisibility(View.INVISIBLE);

		windorManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		handler.post(runnable);

		// 注册上下文菜单
		this.registerForContextMenu(getListView());
	}
	
	/**
	 * 移除窗口的方法
	 * 
	 * */
	private void removeWindow() {
		if (isShowing) {
			isShowing = false;
			dialogText.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * <p>
	 * The runnable will be run on the thread to which this handler is attached.
	 * </p>
	 * */
	Runnable runnable = new Runnable() {

		public void run() {
			isReady = true;
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_APPLICATION,
					WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
							| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
					PixelFormat.TRANSLUCENT);
			windorManager.addView(dialogText, lp);
		}
	};
	
	/**
	 * listview 滚动事件监听器
	 * */
	OnScrollListener onScrollerListener = new OnScrollListener() {

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			
			if(mItems.size()==0){ 
				return ;//如果没有数据，则直接跳出滚动事件的处理
			}
			
			int lastItem = firstVisibleItem + visibleItemCount - 1;
			int middleItem = (lastItem + firstVisibleItem) / 2;
			if (isReady) {
				
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
				Date scanDate = new Date(mItems.get(middleItem).getTimestamp());
				String grouping = formater.format(scanDate);

				if (!isShowing && !groupString.equals(grouping)) {
					isShowing = true;
					dialogText.setVisibility(View.VISIBLE);
				}

				dialogText.setText(grouping);
				handler.removeCallbacks(removeWindow);
				handler.postDelayed(removeWindow, 2000);
				groupString = grouping;
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
	};

	/**
	 * listview 每一项的点击事件监听器
	 * */
	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(ConcernListActivity.this,
					CommentListActivity.class);
			intent.putExtra("ConcernItem", mItems.get(arg2));
			startActivity(intent);
		}
	};

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume");
		mItems = mConcernManager.buildConcernItems();
		// 因为没有必要重新加载adapter适配器，所以只对数据进行删除并notifyDataSetChanged()操作
		((ConcernItemAdapter) this.getListAdapter()).dataSetChanged(mItems);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenu.ContextMenuInfo menuInfo) {
		int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
		menu.add(Menu.NONE, position, Menu.NONE, R.string.clear_one_concern);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int position = item.getItemId();
		mConcernManager.deleteConcernItemById(mItems.get(position).getId());
		// 因为没有必要重新加载adapter适配器，所以只对数据进行删除并notifyDataSetChanged()操作
		((ConcernItemAdapter) this.getListAdapter()).removeItem(position);
		return true;
	}


	@SuppressWarnings("unused")
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
