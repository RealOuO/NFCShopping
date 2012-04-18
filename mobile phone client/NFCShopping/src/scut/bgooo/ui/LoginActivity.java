package scut.bgooo.ui;

import scut.bgooo.db.UserProfileUtil;
import scut.bgooo.entities.Profile;
import scut.bgooo.entities.User;
import scut.bgooo.webservice.WebServiceUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>
 * 用户登录页面
 * 
 * <p>
 * 登录后的账户信息会被保留
 * 
 * @author Leeforall
 * @since 2012年4月9日
 * 
 * */
public class LoginActivity extends Activity {

	protected static final int LOGINSUCCESS = 0;
	protected static final int LOGINFAILE = 1;
	private EditText mUserName;
	private EditText mPassWord;
	private Button mLogin;
	private Button mRigist;
	private User mUser = null;

	/**
	 * 读取用户的登录信息变量
	 * */

	private Profile mProfile;
	private ProgressDialog mProgressDialog;

	private View mNodata;
	private TextView mNodataText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mUserName = (EditText) findViewById(R.id.etUsername);
		mPassWord = (EditText) findViewById(R.id.etPassword);
		mRigist = (Button) findViewById(R.id.btRegist);
		mLogin = (Button) findViewById(R.id.btLogin);

		mNodata = findViewById(R.id.nodatapopup);
		mNodataText = (TextView) mNodata.findViewById(R.id.prompt);
		mNodataText.setText("只有登录才能享受\n更好的服务哦亲！");

		mProfile = UserProfileUtil.readProfile(getApplicationContext());
		if (mProfile != null) {// 如果已经有保存了登录信息，则不必登录
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.haslogin);
			builder.setTitle(mProfile.getUsername());
			builder.setIcon(android.R.drawable.ic_dialog_info);
			builder.setCancelable(true);
			builder.setPositiveButton(R.string.btOK,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int i2) {
							dialog.dismiss();
							UserProfileUtil.saveProfile(
									getApplicationContext(), null);
							Toast.makeText(getApplicationContext(), "请进行登录",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setNegativeButton(R.string.btBack,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							finish();
						}

					});
			builder.show();
		}

		mRigist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(intent);
			}
		});

		mLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mUserName.getText().toString().trim().equals("")
						|| mPassWord.getText().toString().trim().equals("")) {
					Toast.makeText(getApplicationContext(), "输入有误",
							Toast.LENGTH_SHORT).show();
				} else {
					mProgressDialog = new ProgressDialog(LoginActivity.this);
					mProgressDialog.setMessage("登录中……");
					mProgressDialog.setTitle(LoginActivity.this.getResources()
							.getString(R.string.app_name));
					mProgressDialog.show();
					Login();
				}
			}
		});
	}

	private void Login() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mUser = WebServiceUtil.getInstance().login(
						mUserName.getText().toString(),
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
				mProgressDialog.dismiss();
				Toast.makeText(getApplicationContext(), "登陆成功",
						Toast.LENGTH_SHORT).show();
				Profile profile = new Profile(Integer.valueOf(((User) msg.obj)
						.getProperty(1).toString()), ((User) msg.obj)
						.getProperty(2).toString(), mPassWord.getText()
						.toString(), null);
				UserProfileUtil.saveProfile(LoginActivity.this, profile);
				finish();
				break;
			case LOGINFAILE:
				mProgressDialog.dismiss();
				Toast.makeText(getApplicationContext(), "登录失败",
						Toast.LENGTH_SHORT).show();
				break;
			}

		}
	};

}
