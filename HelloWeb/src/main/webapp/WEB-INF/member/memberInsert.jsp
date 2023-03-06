<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="../includes/sidebar.jsp" %>
	<%@ include file="../includes/top.jsp" %>
	
	<h3>회원가입</h3>
	<form action="memberInsert.do" method="post">
	 <table class="table">
	<tr><td>ID:</td> <td><input type="text" name="id"></td></tr><br>
	<tr><td>PW:</td> <td> <input type="password" name="pass"></td></tr><br>
	<tr><td>Name:</td> <td> <input type="text" name="name"></td></tr><br>
	<tr><td>Mail:</td> <td> <input type="email" name="mail"></td></tr><br>
	<tr><td colspan="2" align="center"><input type="submit" class="btn btn-primary"></td></tr>
	</table>
	</form>
			
	<%@ include file="../includes/footer.jsp" %>