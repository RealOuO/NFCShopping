package scut.bgooo.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.concern.ConcernManager;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.Review;
import scut.bgooo.entities.User;
import scut.bgooo.utility.INFCActivity;
import scut.bgooo.utility.Task;
import scut.bgooo.utility.TaskHandler;
import scut.bgooo.webservice.WebServiceUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CommentListActivity extends Activity implements INFCActivity{

	private static final String TAG = CommentListActivity.class.getSimpleName();

	protected static final int REFRESHREVIEWSSUCCESS = 0;
	protected static final int REFRESHREVIEWSFAILE = 1;
	protected static final int REFRESHRATING = 2;

	private float mRating = 0.0f;

	private ConcernManager mConcernManager = null;

	private ArrayList<HashMap<String, Object>> mTempitems = null;
	private ListView mListView;
	private Button mFinishButton;
	private Button mCommentButton;
	private Button mShareButton;
	private TextView mNameTextView;
	private TextView mPriceTextView;
	private RatingBar mRatingBar;
	private CheckBox mCheckBox;
	private ImageView mPicture;

	private View mProcess;

	private ConcernItem mItem;
	private Product mProduct = null;
	private Vector<Review> reviews;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commentlist);

		mItem = (ConcernItem) getIntent().getSerializableExtra("mItem");

		mProcess = this.findViewById(R.id.progress);

		mPicture = (ImageView) findViewById(R.id.ivProduct);
		mFinishButton = (Button) findViewById(R.id.btFinish);
		mFinishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		mCommentButton = (Button) findViewById(R.id.btComment);
		mCommentButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CommentListActivity.this,
						CommentActivity.class);
				intent.putExtra("mItem", mItem);
				startActivity(intent);
			}
		});

		mShareButton = (Button) findViewById(R.id.btShare);
		mShareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final AlertDialog.Builder builder = new AlertDialog.Builder(
						CommentListActivity.this);
				builder.setMessage("是否附加商品图片？");
				builder.setTitle("请选择");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setCancelable(true);
				builder.setPositiveButton(R.string.btOK,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int i2) {
								// TODO Auto-generated method stub
								dialog.dismiss();
								// 添加任务。。。
								HashMap<String, Object> m = new HashMap<String, Object>();
								m.put("Product", mItem);
								Task task = new Task(Task.SEND_SHARE_WEIBO_WITH_IMAGE, m);
								TaskHandler.addTask(task);
							}
						});
				builder.setNegativeButton(R.string.btCancel,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
								// 添加任务。。。
								HashMap<String, Object> m = new HashMap<String, Object>();
								m.put("Product", mItem);
								Task task = new Task(Task.SEND_SHARE_WEIBO_WITHOUT_IMAGE, m);
								TaskHandler.addTask(task);
							}

						});
				builder.show();

			}
		});

		mListView = (ListView) findViewById(R.id.comment_listview);

		mPriceTextView = (TextView) findViewById(R.id.tvproductCost);
		mNameTextView = (TextView) findViewById(R.id.tvProductname);
		mRatingBar = (RatingBar) findViewById(R.id.indicator_ratingbar);
		mCheckBox = (CheckBox) findViewById(R.id.cbStar);

		mConcernManager = new ConcernManager(this);

		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// 收藏该记录
					mItem.setIsCollected((short) 1);
				} else {
					// 取消收藏该记录
					mItem.setIsCollected((short) 0);
				}
				// 更新数据库的数据
				mConcernManager.addConcernItem(mItem);
				Log.d(TAG, "checked changed");
			}
		});

		byte[] data = mItem.getIcon();
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		mPicture.setImageBitmap(bitmap);
		mPriceTextView.setText(String.valueOf(mItem.getPrice()));
		mNameTextView.setText(mItem.getName());
		mRatingBar.setRating(mItem.getRating());

		Log.d(TAG, "is collected" + mItem.getIsCollected());
		if (mItem.getIsCollected() == 0) {
			mCheckBox.setChecked(false);
		} else {
			mCheckBox.setChecked(true);
		}
		
		TaskHandler.allActivity.put(CommentListActivity.class.getSimpleName(),
				(INFCActivity) CommentListActivity.this);
		

	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DownloadRiviews();
		super.onResume();
	}

	private void DownloadRating() {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mRating = WebServiceUtil.getInstance().getAverageRating(
						mItem.getProductId());
				Message message = new Message();
				message.arg1 = REFRESHRATING;
				message.obj = mRating;
				handler.sendMessage(message);
			}
		});
		thread.start();
		thread = null;
	}

	private void DownloadRiviews() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				reviews = WebServiceUtil.getInstance().getReviewsByProductId(
						mItem.getProductId());

				Message message = new Message();
				if (reviews == null) {
					message.arg1 = REFRESHREVIEWSFAILE;
				} else {
					message.arg1 = REFRESHREVIEWSSUCCESS;
					message.obj = reviews;
					DownloadRating();
				}
				handler.sendMessage(message);
			}
		});

		thread.start();
		thread = null;
	}

	final Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.arg1) {
			case REFRESHREVIEWSSUCCESS:
				CommentAdapter ma = new CommentAdapter(
						CommentListActivity.this, reviews);
				mListView.setAdapter(ma);// 添加适配器
				mProcess.setVisibility(View.GONE);
				break;
			case REFRESHREVIEWSFAILE:
				Toast.makeText(getApplicationContext(), "网络连接出错",
						Toast.LENGTH_SHORT).show();
				mProcess.setVisibility(View.GONE);
				break;
			case REFRESHRATING:
				mItem.setRating(mRating);
				mRatingBar.setRating(mRating);
				mConcernManager.addConcernItem(mItem);
				Log.d(TAG, "checked changed");
				Toast.makeText(getApplicationContext(), "已经更新评分",
						Toast.LENGTH_SHORT).show();
				break;
			}

		}
	};

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		if(String.valueOf(param[1]).equals("WITHOUTIMAGE")){
			Toast toast = Toast.makeText(getApplicationContext(), "发送分享成功",
					Toast.LENGTH_SHORT);
			toast.show();
		}else if(String.valueOf(param[1]).equals("WITHIMAGE")){
			Toast toast = Toast.makeText(getApplicationContext(), "发送图片分享成功",
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}

class CommentAdapter extends BaseAdapter {

	Vector<Review> items;
	Context context;

	public CommentAdapter(Context context, Vector<Review> items) {
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
			tv.setText("更新");

			return moreitem;
		} else {
			View commentitem = null;

			if (convertView != null
					&& convertView.findViewById(R.id.tvUsername) != null) {
				Log.d("getview", "doGetView-------get TextView-----------"
						+ position);
				commentitem = convertView;
			} else {
				Log.d("getview", "doGetView-------new TextView-----------"
						+ position);

				commentitem = LayoutInflater.from(context).inflate(
						R.layout.commentitem, null);
			}
			vh.tvCreateAt = (TextView) commentitem
					.findViewById(R.id.tvCreatedDate);
			vh.rbRating = (RatingBar) commentitem
					.findViewById(R.id.indicator_ratingbar);
			vh.tvComment = (TextView) commentitem.findViewById(R.id.tvComment);
			vh.tvUserName = (TextView) commentitem
					.findViewById(R.id.tvUsername);

			String date[] = items.get(position).getProperty(6).toString()
					.split("T");
			vh.tvCreateAt.setText("日期：" + date[0] + "\n时间：" + date[1]);
			vh.tvUserName.setText(((User) items.get(position).getProperty(8))
					.getProperty(2).toString());
			vh.tvComment.setText(items.get(position).getProperty(4).toString());
			vh.rbRating.setRating(Float.valueOf(items.get(position)
					.getProperty(5).toString()) / 10.0f);

			return commentitem;
		}
	}

	private static class ViewHolder {
		TextView tvUserName;
		TextView tvComment;
		TextView tvCreateAt;
		RatingBar rbRating;
	}

	private ViewHolder vh = new ViewHolder();

}
