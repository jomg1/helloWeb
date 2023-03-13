package co.prod.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;

public class memberRemoveAjax implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// 삭제할 아이디 (파라미터에 넘겨줌 -> 삭제 처리 매개값으로 사용)
		MemberService service = new MemberServiceMybatis();
		boolean result = service.removeMember(req.getParameter("id"));
		String json = "";
		if (result) {//{ "retCode":"Success"}
			json = "{\"retCode\":\"Success\"}";
		} else { // {"retCode": "fail"}
			json = "{\"retCode\":\"fail\"}";
		}

		return json + ".ajax";

	}
}