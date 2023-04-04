package co.prod.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;
import co.prod.vo.MembersVO;

public class MemberRemoveJquery implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
//		List<MembersVO> list = new ArrayList<>();
		
		String[] members = req.getParameterValues("memberId");
		for (String member : members)
		System.out.println(members);

		MemberService service = new MemberServiceMybatis();
		
		service.removeMembersAry(members);
		
		String json = "{\"retCode\":\"Success\"}";
		
		return json + ".ajax";
	}

}
