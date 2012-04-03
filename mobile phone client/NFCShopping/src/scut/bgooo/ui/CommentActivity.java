package scut.bgooo.ui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import scut.bgooo.utility.IWeiboActivity;
import scut.bgooo.utility.Task;
import scut.bgooo.utility.TaskHandler;
import scut.bgooo.weibo.WeiboUserListActivity;

import weibo4android.Weibo;
import weibo4android.WeiboException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends Activity implements IWeiboActivity{

	private EditText mCommit;
	private Button mShareCommitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);
	
		mCommit = (EditText) findViewById(R.id.etComment);
		mShareCommitButton = (Button) findViewById(R.id.btShareAndCommit);

		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret",
				Weibo.CONSUMER_SECRET);
		
		mShareCommitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Weibo mWeibo = new Weibo();
				if (WeiboUserListActivity.defaultUserInfo == null) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"请确定微博用户", Toast.LENGTH_LONG);
					toast.show();
					return;
				} else {
					Log.i("token", WeiboUserListActivity.defaultUserInfo.GetAToken());
					Log.i("token", WeiboUserListActivity.defaultUserInfo.GetASecret());
					String commit = mCommit.getText().toString()+"aaaaaaaaaa";
					
					HashMap<String, String> m = new HashMap<String, String>();
					m.put("COMMIT", commit);
					Task task = new Task(Task.SEND_COMMENT_WEIBO, m);
					TaskHandler.addTask(task);					
					finish();
				}
				
			}
		});
		
		TaskHandler.allActivity.put(CommentActivity.class.getSimpleName(), CommentActivity.this);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		String result  = (String)param[0];
		if (result.equals("OK")){
			Toast toast = Toast.makeText(getApplicationContext(),
					"微博发送成功", Toast.LENGTH_LONG);
			toast.show();
		}
		
	}

}
