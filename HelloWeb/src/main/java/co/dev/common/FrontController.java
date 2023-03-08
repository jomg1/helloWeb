package co.dev.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.controller.LoginFormControl;
import co.dev.controller.LogoutControl;
import co.dev.controller.MainControl;
import co.dev.controller.MemberDeleteControl;
import co.dev.controller.MemberDeleteFormControl;
import co.dev.controller.MemberInsertControl;
import co.dev.controller.MemberInsertFormControl;
import co.dev.controller.MemberListControl;
import co.dev.controller.MemberSearchControl;
import co.dev.controller.MemberSearchFormControl;
import co.dev.controller.MemberUpdateControl;
import co.dev.controller.MemberUpdateFormControl;
import co.dev.controller.NoticeAddControl;
import co.dev.controller.NoticeFormControl;
import co.dev.controller.NoticeListControl;
import co.dev.controller.NoticeModifyControl;
import co.dev.controller.NoticeRemoveControl;
import co.dev.controller.NoticeSearchControl;

public class FrontController extends HttpServlet{
	// url 패턴과 실행 컨트롤러 등록 (하나의 클래스 안에서 관리하기 위함)
	// url - controller : map 등록
	Map<String, Control> map = new HashMap<>();
	String enc; // 인코딩 처리를 위한 변수
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		enc = config.getInitParameter("encoding"); //UTF-8
		
		map.put("/main.do", new MainControl());
		//로그인
		map.put("/login.do", new LoginControl());
		map.put("/loginForm.do", new LoginFormControl());
		//로그아웃
		map.put("/logout.do", new LogoutControl());
		//회원목록
		map.put("/memberList.do", new MemberListControl());
		//회원등록화면
		map.put("/memberInsertForm.do", new MemberInsertFormControl()); //MemberInsertFormControl에 주소 등록해둔 것
		//회원 등록처리
		map.put("/memberInsert.do", new MemberInsertControl());
		//회원조회화면
		map.put("/memberSearchForm.do", new MemberSearchFormControl());
		//회원조회
		map.put("/memberSearch.do", new MemberSearchControl());
		//수정조회화면
		map.put("/memberUpdateForm.do", new MemberUpdateFormControl());
		//수정처리
		map.put("/memberUpdate.do", new MemberUpdateControl());
		//회원삭제조회화면
		map.put("/memberDeleteForm.do",new MemberDeleteFormControl());
		//삭제처리
		map.put("/memberDelete.do", new MemberDeleteControl());
		
		//사원목록
//		map.put("/empList.do", new EmpListControl()); //member/empList.jsp
		
		//공지사항 관련
		map.put("/noticeList.do", new NoticeListControl()); //글목록
		map.put("/noticeWriteForm.do", new NoticeFormControl());//공지사항 등록화면
		map.put("/noticeAdd.do", new NoticeAddControl());//공지사항등록
		//서비스 getNotice(int nid), 매퍼(데이터베이스용어) selectNotice(int nid)
		map.put("/noticeSearch.do", new NoticeSearchControl());//공지사항 상세조회
		map.put("/noticeModify.do", new NoticeModifyControl());//정보수정
		map.put("/noticeRemove.do", new NoticeRemoveControl());//정보삭제
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(enc); // 인코딩 : UTF-8
		// url - control 매칭
		String uri = req.getRequestURI(); //http://localhost8080 "/HelloWeb/main.do" 여기를 가지고 옴
		String context = req.getContextPath(); // //HelloWeb
		String path = uri.substring(context.length()); // /main.do //마지막 페이지의 값을 읽어오기 위함
		System.out.println(path);
		
		Control sub = map.get(path); // Parent parent = new Child(); 가능
									 // 자식 class의 인스턴스는 부모 class에 할당 가능
		sub.execute(req, resp);
	}
}
