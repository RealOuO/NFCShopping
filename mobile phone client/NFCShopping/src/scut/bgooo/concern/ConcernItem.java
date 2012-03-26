package scut.bgooo.concern;

public class ConcernItem  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 存放在数据库的ID号
	 * */
	private int Id;
	/**
	 * 从服务器获取到的商品ID号
	 * */
	private int ProductId;
	/**
	 * 从服务器获取到的商品名Name
	 * */
	private String Name;
	/**
	 * 从服务器获取到的商品类型Type
	 * */
	private int Type;
	/**
	 * 从服务器获取到的商品价格
	 * */
	private float Price;
	/**
	 * 从服务器获取到的商品的评分
	 * */
	private int Rating;
	/**
	 * 记录添加的时间
	 * */
	private long Timestamp;
	/**
	 * 标记改记录是否被收藏
	 * */
	private short IsCollected;

	/**
	 * 这个是从数据库获取item的构造函数，要传入id
	 * */
	public ConcernItem(int id, int productId, String name, int type,
			float price, int rating, long timestamp, short iscollected) {
		Id = id;
		ProductId = productId;
		Name = name;
		Type = type;
		Rating = rating;
		Price = price;
		Timestamp = timestamp;
		IsCollected = iscollected;
	}
	
	public ConcernItem(long timestamp){
		Id = 0;
		Timestamp = timestamp;
	}

	public int getId() {
		return Id;
	}

	public int getProductId() {
		return ProductId;
	}

	public String getName() {
		return Name;
	}

	public int getType() {
		return Type;
	}

	public int getRating() {
		return Rating;
	}

	public float getPrice() {
		return Price;
	}

	public long getTimestamp() {
		return Timestamp;
	}

	public short getIsCollected() {
		return IsCollected;
	}
	
	public void setIsCollected(short collected){
		IsCollected=collected;
	}
}
