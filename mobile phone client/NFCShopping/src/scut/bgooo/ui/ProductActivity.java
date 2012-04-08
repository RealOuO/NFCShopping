package scut.bgooo.ui;

import java.util.Random;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.db.ConcernManager;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.SecCategory;
import scut.bgooo.webservice.WebServiceUtil;
import android.app.Activity;
import android.content.Intent;

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

	private ConcernManager mConcernManager = null;

	private String mBarcodeStr = "";
	
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

		btCheckComment=(Button)findViewById(R.id.btCheckComment);
		btAddToCompare=(Button)findViewById(R.id.btAddToCompare);
		
		btCheckComment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent=new Intent(ProductActivity.this,CommentListActivity.class);
//				intent.putExtra("product",mProduct);
//				startActivity(intent);
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
//		String action = intent.getAction();
//		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
//			mItem = new ConcernItem(0, Math.abs(new Random().nextInt()),
//					String.valueOf(new Random().nextInt()), 3,
//					Math.abs(new Random().nextFloat()), 4,
//					System.currentTimeMillis(), (short) 0);
//			mConcernManager.addConcernItem(mItem);
//			DownloadInfo();
//			Log.d(TAG, "discover a tag");
//		}
	}

	private void DownloadInfo() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mProduct = WebServiceUtil.getInstance().getProductByBarcode(
						"1234");
				Message message = new Message();
				if (mProduct==null) {
					message.arg1 = FAILE;
				} else {
					message.arg1 = SUCCESS;
					message.obj = mProduct;
				}
				handler.sendMessage(message);
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
				mName.setText(mProduct.getProperty(4).toString());
				mBarcode.setText(mProduct.getProperty(3).toString());
				mDescription.setText(mProduct.getProperty(9).toString());
				mLocation.setText(mProduct.getProperty(7).toString());
				mPrice.setText(mProduct.getProperty(5).toString());
				mBrand.setText(mProduct.getProperty(6).toString());
				mCategory.setText(((SecCategory)mProduct.getProperty(10)).getProperty(3).toString());
				mProcess.setVisibility(View.GONE);
				break;
			case FAILE:
				Log.d(TAG, "Faile");
				btCheckComment.setClickable(false);
				mProcess.setVisibility(View.GONE);
				break;
			}
			
		}

	};
}
