package model;

import java.util.ArrayList;

import dao.ProductDao;
import dto.ProductDto;

public class SearchLogic {
	public ArrayList<ProductDto> listSearch(String word) {
		
		ProductDao gsDto = new ProductDao();
		ArrayList<ProductDto> List = new ArrayList<ProductDto>();
		List = gsDto.searchProduct(word);
		
		return List;
	}
	
	public ProductDto search(String productId) {
		ProductDto pd = new ProductDto();
		ProductDao pDto = new ProductDao();
		pd = pDto.searchDetail(productId);
		return pd;
	}
}
