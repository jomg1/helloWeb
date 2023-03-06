package co.dev.dao;

import java.util.ArrayList;
import java.util.List;

import co.dev.common.DAO;
import co.dev.vo.EmpVO;

public class EmpDAO extends DAO{

	public List<EmpVO> empList(){
		List<EmpVO> empList = new ArrayList<>();
		getConn();
		String sql = "select * from employees";
		try {
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while(rs.next()) {
//					EmpVO vo = new EmpVO();
//					vo.setId(rs.getInt("employees_id"));
//					vo.setLastName(rs.getString("Last_name"));
//					vo.SetSalary(rs.getInt("salary"));
//					vo.setDepartmentId(rs.getInt("department_id"));
					
//					empList.add(vo);
					
				}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return empList;
	}
}
