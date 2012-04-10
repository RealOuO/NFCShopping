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

		// // 每行的数据
		// TableCell[] cells = new TableCell[5];// 每行5个单元
		// for (int i = 0; i < cells.length - 1; i++) {
		// cells[i] = new TableCell("No." + String.valueOf(i),
		// titles[i].width, LayoutParams.FILL_PARENT, TableCell.STRING);
		// }
		// cells[cells.length - 1] = new TableCell(R.drawable.icon,
		// titles[cells.length - 1].width, LayoutParams.WRAP_CONTENT,
		// TableCell.IMAGE);
		// // 把表格的行添加到表格
		// for (int i = 0; i < 12; i++)
		// table.add(new TableRow(cells));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		ArrayList<TableRow> table = new ArrayList<TableRow>();
		TableCell[] titles = new TableCell[MainActivity.mItemArray.size()+1];// 每行根据对比数目创建单元

		int width = this.getWindowManager().getDefaultDisplay().getWidth()
				/2;
		//第一列
		titles[0] = new TableCell("属性" , width + 8 * 0,
				LayoutParams.FILL_PARENT, TableCell.STRING);
		// 定义标题
		for (int i = 1; i < titles.length; i++) {
			titles[i] = new TableCell("商品" + String.valueOf(i), width,
					LayoutParams.FILL_PARENT, TableCell.STRING);
		}
		table.add(new TableRow(titles));
		
		if (MainActivity.mItemArray.size() > 0) {

			for (int i = 0; i < ConcernItem.getCount(); i++) {
				TableCell[] cells = new TableCell[MainActivity.mItemArray.size()+1];// 每行5个单元
				switch(i){
				case 0:
					cells[0] = new TableCell("商品名", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 1:
					cells[0] = new TableCell("品牌", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 2:
					cells[0] = new TableCell("价格", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 3:
					cells[0] = new TableCell("类别", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 4:
					cells[0] = new TableCell("产地", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 5:
					cells[0] = new TableCell("详细描述", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				case 6:
					cells[0] = new TableCell("评分", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;
				default:
					cells[0] = new TableCell("其他", titles[0].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
					break;			
				}
				for (int j = 1; j < cells.length; j++) {
					cells[j] = new TableCell(MainActivity.mItemArray.get(j-1)//注意要减去1
							.getAttribute(i).toString(), titles[j].width,
							LayoutParams.FILL_PARENT, TableCell.STRING);
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
