package scut.bgooo.updater.ui;

import java.io.IOException;

import scut.bgooo.dataobject.mifare.MifareSector;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NFCWriterActivity extends Activity {

	private final static String TAG = NFCWriterActivity.class.getName();

	// NFC parts
	private static NfcAdapter mAdapter;
	private static PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;

	private Button btCommit;
	private Button btCancel;
	private TextView tvTitle;
	private TextView tvType;
	private TextView tvBarcode;
	private CheckBox cbIsConnecting;

	private MifareClassic mfc = null;
	private String barCode = "";
	private String barCodeType = "";
	private int mCount = 0;

	/**
	 * android.os.Handler是Android SDK中处理定时操作的核心类。
	 * 通过Handler类，可以提交和处理一个Runnable对象。 这个对象的run 方法可以立刻执行，也可以在指定时间之后执行（可以称为预约执行
	 * */
	Handler handler = new Handler();
	/**
	 * 监听是否跟卡片取得连接的线程对象
	 * */
	private Runnable checkRunnable;

	class RunCheck implements Runnable {

		// 获取Activity对象
		NFCWriterActivity activity;

		public RunCheck(NFCWriterActivity activity) {
			this.activity = activity;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			activity.handleCheck();
		}
	}

	/**
	 * 
	 * UI处理标签连接的方法
	 * 
	 * */
	protected void handleCheck() {
		if (mfc.isConnected()) {
			Log.d("fuck", "连接标签");
			cbIsConnecting.setChecked(true);
			cbIsConnecting.setText("取得连接");
			handler.postDelayed(checkRunnable, 2000);// 相当于线程的循环，如果线程不停止则每2秒执行一次
		} else {
			// 失去连接就停止线程
			Log.d("fuck", "失去连接");
			cbIsConnecting.setChecked(false);
			cbIsConnecting.setText("失去连接");
			handler.removeCallbacks(checkRunnable);// 停止线程
		}
	}

	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writer);
		mAdapter = NfcAdapter.getDefaultAdapter(this);
		Intent intent = getIntent();
		barCode = intent.getStringExtra("Barcode");
		barCodeType = intent.getStringExtra("Type");
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

		btCommit = (Button) findViewById(R.id.btCommit);
		btCancel = (Button) findViewById(R.id.btCancel);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvBarcode = (TextView) findViewById(R.id.tvBarcode);
		tvBarcode.setText(barCode);
		tvType = (TextView) findViewById(R.id.tvType);
		tvType.setText(barCodeType);
		cbIsConnecting = (CheckBox) findViewById(R.id.cbIsConnecting);
		cbIsConnecting.setClickable(false);

		btCommit.setOnClickListener(onclickListener);

		Log.d(TAG, "Create");
		checkRunnable = new RunCheck(this);
	}

	private OnClickListener onclickListener = new OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (cbIsConnecting.isChecked()) {
				if (writeContentToCard(tvBarcode.getText().toString())) {
					finish();
					Toast.makeText(NFCWriterActivity.this, "写入成功", 1000).show();
				} else {
					Toast.makeText(NFCWriterActivity.this, "写卡过程出错，\n写入失败",
							1000).show();
				}
			} else {
				Toast.makeText(NFCWriterActivity.this, "失去连接，\n写入失败", 1000)
						.show();
			}

		}
	};

	@Override
	public void onResume() {
		super.onResume();
		mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
				mTechLists);
		// 响铃和震动的配置
		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	public void onNewIntent(Intent intent) {
		Log.d(TAG, "Discovered tag with intent: " + intent);
		tvTitle.setText("扫描到第" + ++mCount + "标签");
		resolveIntent(intent);
		// 执行监听线程
		handler.post(checkRunnable);
	}

	@Override
	public void onPause() {
		mAdapter.disableForegroundDispatch(this);
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		handler.removeCallbacks(checkRunnable);// 停止线程
		super.onDestroy();
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

			try {
				mfc.connect();
				Log.d("fuck", "连接标签");
				cbIsConnecting.setChecked(true);
				cbIsConnecting.setText("取得连接");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d("fuck", "连接出错");
				cbIsConnecting.setChecked(false);
				cbIsConnecting.setText("失去连接");
				e.printStackTrace();
			}
			// 响铃和震动
			playBeepSoundAndVibrate();
		}
	}

	private void initBeepSound() {
		// TODO Auto-generated method stub
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

	private boolean writeContentToCard(String content) {

		try {
			if (mfc.equals(null)) {
				Log.d(TAG, "没有mfc的实例");
				return false;
			} else {
				// 5.1) Connect to card
			}
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
						if (i > contentToBytes.length) {
							toBeWrited[i] = (byte) '\0';
						} else {
							toBeWrited[i] = contentToBytes[i - 1];
						}
					}
				}
				mfc.writeBlock(bIndex + 1, toBeWrited);
				mfc.close();
				return true;
			}
		} catch (IOException e) {
			Log.d("fuck", "读写出错，抛出异常");
			cbIsConnecting.setChecked(false);
			cbIsConnecting.setText("失去连接");
		}
		return false;
	}

}
