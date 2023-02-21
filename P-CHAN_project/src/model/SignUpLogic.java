package model;

import dao.MemberDao;
import dto.MemberDto;

public class SignUpLogic {
	public boolean signUp(MemberDto member) {
		MemberDao mDao = new MemberDao();
		
		String memberId = generateMemberId();
		member.setMemberId(memberId);
		int result = mDao.signUp(member);
		if(result >= 1) {
			return true;
		}
		return false;
	}
	
	public String generateMemberId() {
		MemberDao mDao = new MemberDao();
		String lastId = mDao.memberIdGen();
		System.out.println(lastId);
		String memberId = "";
		
		String strNum = lastId.substring(lastId.length() - 4);
		int num = Integer.parseInt(strNum);
		num++;
		
		if(num > 9999) {
			return null;
		}
		
		strNum = Integer.toString(num);
		int valLen = String.valueOf( strNum ).length();
		if(valLen == 1) {
			memberId = "P000" + strNum;
		}
		if(valLen == 2) {
			memberId = "P00" + strNum;
		}
		if(valLen == 3) {
			memberId = "P0" + strNum;
		}
		if(valLen == 4) {
			memberId = "P" + strNum;
		}
		
		return memberId;
	}

}
