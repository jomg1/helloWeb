<%@page import="co.dev.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/top.jsp"></jsp:include>
	
	<%
	// 자바 영역
		List<MemberVO> list = (List<MemberVO>) request.getAttribute("members"); // list의 주소값 반환(리턴 타입 object)
		System.out.print(list);
	%>
	<table class="table">
		<thead>
		<tr><th>id</th><th>Name</th><th>Pass</th><th>Mail</th></tr>	
		</thead>
		<tbody>		
		
	<%
		for (MemberVO member : list){
			
	%>
			<tr><td><a href='memberSearch.do?job=update&id=<%=member.getId() %>'><%=member.getId() %></a></td>
			 	<td><%=member.getName() %></td>
			 	<td><%=member.getPasswd() %></td>
			 	<td><%=member.getMail() %></td></tr>
	<%
		}
	%>
		</tbody>
	</table>
	<p>회원등록페이지 이동</p>
	<a href="memberInsertForm.do">회원등록페이지</a>
	
<jsp:include page="../includes/footer.jsp"></jsp:include>
