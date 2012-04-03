package scut.bgooo.ui;

import scut.bgooo.ui.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegisterActivity extends Activity {

	private EditText mUserName;
	private EditText mUserPassword;
	private Button mConfirm;
	private Button mCancle;
	private RadioGroup mGenderChooser;
	private RadioButton mMale;
	private RadioButton mFemale;
	private RadioButton mOther;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		mUserName = (EditText) findViewById(R.id.reuser);
		mUserPassword = (EditText) findViewById(R.id.repassword);

		mFemale=(RadioButton)findViewById(R.id.female);
		mMale=(RadioButton)findViewById(R.id.male);
		mOther=(RadioButton)findViewById(R.id.other);
		
		mGenderChooser=(RadioGroup)findViewById(R.id.radioGroup_gender);
		mGenderChooser.setOnCheckedChangeListener(onCheckedChangeListener);
		
		mConfirm = (Button) findViewById(R.id.reconfirm);
		mCancle = (Button) findViewById(R.id.recancle);

		mConfirm.setOnClickListener(onClickListener);
		mCancle.setOnClickListener(onClickListener);

	}
	
	RadioGroup.OnCheckedChangeListener onCheckedChangeListener=new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if(checkedId==mFemale.getId()){
				
			}else if(checkedId==mMale.getId()){
				
			}else if(checkedId==mOther.getId()){
				
			}else{
				//do nothing
			}
		}
	};
	
	OnClickListener onClickListener=new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.equals(mConfirm)){
				
			}else if(v.equals(mCancle)){
				finish();
			}

		}
	};

}
