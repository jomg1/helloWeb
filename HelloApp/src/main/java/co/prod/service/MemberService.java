package co.prod.service;

import java.util.List;

import co.prod.vo.EmpVO;
import co.prod.vo.MemberVO;
import co.prod.vo.MembersVO;


public interface MemberService {
	//회원목록
	public List<MemberVO> getMembers();
	//회원 등록
	public boolean addMember(MemberVO vo);
	//회원 조회
	public MemberVO getMember(String id);
	//회원 정보 수정
	public boolean modifyMember(MemberVO vo);
	//회원 정보 삭제
	public boolean removeMember(String id);
	// id/pw 로그인 처리
	public MemberVO login(MemberVO vo);
	
	//Members 목록
	public List<MembersVO> membersList();
	//Members 등록
	public boolean addMembers(MembersVO vo);
	//Members 다중 삭제
	public boolean removeMembers(List<MembersVO> list);
	//다중삭제
	public boolean removeMembersAry(String[] users);
	
	//사원목록
	public List<EmpVO> emplyeeList();
	//사원추가
	public boolean addEmployee(EmpVO vo);
	//사원 삭제
	public boolean removeEmployee(int id);
}
