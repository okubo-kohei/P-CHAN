package dto;

public class ProductDto {
	private String productId;
	private String productName;
	private int price;
	private int stock;
	private String maker;
	private String type;
	private String salesPoint;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSalesPoint() {
		return salesPoint;
	}
	public void setSalesPoint(String salesPoint) {
		this.salesPoint = salesPoint;
	}
	
	
}
