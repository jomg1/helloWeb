<%@page import="co.dev.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<%@ include file="../includes/sidebar.jsp" %>
	<%@ include file="../includes/top.jsp" %>
	
  <h3>회원삭제조회(memberDelete.jsp)</h3>
  
  <%
  	MemberVO member = (MemberVO) request.getAttribute("vo");
  	String message = (String) request.getAttribute("message");//atrribute 이름 메세지를 읽어오겠다.
  	if (message != null) {
  %>
  	<p><%=message %></p>
  <%
  	}
  %>
  <form action="memberSearch.do">
  <input type="hidden" name="job" value="delete">
    찾을ID: <input type="text" name="id"><br>
    <input type="submit" value="찾기">
  </form>
  
  <p></p>
  <%
  	if (member != null){ //조회된 정보가 있으면 화면 결과 표시
  	%>
  	<h3>회원삭제정보</h3>
  	  <form action="memberDelete.do">
      ID: <input type="text" name="id" value="<%=member.getId()%>" readonly><br>
      <input type ="submit" value="삭제">
    </form>
  	
  <%
  	}
  %>	
  	
	<%@ include file="../includes/footer.jsp" %>