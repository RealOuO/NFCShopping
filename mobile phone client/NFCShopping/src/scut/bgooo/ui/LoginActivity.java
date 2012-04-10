package scut.bgooo.ui;


import scut.bgooo.db.UserProfileUtil;
import scut.bgooo.entities.User;
import scut.bgooo.webservice.WebServiceUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	protected static final int LOGINSUCCESS = 0;
	protected static final int LOGINFAILE = 1;
	private EditText mUserName;
	private EditText mPassWord;
	private Button mLogin;
	private Button mRigist;
	private User mUser = null;
	
	public ProgressDialog mProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mUserName=(EditText)findViewById(R.id.etUsername);
		mPassWord=(EditText)findViewById(R.id.etPassword);
		mRigist=(Button)findViewById(R.id.btRegist);
		mLogin=(Button)findViewById(R.id.btLogin);
		
		mRigist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
		
		mLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mProgressDialog = new ProgressDialog(LoginActivity.this);
				mProgressDialog.setMessage(LoginActivity.this.getResources().getString(
						R.string.app_name));
				mProgressDialog.setTitle(LoginActivity.this.getResources().getString(
						R.string.app_name));
				mProgressDialog.show();
				Login();
			}
		});
	}
	
	private void Login(){
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mUser = WebServiceUtil.getInstance().login(mUserName.getText().toString(),
						mPassWord.getText().toString());

				Message message = new Message();
				if (mUser != null) {
					message.arg1 = LOGINSUCCESS;
					message.obj = mUser;
				} else {
					message.arg1 = LOGINFAILE;
				}
				handler.sendMessage(message);
			}
		});

		thread.start();
		thread = null;
		
	}
	
	final Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.arg1) {
			case LOGINSUCCESS:
				Toast.makeText(getApplicationContext(), "µÇÂ½³É¹¦",
						Toast.LENGTH_SHORT).show();
				MainActivity.mNowUser = (User) msg.obj;
				UserProfileUtil.saveProfile(LoginActivity.this, (User) msg.obj, mPassWord.getText().toString());
				mProgressDialog.dismiss();
				finish();
				break;
			case LOGINFAILE:
				mProgressDialog.dismiss();
				Toast.makeText(getApplicationContext(), "µÇÂ¼Ê§°Ü",
						Toast.LENGTH_SHORT).show();
				break;
			}

		}
	};

}
