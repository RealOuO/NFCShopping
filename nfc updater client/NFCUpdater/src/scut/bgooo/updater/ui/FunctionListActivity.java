package scut.bgooo.updater.ui;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class FunctionListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		String[] strs = { "读取商品标签", "更新商品标签" ,"设置","帮助","关于"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs);
		setListAdapter(adapter);

		getListView().setOnItemClickListener(itemClickListener);
	}

	OnItemClickListener itemClickListener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent=null;
			switch (arg2) {
			case 0:
				intent= new Intent(FunctionListActivity.this,
						MainActivity.class);
				Toast.makeText(getApplication(), "请讲读写芯片贴近标签", 3000).show();
				startActivity(intent);
				break;
			case 1:
				intent = new Intent(FunctionListActivity.this,
						CaptureActivity.class);
				Toast.makeText(getApplication(), "请拍摄商品的条形码", 3000).show();
				startActivity(intent);
				break;
			}
		}
	};
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.d("keydown", ".................." + keyCode);
		if (keyCode == KeyEvent.KEYCODE_BACK)// 如果用户按下了返回键
		{
			final AlertDialog.Builder builder = new AlertDialog.Builder(
					this);
			builder.setMessage(R.string.msg_sure);
			builder.setCancelable(true);
			builder.setPositiveButton(R.string.btOK,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int i2) {
							dialog.dismiss();
							finish();
						}
					});
			builder.setNegativeButton(R.string.btCancel, null);
			builder.show();
		}
		return true;
	}

}
