package scut.bgooo.weibo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import scut.bgooo.ui.R;
import scut.bgooo.ui.R.id;
import scut.bgooo.ui.R.layout;
import scut.bgooo.userdb.DataHelper;
import scut.bgooo.userdb.UserInfo;
import weibo4android.User;
import weibo4android.Weibo;
import weibo4android.WeiboException;
import weibo4android.http.AccessToken;
import weibo4android.http.RequestToken;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AuthorizeActivity extends Activity {

	private TextView mUserId;
	private TextView mAccessToken1;
	private TextView mAccessSecret;
	private TextView mUserName;
	private Button mButton;
	private ImageView mImageView;

	private Weibo mWeibo;
	private RequestToken mRequestToken;
	private AccessToken mAccessToken;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verifier);
		mUserId = (TextView) findViewById(R.id.t1);
		mAccessToken1 = (TextView) findViewById(R.id.t2);
		mAccessSecret = (TextView) findViewById(R.id.t3);
		mButton = (Button) findViewById(R.id.b1);
		mUserName= (TextView)findViewById(R.id.t4);
		mImageView = (ImageView)findViewById(R.id.imageView1);
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		mWeibo = new Weibo();		
		
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					mRequestToken = mWeibo.getOAuthRequestToken();
					mAccessToken = null;
					String oAuthUrl = mRequestToken.getAuthenticationURL();
					Intent intent = new Intent(AuthorizeActivity.this,VerifierWebViewActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("URL", oAuthUrl);
					intent.putExtras(bundle);
					Log.d("NFC", "前往认证");
					startActivity(intent);
					
				} catch (WeiboException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
	}

	/**
	 * 从WEBVIEW返回后，即注册了用户了
	 */
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
		
		mWeibo.setToken(mAccessToken.getToken(),mAccessToken.getTokenSecret());
		mAccessSecret.setText(mAccessToken.getTokenSecret());
		mAccessToken1.setText(mAccessToken.getToken());
		mUserId.setText(Long.toString(mAccessToken.getUserId()));
		UserInfo userInfo = new UserInfo();
		userInfo.SetAccessSecret(mAccessToken.getTokenSecret());
		userInfo.SetAccessToken(mAccessToken.getToken());
		userInfo.SetUserId(Long.toString(mAccessToken.getUserId()));
		try {
			User weiboUser = mWeibo.verifyCredentials();
			userInfo.SetUserName(weiboUser.getScreenName());
			URL url = weiboUser.getProfileImageURL();//用记头像的URL地址
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			InputStream inPutStream = conn.getInputStream();
			 //获取到图片的二进制数据  
	        byte[] data = readInputStream(inPutStream); 	
	        userInfo.SetIcon(data);
	        Bitmap userIcon = BitmapFactory.decodeByteArray(data, 0, data.length);//生成图片
	        mUserName.setText(userInfo.GetUserName());
	        mImageView.setImageBitmap(userIcon);	 
	        mImageView.setMaxHeight(100);
	        mImageView.setMaxWidth(100);
	        DataHelper dataHelper = new DataHelper(this);
	        dataHelper.SaveUserInfo(userInfo);
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
		
	}
	
	public static byte[] readInputStream(InputStream inStream) throws Exception  
    {  
        //构造一个ByteArrayOutputStream  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //设置一个缓冲区  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        //判断输入流长度是否等于-1，即非空  
        while( (len=inStream.read(buffer)) != -1 )  
        {  
            //把缓冲区的内容写入到输出流中，从0开始读取，长度为len  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        return outStream.toByteArray();  
    }  

}
