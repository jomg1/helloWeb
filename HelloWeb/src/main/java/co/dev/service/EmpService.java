package co.dev.service;

import java.util.List;

import co.dev.vo.EmpVO;
import co.dev.vo.MemberVO;

public interface EmpService {
	//회원 목록
	public List<EmpVO> getEmp();
	
}
