package scut.bgooo.ui;

import java.util.List;
import java.util.Map;

import scut.bgooo.concern.ConcernManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
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
		
		String[] strs = { "注册", "绑定微博账号", "清空关注列表", "应用设置", "帮助","反馈" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs);
		setListAdapter(adapter);

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch(arg2){
				case 0:
					mConcernManager.clearConcern();
					Log.d(TAG, "清空关注列表");
					break;
				case 1:
					mConcernManager.clearConcern();
					Log.d(TAG, "清空关注列表");
					break;
				case 2:
					mConcernManager.clearConcern();
					Log.d(TAG, "清空关注列表");
					break;
				case 3:
					mConcernManager.clearConcern();
					Log.d(TAG, "清空关注列表");
					break;
				}
			}
		});
	}

}
