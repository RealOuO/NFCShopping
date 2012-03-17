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

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commentlist);

		mTempitems = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Name", "µÚ" + i + "Ãû×Ö");
			map.put("Comment", "µÚ" + i + "ÆÀÂÛ");
			map.put("Rating", 4.0);
			mTempitems.add(map);
		}
		mFinishButton=(Button)findViewById(R.id.btFinish);
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
		mListView.setAdapter(ma);// ï¿½ï¿½ï¿½ï¿½ï¿½Ð±ï¿½

		mConcernManager = new ConcernManager(this);
		resolveIntent(getIntent());

	}

	void resolveIntent(Intent intent) {
		// Parse the intent
		String action = intent.getAction();
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
			ConcernItem item = new ConcernItem(0, Math.abs(new Random()
					.nextInt()), String.valueOf(new Random().nextInt()), 3,
					Math.abs(new Random().nextFloat()), 4,
					System.currentTimeMillis(), (short) 1);
			mConcernManager.addConcernItem(item);
			Log.d(TAG, "discover a tag");
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
			tv.setText("¸üÐÂ");
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
				// ï¿½ï¿½xmlï¿½ï¿½ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½Viewï¿½ï¿½ï¿½ï¿½
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