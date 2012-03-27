package scut.bgooo.ui;

import java.util.List;
import java.util.Map;


import scut.bgooo.concern.ConcernManager;
import scut.bgooo.weibo.WeiboUserListActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MoreActivity extends ListActivity {


	private static final String TAG = MoreActivity.class.getSimpleName();

	private ConcernManager mConcernManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mConcernManager = new ConcernManager(this);

		String[] strs = { "注册", "绑定微博账号", "清空关注列表", "应用设置", "帮助", "反馈" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs);
		setListAdapter(adapter);

		// 提示对话框
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);


		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0: {
					Intent intent = new Intent(MoreActivity.this, RegisterActivity.class);
					startActivity(intent);
				}
					break;
				case 1: {
					Intent intent = new Intent(MoreActivity.this, WeiboUserListActivity.class);					
					startActivity(intent);
				}break;					
				case 2:
					builder.setMessage(R.string.msg_sure);
					builder.setCancelable(true);
					builder.setPositiveButton(R.string.btOK,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int i2) {
									mConcernManager.clearConcern();
									Log.d(TAG, "清空关注列表");
									dialog.dismiss();
								}
							});
					builder.setNegativeButton(R.string.btCancel, null);
					builder.show();
					break;
				case 3:{
					Intent intent = new Intent(MoreActivity.this,CommentActivity.class);
					startActivity(intent);
				}

					break;
				case 4:
					break;
				case 5: {
					Intent intent = new Intent(MoreActivity.this, FeedBackActivity.class);
					startActivity(intent);
				}

				}
			}
		});
	}

}
