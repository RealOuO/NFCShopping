package scut.bgooo.entities;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * 
 * 二级目录类
 * 
 * 2012年4月1日21:37:21 调试成功
 * */
public class SecCategory implements KvmSerializable {

	private int SecCategoryID;
	private int CategoryID;
	private String SecCategoryName;
	private String EntityKey;

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		Object res = null;
		switch (arg0) {
		case 0:
			res = this.SecCategoryID;
			break;
		case 1:
			res = this.CategoryID;
			break;
		case 2:
			res = this.SecCategoryName;
			break;
		case 3:
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
		return 4;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "secCategoryID";
			break;
		case 1:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "categoryID";
			break;
		case 2:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "secCategoryName";
			break;
		case 3:
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
			this.SecCategoryID = Integer.valueOf(arg1.toString());
			break;
		case 1:
			this.CategoryID = Integer.valueOf(arg1.toString());
			break;
		case 2:
			this.SecCategoryName = arg1.toString();
			break;
		case 3:
			this.EntityKey=arg1.toString();
			break;
		default:
			break;
		}
	}

}
