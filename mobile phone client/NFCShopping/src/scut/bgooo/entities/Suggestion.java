package scut.bgooo.entities;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Suggestion implements KvmSerializable ,Serializable{

	private int Id; //反馈的id
	private int UserId; // 反馈的用户id
	private String Text;// 反馈的内容
	private String CreatedAt;// 创建时间
	private String EntityKey;// 主键

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		Object res = null;
		switch (arg0) {
		case 0:
			res = this.Id;
			break;
		case 1:
			res = this.UserId;
			break;
		case 2:
			res = this.Text;
			break;
		case 3:
			res = this.CreatedAt;
			break;
		case 4:
			res = this.EntityKey;
			break;
		default:
			break;
		}
		return res;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "id";
			break;
		case 1:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userID";
			break;
		case 2:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "description";
			break;
		case 3:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "date";
			break;
		case 4:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "EntityKey";
			break;
		default:
			break;
		}
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg1 == null)
			return;
		switch (arg0) {
		case 0:
			this.Id = Integer.valueOf(arg1.toString());
			break;
		case 1:
			this.UserId = Integer.valueOf(arg1.toString());
			break;
		case 2:
			this.Text = arg1.toString();
			break;
		case 3:
			this.CreatedAt = arg1.toString();
			break;
		case 4:
			this.EntityKey = arg1.toString();
			break;
		default:
			break;
		}
	}

}
