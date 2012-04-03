package scut.bgooo.ui;

import scut.bgooo.entities.Suggestion;
import scut.bgooo.webservice.WebServiceUtil;
import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
		
		
		mConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Suggestion suggestion=new Suggestion();
				suggestion.setProperty(1, 2);
				suggestion.setProperty(2, "afjkafjakfsoifsdjlf");
				if(WebServiceUtil.getInstance().AddSuggestion(suggestion)){
					Toast.makeText(getApplicationContext(), "提交成功", 2000).show();
				}else{
					Toast.makeText(getApplicationContext(), "提交失败", 2000).show();
				}
			}
		});
		
	}

}
