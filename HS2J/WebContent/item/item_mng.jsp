<%--
  /**
  * @Class Name : item_mng.jsp
  * @Description : 아이템 한 개 화면
  * @Modification Information
  *
  *   수정일            		  수정자                      수정내용
  *  ----------    --------    ---------------------------
  *  2018.4.24     	hamooy  	    최초 생성
  *
  * author hamooy
  * since 2018. 4. 24
  *
  * Copyright (C) 2018 by hamooy  All right reserved.
  */
--%>
<%@page import="com.hs2j.User.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hs2j.item.vo.ItemUserCodeFileReqVO"%>
<%@page import="com.hs2j.comm.StringUtil"%>
<%@page import="com.hs2j.comm.DTO"%>
<%@page import="java.util.List"%>
<%@page import="com.hs2j.code.dao.CodeDAO"%>
<%@page import="com.hs2j.code.vo.CodeVO"%>
<%@page import="com.hs2j.comm.HS2JConst"%>
<%@page import="org.apache.log4j.Logger"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	
	ItemUserCodeFileReqVO vo = null;
	if(null!=request.getAttribute("vo")){
		vo = (ItemUserCodeFileReqVO)request.getAttribute("vo");
	}else{
		vo = new ItemUserCodeFileReqVO();
	}
	
	//로그인처리
	UserVO member = null;
	if(null != session.getAttribute("member")){
		member = (UserVO)session.getAttribute("member");
	}else{
		member = new UserVO();
	}
	
	request.setAttribute("receiver", vo.getItemRegPerson());
	LOG.debug("itemRegPerson=" + vo.getItemRegPerson());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%= HS2JConst.context %>/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">

<script src="<%= HS2JConst.context %>/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="row title-bar" style="padding:0">
    	<div class="col-md-12">
    		<h1 class="wow fadeInUp">Pitching</h1>
    		<div class="heading-border"></div>
    		<p class="wow fadeInUp" data-wow-delay="0.4s" style="padding:0">아이템 보기</p> 
    	</div>
    </div>	
    
<div class="container" style="padding-top: 30px; padding-bottom: 30px;">
<!-- message form -->
<form name = "msgFrm" id = "msgFrm">
	<input type="hidden" name="itemRegPerson" id="itemRegPerson" value="${vo.itemRegPerson}" />
</form>
<!--// message form -->

<!-- invest form -->
<form name="investFrm" id="investFrm">
	<input type="hidden" name="itemRoyalty" id="itemRoyalty" value="${vo.itemRoyalty}" />
	<input type="hidden" name="itemRepaydate" id="itemRepaydate" value="${vo.itemRepaydate}" />
	<input type="hidden" name="itemInvestment" id="itemInvestment" value="${vo.itemInvestment}" />
	<input type="hidden" name="totalMoney" id="totalMoney" value="${vo.totalMoney}" />
	<input type="hidden" name="itemSeq" id="itemSeq" value="${vo.itemSeq}" />
</form>
<!-- invest form -->

<!-- invite form -->
<form name="inviteFrm" id="inviteFrm">
	<input type="hidden" name="itemRoyalty" id="itemRoyalty" value="${vo.itemRoyalty}" />
	<input type="hidden" name="itemRepaydate" id="itemRepaydate" value="${vo.itemRepaydate}" />
	<input type="hidden" name="itemInvestment" id="itemInvestment" value="${vo.itemInvestment}" />
	<input type="hidden" name="totalMoney" id="totalMoney" value="${vo.totalMoney}" />
	<input type="hidden" name="itemSeq" id="itemSeq" value="${vo.itemSeq}" />
</form>
<!-- invite form -->

<!-- form -->
<form name="itemfrm" id="itemfrm" action="<%=HS2JConst.context%>/item.do" method = "post">
	<input type = "hidden" name = "workDiv" id = "workDiv" /> 
	<input type="hidden" name="itemSeq" id = "itemSeq"/> 

	<hr>
	<div style="margin-left:50px; padding:10px;">
		<input type="button" class="btn btn-general btn-green" value="뒤로 가기" id="back_btn" onclick="javascript:ctrBack();"/>
		<c:if test="${null != member.getUserId()}">	
			<c:if test="${vo.itemRegPerson == member.getUserId()}">
				<input type="button" class="btn btn-general btn-green" value="아이템 수정" id="update_btn" onclick="javascript:ctrUpdate();"/>				
			</c:if>	
		</c:if>
	</div>
	
	<!-- user table list -->
	<div style="border: 2px solid black; margin-left:50px; padding:10px;">
		
		<h2>아이템 분류
		<label>${vo.itemCategory }</label>  
		                      
		&nbsp;&nbsp; / &nbsp;&nbsp;모집분야 
		<label>${vo.itemBranch }</label>  </h2>
		                       
		<label>아이템 제목</label>
		${vo.itemTitle }        <br>
		                       
		<label>아이템 진행 기간</label>
		${vo.itemStartDate } &nbsp; ~ &nbsp;
		${vo.itemEndDate }<br>
		
		<hr>
		
		<label>작성자 ID</label>  
		${vo.userId }           <br>
		                       
		<label>작성자 이메일   </label>            
		${vo.userEmail }        <br>
		                        
		<label>작성자 이름      </label>     
		${vo.userName }         <br>
		                      
		<label>작성자 경력  </label>           
		${vo.userWork }         <br>
		                      
		                   
		<label>작성자 전화번호     </label>     
		0${vo.userPNumber }     
		
		<c:if test="${null != member.getUserId()}">	
			<c:if test="${vo.itemRegPerson != member.getUserId()}">	
					<input style="float:right; padding-bottom:1px; " type="button" class="btn btn-general btn-green"  value="쪽지 보내기" id="msg_btn"/><br>
			</c:if>
		</c:if>
	</div>
	<!--// user table list -->
	
	<hr>
	<!-- invest table list -->
	<div style="margin-left:50px; padding:10px; width:70%;">
		<table>
			<tr>
				<td><label>참가 인원 수</label> </td>
				<td>${vo.totalPerson}</td>
				<td>/</td>
				<td>${vo.itemCount}</td>
			<tr>
				<td><label>목표 투자금액</label> </td>
				<td>${vo.itemInvestment}</td>
			</tr>
			<tr>
				<td><label>현 투자금액 </label></td>
				<td>${vo.totalMoney}</td>
			</tr>
			<c:if test="${null != member.getUserId()}">	
				<c:if test="${vo.itemRegPerson != member.getUserId()}">	
					<c:if test="${20 == member.getUserAccount()}">
						<tr>
							<td><input type="button"  value="참여하기" id="invite_btn" /></td>
						</tr>
					</c:if>
					<c:if test="${20 != member.getUserAccount()}">
						<tr>
							<td><input type="button" class="btn btn-general btn-green" value="투자하기" id="invest_btn" /></td>
						</tr>
					</c:if>
				</c:if>	
			</c:if>
		</table>
	</div>
	<!--// invest table list -->
	
	<hr>
	
	<!-- item table list -->
	<div style="margin-left:50px; padding:10px; width:70%;">
		<table>
			<tr>
				<td><label>아이템 내용</label></td>
			</tr>
			<tr style="padding:10px; width:70%; height:50%;">
				<td><h3>${vo.itemContents }</h3></td>
			</tr>
		</table>
	</div>
	<!--// item table list -->
	
</form>
<!--// form -->
</div>
<!-- script -->

<script type="text/javascript">
	/*수정*/
	function ctrUpdate(){
		var d = ${vo.itemSeq};
		var itemfrm = document.itemfrm;
	    itemfrm.workDiv.value = "3100";
	    itemfrm.itemSeq.value = d;
	    itemfrm.action = "/HS2J/item.do";
	    itemfrm.submit();
	}
	
	/* 쪽지보내기 */
	function sendMsg(frm){
		//팝업창에 출력될 페이지 URL
		var popUrl = "<%=HS2JConst.context%>/my_page/msg/msg_new.jsp";	
		var title = "msg_new";
		var status = "toolbar=no,directories=no,scrollbars=no,resizable=no,"
					 + "status=no,menubar=no,width=300, height=350, top=0,left=20"; 
		
		window.open("",title,status);	
		
		frm.target = title;
		frm.action = popUrl;
		frm.method = "POST";
		frm.submit();
	}
	
	/* 투자하기 */
	function investMoney(frm){
		//팝업창에 출력될 페이지 URL
		var popUrl = "<%=HS2JConst.context%>/item/req_new.jsp";	
		var title = "req_new";
		var status = "toolbar=no,directories=no,scrollbars=no,resizable=no,"
					 + "status=no,menubar=no,width=500, height=350, top=0,left=20"; 
		
		window.open("",title,status);	
		
		frm.target = title;
		frm.action = popUrl;
		frm.method = "POST";
		frm.submit();
	}
	
	/* 참여하기 */
	function invitePerson(frm){
		//팝업창에 출력될 페이지 URL
		var popUrl = "<%=HS2JConst.context%>/item/req_new.jsp";	
		var title = "req_new";
		var status = "toolbar=no,directories=no,scrollbars=no,resizable=no,"
					 + "status=no,menubar=no,width=450, height=600, top=0,left=20"; 
		
		window.open("",title,status);	
		
		frm.target = title;
		frm.action = popUrl;
		frm.method = "POST";
		frm.submit();
	}

	$(document).ready(function(){
		if("" != '${message}'){
			alert('${message}');
		}
		
		$("#invite_btn").click(function(){
			var totalperson = ${vo.totalPerson};
			var count = ${vo.itemCount};
			
			var co = totalperson >= count;
			if(co==true){
				if(false==confirm("참여자가 현재 꽉 차있습니다. 그래도 참여하시겠습니까?")) return;
			}
			
			var inviteFrm = document.inviteFrm;
			invitePerson(inviteFrm);
		});
		$("#invest_btn").click(function(){
			var investFrm = document.investFrm;
			investMoney(investFrm);
		});
		$("#msg_btn").click(function(){
			var msgFrm = document.msgFrm;
			sendMsg(msgFrm);
		});
		$("#back_btn").click(function(){
			var itemfrm = document.itemfrm;
		    itemfrm.workDiv.value = "1000";
		    itemfrm.action = "/HS2J/item.do";
		    itemfrm.submit();
		});
	});
</script>
<!--// script -->
</body>
</html>