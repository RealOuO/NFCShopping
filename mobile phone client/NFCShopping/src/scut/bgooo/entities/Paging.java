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
