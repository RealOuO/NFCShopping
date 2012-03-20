package scut.bgooo.entities;

public class Comment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Id;
	private String Text;
	private Product Product;
	private int Rating;
	private long CreatedAt;

	public Comment(int id, String text, Product product, int rating,
			long createAt) {
		Id = id;
		Text = text;
		Product = product;
		Rating = rating;
		CreatedAt = createAt;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product product) {
		Product = product;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public long getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(long createdAt) {
		CreatedAt = createdAt;
	}

}
