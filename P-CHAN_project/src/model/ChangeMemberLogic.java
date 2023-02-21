package model;

import dao.MemberDao;
import dto.MemberDto;

public class ChangeMemberLogic {
	public boolean changeMember(MemberDto member) {
		MemberDao memDao = new MemberDao();
		int num = memDao.updateMember(member);
		
		if(num == 1) {
			return true;
		}
		return false;
	}

}
