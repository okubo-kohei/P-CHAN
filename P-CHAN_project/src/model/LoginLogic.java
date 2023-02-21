package model;

import dao.MemberDao;

public class LoginLogic {
	public String login(String loginId, String inPass, String challenge) {
		Crypto cpt = new Crypto();
		MemberDao mDao = new MemberDao();

		String[] list = mDao.login(loginId);//メアドか電話番号をもとにパスワードを取得（登録時にハッシュ化済み）
		if(list != null) {
			String memberId = list[0];
			String dbpass = list[1];
			String dbHashPass = cpt.sha256(dbpass + challenge);
			
			if(inPass.equals(dbHashPass)) {
				return memberId;
			}
		}
		return null;
	}
	
	public boolean loginChange(String memberId, String inPass, String challenge) {
		Crypto cpt = new Crypto();
		MemberDao mDao = new MemberDao();

		String dbpass = mDao.loginChange(memberId);//メアドか電話番号をもとにパスワードを取得（登録時にハッシュ化済み）
		String dbHashPass = cpt.sha256(dbpass + challenge);
		
		if(inPass.equals(dbHashPass)) {
			return true;
		}
		return false;
	}
}
