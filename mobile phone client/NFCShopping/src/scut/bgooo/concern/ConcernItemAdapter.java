package scut.bgooo.concern;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import scut.bgooo.ui.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ConcernItemAdapter extends BaseAdapter {

	private Context mContext; // 运行上下文
	private List<ConcernItem> mItems; // 商品信息集合

	public ConcernItemAdapter(Context context, List<ConcernItem> items) {
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
		return mItems.get(position).getId();
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

	private ViewHolder vh = new ViewHolder();

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View concernitem = null;
		if (mItems.get(position).getId() == 0) {
			concernitem = LayoutInflater.from(mContext).inflate(
					R.layout.datetagitemview, null);

			TextView tv = (TextView) concernitem.findViewById(R.id.tvDatetag);

			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date scanDate = new Date(mItems.get(position).getTimestamp());

			String date = formater.format(scanDate);
			tv.setText(date);
		} else {
			concernitem = LayoutInflater.from(mContext).inflate(
					R.layout.productitem, null);

			vh.mImageView = (ImageView) concernitem
					.findViewById(R.id.goods_image);
			vh.mGoodScore = (RatingBar) concernitem.findViewById(R.id.score);
			vh.mGoodsNmae = (TextView) concernitem.findViewById(R.id.name);
			vh.mGoodsPrice = (TextView) concernitem.findViewById(R.id.price);

			ConcernItem item = mItems.get(position);
			byte [] data = item.getIcon();
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			vh.mImageView.setImageBitmap(bitmap);
			vh.mGoodScore.setRating(item.getRating());
			vh.mGoodsNmae.setText(item.getName());
			vh.mGoodsPrice.setText(String.valueOf(item.getPrice()));

		}
		return concernitem;
	}

	/**
	 * 当某些数据变化的时候，通知其改变
	 * 
	 * */
	public void dataSetChanged(List<ConcernItem> items) {
		this.mItems = items;
		this.notifyDataSetChanged();
	}

	/**
	 * 移除该位置的项
	 */
	public void removeItem(int position) {
		mItems.remove(position);
		this.notifyDataSetChanged();
	}

	// 默认情况，如果这个方法不是分割符，返回true
	// 分隔符是无选中和无点击事件的
	// 说白了，你想不想把改position项当做分隔符，想的话就返回false，否则返回true
	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		if (mItems.get(position).getId() == 0) {
			return false;
		}
		return true;
	}
}
