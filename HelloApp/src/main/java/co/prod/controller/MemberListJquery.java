package co.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;
import co.prod.vo.MembersVO;

public class MemberListJquery implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		
		MemberService service = new MemberServiceMybatis();
		List<MembersVO> list = service.membersList();
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(list);
		return json + ".ajax";
	}

}
