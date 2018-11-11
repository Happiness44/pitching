<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<title>창업공간 목록</title>
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>
<body>
<%@page import="java.util.List"%>
<%@page import="com.hs2j.code.dao.CodeDAO"%>
<%@page import="com.hs2j.code.vo.CodeVO"%>
<%@page import="com.hs2j.comm.StringUtil" %>
<%@page import="com.hs2j.comm.DTO" %>

<%

%>
<p>
<!-- hidden area -->
<form name = "hiddenArea" id = "hiddenArea">
	<input type="hidden" name="workDiv" id="workDiv" value="1000" /> 
	<input type="hidden" name = "pageNum" id = "pageNum" value="1" />
	<input type="hidden" name = "totalCnt" id = "totalCnt" value="99" />
	<input type="hidden" name = "pageSize" id = "pageSize" value="10" />
</form>

<!--====================================================
                       HOME-P
======================================================-->
    <div id="home-p" class="home-p pages-head1 text-center">
      <div class="container">
        <h1 class="wow fadeInUp" data-wow-delay="0.1s">창업 공간 정보</h1>
        <p>현재 서울시 강남구의 사무실 정보만 지원하고 있습니다.</p>
      </div><!--/end container-->
    </div> 

<p>
<!--item area-->
   <section id="client" class="client testimonials-p2">
      <div class="container"> 
        <div class="row">
            	
	<c:choose>
	<c:when test="${list.size()>0}">
		
		<c:forEach var = "spaceVO" items="${list}">
			
			<div class="col-md-3 col-sm-12" style="margin-top:30px; margin-right:30px;margin-left:35px; display:inline-block;">
			<div class="client-cont">
			  <img src=https://landthumb-phinf.pstatic.net<c:out value = "${spaceVO.photoUrl}" /> style="width:300px; height:250px;" >
				 <h2>
				 <c:out value = "${fn:substring(spaceVO.title,0,20)}" /></h2>
              <p class="desc"><c:out value = "${spaceVO.price}" /></p>
              <i class="fa fa-arrow-circle-o-right"></i>
              <a href="http://land.naver.com<c:out value="${spaceVO.url}"/> ">네이버 부동산에서 보기</a>
            
            </div></div>
			&nbsp; &nbsp; &nbsp; &nbsp; 
			
		</c:forEach>
	</c:when>
		<c:otherwise>
			데이터가 없습니다.
		</c:otherwise>
	</c:choose>
	  </div>
      </div>        
    </section>  
<!-- //item area -->

<!--  page Area -->
<div id = "pageArea"> 
		
</div>
<!--  //page Area -->

<script type="text/javascript">

//화면보기
function ctrSelectList(){
	var hiddenArea = document.hiddenArea;
	console.log(hiddenArea);
	hiddenArea.workDiv.value = "1000";
	hiddenArea.action = "/HS2J/space.json";
	hiddenArea.submit();
}

$(document).ready(function(){
	
});//document.ready

</script>

</body>
</html>