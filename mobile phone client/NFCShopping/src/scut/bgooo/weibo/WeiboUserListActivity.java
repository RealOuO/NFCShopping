package scut.bgooo.weibo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import scut.bgooo.ui.R;
import scut.bgooo.userdb.DataHelper;
import scut.bgooo.userdb.UserInfo;
import weibo4android.User;
import weibo4android.Weibo;
import weibo4android.WeiboException;
import weibo4android.http.AccessToken;
import weibo4android.http.RequestToken;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class WeiboUserListActivity extends Activity {

	private Button mClearList;
	private Button mDelUser;
	private Button mAddUser;
	private ListView mUserList;
	private DataHelper dataHelper;
	private List<UserInfo> mList;
	public static Weibo mWeibo;
	private RequestToken mRequestToken;
	private AccessToken mAccessToken;   
    private int defaultUser = -1;//默认用户
    public static UserInfo defaultUserInfo = null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webuser);
		mClearList = (Button) findViewById(R.id.clear);
		mDelUser = (Button) findViewById(R.id.del);
		mAddUser = (Button) findViewById(R.id.add);
		mUserList = (ListView) findViewById(R.id.user);
		
		dataHelper = new DataHelper(this);//打开数据库　一直到这个activity销毁时才关闭
		mList = dataHelper.GetUserList(false);
		if (mList.isEmpty()) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"您尚未绑定用户,请添加用户绑定", Toast.LENGTH_SHORT);
			toast.show();
			Log.d("NFC", "AA");
		} else {
			MyAdapter myAdapter = new MyAdapter(this, mList);
			mUserList.setAdapter(myAdapter);
			mUserList.setClickable(true);
		}

		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret",
				Weibo.CONSUMER_SECRET);

		mAddUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					ConnectivityManager conn = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
					NetworkInfo net = conn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
					if (false == net.isConnectedOrConnecting()) {
						Toast toast = Toast.makeText(getApplicationContext(), "请检查网络配置", Toast.LENGTH_SHORT);
						toast.show();
					} else {
						mWeibo = new Weibo();
						mRequestToken = mWeibo.getOAuthRequestToken();
						mAccessToken = null;
						String oAuthUrl = mRequestToken.getAuthenticationURL();
						Intent intent = new Intent(WeiboUserListActivity.this,
								VerifierWebViewActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("URL", oAuthUrl);
						intent.putExtras(bundle);
						Log.d("NFC", "前往认证");
						startActivity(intent);
					}
					
				} catch (WeiboException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		mClearList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dataHelper.ClearUserInfo(mList);
				mList = dataHelper.GetUserList(true);

				MyAdapter myAdapter = new MyAdapter(WeiboUserListActivity.this,
						mList);
				mUserList.setAdapter(myAdapter);
				Toast toast = Toast.makeText(getApplicationContext(),
						"您尚未绑定用户,请添加用户绑定", Toast.LENGTH_SHORT);
				toast.show();
				defaultUser = -1;
			}
		});
		
		mDelUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (defaultUser >= 0) {
					UserInfo user = mList.get(defaultUser);
					dataHelper.DelUserInfo(user.GetUserId());
					mList = dataHelper.GetUserList(true);
					if (0 == mList.size()) {
						Toast toast = Toast.makeText(getApplicationContext(),
								"您尚未绑定用户,请添加用户绑定", Toast.LENGTH_SHORT);
						toast.show();
					} else if (mList.size() >=1) {
						Toast toast = Toast.makeText(getApplicationContext(),
								"请选择默认用户", Toast.LENGTH_SHORT);
						toast.show();
					}
					MyAdapter myadapter = new MyAdapter(WeiboUserListActivity.this, mList);
					mUserList.setAdapter(myadapter);
					defaultUser = -1;
				} else  {
					Toast toast = Toast.makeText(getApplicationContext(),
							"请选择默认用户", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Bundle bundle = intent.getExtras();
		String Pin = bundle.getString("PIN");		
		try {
			mAccessToken = mRequestToken.getAccessToken(Pin);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//mWeibo.setToken(mAccessToken.getToken(), mAccessToken.getTokenSecret());
		
		// DataHelper dataHelper = new DataHelper(this);
		//判断是否在数据库中已经存在这个用户　
		if (dataHelper.HaveUserInfo(Long.toString(mAccessToken.getUserId()))) {
			Toast toast = Toast.makeText(getApplicationContext(), "该用户已经绑定",
					Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		//如果不存在，则生成一个USERINFO对象再存入数据库中
		UserInfo userInfo = new UserInfo();
		userInfo.SetAccessSecret(mAccessToken.getTokenSecret());
		userInfo.SetAccessToken(mAccessToken.getToken());
		userInfo.SetUserId(Long.toString(mAccessToken.getUserId()));

		try {
			User weiboUser = mWeibo.verifyCredentials();
			userInfo.SetLocation(weiboUser.getLocation());
			userInfo.SetUserName(weiboUser.getScreenName());
			URL url = weiboUser.getProfileImageURL();// 用记头像的URL地址
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			InputStream inPutStream = conn.getInputStream();
			// 获取到图片的二进制数据
			byte[] data = readInputStream(inPutStream);
			userInfo.SetIcon(data);
			userInfo.SetDefault(true);//把最新验证的用户设为默认
			if (mList.size() != 0) {
				dataHelper.UpdateDefault(mList.get(defaultUser));
			}
			//dataHelper.SaveUserInfo(userInfo);//把更新的userinfo对象存入数据库
			if(dataHelper.SaveUserInfo(userInfo) == -1) {
				Toast toast = Toast.makeText(getApplicationContext(), "写入数据失败",
						Toast.LENGTH_SHORT);
				toast.show();
			}
			//再更新适配器
			mList = dataHelper.GetUserList(false);
			MyAdapter myAdapter = new MyAdapter(this, mList);
			mUserList.setAdapter(myAdapter);
			
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("NFC", "成功绑定");
		
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		// 构造一个ByteArrayOutputStream
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 设置一个缓冲区
		byte[] buffer = new byte[1024];
		int len = 0;
		// 判断输入流长度是否等于-1，即非空
		while ((len = inStream.read(buffer)) != -1) {
			// 把缓冲区的内容写入到输出流中，从0开始读取，长度为len
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		return outStream.toByteArray();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		dataHelper.Close();
		if (mList.size() != 0) {
			defaultUserInfo = mList.get(defaultUser);		
		} else {
			defaultUserInfo = null;
		}
		Log.d("NFC", "关闭数据库");
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		if (0 == mList.size()) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"您尚未绑定用户,请添加用户绑定", Toast.LENGTH_SHORT);
			toast.show();
		}
		super.onRestart();

	}

	private class MyAdapter extends BaseAdapter {

		private Context mContext; // 运行上下文
		private List<UserInfo> mListItems; // 商品信息集合
		private LayoutInflater mListContainer; // 视图容器

		public MyAdapter(Context context, List<UserInfo> listItems) {
			mContext = context;
			mListItems = listItems;
			mListContainer = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub		
			final int selectID = arg0;
			ViewHolder viewHolder = null;
			if (arg1 == null) {
				viewHolder = new ViewHolder();
				// 获取list_item布局文件的视图
				arg1 = mListContainer.inflate(R.layout.weibouseritem, null);
				// 获取控件对象
				viewHolder.mUserIcon = (ImageView) arg1
						.findViewById(R.id.usericon);
				viewHolder.mUserName = (TextView) arg1
						.findViewById(R.id.username);
				viewHolder.mUserLocaton = (TextView) arg1
						.findViewById(R.id.userlocation);
				viewHolder.mCheckBox = (CheckBox) arg1
						.findViewById(R.id.checkBox1);
				// 设置控件集到arg1
				arg1.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) arg1.getTag();
			}
			if (mList.size() != 0) {
				final UserInfo user = mList.get(selectID);
				byte[] data = user.GetIcon();
				Bitmap userIcon = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				viewHolder.mUserIcon.setImageBitmap(userIcon);
				viewHolder.mUserName.setText(user.GetUserName());
				viewHolder.mUserLocaton.setText(user.GetLocationg());
				if(user.IsDefault()) {
					defaultUser = selectID;
					viewHolder.mCheckBox.setChecked(true);
				}
				viewHolder.mCheckBox
						.setOnCheckedChangeListener(new OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								// TODO Auto-generated method stub
								if (isChecked) {
									Toast toast = Toast.makeText(
											getApplicationContext(), "您选择"+user.GetUserName()+"为默认用户",
											Toast.LENGTH_SHORT);
									toast.show();
									if (defaultUser != -1)dataHelper.UpdateDefault(mList.get(defaultUser));									
									defaultUser = selectID;
									user.SetDefault(true);
									dataHelper.UpdateUserInfo(user);
								}
							}
						});

			}
			return arg1;

		}

		private class ViewHolder {
			public ImageView mUserIcon;// 用户图片用
			public TextView mUserName;// 用户昵称
			public TextView mUserLocaton;// 用户注册地址
			public CheckBox mCheckBox;// 复选框用
		}
	}
	

}


