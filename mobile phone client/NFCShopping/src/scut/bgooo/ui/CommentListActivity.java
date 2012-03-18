package scut.bgooo.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.concern.ConcernManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CommentListActivity extends Activity {

	private static final String TAG = CommentListActivity.class.getSimpleName();

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

	private ConcernItem item;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commentlist);

		mTempitems = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Name", "第" + i + "名字");
			map.put("Comment", "第" + i + "评论");
			map.put("Rating", 4.0);
			mTempitems.add(map);
		}
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
				startActivity(intent);
			}
		});
		mShareButton = (Button) findViewById(R.id.btShare);
		mShareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		CommentAdapter ma = new CommentAdapter(this, mTempitems);
		mListView = (ListView) findViewById(R.id.comment_listview);
		mListView.setAdapter(ma);// 添加适配器

		mPriceTextView = (TextView) findViewById(R.id.tvproductCost);
		mNameTextView = (TextView) findViewById(R.id.tvProductname);
		mRatingBar = (RatingBar) findViewById(R.id.indicator_ratingbar);
		mCheckBox = (CheckBox) findViewById(R.id.cbStar);

		mConcernManager = new ConcernManager(this);
		resolveIntent(getIntent());

		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					item.setIsCollected((short) 1);
				} else {
					item.setIsCollected((short) 0);
				}
				mConcernManager.addConcernItem(item);
				Log.d(TAG, "checked changed");
			}
		});
	}

	void resolveIntent(Intent intent) {
		// Parse the intent
		String action = intent.getAction();
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
			item = new ConcernItem(0, Math.abs(new Random().nextInt()),
					String.valueOf(new Random().nextInt()), 3,
					Math.abs(new Random().nextFloat()), 4,
					System.currentTimeMillis(), (short) 0);
			mConcernManager.addConcernItem(item);
			Log.d(TAG, "discover a tag");
		} else {
			item = (ConcernItem) intent.getSerializableExtra("ConcernItem");
			mPriceTextView.setText(String.valueOf(item.getPrice()));
			mNameTextView.setText(item.getName());
			mRatingBar.setRating((float) item.getRating());
			if (item.getIsCollected() == 0) {
				Log.d(TAG, "is collected" + item.getIsCollected());
				mCheckBox.setChecked(false);
			} else {
				mCheckBox.setChecked(true);
			}
		}
	}
}

class CommentAdapter extends BaseAdapter {

	ArrayList<HashMap<String, Object>> items;
	Context context;

	public CommentAdapter(Context context,
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
			vh.rbRating = (RatingBar) commentitem
					.findViewById(R.id.indicator_ratingbar);
			vh.tvComment = (TextView) commentitem.findViewById(R.id.tvComment);
			vh.tvUserName = (TextView) commentitem
					.findViewById(R.id.tvUsername);

			vh.tvUserName.setText(items.get(position).get("Name").toString());
			vh.tvComment.setText(items.get(position).get("Comment").toString());
			vh.rbRating.setRating(Float.valueOf(items.get(position)
					.get("Rating").toString()));

			return commentitem;

		}
	}

	private static class ViewHolder {
		TextView tvUserName;
		TextView tvComment;
		RatingBar rbRating;
	}

	private ViewHolder vh = new ViewHolder();

}