<%@page import="com.hs2j.User.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%
	String id = request.getParameter("id");
	UserDAO dao = new UserDAO();
	int result = dao.checkUserId(id);
	
	if(result!=0){ %>
	<center>
		<br/><br/>
		<h4>이미 사용중인 ID 입니다.</h4>
	</center>
	<%} else { %>
	<center>
		<br/><br/>
		<h4>입력하신 <%=id %>는 사용하실 수 있는 ID 입니다.</h4>
	</center>
	<%}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>