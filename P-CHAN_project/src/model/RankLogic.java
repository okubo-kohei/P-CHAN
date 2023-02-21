package model;

import java.util.ArrayList;

import dao.MemberDao;
import dao.RankDao;
import dto.BuyProductDto;
import dto.MemberDto;

public class RankLogic {
	public boolean rankUpdate(ArrayList<BuyProductDto> buyList, String memberId, int point) {
		int sum = 0;
		int loop = buyList.size();
		
		for(int i = 0; i < loop; i++) {
			BuyProductDto buy = buyList.get(i);
			
			int price = buy.getPrice();
			int quantity = buy.getQuantity();
			
			sum += price * quantity;
		}
		
		//MemberDaoから累計ポイント取る
		MemberDao mDao = new MemberDao();
		MemberDto member = mDao.searchDetail(memberId);
		
		if(member == null) {
			return false;
		}
		sum = sum / 100;
		int cumPoint = member.getCumulativePoint();
		int culPoint = member.getCurrentPoint();
		cumPoint += sum;
		culPoint += sum - point;
		
		//RankDaoに購入金額を足したポイント渡す
		RankDao rDao = new RankDao();
		int rank = rDao.searchRank(cumPoint);
		
		if(rank < 0) {
			return false;
		}
		
		//帰ってきたランクと購入金額をMemberDaoに渡し、更新（累計、現在ポイントの2種）
		member.setCumulativePoint(cumPoint);
		member.setCurrentPoint(culPoint);
		member.setRank(rank);
		
		mDao.updateMember(member);
		
		return true;
	}
	
	public int dicountSearch(int rank) {
		int discount;
		
		RankDao rDao = new RankDao();
		discount = rDao.searchDiscount(rank);
		
		return discount;
	}
}
