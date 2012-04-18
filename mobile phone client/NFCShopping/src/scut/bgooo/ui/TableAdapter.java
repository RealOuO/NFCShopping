package scut.bgooo.ui;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class TableAdapter extends BaseAdapter {

	private Context context;
	private List<TableRow> table;

	public TableAdapter(Context context, List<TableRow> table) {
		this.context = context;
		this.table = table;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return table.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return table.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TableRow tableRow = table.get(position);
		return new TableRowView(this.context, tableRow);
	}

	class TableRowView extends LinearLayout {

		public TableRowView(Context context, TableRow tableRow) {
			super(context);
			// TODO Auto-generated constructor stub
			this.setOrientation(LinearLayout.HORIZONTAL);
			for (int i = 0; i < tableRow.getSize(); i++) {// 逐个格单元添加到行
				TableCell tableCell = tableRow.getCellValue(i);
				if (tableCell.type == TableCell.STRING) {// 如果格单元是文本内容
					TextView textCell = new TextView(context);
					textCell.setGravity(Gravity.CENTER);
					textCell.setTextSize(18);
					textCell.setBackgroundColor(Color.BLACK);// 背景黑色
					textCell.setText(String.valueOf(tableCell.value));
					LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
							tableCell.width, tableCell.height);// 按照格单元指定的大小设置空间
					layoutParams.setMargins(1, 1, 1, 1);// 预留空隙制造边框
					addView(textCell, layoutParams);
				} else if (tableCell.type == TableCell.IMAGE) {// 如果格单元是图像内容
					ImageView imgCell = new ImageView(context);
					imgCell.setBackgroundColor(Color.BLACK);// 背景黑色
					byte[] data = (byte[]) tableCell.value;
					Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
							data.length);
					imgCell.setImageBitmap(bitmap);
					int sizeW = tableCell.width;
					int sizeH = tableCell.width;
					LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
							sizeW, sizeH);// 按照格单元指定的大小设置空间
					imageLayoutParams.setMargins(1, 1, 1, 1);
					addView(imgCell, imageLayoutParams);
				}
			}
			this.setBackgroundColor(Color.WHITE);// 背景白色，利用空隙来实现边框

		}

	}
}
