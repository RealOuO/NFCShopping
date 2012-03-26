package scut.bgooo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FeedBackActivity extends Activity {

	private Button mCancle;
	private Button mConfirm;
	private EditText mFeedBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		mCancle = (Button)findViewById(R.id.fbcancle);
		mConfirm= (Button)findViewById(R.id.fbconfirm);
		mFeedBack = (EditText)findViewById(R.id.fbedit);
		
		
	}

}
