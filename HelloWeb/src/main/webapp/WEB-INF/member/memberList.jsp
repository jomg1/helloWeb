<%@page import="co.dev.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	// 자바 영역
		List<MemberVO> list = (List<MemberVO>) request.getAttribute("members"); // list의 주소값 반환(리턴 타입 object)
		System.out.print(list);
	%>
	<ul>
	<%
		for (MemberVO member : list){
			
	%>
			<li>id: <%=member.getId() %>, name: <%=member.getName() %></li>
		<%
		}
		%>
	</ul>
</body>
</html>