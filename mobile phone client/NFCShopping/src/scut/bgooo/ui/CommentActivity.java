package scut.bgooo.ui;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import scut.bgooo.utility.INFCActivity;
import scut.bgooo.utility.Task;
import scut.bgooo.utility.TaskHandler;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.db.UserProfileUtil;
import scut.bgooo.entities.Profile;
import scut.bgooo.entities.Review;
import scut.bgooo.webservice.WebServiceUtil;

import weibo4android.Weibo;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends Activity implements INFCActivity {

	protected static final int SUCCESS = 0;
	protected static final int FAILE = 1;
	protected static final int NOUSER = 2;
	private EditText mComment;
	private Button mShareCommitButton;
	private Button mCommitButton;
	private TextView mProductName;
	private ConcernItem mItem;
	private RatingBar mRatingbar;

	public ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);

		mItem = (ConcernItem) getIntent().getSerializableExtra("mItem");

		mComment = (EditText) findViewById(R.id.etComment);
		mShareCommitButton = (Button) findViewById(R.id.btShareAndCommit);
		mCommitButton = (Button) findViewById(R.id.btCommit);
		mRatingbar = (RatingBar) findViewById(R.id.ratingbar);

		mProductName = (TextView) findViewById(R.id.tvProductname);
		mProductName.setText(mItem.getName());


		mShareCommitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (WeiboUserListActivity.defaultUserInfo == null) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"请确定微博用户", Toast.LENGTH_LONG);
					toast.show();
					return;
				} else {
					mProgressDialog = new ProgressDialog(CommentActivity.this);
					mProgressDialog.setMessage(CommentActivity.this.getResources()
							.getString(R.string.app_name));
					mProgressDialog.setTitle(CommentActivity.this.getResources()
							.getString(R.string.app_name));
					mProgressDialog.show();
					Log.i("token",
							WeiboUserListActivity.defaultUserInfo.GetAToken());
					Log.i("token",
							WeiboUserListActivity.defaultUserInfo.GetASecret());
					String commitStr = "我对" + "#" + mItem.getName() + "#的评价是\n"
							+ mComment.getText().toString() + "我给他打"
							+ mRatingbar.getRating() + "分------#YY超市#";

					HashMap<String, String> m = new HashMap<String, String>();
					m.put("COMMIT", commitStr);
					Task task = new Task(Task.SEND_COMMENT_WEIBO, m);
					TaskHandler.addTask(task);
					UpdateReview();
				}

			}
		});

		mCommitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mProgressDialog = new ProgressDialog(CommentActivity.this);
				mProgressDialog.setMessage(CommentActivity.this.getResources()
						.getString(R.string.app_name));
				mProgressDialog.setTitle(CommentActivity.this.getResources()
						.getString(R.string.app_name));
				mProgressDialog.show();
				UpdateReview();
			}
		});

		TaskHandler.allActivity.put(CommentActivity.class.getSimpleName(),
				CommentActivity.this);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	private void UpdateReview() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Review review = new Review();
				Profile user = UserProfileUtil
						.readProfile(CommentActivity.this);
				if (user != null) {
					review.setProperty(2, user.getId());// 暂时还没处理完用户逻辑
					review.setProperty(3, mItem.getProductId());
					review.setProperty(4, mComment.getText().toString());
					review.setProperty(5,
							Float.valueOf(mRatingbar.getRating() * 10)
									.intValue()); // 注意要将浮点数转换为String才能传到后台
					Message msg = new Message();
					if (WebServiceUtil.getInstance().AddReview(review)) {
						msg.arg1 = SUCCESS;
					} else {
						msg.arg1 = FAILE;
					}
					handler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.arg1 = NOUSER;
					handler.sendMessage(msg);
				}
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
			case SUCCESS:
				Toast.makeText(getApplicationContext(), "提交成功", 2000).show();
				mProgressDialog.dismiss();
				finish();
				break;
			case FAILE:
				Toast.makeText(getApplicationContext(), "提交失败", 2000).show();
				mProgressDialog.dismiss();
				break;
			case NOUSER:
				Toast.makeText(getApplicationContext(), "请先登录", 2000).show();
				mProgressDialog.dismiss();
				break;
			}

		}
	};

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		String result = (String) param[0];
		if (result.equals("OK")) {
			Toast toast = Toast.makeText(getApplicationContext(), "微博发送成功",
					Toast.LENGTH_LONG);
			toast.show();
		}

	}

}
