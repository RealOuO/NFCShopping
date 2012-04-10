package scut.bgooo.ui;

import java.util.ArrayList;
import java.util.List;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.entities.User;
import scut.bgooo.utility.TaskHandler;
import scut.bgooo.weibo.WeiboUserItem;
import scut.bgooo.weibo.WeiboUserManager;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

/**
 * 这是本系统的TAB的启动类
 * 
 * @author 肥哥
 * 
 */
public class MainActivity extends TabActivity {

	public View msgTitle;// 信息头部按钮
	private TabHost mTabHost;// 载体tabhost	
	private TaskHandler mTaskHandler = new TaskHandler();
	public static ArrayList<ConcernItem> mItemArray=new ArrayList<ConcernItem>();
	public static User mNowUser=null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintabs);
		_InitTabs();
		initWeiboDefaultUser();
		Thread t = new Thread(mTaskHandler);
		t.start();
	}

	/**
	 * 初始化tabs
	 */
	private void _InitTabs() {

		mTabHost = getTabHost();
		// LayoutInflater.from(this).inflate(R.layout.maintabs,
		// mTabHost.getTabContentView(), true);
		mTabHost.addTab(mTabHost.newTabSpec("TAB_CONCERNS").setIndicator("关注")
				.setContent(new Intent(this, ConcernListActivity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("TAB_COMPARE").setIndicator("对比")
				.setContent(new Intent(this, CompareActivity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("TAB_COLLECT").setIndicator("收藏")
				.setContent(new Intent(this, CollectionListActivity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("TAB_DISCOUNT").setIndicator("优惠")
				.setContent(new Intent(this, DiscountListActivity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("TAB_MORE").setIndicator("更多")
				.setContent(new Intent(this, MoreActivity.class)));
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
}