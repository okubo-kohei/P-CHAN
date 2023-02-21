package model;

import dao.MemberDao;

public class DeleteLogic {
	public boolean delete(String memberId) {
		MemberDao memDao = new MemberDao();
		System.out.println(memberId);
		int num = memDao.memberDel(memberId);
		
		if(num == 1) {
			return true;
		}
		return false;
	}
}
