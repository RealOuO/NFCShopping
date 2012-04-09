package scut.bgooo.ui;

public class TableCell {

	static public final int STRING = 0;
	static public final int IMAGE = 1;
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
