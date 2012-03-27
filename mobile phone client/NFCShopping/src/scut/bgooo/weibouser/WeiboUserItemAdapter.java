package scut.bgooo.weibouser;

import java.util.List;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.ui.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class WeiboUserItemAdapter extends BaseAdapter {

	private Context mContext; // 运行上下文
	private List<WeiboUserItem> mItems; // 商品信息集合

	public WeiboUserItemAdapter(Context context, List<WeiboUserItem> items) {
		this.mContext = context;
		this.mItems = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position).GetId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View weibousertitem = null;

		if (convertView != null) {
			Log.d("getview", "doGetView-------get TextView-----------"
					+ position);
			weibousertitem = convertView;
		} else {
			Log.d("getview", "doGetView-------new TextView-----------"
					+ position);

			weibousertitem = LayoutInflater.from(mContext).inflate(
					R.layout.weibouseritem, null);
			viewHolder.mUserIcon = (ImageView) weibousertitem.findViewById(R.id.usericon);
			viewHolder.mUserName = (TextView) weibousertitem.findViewById(R.id.username);
			viewHolder.mUserLocaton = (TextView) weibousertitem
					.findViewById(R.id.userlocation);
			viewHolder.mCheckBox = (CheckBox) weibousertitem.findViewById(R.id.checkBox1);
		}
		if (mItems.size() != 0) {
			final WeiboUserItem user = mItems.get(position);
			byte[] data = user.GetIcon();
			Bitmap userIcon = BitmapFactory.decodeByteArray(data, 0,
					data.length);
			viewHolder.mUserIcon.setImageBitmap(userIcon);
			viewHolder.mUserName.setText(user.GetUserName());
			viewHolder.mUserLocaton.setText(user.GetLocationg());
			if(user.IsDefault()) {
//				defaultUser = position;
				viewHolder.mCheckBox.setChecked(true);
			}
			viewHolder.mCheckBox
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(
								CompoundButton buttonView, boolean isChecked) {
							// TODO Auto-generated method stub
							if (isChecked) {
//								Toast toast = Toast.makeText(
//										getApplicationContext(), "您选择"+user.GetUserName()+"为默认用户",
//										Toast.LENGTH_SHORT);
//								toast.show();
//								if (defaultUser != -1)dataHelper.UpdateDefault(mList.get(defaultUser));									
//								defaultUser = selectID;
//								user.SetDefault(true);
//								dataHelper.UpdateUserInfo(user);
							}
						}
					});

		}
		return null;
	}

	/**
	 * 每一个listitem里的东西
	 * 
	 * @author 肥哥
	 * 
	 */
	private class ViewHolder {
		public ImageView mUserIcon;// 用户图片用
		public TextView mUserName;// 用户昵称
		public TextView mUserLocaton;// 用户注册地址
		public CheckBox mCheckBox;// 复选框用
	}

	ViewHolder viewHolder = new ViewHolder();
}
