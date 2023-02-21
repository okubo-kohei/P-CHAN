package model;

import java.util.ArrayList;

import dao.ProductDao;
import dto.BuyProductDto;

public class BuyLogic {
	public boolean buy(ArrayList<BuyProductDto> buyList) {
		
		ProductDao pDao = new ProductDao();
		boolean buyCheck = pDao.buy(buyList);
		
		return buyCheck;
	}
}
