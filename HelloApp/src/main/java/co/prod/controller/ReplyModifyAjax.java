package co.prod.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.prod.common.Control;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;
import co.prod.vo.ReplyVO;

public class ReplyModifyAjax implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse response) {
		
		ProductService service = new ProductServiceImpl();
		ReplyVO vo = new ReplyVO();
		
		String no = req.getParameter("no");
		String content = req.getParameter("content");
		
		vo.setReplyNo(Integer.parseInt(no));
		vo.setReplyContent(content);
		
		boolean result = service.modifyReply(vo);
		
		vo = service.getReply(Integer.parseInt(no));
		
		Map<String, Object> resultMap = new HashMap<>();
		
		if(result) {
			//업데이트 여부 확인하기 위함
			resultMap.put("retCode", "Success");
			resultMap.put("reply", vo);
		}else {
			resultMap.put("retCode", "Fail");
			resultMap.put("reply",null);
		}
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(resultMap);
		
		
		
		return json + ".ajax";
	}

}
