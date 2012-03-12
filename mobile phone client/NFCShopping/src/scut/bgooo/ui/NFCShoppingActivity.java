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
import android.widget.RatingBar;
import android.widget.TextView;

public class NFCShoppingActivity extends Activity {

	private ArrayList<HashMap<String, Object>> mTempitems = null;
	private ListView myListView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commentlist);

		mTempitems = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Name", "第" + i + "个某用户");
			map.put("Comment", "第" + i + "个用户的评论");
			map.put("Rating", 4.0);
			mTempitems.add(map);
		}
		myListView=(ListView)findViewById(R.id.comment_listview);
		CommentAdapter ma = new CommentAdapter(this, mTempitems);
		myListView.setAdapter(ma);// 更新列表

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
			tv.setText("更多");
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
				// 把xml布局文件变成View对象
				commentitem = LayoutInflater.from(context).inflate(
						R.layout.commentitem, null);
			}
			vh.rbRating=(RatingBar)commentitem.findViewById(R.id.indicator_ratingbar);
			vh.tvComment=(TextView)commentitem.findViewById(R.id.tvComment);
			vh.tvUserName=(TextView)commentitem.findViewById(R.id.tvUsername);
			
			vh.tvUserName.setText(items.get(position).get("Name").toString());
			vh.tvComment.setText(items.get(position).get("Comment").toString());
			vh.rbRating.setRating(Float.valueOf(items.get(position).get("Rating").toString()));
			
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