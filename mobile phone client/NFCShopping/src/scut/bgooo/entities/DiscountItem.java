package scut.bgooo.entities;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

/**
 * 对应的是每一期里面，每一项的discount
 * 
 * @author 肥哥
 *
 */
public class DiscountItem implements KvmSerializable ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Id;
	private int ProductId;
	private int DiscountId;
	private float Percent;
	private String Description;
	private String EntityKey;

	public Product Product;

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
			res = this.DiscountId;
			break;
		case 3:
			res = this.ProductId;
			break;
		case 4:
			res = this.Percent;
			break;
		case 5:
			res = this.Description;
			break;
		case 6:
			res = this.Product;
			break;
		default:
			break;
		}
		return res;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 7;
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
			arg2.name = "id";
			break;
		case 2:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "discountID";
			break;
		case 3:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "productID";
			break;
		case 4:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "discountPercent";
			break;
		case 5:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "description";
			break;
		case 6:
			arg2.type = PropertyInfo.OBJECT_CLASS;
			arg2.name = "Product";
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
			this.DiscountId = Integer.valueOf(arg1.toString());
			break;
		case 3:
			this.ProductId = Integer.valueOf(arg1.toString());
			break;
		case 4:
			this.Percent = Float.valueOf(arg1.toString());
			break;
		case 5:
			this.Description = arg1.toString();
			break;
		case 6:
			this.Product = (Product) arg1;
			break;
		default:
			break;
		}
	}

}
