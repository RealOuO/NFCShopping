package scut.bgooo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * 
 * 优惠实体类，对应着优惠信息
 * 
 * @author Leeforall
 * @since 2012年3月16日
 */
public class Discount implements KvmSerializable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id; // 优惠ID
	private String EntityKey;
	private String Description;
	private String CreatedAt;

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
			res = this.Description;
			break;
		case 3:
			res = this.CreatedAt;
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
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "EntityKey";
			break;
		case 1:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "discountID";
			break;
		case 2:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "description";
			break;
		case 3:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "createdAt";
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
			this.EntityKey=arg1.toString();
			break;
		case 1:
			this.Id=Integer.valueOf(arg1.toString());
			break;
		case 2:
			this.Description=arg1.toString();
			break;
		case 3:
			this.CreatedAt=arg1.toString();
			break;
		default:
			break;
		}
	}

}
