/*
 * Copyright (C) 2012 The Team of BGOOO
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package scut.bgooo.ui;

import scut.bgooo.entities.User;
import scut.bgooo.ui.R;
import scut.bgooo.webservice.WebServiceUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	protected static final int REGISTSUCCESS = 0;
	protected static final int REGISTFAILE = 1;
	private EditText mUserName;
	private EditText mUserPassword;
	private EditText mUserRepeatPassword;
	private Button mConfirm;
	private Button mCancle;
	private RadioGroup mGenderChooser;
	private RadioButton mMale;
	private RadioButton mFemale;
	private RadioButton mOther;
	private String mPassword;
	private String mUsername;
	private String mRepeatPassword;
	private int mGender;
	private User mUser = null;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		mUserName = (EditText) findViewById(R.id.reuser);
		mUserPassword = (EditText) findViewById(R.id.repassword1);
		mUserRepeatPassword = (EditText) findViewById(R.id.repassword2);
		mFemale = (RadioButton) findViewById(R.id.female);
		mMale = (RadioButton) findViewById(R.id.male);
		mOther = (RadioButton) findViewById(R.id.other);

		mGenderChooser = (RadioGroup) findViewById(R.id.radioGroup_gender);
		mGenderChooser.setOnCheckedChangeListener(onCheckedChangeListener);

		mConfirm = (Button) findViewById(R.id.reconfirm);
		mCancle = (Button) findViewById(R.id.recancle);

		mConfirm.setOnClickListener(onClickListener);
		mCancle.setOnClickListener(onClickListener);

	}

	RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (checkedId == mFemale.getId()) {
				mGender = 1;
			} else if (checkedId == mMale.getId()) {
				mGender = 2;
			} else if (checkedId == mOther.getId()) {
				mGender = 3;
			} else {
				// do nothing
			}
		}
	};

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.equals(mConfirm)) {
				mPassword = mUserPassword.getText().toString();
				mRepeatPassword = mUserRepeatPassword.getText().toString();
				mUsername = mUserName.getText().toString();
				if (mPassword.equals(mRepeatPassword)) {
					mProgressDialog = new ProgressDialog(RegisterActivity.this);
					mProgressDialog.setMessage("×¢²áÖÐ¡­¡­");
					mProgressDialog.setTitle(RegisterActivity.this.getResources()
							.getString(R.string.app_name));
					mProgressDialog.show();
					Regist();
				}
			} else if (v.equals(mCancle)) {
				finish();
			}

		}
	};

	private void Regist() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mUser = WebServiceUtil.getInstance().regist(mUsername,
						mPassword, mGender);

				Message message = new Message();
				if (mUser != null) {
					message.arg1 = REGISTSUCCESS;
					message.obj = mUser;
				} else {
					message.arg1 = REGISTFAILE;
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
			case REGISTSUCCESS:
				mProgressDialog.dismiss();
				Toast.makeText(getApplicationContext(), "×¢²á³É¹¦",
						Toast.LENGTH_SHORT).show();
				finish();
				break;
			case REGISTFAILE:
				mProgressDialog.dismiss();
				Toast.makeText(getApplicationContext(), "×¢²áÊ§°Ü",
						Toast.LENGTH_SHORT).show();
				break;
			}

		}
	};

}
