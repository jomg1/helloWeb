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
import co.prod.vo.MemberVO;

public class MemberAddAjax implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MemberVO vo = new MemberVO();
		//id, name, pw, mail, auth : 파라미터 이름
		vo.setId(req.getParameter("id"));
		vo.setName(req.getParameter("name"));
		vo.setPasswd(req.getParameter("pw"));
		vo.setMail(req.getParameter("mail"));
		vo.setAuth(req.getParameter("auth"));
		
		MemberService service = new MemberServiceMybatis();
		boolean result = service.addMember(vo);
		Map<String, Object> map = new HashMap<>();
		
		Gson gson = new GsonBuilder().create();
		String json = "";
		
		if(result) {
			map.put("retCode", "success");
			map.put("member", vo);
		}else {
			map.put("retCode", "Fail");
			map.put("member", null);
		}
		json = gson.toJson(map);
		
		return json + ".ajax";
	}

}
