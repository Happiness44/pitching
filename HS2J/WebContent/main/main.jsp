<%@page import="com.hs2j.User.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hs2j.comm.HS2JConst"%>
<%@page import="org.apache.log4j.Logger"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<%--
  /**
  * @Class Name : file.jsp
  * @Description : 
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018. 4. 12.    소현      최초 생성
  *
  * author SIST01
  * since 2018. 4. 12.
  *
  * Copyright (C) 2018 by SIST01  All right reserved.
  */
--%>
<% Logger log = Logger.getLogger(this.getClass());
	UserVO member = null;
	if(null != session.getAttribute("member")){
		member = (UserVO)session.getAttribute("member");
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

	<% if (member ==null){  %>
	<!-- 로그인 -->
	<form class="form-inline" action="<%=HS2JConst.context %>/main/loginCheck.jsp"  method="post">
		<div class="container-fluid text-right">	
			아이디:<input type="text" name="userId" size="10">
			비밀번호:<input type="password" name="password" size="10">
			<input type = "submit" class="btn btn-default btn-sm" value="로그인" />
			<input type="button" class="btn btn-default btn-sm" value="회원가입" id="save_btn"  />
		</div>	
	</form>
	<form name = "frm" id = "frm"   class="form-inline" action="<%=HS2JConst.context%>/user.do" method = "post">
    	<input type="hidden"  name="workDiv" id="workDiv" />
    	<input type="hidden"  name="userId"  id="userId" />
    </form>
	<%
		}
	else{
	%>
	<form name="frmEdit" class="form-inline" id="frmEdit"  action="<%=HS2JConst.context%>/main/logout.jsp" method="post">
		<div class="container-fluid text-right">
			<%=member.getUserId() %>님 환영합니다.
			<input type="submit" class="btn btn-default btn-sm" value="로그아웃"/>
			<input type="hidden" name="workDiv" id="workDiv"   />
			<input type="hidden" name="userId" id="userId3" value=<%=member.getUserId() %>  />
			<input type="hidden" id="userId" value=<%=member.getUserId() %>   />
			<input type="button" class="btn btn-default btn-sm" value="회원정보수정" id="user_updateBtn"/>
			   <c:if test="${'10' == member.userAccount }">
		      	<input type="button" class="btn btn-default btn-sm" value="회원관리" id="list_btn" />
		      </c:if>
	     </div>
<%-- 			<%if(member.getUserAccount().toString() == "10"){ %>
			<input type="button" value="회원관리" id="list_btn" />
			<%} %> --%>
	<%
		}
	%>		
	</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">

/*등록화면으로 이동*/
function ctrSave(){
	var frm = document.frm;
	frm.workDiv.value = "2100";
	frm.action="/HS2J/user.do";
	frm.submit();		
	
}
function ctrUserUpdate(){
	 
	var userId1= $("#userId").val();
    //console.log("ㅋㅋㅋuserId="+userId);
	var frm = document.frmEdit;
	frm.workDiv.value = "1100";
	frm.userId.value = userId1;
	//alert(userId1);
	
	 frm.action="/HS2J/user.do";
	
	frm.submit(); 
	
}  
function ctrSelectList(){
	
	var frm = document.frmEdit;
	frm.workDiv.value = "1000";
	frm.action="/HS2J/user.do";
	frm.submit();
	
}

$(document).ready(function(){

	   console.log('${message}');
	   
	   if("" != '${message}'){
	      alert('${message}');
	   }
		//Grid Click
		$("#user_updateBtn").click(function(){
			//alert("tr");
			ctrUserUpdate(); 
		});

		//회원관리 페이지로 이동
		$("#list_btn").click(function(){
			ctrSelectList();
	   });
		//회원가입 페이지로 이동
		$("#save_btn").click(function(){
			ctrSave();
	   });
		
});
</script>
</body>
</html>