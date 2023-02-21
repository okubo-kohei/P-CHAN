package model;

import dao.MemberDao;

public class ChangePassLogic {
	public boolean changePass(String memberId, String newPass) {
		Crypto cpt = new Crypto();
		String hashNewPass = cpt.sha256(newPass);
		
		MemberDao memDto = new MemberDao();
		int num = memDto.updatePass(memberId, hashNewPass);
		
		if(num == 1) {
			return true;
		}
		return false;
	}
}
