package scut.bgooo.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.concern.ConcernManager;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.SecCategory;
import scut.bgooo.utility.Task;
import scut.bgooo.webservice.WebServiceUtil;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends Activity {

	private static final String TAG = ProductActivity.class.getSimpleName();

	protected static final int SUCCESS = 0;
	protected static final int FAILE = 1;
	protected static final int REFRESHRATING = 2;
	protected static final int GET_PRODUCTIMAGE = 3;
	private float mRating;

	private ConcernManager mConcernManager = null;

	private String mBarcodeStr = "";
	private byte[] mdata = null;

	private ConcernItem mItem;
	private Product mProduct;

	private TextView mName;
	private TextView mPrice;
	private TextView mBrand;
	private TextView mLocation;
	private TextView mDescription;
	private TextView mBarcode;
	private TextView mCategory;

	private View mProcess;

	private ImageView mPicture;

	private Button btCheckComment;
	private Button btAddToCompare;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productdetail);

		mName = (TextView) findViewById(R.id.tvProductname);
		mPrice = (TextView) findViewById(R.id.tvPrice);
		mBrand = (TextView) findViewById(R.id.tvBrand);
		mLocation = (TextView) findViewById(R.id.tvLocation);
		mDescription = (TextView) findViewById(R.id.tvDescription);
		mBarcode = (TextView) findViewById(R.id.tvBarcode);
		mCategory = (TextView) findViewById(R.id.tvType);
		mPicture = (ImageView) findViewById(R.id.ivPicture);
		btCheckComment = (Button) findViewById(R.id.btCheckComment);
		btAddToCompare = (Button) findViewById(R.id.btAddToCompare);

		btCheckComment.setClickable(false);
		btCheckComment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProductActivity.this,
						CommentListActivity.class);
				intent.putExtra("mItem", mItem);
				startActivity(intent);

			}
		});

		mProcess = this.findViewById(R.id.progress);

		mConcernManager = new ConcernManager(this);
		resolveIntent(getIntent());
	}

	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		resolveIntent(intent);

	}

	void resolveIntent(Intent intent) {
		// Parse the intent
		String action = intent.getAction();
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
			// Get an instance of the TAG from the NfcAdapter
//			Tag productTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//
//			MifareClassic mfc = MifareClassic.get(productTag);
//
//			try {
//				// Conncet to card
//				mfc.connect();
//				boolean auth = false;
//				auth = mfc.authenticateSectorWithKeyA(0,
//						MifareClassic.KEY_DEFAULT);
//
//				if (auth) {
//					byte[] data = mfc.readBlock(1);
//
//				}
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
			DownloadInfo();
			Log.d(TAG, "discover a tag");
		}

	}

	private void DownloadInfo() {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mProduct = WebServiceUtil.getInstance().getProductByBarcode(
						"1234");
				Message message = new Message();
				if (mProduct == null) {
					message.arg1 = FAILE;
				} else {
					message.arg1 = SUCCESS;
					message.obj = mProduct;
					DownloadRating();//下载评分
				}
				handler.sendMessage(message);
			}
		});
		thread.start();
		thread = null;
	}

	private void DownloadRating() {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mRating = WebServiceUtil.getInstance().getAverageRating(
						Integer.valueOf(mProduct.getProperty(1)
								.toString()));
				Message message = new Message();
				message.arg1 = REFRESHRATING;
				message.obj = mRating;
				DownloadPicture();//下载图片
				handler.sendMessage(message);
			}
		});
		thread.start();
		thread = null;
	}

	private void DownloadPicture() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					// int id =
					// Integer.valueOf(mProduct.getProperty(1).toString());
					String URL = WebServiceUtil.ImageURL
							+ mProduct.getProperty(8).toString();
					URL url;
					url = new URL(URL);
					Bitmap bitmap = getProductImage(url);
					Message msg = new Message();
					msg.arg1 = GET_PRODUCTIMAGE;
					msg.obj = bitmap;
					handler.sendMessage(msg);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
				Log.d(TAG, mProduct.toString());
				mBarcode.setText(mProduct.getProperty(3).toString());
				mName.setText(mProduct.getProperty(4).toString());
				mPrice.setText(mProduct.getProperty(5).toString());
				mBrand.setText(mProduct.getProperty(6).toString());
				mLocation.setText(mProduct.getProperty(7).toString());
				mDescription.setText(mProduct.getProperty(9).toString());
				mCategory.setText(((SecCategory) mProduct.getProperty(10))
						.getProperty(3).toString());

				mProcess.setVisibility(View.GONE);
				break;
			case FAILE:
				Log.d(TAG, "Faile");
				btCheckComment.setClickable(false);
				mProcess.setVisibility(View.GONE);
				break;
			case REFRESHRATING:
				btCheckComment.setClickable(true);
				
				break;
			case GET_PRODUCTIMAGE: {
				if (msg.obj != null) {
					mItem = new ConcernItem(
							0,
							Integer.valueOf(mProduct.getProperty(1).toString()),
							mProduct.getProperty(4).toString(),
							Integer.valueOf(mProduct.getProperty(2).toString()),
							((SecCategory) mProduct.getProperty(10))
									.getProperty(3).toString(),
							Float.valueOf(mProduct.getProperty(5).toString()),
							mRating, mProduct.getProperty(6).toString(),
							mProduct.getProperty(7).toString(), mProduct
									.getProperty(3).toString(), mProduct
									.getProperty(9).toString(), System
									.currentTimeMillis(), (short) 0, mdata);
					mConcernManager.addConcernItem(mItem);
					mPicture.setImageBitmap((Bitmap) msg.obj);
				}
			}
				break;
			}
		}

	};

	public Bitmap getProductImage(URL Url) {
		Bitmap bitmap = null;
		try {
			URL url = Url;
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(1000);
			InputStream inputstream = conn.getInputStream();
			mdata = readInputStream(inputstream);
			bitmap = BitmapFactory.decodeByteArray(mdata, 0, mdata.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bitmap;
	}

	public byte[] readInputStream(InputStream inStream) throws Exception {
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
}
