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

public class Paging implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Page;
	private int Count;

	public Paging(int page, int count) {
		Page = page;
		Count = count;
	}

	public void setPage(int page) {
		if (page < 1) {
			throw new IllegalArgumentException(
					"page should be positive integer. passed:" + page);
		}
		Page = page;
	}

	public int getPage() {
		return Page;
	}

	public void setCount(int count) {
		if (count < 1) {
			throw new IllegalArgumentException(
					"page should be positive integer. passed:" + count);
		}
		Count = count;
	}

	public int getCount() {
		return Count;
	}
}
