<%@page import="com.hs2j.comm.HS2JConst"%>
<%@page import="java.util.List"%>
<%@page import="com.hs2j.code.dao.CodeDAO"%>
<%@page import="com.hs2j.code.vo.CodeVO"%>
<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="com.hs2j.comm.DTO"%>
<%@page import="com.hs2j.comm.StringUtil"%>


<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
    
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
    //code조회
    CodeVO vo01=new CodeVO();
    vo01.setMstId("USER_ACCOUNT");
    CodeDAO codeDao=new CodeDAO();
	List<DTO> list = codeDao.do_selectList(vo01);
	
	//level처리 
	String userAccount = "";
	UserVO vo = null;
	if(null != request.getAttribute("vo")){
		vo = (UserVO)request.getAttribute("vo");
		userAccount = StringUtil.nvl(vo.getUserAccount(),"");
	}else{
		userAccount = "10";
		vo =new UserVO();
	}
    //--code조회	
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    
<title>회원가입</title>
    <style type="text/css">
    	.no-gutters {
		  margin-right: 0;
		  margin-left: 0;

		  > .col,
		  > [class*="col-"] {
		    padding-right: 0;
		    padding-left: 0;
		  }
      }
    </style>

    <!-- 부트스트랩 -->
    <link href="<%=HS2JConst.context%>/css/bootstrap.min.css" rel="stylesheet">    
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="<%=HS2JConst.context%>/js/bootstrap.min.js"></script>  

</head>
<body>
<!--====================================================
                       HOME-P
======================================================-->
    <div id="home-p" class="home-p pages-head2 text-center">
      <div class="container">
		
		<h1  class="wow fadeInUp" data-wow-delay="0.1s">회원가입</h1>
      </div><!--/end container-->
    </div>  

	<section id="cart" class="cart">  
		

<!-- 회원관리 -->
<div class="container" style="padding-top: 30px; padding-bottom: 30px;">
	<form name="userfrmEdit" class="form-horizontal" id="userfrmEdit" action="<%=HS2JConst.context%>/user.do" method="post">
		<input type="hidden" name="workDiv" id="workDiv"   />
	    <!-- 계정 -->
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">계정</label>
				<div class="col-sm-10 text-left">
					<label class="radio-inline"><input type="radio" name="userAccount" value="20" <c:if test="${vo.userAccount == '20'}" >checked</c:if> />창업자</label>
					<label class="radio-inline"><input type="radio" name="userAccount" value="30" <c:if test="${vo.userAccount == '30'}" >checked</c:if> />개인투자자</label>
					<label class="radio-inline"><input type="radio" name="userAccount" value="40" <c:if test="${vo.userAccount == '40'}" >checked</c:if> />기업투자자</label>
				</div>			
		</div>	
		<!-- //계정 -->
		
		<!-- 이름 -->
	    <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">이름</label>
			<div class="col-sm-12">
				<input type="text" class="form-control" name="userName" id="userName"  value="<c:out value='${vo.userName }'/>" maxlength="20" placeholder="이름(공백없이 입력)" />
			</div>
		</div>	
		<!-- //이름 -->
		
		<!-- 아이디 -->	
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
			<div class="col-sm-12">
				<input type="text"  class="form-control" name="userId" id="userId2" value="<c:out value='${vo.userId }'/>" <c:if test="${vo.workDiv != '2100'}">readonly</c:if>  maxlength="10" placeholder="아이디 (10자리) *중복체크 안할 시 회원가입이 안될 수 있습니다.*" />			
				<div class="container-fluid text-center">
				<input type="button"  class="btn btn-default btn-sm" style="width:200px" value="중복체크" id="check_btn" <c:if test="${vo.workDiv != '2100'}">readonly</c:if> />
				</div>
			</div>	
		</div>
		<!-- //아이디 -->
		
		<!-- 비번 -->	
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">비번</label>
			<div class="col-sm-12">
				<input type="password" class="form-control" name="userPw" id="userPw"   value="<c:out value='${vo.userPw }'/>"   maxlength="15" placeholder="비번(15자리)" />
			</div>	
		</div>
		<!-- //비번 -->
		
		<!-- 이메일 -->
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">이메일</label>
			<div class="col-sm-12">
				<input type="text" class="form-control" name="userEmail" id="userEmail"    value="<c:out value='${vo.userEmail }'/>" maxlength="40" placeholder="이메일" />
			</div>
		</div>
		<!-- //이메일 -->
		
		<!-- 핸드폰번호 -->
		<div class="form-group">	
			<label for="inputEmail3" class="col-sm-2 control-label">핸드폰 번호</label>
			<div class="col-sm-12">
				<input type="number" class="form-control" name="userPNumber" id="userPNumber"  value="<c:out value='${vo.userPNumber }'/>" maxlength="10" placeholder="'-'없이 앞에 0을 제외한 번호를 입력하세요.Ex)10-****-****"/>
			</div>	
		</div>	
		<!-- //핸드폰번호 -->
		
		<!-- 추가항목 -->
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">전문 분야</label>
			<div class="col-sm-12">
				<input type="text" class="form-control" name="userSpecialism" id="userSpecialism" value="<c:out value='${vo.userSpecialism }'/>" style="height:80px" maxlength="200" placeholder="전문분야를 작성해주세요. Ex)"/>
			</div>
		</div>
		
		<div class="form-group">						
			<label for="inputEmail3" class="col-sm-2 control-label">경력 사항</label>
			<div class="col-sm-12">	
				<input type="text" class="form-control" name="userWork" id="userWork" value="<c:out value='${vo.userWork }'/>" style="height:80px" maxlength="200"  placeholder="경력사항을 작성해주세요. Ex)CJ기업, 쌍용"/>
			</div>
		</div>	
										
	</form>
	<!--// 회원관리 -->
		     		<!-- Button area -->
	<div class="container-fluid text-center" style="padding-bottom: 20px;">
		<input type="button"  class="btn btn-default btn-sm" value="등록" id="register_btn" <c:if test="${vo.workDiv != '2100'}">disabled</c:if> />
		<input type="button"  class="btn btn-default btn-sm" value="수정" id="update_btn" <c:if test="${vo.workDiv != '1100'}">disabled</c:if> />
		<input type="button"   class="btn btn-default btn-sm" value="메인" id="move_btn"/>
	</div>
</div>
</section>
<script type="text/javascript">
/*등록*/
function ctrAdd(){
	//if( false == confirm("회원가입 하시겠습니까?")) return;
	
	var frm = document.userfrmEdit;
	frm.workDiv.value = "2000";
	frm.action="/HS2J/user.do";
	frm.submit();
	
}
function ctrMove(){
	var frm = document.userfrmEdit;
	frm.workDiv.value = "2200";
	frm.action="/HS2J/user.do";
	frm.submit();
	
}
/*수정*/
function ctrUpdate(){
    if( false == confirm("수정 하시겠습니까?")) return;
	
	var frm = document.userfrmEdit;
	frm.workDiv.value = "3000";
	frm.action="/HS2J/user.do";
	frm.submit();
	
}
function checkValide(){
	//계정
	var robj= document.getElementsByName("userAccount");
 		var checknum=null;
 		
 		for(var i=0;i<robj.length;i++){
 			if(robj[i].checked == true) {
 				checknum = robj[i].value;
 			}
 		}
 		if(checknum==null){
 			alert("계정을 선택하세요.");
 			return false;
 		}
	//이름
	var userName = $("#userName").val()
	if(""== userName){
	   alert("이름를 입력 하세요.");
	   $("#userName").focus();
	   return false;
	}
	
	//id
	var userId2= $("#userId2").val();
	if(""== userId2){
	   alert("Id를 입력 하세요.");
	   $("#userId2").focus();
	   return false;
	}
	
	//비번
	var userPw= $("#userPw").val();
	if(""== userPw){
	   alert("비밀번호를 입력 하세요.");
	   $("#userPw").focus();
	   return false;
	}
	
	//이메일
	var userEmail = $("#userEmail").val();
	if(""== userEmail){
	   alert("이메일을 입력 하세요.");
	   $("#userEmail").focus();
	   return false;
	}
	
	//핸드폰번호
	var userPNumber = $("#userPNumber").val();
	if(""== userPNumber){
	   alert("핸드폰번호를 입력 하세요.");
	   $("#userPNumber").focus();
	   return false;
	}else if( userPNumber.length > "10"){
		alert("핸드폰번호 맨앞 0을빼고 입력했는지 다시 확인해주세요.");
		return false;
	}
	return true;
}//save_btn	

function confirmId(){
	if($("#userId2").val() == ""){
		alert("ID를 입력하세요"); return;
	}
	url = "/HS2J/main/confirmId.jsp?id="+$("#userId2").val();
	open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no,scrollbar=no,resizable=no,width=300,height=200")
}
$(document).ready(function(){
   //alert('document');
   if("" != '${message}'){
      alert('${message}');
   }
		   
   $("#register_btn").click(function(){
      if(true==checkValide()){
         ctrAdd();
      }      
   });
   $("#move_btn").click(function(){
	  // alert("r");
	    	  ctrMove();      
	}); 
   
   $("#check_btn").click(function(){
	   confirmId();
	   //alert(document.getElementById("userId").value);
   });
   $("#update_btn").click(function(){
	   ctrUpdate();
   });
   
});
</script>
</body>
</html>