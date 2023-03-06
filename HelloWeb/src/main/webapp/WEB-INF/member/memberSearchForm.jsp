<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="../includes/sidebar.jsp" %>
	<%@ include file="../includes/top.jsp" %>

<body>
  <h3>회원조회(memberSearchForm.jsp)</h3>
  <%
  	String message = (String) request.getAttribute("message");//atrribute 이름 메세지를 읽어오겠다.
  	if (message != null) {
  %>
  	<p><%=message %></p>
  <%
  	}
  %>
  <form action="memberSearch.do">
    <input type="hidden" name="job" value="search">
    찾을ID: <input type="text" name="id">
    <input type="submit" value="찾기">
  </form>
  
	<%@ include file="../includes/footer.jsp" %>