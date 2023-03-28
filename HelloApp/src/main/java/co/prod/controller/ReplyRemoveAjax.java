package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;

public class ReplyRemoveAjax implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		//댓글삭제하는 컨트롤
		String replyId = req.getParameter("replyId");
		ProductService service = new ProductServiceImpl();
		boolean result = service.removeReply(Integer.parseInt(replyId));
		
		String json = "";
		if(result) {
			// {"retCode": "Success"} //해당 형태의 제이슨 포맷을 넘겨줌
			json = "{\"retCode\": \"Success\"}";
		}else {
			// {"retCode": "Fail"}
			json ="{\"retCode\": \"Fail\"}";
		}
		return json + ".ajax"; //ajax인지 .do인지 구분하기 위해 ".ajax" 넣어줌
	}

}
