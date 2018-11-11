<%--
  /**
  * @Class Name : item_register.jsp
  * @Description : 아이템등록화면
  * @Modification Information
  *
  *   수정일            		  수정자                      수정내용
  *  ----------    --------    ---------------------------
  *  2018.4.24     	SIST01  	    최초 생성
  *
  * author SIST01
  * since 2018. 4. 24.
  *
  * Copyright (C) 2018 by SIST01  All right reserved.
  */
--%>
<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="com.hs2j.item.vo.ItemUserCodeFileReqVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="com.hs2j.comm.HS2JConst"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	ItemUserCodeFileReqVO vo;
	String check = "1";
	if(null != request.getAttribute("vo")){
	    vo = (ItemUserCodeFileReqVO)request.getAttribute("vo");
	}else{
		vo = new ItemUserCodeFileReqVO();
		check = "3";
	}

	UserVO member = null;
	if(null != session.getAttribute("member")){
		member = (UserVO)session.getAttribute("member");
	}
	//log.debug("현재 로그인 유저 ID:" + member.getUserId() + "/ 계정유형 : " + member.getUserAccount());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩 -->


</head>
<body>
    <div class="row title-bar" style="padding:0">
    	<div class="col-md-12">
    		<h1 class="wow fadeInUp">Pitching</h1>
    		<div class="heading-border"></div>
    		<p class="wow fadeInUp" data-wow-delay="0.4s" style="padding:0">아이템 등록</p> 
    	</div>
    </div>
    
    
    <div class="container" style="padding-top: 30px; padding-bottom: 30px;">
	<!--  form area -->
	<form name = "itemfrmEdit" id = "itemfrmEdit" action="<%=HS2JConst.context%>/item.do" method = "post">
        <input type = "hidden" name = "workDiv" id = "workDiv" /> 
        <input type = "hidden" name = "itemRegPerson" id = "itemRegPerson" value="${member.getUserId() }"/> 
        <input type = "hidden" name = "itemCompyn" id = "itemCompyn" value="10"/>  
		<input type="hidden" name="itemSeq" id = "itemSeq" value="${vo.itemSeq }"/> 
	
		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">제목</h6>
			<input type="text"  class="form-control" name="itemTitle" id="itemTitle"  value="<c:out value='${vo.itemTitle }'/>" maxlength="20"  placeholder="제목"/>
		</div>
		
		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">분류</h6>
			<input type = "radio" name="itemCategory" value="3200" checked="checked" <c:if test="${vo.itemCategory == 'IT'}" >checked</c:if>/>IT&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="3100" <c:if test="${vo.itemCategory == '헬스,뷰티'}" >checked</c:if>/>헬스,뷰티&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="120" <c:if test="${vo.itemCategory == '키즈'}" >checked</c:if>/>키즈&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="100" <c:if test="${vo.itemCategory == '패션'}" >checked</c:if>/>패션&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="3800"<c:if test="${vo.itemCategory == '푸드'}" >checked</c:if>/>푸드&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="3700" <c:if test="${vo.itemCategory == '교육'}" >checked</c:if>/>교육&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="3600" <c:if test="${vo.itemCategory == '의료'}" >checked</c:if>/>의료&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="3500" <c:if test="${vo.itemCategory == '물류'}" >checked</c:if>/>물류&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="3400" <c:if test="${vo.itemCategory == '문화콘텐츠'}" >checked</c:if>/>문화콘텐츠&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="3300" <c:if test="${vo.itemCategory == '금융'}" >checked</c:if>/>금융&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="110" <c:if test="${vo.itemCategory == '도시재생'}" >checked</c:if>/>도시재생&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemCategory" value="130" <c:if test="${vo.itemCategory == '기타'}" >checked</c:if>/>기타&nbsp;&nbsp;&nbsp;
		</div>
		
		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">지역</h6>
			<input type = "radio" name="itemRegion" value="10" checked="checked" <c:if test="${vo.itemRegion == '서울'}" >checked</c:if>/>서울&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="20" <c:if test="${vo.itemRegion == '광주'}" >checked</c:if>/>광주&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="30" <c:if test="${vo.itemRegion == '대구'}" >checked</c:if>/>대구&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="40" <c:if test="${vo.itemRegion == '대전'}" >checked</c:if>/>대전&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="50" <c:if test="${vo.itemRegion == '부산'}" >checked</c:if>/>부산&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="60" <c:if test="${vo.itemRegion == '울산'}" >checked</c:if>/>울산&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="110" <c:if test="${vo.itemRegion == '경기'}" >checked</c:if>/>경기&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="100" <c:if test="${vo.itemRegion == '독도'}" >checked</c:if>/>독도&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="90" <c:if test="${vo.itemRegion == '울릉도'}" >checked</c:if>/>울릉도&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="80" <c:if test="${vo.itemRegion == '제주도'}" >checked</c:if>/>제주도&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemRegion" value="70" <c:if test="${vo.itemRegion == '인천'}" >checked</c:if>/>인천&nbsp;&nbsp;&nbsp;
		</div>
		
		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">기간</h6>
			<input type="text" id="itemStartDate" name="itemStartDate" value="<c:out value='${vo.itemStartDate }'/>" placeholder="시작 날짜"> ~ 
			<input type="text" id="itemEndDate" name="itemEndDate" value="<c:out value='${vo.itemEndDate }'/>" placeholder="끝 날짜">
		</div>
				
		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">참여자</h6>		
			<input type="text"  class="form-control" name="itemCount" id="itemCount"  value="<c:out value='${vo.itemCount }'/>" maxlength="20"  placeholder="참여자 수"/>명
		</div>
		
		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">모집 분야</h6>
			<input type = "radio" name="itemBranch" value="10" checked="checked" <c:if test="${vo.itemBranch == '기획'}" >checked</c:if>/>기획&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemBranch" value="20" <c:if test="${vo.itemBranch == '디자인'}" >checked</c:if>/>디자인&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemBranch" value="30" <c:if test="${vo.itemBranch == '개발'}" >checked</c:if>/>개발&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemBranch" value="40" <c:if test="${vo.itemBranch == '마케팅'}" >checked</c:if>/>마케팅&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="itemBranch" value="50" <c:if test="${vo.itemBranch == '기타'}" >checked</c:if>/>기타&nbsp;&nbsp;&nbsp;
		</div>	
			
		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">내용</h6>
			<input type="text"  class="form-control" name="itemContents" id="itemContents"  value="<c:out value='${vo.itemContents }'/>" maxlength="20"  placeholder="내용"/>
		</div>
		
		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">투자 금액</h6>
			<input type="text"  class="form-control" name="itemInvestment" id="itemInvestment"  value="<c:out value='${vo.itemInvestment }'/>" maxlength="20"  placeholder="투자금액"/>
		</div>	

		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">로열티</h6>
			<input type="text"  class="form-control" name="itemRoyalty" id="itemRoyalty"  value="<c:out value='${vo.itemRoyalty }'/>" maxlength="20"  placeholder="로열티"/>
		</div>
		
		<div class="form-inline" style="padding-bottom:20px;">
			<h6 style="width:100px; text-align: left; margin:0px;">상환 날짜</h6>
			<input type="text" id="itemRepaydate" name="itemRepaydate" value="<c:out value='${vo.itemRepaydate }'/>" placeholder="상환 날짜">
		</div>
	</form>
<!--  form area -->

	<!-- Button area -->
	<div style="padding-top: 20px; text-align: center;">		
		<input type="button" class="btn btn-general btn-green" value="완료" id="item_save_btn"/>		
		<input type="button" class="btn btn-general btn-green" value="취소" id="item_del_btn" />
	</div>
	<!-- Button area -->
	
	</div>

<!-- script -->
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script src="<%= HS2JConst.context %>/js/bootstrap.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		var aa = '${vo.itemSeq}';
				
		$("#item_del_btn").click(function(){
			if(false==confirm("취소 하시겠습니까?")) return;
			
			var itemfrmEdit = document.itemfrmEdit;
			itemfrmEdit.workDiv.value = "1200";
			itemfrmEdit.submit();
		});
		
		$("#item_save_btn").click(function(){
			 if(true==checkValide()){
				if(aa==""){
					if(false==confirm("저장 하시겠습니까?")) return;
					
					var itemfrmEdit = document.itemfrmEdit;
					itemfrmEdit.workDiv.value = "2000";
					itemfrmEdit.action = "/HS2J/item.do";
					itemfrmEdit.submit();	
				}else{
					var itemfrmEdit = document.itemfrmEdit;
					itemfrmEdit.workDiv.value = "3200";
				    itemfrmEdit.itemSeq.value = seq;
					itemfrmEdit.action = "/HS2J/item.do";
					itemfrmEdit.submit();
				}
			}
		});
	});
	
	function checkValide(){
		//제목
		if(""==$("#itemTitle").val()){
			alert("제목을 입력 하세요.");
			$("#itemTitle").focus();
			return false;
		}
		//참여자 수
		if(""==$("#itemCount").val()){
			alert("참여자 수를 입력 하세요.");
			$("#itemCount").focus();
			return false;
		}
		//시작 날짜
		if(""==$("#itemStartDate").val()){
			alert("시작 날짜를 입력 하세요.");
			$("#itemStartDate").focus();
			return false;
		}
		//끝 날짜
		if(""==$("#itemEndDate").val()){
			alert("끝 날짜를 입력 하세요.");
			$("#itemEndDate").focus();
			return false;
		}
		//내용
		if(""==$("#itemContents").val()){
			alert("내용을 입력 하세요.");
			$("#itemContents").focus();
			return false;
		}
		//투자 금액
		if(""==$("#itemInvestment").val()){
			alert("투자 금액를 입력 하세요.");
			$("#itemInvestment").focus();
			return false;
		}
		//로열티
		if(""==$("#itemRoyalty").val()){
			alert("로열티를 입력 하세요.");
			$("#itemRoyalty").focus();
			return false;
		}
		//상환 날짜
		if(""==$("#itemRepaydate").val()){
			alert("상환 날짜를 입력 하세요.");
			$("#itemRepaydate").focus();
			return false;
		}
		return true;
	}
	$(function() {
	    $( "#itemStartDate" ).datepicker({
	    	minDate: 0,
	    	dateFormat: 'yy-mm-dd'
	    });
	    $( "#itemEndDate" ).datepicker({
	    	minDate: 0,
	    	dateFormat: 'yy-mm-dd'
	    });
	    $( "#itemRepaydate" ).datepicker({
	    	minDate: 0,
	    	dateFormat: 'yy-mm-dd'
	    });
	  });
	
</script>
<!--// script -->

</body>
</html>