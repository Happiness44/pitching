<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="com.hs2j.User.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%--
  /**
  * @Class Name : 
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2018.04.11
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%
Logger log = Logger.getLogger(this.getClass());
	request.setCharacterEncoding("UTF-8");
	
	String userId= request.getParameter("userId");
	String password= request.getParameter("password");
	
	//UserVO member = ((UserVO)request.getAttribute("member"));
		
	UserDAO userDAO = new UserDAO();
	UserVO userVO= new UserVO();


	UserVO result = userDAO.checkPasswd(userId,password);
	//log.debug("11111111111"+result);
	if(result != null ){

		session.setAttribute("member", result);
		response.sendRedirect("main_bootstrap.jsp");
	}else{
		out.println("<script>");
		out.println("alert('id&pw를 확인하세요')");
		out.println("location.href='main_bootstrap.jsp'");
		out.println("</script>");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인여부 검사</title>
</head>
<body>
</body>
</html>