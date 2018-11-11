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
<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- 부트스트랩 -->
<link href="<%=HS2JConst.context%>/css/bootstrap.min.css" rel="stylesheet">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쪽지 확인</title>
<%
Logger LOG = Logger.getLogger(this.getClass());

//cache Control
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
if (request.getProtocol().equals("HTTP/1.1"))
	response.setHeader("Cache-Control", "no-cache");

//임시 로그인 처리 (ID: testuser, user_account : 20 창업자)
UserVO tmpUser = new UserVO();
tmpUser.setUserId("testuser");
tmpUser.setUserAccount("20");
LOG.debug("현재 로그인 유저 ID:" + tmpUser.getUserId() + "/ 계정유형 : " + tmpUser.getUserAccount());

request.setAttribute("userId", tmpUser.getUserId());
%>
</head>
<body style="margin-left:25px;">
<h2>쪽지</h2>
<form name="hiddenArea">


<div class = "msgContainer col-sm-10">
<div class="form-group row">
<input type="hidden" name="workDiv" id="workDiv" />
<input type="hidden" name="seq" id="seq" value="<c:out value='${outVO.seq}'/>" />
</form>

보낸 사람 : <input type="text" class="form-control-plaintext" name="sender" id="sender" value="<c:out value='${outVO.sender}'/>" readonly />
<div></div>
받는 사람 : <input type="text" class="form-control-plaintext" name="receiver" id="receiver" value="<c:out value='${outVO.receiver}'/>" readonly /> <br/>
<div></div>
보낸 시간 : <input type="text" class="form-control-plaintext" name="msgDate" id="msgDate" value="<c:out value='${outVO.msgDate}' />"/> <br/>
<div></div>
제목 : <input type="text" class="form-control-plaintext" name="title" id="title" value="<c:out value='${outVO.title}' />"readonly /> <br/>
<div></div>
내용 : <input type="text" class="form-control-plaintext" name="contents" id="contents" value="<c:out value='${outVO.contents}' />" readonly /> <br/>
</div>
</div>
<p><p>
<!--  Button Area -->
<div>
<!--  내가 보낸 쪽지가 아닐 때만 답장 버튼이 있다 -->
<c:choose>
<c:when test="${userId != outVO.sender}"> 
	<input type="button" value="답장" id="reply_btn" class="btn"  />
</c:when>

</c:choose>
<input type="button" value="엮인 쪽지" id="connectedMsg_btn" class="btn"/>
</div>
<!--  Button Area -->
<script type="text/javascript">
//답장 기능
function ctrReply(){
	var hiddenArea = document.hiddenArea;
	hiddenArea.workDiv.value = "2100";
	hiddenArea.action = "/HS2J/msg.json";
	hiddenArea.submit();
}//ctrReply

//엮인쪽지 보기(hierList)
function ctrHierList(){
	var hiddenArea = document.hiddenArea;
	hiddenArea.workDiv.value = "1500";
	hiddenArea.action = "/HS2J/msg.json";
	hiddenArea.submit();
}//ctrHierList

$(document).ready(function(){
	$("#reply_btn").click(function(){
		ctrReply();
	})
	$("#connectedMsg_btn").click(function(){
		ctrHierList();
	})
	
});

</script>

</body>
</html>