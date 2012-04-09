package scut.bgooo.concern;

public class ConcernItem implements java.io.Serializable {

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
	private float Rating;

	private String Location;

	private String Brand;

	private String SecCategory; // 暂时还没存数据库

	private String Barcode;

	private String Description;
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
			String seccategory, float price, float rating, String brand,
			String location, String barcode, String description,
			long timestamp, short iscollected) {
		Id = id;
		ProductId = productId;
		Name = name;
		Type = type;
		SecCategory = seccategory;
		Rating = rating;
		Price = price;
		setBarcode(barcode);
		setBrand(brand);
		setLocation(location);
		setDescription(description);
		Timestamp = timestamp;
		IsCollected = iscollected;
	}

	public ConcernItem(long timestamp) {
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

	public float getRating() {
		return Rating;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getBrand() {
		return Brand;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getLocation() {
		return Location;
	}

	public void setBarcode(String barcode) {
		Barcode = barcode;
	}

	public String getBarcode() {
		return Barcode;
	}

	public void setSecCategory(String secCategory) {
		SecCategory = secCategory;
	}

	public String getSecCategory() {
		return SecCategory;
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

	public void setIsCollected(short collected) {
		IsCollected = collected;
	}

	public static int getCount() {
		return 7;
	}

	public Object getAttribute(int index) {
		Object temp = null;
		switch (index) {
		case 0:
			temp = getName();
			break;
		case 1:
			temp = getBrand();
			break;
		case 2:
			temp = getPrice();
			break;
		case 3:
			temp = getSecCategory();
			break;
		case 4:
			temp = getLocation();
			break;
		case 5:
			temp = getDescription();
			break;
		case 6:
			temp = getRating();
			break;
		default:
			break;
		}
		return temp;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDescription() {
		return Description;
	}
}
