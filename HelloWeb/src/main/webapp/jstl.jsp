<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- jstl 사용 taglib , prefix 기억 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl.jsp</title>
</head>
<body>
  <h3>jstl.jsp page</h3>
  <%
    String name = (String) application.getAttribute("name");
  	String n = "Hong";
  	
  	if(n != null){
  		System.out.print("name: "+n);
  	} else {
  		System.out.print("m은 null입니다");
  	}
  %>
  <p>name: <%=name %></p>
  <!-- 아래와 같은 의미 -->
  <p>name: ${name }</p><!-- request, session, application -->
  <p>Members: ${list }</p>
  
  <!-- prefix="c" 이므로 c태그 활용 -->
  <c:set value="Hong" var="m"></c:set> <!-- 값 입력 변수 m에 hong 값을 담는다 -->
  <c:out value="${m }"></c:out> <!-- 값 출력 (읽어옴)-->
  
  <!-- 조건문 else 없이 if 하나만 사용할 때 -->
  <c:if test="${m != null }"> <!-- test 어트리뷰트사용 -->
    <p>m의 값은 null이 아닙니다. <c:out value="${m }"></c:out></p>
  </c:if>
  
  <!-- if, else 구문 태그 사용 -->
  <c:choose>
    <c:when test="${m != null }">
      <p>m은 null아닙니다. ${m }</p>
    </c:when>
    <c:otherwise>
      <p>m은 null입니다.</p>
    </c:otherwise>
  </c:choose>
  
  <!-- if, if else 구문 태그 사용 -->
  <c:set var="score" value="85"></c:set>
  
  <c:choose>
    <c:when test="${score > 90 }">
      <p>A학점입니다</p>
    </c:when>
    <c:when test="${score > 80 }">
      <p>B학점입니다</p>
    </c:when>
    <c:when test="${score > 70 }">
      <p>C학점입니다</p>
    </c:when>
    <c:otherwise>
      <p>D학점입니다</p>
    </c:otherwise>  
  </c:choose>
  
</body>
</html>