package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;

public class MemberJquery implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "member/memberjquery.tiles";
	}

}
