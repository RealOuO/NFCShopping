package scut.bgooo.ui;

import java.io.File;
import java.util.Vector;

import scut.bgooo.entities.Discount;
import scut.bgooo.entities.DiscountItem;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.Review;
import scut.bgooo.webservice.WebServiceUtil;
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

public class CommentActivity extends Activity {

	private EditText mCommit;
	private Button mShareCommitButton;
	private Button mCommitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);
		// setContentView(R.layout.verifier);
		mCommit = (EditText) findViewById(R.id.etComment);
		mShareCommitButton = (Button) findViewById(R.id.btShareAndCommit);
		mCommitButton = (Button) findViewById(R.id.btCommit);
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
					Log.i("token",
							WeiboUserListActivity.defaultUserInfo.GetAToken());
					Log.i("token",
							WeiboUserListActivity.defaultUserInfo.GetASecret());
					mWeibo.setToken(
							WeiboUserListActivity.defaultUserInfo.GetAToken(),
							WeiboUserListActivity.defaultUserInfo.GetASecret());
				}

				String commit = mCommit.getText().toString() + "aaaaaaaaaa";
				try {
					mWeibo.updateStatus(commit);
					Toast toast = Toast.makeText(getApplicationContext(),
							"微博发送成功", Toast.LENGTH_LONG);
					toast.show();
				} catch (WeiboException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
			}
		});

		mCommitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Review review = new Review();
				// review.setProperty(1, 2);
				// review.setProperty(2, 5);
				// review.setProperty(3, "safsfafsasfaf");
				// review.setProperty(4, 3*10); //注意要将浮点数转换为String才能传到后台
				// if (WebServiceUtil.getInstance().AddReview(review)) {
				// Toast.makeText(getApplicationContext(), "提交成功", 2000)
				// .show();
				// } else {
				// Toast.makeText(getApplicationContext(), "提交失败", 2000)
				// .show();
				// }

				Vector<Review> reviews =WebServiceUtil.getInstance().getReviewsByProductId(2);
				Log.d("dfdf", reviews.size() + "");
				for (int i = 0; i < reviews.size(); i++) {
					Log.d("fff", reviews.get(i) + "");
					for(int j=0;j<reviews.get(i).getPropertyCount();j++){
						Log.d("abc", reviews.get(i).getProperty(j).toString());
					}
				}
//				Log.d("dfadfafdasdfafd", WebServiceUtil.getInstance().regist("Lee", "dfafsda", 4).toString());
//				Vector<Discount> vector = WebServiceUtil.getInstance()
//						.getDiscounts();
//				Log.d("dfdf", vector.size() + "");
//				for (int i = 0; i < vector.size(); i++) {
//					Log.d("fff", vector.get(i) + "");
//					for(int j=0;j<vector.get(i).getPropertyCount();j++){
//						Log.d("abc", vector.get(i).getProperty(j).toString());
//					}
//				}
				Vector<DiscountItem> vector1 = WebServiceUtil.getInstance()
						.getDiscountItems(1);
				Log.d("dfdf", vector1.size() + "");
				for (int i = 0; i < vector1.size(); i++) {
					Log.d("fff", vector1.get(i) + "");
					for(int j=0;j<vector1.get(i).getPropertyCount();j++){
						Log.d("abc", vector1.get(i).getProperty(j).toString());
					}
				}
			}
		});
	}

}
