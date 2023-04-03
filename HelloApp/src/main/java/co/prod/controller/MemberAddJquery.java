package co.prod.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;
import co.prod.vo.MembersVO;

public class MemberAddJquery implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		
		//name: "홍길동", addr: '', tel: '', pw:
		
//		vo.setId(req.getParameter("id"));
//		vo.setName(req.getParameter("name"));
//		vo.setAddr(req.getParameter("addr"));
//		vo.setTel(req.getParameter("tel"));
//		vo.setPw(req.getParameter("pw"));
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String addr = req.getParameter("addr");
		String tel = req.getParameter("tel");
		String pw = req.getParameter("pw");
		
		MembersVO vo = new MembersVO();
		vo.setMemberId(id);
		vo.setMemberName(name);
		vo.setMemberAddr(addr);
		vo.setMemberTel(tel);
		vo.setMemberPw(pw);
		
		System.out.println(vo);
		
		MemberService service = new MemberServiceMybatis();
		boolean result = service.addMembers(vo);
		Map<String, Object> map = new HashMap<>();
		
		Gson gson = new GsonBuilder().create();
		String json = "";
		
		if(result) {
			map.put("retCode", "Success");
			map.put("member", vo);
		}else {
			map.put("retCode", "Fail");
			map.put("member", null);
		}
		json = gson.toJson(map);
		
		return json + ".ajax";
		
	}

}
