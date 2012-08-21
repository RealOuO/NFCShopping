/*
 * Copyright (C) 2012 The Team of BGOOO
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package scut.bgooo.ui;

import java.util.ArrayList;
import java.util.List;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.utility.TaskHandler;
import scut.bgooo.weibo.WeiboUserItem;
import scut.bgooo.weibo.WeiboUserManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class NFCShoppingTab extends TabActivity implements
		OnCheckedChangeListener {

	private TabHost mHost;
	private Intent mConcernIntent;
	private Intent mMoreIntent;
	private Intent mCompareIntent;
	private Intent mDiscountIntent;
	private Intent mCollectionIntent;

	public static ArrayList<ConcernItem> mItemArray = new ArrayList<ConcernItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

//		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//				.detectDiskReads().detectDiskWrites().detectNetwork() // or
//																		// .detectAll()
//																		// for
//																		// all
//																		// detectable
//																		// problems
//				.penaltyLog().build());
//		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
//				.build());
		setContentView(R.layout.maintab);

		this.mCollectionIntent = new Intent(this, CollectionListActivity.class);
		this.mDiscountIntent = new Intent(this, DiscountListActivity.class);
		this.mCompareIntent = new Intent(this, CompareActivity.class);
		this.mConcernIntent = new Intent(this, ConcernListActivity.class);
		this.mMoreIntent = new Intent(this, MoreActivity.class);

		initRadios();
		setupIntent();
		if (!TaskHandler.getInstance().isRunning()) {
			Log.d("Thread", "start");
			initWeiboDefaultUser();// 找到默认的微博用户
			Thread t = new Thread(TaskHandler.getInstance());
			t.start();
		}
	}

	private void initWeiboDefaultUser() {
		WeiboUserManager datahelp = new WeiboUserManager(this);
		List<WeiboUserItem> userList = datahelp.GetUserList(true);
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).IsDefault()) {
				WeiboUserListActivity.defaultUserInfo = userList.get(i);
			}
		}
	}

	private void initRadios() {
		RadioGroup mainGroup = (RadioGroup) this.findViewById(R.id.main_radio);
		mainGroup.setOnCheckedChangeListener(this);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.d("Thread", "stop");
		TaskHandler.getInstance().stop(); // 停止线程
		super.onDestroy();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		Log.d("radiou group", "you selected=" + checkedId);
		switch (checkedId) {
		case R.id.radio_button0:// 首页
			mHost.setCurrentTabByTag("concern_tab");
			break;
		case R.id.radio_button1:// 信息
			mHost.setCurrentTabByTag("compare_tab");
			break;
		case R.id.radio_button2:// 资料
			mHost.setCurrentTabByTag("collection_tab");
			break;
		case R.id.radio_button3:// 搜索
			mHost.setCurrentTabByTag("discount_tab");
			break;
		case R.id.radio_button4:// 更多
			mHost.setCurrentTabByTag("more_tab");

		}
	}

	private void setupIntent() {
		this.mHost = getTabHost();
		TabHost localTabHost = this.mHost;

		localTabHost.addTab(buildTabSpec("concern_tab", R.string.main_concern,
				R.drawable.icon_1_n, this.mConcernIntent));

		localTabHost.addTab(buildTabSpec("compare_tab",
				R.string.main_comparation, R.drawable.icon_2_n,
				this.mCompareIntent));

		localTabHost.addTab(buildTabSpec("collection_tab",
				R.string.main_collection, R.drawable.collect,
				this.mCollectionIntent));

		localTabHost.addTab(buildTabSpec("discount_tab",
				R.string.main_discount, R.drawable.icon_4_n,
				this.mDiscountIntent));

		localTabHost.addTab(buildTabSpec("more_tab", R.string.main_more,
				R.drawable.icon_5_n, this.mMoreIntent));

	}

	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) {
		return this.mHost
				.newTabSpec(tag)
				.setIndicator(getString(resLabel),
						getResources().getDrawable(resIcon))
				.setContent(content);
	}

}