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

		String[] strs = { "ע��", "��΢���˺�", "��չ�ע�б�", "Ӧ������", "����", "����" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs);
		setListAdapter(adapter);

		// ��ʾ�Ի���
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					builder.setMessage(R.string.msg_sure);
					builder.setCancelable(true);
					builder.setPositiveButton(R.string.btOK,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int i2) {
									mConcernManager.clearConcern();
									Log.d(TAG, "��չ�ע�б�");
									dialog.dismiss();
								}
							});
					builder.setNegativeButton(R.string.btCancel, null);
					builder.show();
					break;
				case 3:

					break;
				}
			}
		});
	}

}