package scut.bgooo.ui;

import java.util.ArrayList;

import scut.bgooo.concern.ConcernItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;

public class CompareActivity extends Activity {

	private ListView mCompareList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compare);

		/*
		 * http://blog.csdn.net/hellogv/article/details/6075014
		 * 有具体的类似的功能实现。课参考。。。。。。。。。。。。
		 */
		mCompareList = (ListView) findViewById(R.id.lvCompare);

		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		ArrayList<TableRow> table = new ArrayList<TableRow>();
		TableCell[] titles = new TableCell[NFCShoppingTab.mItemArray.size() + 1];// 每行根据对比数目创建单元

		int width = this.getWindowManager().getDefaultDisplay().getWidth() / 2;
		// 第一列
		titles[0] = new TableCell("属性", width + 8 * 0,
				LayoutParams.FILL_PARENT, TableCell.STRING);
		// 定义标题
		for (int i = 1; i < titles.length; i++) {
			titles[i] = new TableCell("商品" + String.valueOf(i), width,
					LayoutParams.FILL_PARENT, TableCell.STRING);
		}
		table.add(new TableRow(titles));

		if (NFCShoppingTab.mItemArray.size() > 0) {

			for (int i = 0; i < ConcernItem.getCount(); i++) {
				TableCell[] cells = new TableCell[NFCShoppingTab.mItemArray
						.size() + 1];// 每行5个单元
				switch (i) {
				case 0:
					cells[0] = new TableCell("商品图片", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 1:
					cells[0] = new TableCell("商品名", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 2:
					cells[0] = new TableCell("品牌", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 3:
					cells[0] = new TableCell("价格(元)", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 4:
					cells[0] = new TableCell("评分(5分制)", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 5:
					cells[0] = new TableCell("类别", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 6:
					cells[0] = new TableCell("产地", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 7:
					cells[0] = new TableCell("详细描述", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				default:
					cells[0] = new TableCell("其他", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				}
				for (int j = 1; j < cells.length; j++) {
					if (i == 0) {
						cells[j] = new TableCell(NFCShoppingTab.mItemArray
								.get(j - 1)// 注意要减去1
								.getAttribute(i), titles[j].width,
								LayoutParams.FILL_PARENT, TableCell.IMAGE);
					} else {
						cells[j] = new TableCell(NFCShoppingTab.mItemArray
								.get(j - 1)// 注意要减去1
								.getAttribute(i).toString(), titles[j].width,
								LayoutParams.FILL_PARENT, TableCell.STRING);
					}

				}
				table.add(new TableRow(cells));
			}

			TableAdapter tableAdapter = new TableAdapter(this, table);
			mCompareList.setAdapter(tableAdapter);
			mCompareList.setOnItemClickListener(new ItemClickEvent());
		}

		super.onResume();
	}

	class ItemClickEvent implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Toast.makeText(CompareActivity.this,
					"选中第" + String.valueOf(arg2) + "行", 500).show();
		}
	}

}
