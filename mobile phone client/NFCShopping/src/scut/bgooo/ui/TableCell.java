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

public class TableCell {

	static public final int STRING = 0;
	static public final int IMAGE = 1;
	static public final int RATING=2;
	public Object value;
	public int width;
	public int height;
	public int type;

	public TableCell(Object value, int width, int height, int type) {
		this.value = value;
		this.width = width;
		this.height = height;
		this.type = type;
	}

}
