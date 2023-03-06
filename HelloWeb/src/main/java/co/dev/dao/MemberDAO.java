package co.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.dev.common.DAO;
import co.dev.vo.MemberVO;

public class MemberDAO extends DAO{
	
	//회원삭제
	public int deleteMember(String id) {
		getConn();
		String sql = "delete from members where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			//delete call
			int r = psmt.executeUpdate();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConn();
		}
		return 0;
		
	}
	
	//회원수정
	public int updateMember(MemberVO vo) {
		getConn();
		String sql = "update members\r\n"
				+ "set name =?,\r\n"
				+ "    passwd = ?,\r\n"
				+ "    mail = ?\r\n"
				+ "where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getPasswd());
			psmt.setString(3, vo.getMail());
			psmt.setString(4, vo.getId());
			//update call
			int r = psmt.executeUpdate(); //쿼리에 처리된 건수를 반환.
			return r;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConn();
		}
		return 0; //변경된 건수 0
	}
	
	//회원조회
	public MemberVO selectMember(String id) {
		getConn();
		String sql = "select * from members where id=?";		
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				rs = psmt.executeQuery(); //id:Key
				
				if(rs.next()) {
					MemberVO vo = new MemberVO();
					vo.setId(id);
					vo.setPasswd(rs.getString("passwd"));
					vo.setName(rs.getString("name"));
					vo.setMail(rs.getString("mail"));
					
					return vo;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disConn();
			}
			return null;
		
	}
	
	//회원 등록
	public int insertMember(MemberVO vo) {
		getConn();
		String sql = "insert into members (id, name, passwd, mail)\r\n"
				+ "values(?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPasswd());
			psmt.setString(4, vo.getMail());
			int r = psmt.executeUpdate(); //쿼리에 처리된 건수를 반환.
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disConn();
		}
		return 0; //처리가 되지 않음을 의미
	}

	// 회원목록
	
	public List<MemberVO> memberList(){
		//반환은 컬렉션 타입 (MemberVO)
		List<MemberVO> memberList = new ArrayList<>();
		getConn();
		String sql = "select * from members order by id";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setMail(rs.getString("mail"));
				
				memberList.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConn();
		}
		
		return memberList;
	}
}
