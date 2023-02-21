package dto;

public class BuyProductDto extends ProductDto {
	
	public BuyProductDto(ProductDto product){
		super.setProductId(product.getProductId());
		super.setProductName(product.getProductName());
		super.setPrice(product.getPrice());
		super.setStock(product.getStock());
		super.setMaker(product.getMaker());
		super.setType(product.getType());
		super.setSalesPoint(product.getSalesPoint());
	}
	
	public BuyProductDto() {
		
	}
	
	private Integer quantity;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
