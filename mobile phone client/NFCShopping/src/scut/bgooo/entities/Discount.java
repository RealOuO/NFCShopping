package scut.bgooo.entities;

import java.util.Date;

/**
 * 
 * 优惠实体类，对应着优惠信息
 * 
 * @author Leeforall
 * @since 2012年3月16日
 */
public class Discount implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id; // 优惠ID
	private Product Product; // 商品实体
	private String Description; // 描述
	private Date StartDate; // 起始日期
	private Date EndDate; // 结束日期
	private float Discount; // 折扣
	private String ImageUrl; // 图片路径

	public Date getStartDate() {
		return StartDate;
	}

	public int getId() {
		return Id;
	}

	public Product getProduct() {
		return Product;
	}

	public String getDescription() {
		return Description;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public float getDiscount() {
		return Discount;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

}
