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
package scut.bgooo.entities;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

/**
 * 商品信息实体类
 * 
 * 2012年4月1日 调试成功的类
 * 
 * 
 * */
public class Product implements KvmSerializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EntityKey;
	private int Id; // 商品id
	private String Barcode; // 商品的条形码编号
	private String Name; // 商品名
	private String Price; // 商品价格
	private int SecCategoryID;
	private String Brand;
	private String Location;
	private String ImpageURL;
	private String Description;

	private SecCategory SecCategory;

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
			res = this.SecCategoryID;
			break;
		case 3:
			res = this.Barcode;
			break;
		case 4:
			res = this.Name;
			break;
		case 5:
			res = this.Price;
			break;
		case 6:
			res = this.Brand;
			break;
		case 7:
			res = this.Location;
			break;
		case 8:
			res = this.ImpageURL;
			break;
		case 9:
			res = this.Description;
			break;
		case 10:
			res = this.SecCategory;
			break;
		default:
			break;
		}
		return res;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 11;
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
			arg2.name = "productID";
			break;
		case 2:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "secCategoryID";
			break;
		case 3:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "barCode";
			break;
		case 4:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "productName";
			break;
		case 5:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "price";
			break;
		case 6:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "brand";
			break;
		case 7:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "location";
			break;
		case 8:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "imageURL";
			break;
		case 9:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "description";
			break;
		case 10:
			arg2.type = PropertyInfo.OBJECT_CLASS;
			arg2.name = "SecCategory";
		default:
			break;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.EntityKey + "\n" + this.Id + "\n" + this.SecCategoryID
				+ "\n" + this.Location + "\n" + this.Barcode + "\n"
				+ this.Brand + "\n" + this.Description+this.SecCategory.toString();
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
			this.SecCategoryID = Integer.valueOf(arg1.toString());
			break;
		case 3:
			this.Barcode = arg1.toString();
			break;
		case 4:
			this.Name = arg1.toString();
			break;
		case 5:
			this.Price = arg1.toString();
			break;
		case 6:
			this.Brand = arg1.toString();
			break;
		case 7:
			this.Location = arg1.toString();
			break;
		case 8:
			this.ImpageURL = arg1.toString();
			break;
		case 9:
			this.Description = arg1.toString();
			break;
		case 10:
			this.SecCategory = (SecCategory)arg1;
			break;
		default:
			break;
		}
	}
}
