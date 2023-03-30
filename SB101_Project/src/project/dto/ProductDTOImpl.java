package project.dto;

public class ProductDTOImpl implements ProductDTO {
	private int productId;
	private int sellerId;
	private String name;
	private double price;
	private int quantity;
	private int cid;

	public ProductDTOImpl(String name, double price, int quantity, int cid) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.cid = cid;
	}

	public ProductDTOImpl(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

}
