package scut.bgooo.updater.ui;

import java.io.IOException;

import scut.bgooo.dataobject.mifare.MifareSector;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NFCWriterActivity extends Activity {

	// NFC parts
	private static NfcAdapter mAdapter;
	private static PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;

	private EditText et;
	private Button bt;
	private TextView tv;

	MifareClassic mfc;

	private int mCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writer);

		mAdapter = NfcAdapter.getDefaultAdapter(this);
		// Create a generic PendingIntent that will be deliver to this activity.
		// The NFC stack
		// will fill in the intent with the details of the discovered tag before
		// delivering to
		// this activity.
		mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		// Setup an intent filter for all MIME based dispatches
		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

		try {
			ndef.addDataType("*/*");
		} catch (MalformedMimeTypeException e) {
			throw new RuntimeException("fail", e);
		}
		mFilters = new IntentFilter[] { ndef, };

		// Setup a tech list for all NfcF tags
		mTechLists = new String[][] { new String[] { MifareClassic.class
				.getName() } };

		et = (EditText) findViewById(R.id.etContent);
		bt = (Button) findViewById(R.id.btOk);
		tv = (TextView) findViewById(R.id.tvTitle);
		bt.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(et.getText().toString().length()!=0){
					if(writeContentToCard(et.getText().toString())){
						Toast.makeText(NFCWriterActivity.this, "写入成功", 1000).show();
					}else{
						Toast.makeText(NFCWriterActivity.this, "写入失败", 1000).show();
					}
				}else{
					Toast.makeText(NFCWriterActivity.this, "写入失败", 1000).show();
				}

			}
		});
	}

	void resolveIntent(Intent intent) {
		// 1) Parse the intent and get the action that triggered this intent
		String action = intent.getAction();
		// 2) Check if it was triggered by a tag discovered interruption.
		if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
			// 3) Get an instance of the TAG from the NfcAdapter
			Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			// 4) Get an instance of the Mifare classic card from this TAG
			// intent
			mfc = MifareClassic.get(tagFromIntent);
			Log.d("ISNULL", mfc.toString());

		}
	}

	private boolean writeContentToCard(String content) {

		try { // 5.1) Connect to card
			if(mfc.equals(null)){
				return false;
			}
			mfc.connect();
			boolean auth = false;
			// 5.2) and get the number of sectors this card has..and loop
			// thru these sectors
			int secCount = mfc.getSectorCount();
			int bCount = 0;
			int bIndex = 0;
			auth = mfc.authenticateSectorWithKeyA(0, MifareClassic.KEY_DEFAULT);
			if (auth) {
				// 6.2) In each sector - get the block count
				bCount = mfc.getBlockCountInSector(0);
				bCount = Math.min(bCount, MifareSector.BLOCKCOUNT);
				bIndex = mfc.sectorToBlock(0);
				Log.d("index", bIndex + "");
				byte[] toBeWrited = new byte[16];
				byte[] contentToBytes = content.getBytes();
				Integer tlength = contentToBytes.length;
				byte length = tlength.byteValue();
				toBeWrited[0] = length;
				if (contentToBytes.length <= toBeWrited.length - 1) {
					for (int i = 1; i < toBeWrited.length; i++) {
						if (i  > contentToBytes.length) {
							toBeWrited[i] = (byte) '\0';
						} else {
							toBeWrited[i] = contentToBytes[i - 1];
						}
					}
				}
				mfc.writeBlock(bIndex+1, toBeWrited);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public void onResume() {
		super.onResume();
		mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
				mTechLists);
	}

	@Override
	public void onNewIntent(Intent intent) {
		Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
		tv.setText("Discovered tag " + ++mCount + " with intent: " + intent);
		resolveIntent(intent);
	}

	@Override
	public void onPause() {
		super.onPause();
		mAdapter.disableForegroundDispatch(this);
	}

}
