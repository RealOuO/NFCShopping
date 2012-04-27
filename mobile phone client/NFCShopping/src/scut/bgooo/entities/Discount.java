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
import java.util.Date;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * 
 * 优惠实体类，对应着优惠信息,对应的是一期的优惠
 * 
 * @author Leeforall
 * @since 2012年3月16日
 */
public class Discount implements KvmSerializable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id; // 优惠ID，就是对应的优惠列表的id
	private String EntityKey;
	private String Description;//优惠描述
	private String StartDate;
	private String EndDate;

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
			res = this.StartDate;
			break;
		case 4:
			res = this.EndDate;
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
			arg2.name = "startDate";
			break;
		case 4:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "endDate";
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
			this.StartDate=arg1.toString();
			break;
		case 4:
			this.EndDate=arg1.toString();
			break;
		default:
			break;
		}
	}

}
