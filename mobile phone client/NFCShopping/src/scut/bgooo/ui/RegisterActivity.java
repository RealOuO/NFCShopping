package scut.bgooo.ui;

import scut.bgooo.ui.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	private EditText mUserName;
	private EditText mUserPassword;
	private Button mConfirm;
	private Button mCancle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		mUserName = (EditText)findViewById(R.id.reuser);
		mUserPassword = (EditText)findViewById(R.id.repassword);
		mConfirm = (Button)findViewById(R.id.reconfirm);
		mCancle = (Button)findViewById(R.id.recancle);
		
		mConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mCancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();	
			}
		});
		
	}
	
}
