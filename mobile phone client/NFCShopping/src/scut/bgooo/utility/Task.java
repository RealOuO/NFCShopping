package scut.bgooo.utility;

import java.util.Map;

public class Task {

	private int mTaskId ;//任务的ID
	private Map taskParam;// 任务参数
	public static final int GET_USER_INFORMATION = 1;//注册完后获得用户的信息	
	public static final int SEND_COMMENT_WEIBO = 2;//完成评论后，发送评论信息
	public static final int GET_DISCOUNT = 3;//获得商品优惠列表
	public static final int GET_DISCOUNTITEM = 4;//获得具体某期的商品优惠条目
	
	public Task(int id,  Map param) {
		 mTaskId = id;
		 taskParam = param;
	}

	public int getTaskID() {
		return mTaskId;
	}

	public void setTaskID(int taskID) {
		 mTaskId = taskID;
	}

	public Map getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(Map taskParam) {
		taskParam = taskParam;
	}
}
