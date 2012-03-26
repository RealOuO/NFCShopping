package scut.bgooo.ui;

import java.io.File;

import scut.bgooo.weibo.WeiboUserListActivity;

import weibo4android.Weibo;
import weibo4android.WeiboException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends Activity {

	private EditText mCommit;
	private Button mShareCommitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);
		// setContentView(R.layout.verifier);
		mCommit = (EditText) findViewById(R.id.etComment);
		mShareCommitButton = (Button) findViewById(R.id.btShareAndCommit);
		// mWeibo = (Weibo)bundle.get("WEIBO");
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
				} else {
					mWeibo.setToken(WeiboUserListActivity.defaultUserInfo.GetAToken(),
							WeiboUserListActivity.defaultUserInfo.GetASecret());
				}
				
				String commit = mCommit.getText().toString();
				try {
					mWeibo.updateStatus(commit);
				} catch (WeiboException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
