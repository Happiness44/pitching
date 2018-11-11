<%--
  /**
  * @Class Name : file.jsp
  * @Description : 
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018. 4. 24.     SIST01      최초 생성
  *
  * author SIST01
  * since 2018. 4. 24.
  *
  * Copyright (C) 2018 by SIST01  All right reserved.
  */
--%>
<%@page import="com.hs2j.comm.HS2JConst"%>
<%@page import="com.hs2j.message.vo.MessageVO"%>
<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- 부트스트랩 -->
<link href="<%=HS2JConst.context%>/css/bootstrap.min.css" rel="stylesheet">
<%
	Logger LOG = Logger.getLogger(this.getClass());

	//cache Control
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (request.getProtocol().equals("HTTP/1.1"))
		response.setHeader("Cache-Control", "no-cache");
	
	//로그인처리
	UserVO member = null;
	String userId = null;
	String userAccount = null;
	
	if(null != session.getAttribute("member")){
		member = (UserVO)session.getAttribute("member");
		userId = member.getUserId();
		userAccount = member.getUserAccount();
		LOG.debug("현재 로그인 유저 ID:" + userId + "/ 계정유형 : " + userAccount);
	}else{
		LOG.debug("로그인 안함");
	}
	//보내는 사람 가져오기
	String receiver = null;
	String newPageTitle = "새 쪽지";
	if(null != request.getParameter("itemRegPerson")){
		receiver = (String) request.getParameter("itemRegPerson");
		LOG.debug("receiver=" + receiver);
		
	} else {
		//답장일 경우
		MessageVO mVO = (MessageVO) request.getAttribute("outVO");
		receiver = mVO.getSender();
		newPageTitle = "답장 보내기";
	}
	
	
%>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새 쪽지</title>
</head>
<body>
<div id = "container">

<form id = "msgForm" style="margin-left:25px;">
<h2><%=newPageTitle%></h2>
	<input type="hidden" name="workDiv" id="workDiv" value="2000" />
	<input type="hidden" name="sender" id="sender" value=<%=userId%> />
	<input type="hidden" name="noParent" id="noParent" value="<c:out value='${outVO.seq}'/>" />
	받는 사람 :<br/> <input type="text" class="form-control" name="receiver" id="receiver" value="<%=receiver%>" style="width:200px;"/>
<p><p>제목 : <br/><input type="text" class="form-control" name="title" id="title" maxlength = "80" style="width:200px;"/>
<p><p>내용 : <br/>
<textarea id = "contents" class="form-control" name="contents" rows="10" cols="50" style="width:350px;"></textarea>
</form>
<!--  button area -->
<p>
<div style="margin-left:25px;">
<input type="button" class="btn btn-general btn-green" value="보내기" id="send_btn" class = "btn"/>
<input type="button" class="btn btn-general btn-green" value="취소" id="cancel_btn" class = "btn" />
</div>
</div>
<!--  // button area -->
<script type="text/javascript">
function ctrAdd(){
	   var frm = document.getElementById('msgForm');
	   frm.action = "/HS2J/msg.json";
	   frm.submit();
	}
	
$(document).ready(function(){
	$('#send_btn').click(function(){
		ctrAdd();
	});
})

</script>
</body>
</html>