package scut.bgooo.entities;

/**
 * 商品信息实体类
 * */
public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;    //商品id
	private String barcode; // 商品的条形码编号
	private String Name;   //商品名
	private int AverageRating;   //商品平均得分
	private float Price;  //商品价格

	public int getId() {
		return Id;
	}

	public String getName() {
		return Name;
	}

	public int getAverageRating() {
		return AverageRating;
	}

	public float getPrice() {
		return Price;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcode() {
		return barcode;
	}
}
