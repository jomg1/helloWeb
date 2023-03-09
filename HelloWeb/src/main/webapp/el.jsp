<%@page import="co.dev.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 연습</title>
</head>
<body>
  <%
    request.getAttribute("");// jsp 페이지가 서블릿으로 변환
    //response.sendRedirect("jstl.jsp"); // 내장객체. 페이지 재지정(다른 곳으로 이동)
    //request.setAttribute("name", "Hong");
    session.setAttribute("name", "Hong");
    List<MemberVO> members = new ArrayList<>();
    MemberVO m1 = new MemberVO();
    m1.setId("user1");m1.setName("사용자1");
    MemberVO m2 = new MemberVO();
    m2.setId("user2");m2.setName("사용자2");
    members.add(m1);
    members.add(m2);
    
    request.setAttribute("list", members);
    request.getRequestDispatcher("jstl2.jsp").forward(request, response);
    //request.setAttribute("name", "Hong") jstl.jsp로 페이지를 재지정하면서 요청정보, 응답정보 객체들을 함께 넘겨줌
  %>
  Literal: ${"string" }<br>
  연산자: ${3>5 }<br>
  		<p>${10-4 }</p>
  파라미터: <p>${param.p }</p>
</body>
</html>