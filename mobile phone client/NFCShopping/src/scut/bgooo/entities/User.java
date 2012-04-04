package scut.bgooo.entities;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * 2012年4月1日 调试成功的类
 */
public class User implements KvmSerializable ,Serializable{

	
	private static final long serialVersionUID = 1L;
	private String EntityKey;
	private int Id;
	private String Username;
	// private String Password; 注意不要返回用户密码到手机端，因为泄露了用户的信息
	private int Gender;
	private int VisitedTimes;
	private String LastVisitedDate;

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		Object res = null;
		switch (arg0) {
		case 0:
			res = this.EntityKey;
			break;
		case 1:
			res = this.Id;
			break;
		case 2:
			res = this.Username;
			break;
		case 3:
			res = this.Gender;
			break;
		case 4:
			res = this.VisitedTimes;
			break;
		case 5:
			res = this.LastVisitedDate;
			break;
		
		default:
			break;
		}
		return res;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "EntityKey";
			break;
		case 1:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "userID";
			break;
		case 2:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userName";
			break;
		case 3:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "gender";
			break;
		case 4:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "visitedTimes";
			break;
		case 5:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "lastVisitedDate";
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
			this.EntityKey = arg1.toString();
			break;
		case 1:
			this.Id = Integer.valueOf(arg1.toString());
			break;
		case 2:
			this.Username = arg1.toString();
			break;
		case 3:
			this.Gender = Integer.valueOf(arg1.toString());
			break;
		case 4:
			this.VisitedTimes = Integer.valueOf(arg1.toString());
			break;
		case 5:
			this.LastVisitedDate = arg1.toString();
			break;		
		default:
			break;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.EntityKey + "\n" + this.Id + "\n" + this.Username + "\n"
				+ "\n" + this.Gender + "\n" + this.VisitedTimes + "\n"
				+ this.LastVisitedDate + "\n";
	}

}
