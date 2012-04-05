package scut.bgooo.weibo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scut.bgooo.ui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VerifierWebViewActivity extends Activity {

	private WebView mWebView;
	private String mOauthPin = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		mWebView = (WebView) findViewById(R.id.webView1);
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("URL");
		String URL = bundle.getString("URL");
		
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setSupportZoom(true);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.loadUrl(URL);
		
		
		mWebView.addJavascriptInterface(new JavaScriptInterface(), "Methods");
		WebViewClient wvc = new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				view.loadUrl("javascript:window.Methods.getHTML('<head>'+document.getElementsByTagName('span')[0].innerHTML+'</head>');");
				super.onPageFinished(view, url);
			}
		};
		mWebView.setWebViewClient(wvc);
	}

	class JavaScriptInterface {
		public void getHTML(String html) {
			if (getPin(html)) {
				Intent intent = new Intent(VerifierWebViewActivity.this,
						WeiboUserListActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("PIN", mOauthPin);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			} else {

			}
		}
		
		public boolean getPin(String html) {
			// 最纠结的正则表达式
//			String regEx = "[\u4e00-\u9fa5]{6}：[<][s][p][a][n][\\s][c][l][a][s][s][=][\"][f][b][\"][>][0-9]{6}";
//			Pattern pattern = Pattern.compile(regEx);
//			Matcher matcher = pattern.matcher(html);
//			if (matcher.find()) {
//				regEx = "[0-9]{6}";
//				Pattern p = Pattern.compile(regEx);
//				Matcher m = p.matcher(matcher.group(0));
//				if (m.find()) {
//					mOauthPin = m.group(0);
//					return true;
//				}
//			}
			String regEx = "[0-9]{6}";
			Pattern patten = Pattern.compile(regEx);
			Matcher matcher = patten.matcher(html);
			boolean result = matcher.find();
			if (result) {
				mOauthPin = matcher.group(0);
				return true;
			}
			return false;
		}
	}

	

}
