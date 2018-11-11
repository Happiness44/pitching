<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="com.hs2j.comm.StringUtil"%>
<%@page import="com.hs2j.comm.DTO"%>
<%@page import="java.util.List"%>
<%@page import="com.hs2j.code.dao.CodeDAO"%>
<%@page import="com.hs2j.code.vo.CodeVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.hs2j.comm.HS2JConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
         
<%--
  /**
  * @Class Name : user_list.jsp
  * @Description : 회원조회 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018.04.23            최초 생성
  *
  * author 실행환경 개발팀
  * since 2018.04.23
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%
    Logger log = Logger.getLogger(this.getClass());
	//paging
	String searchDiv	= "";//검색구분
	String searchWord	= "";//검색어
	String pageSize 	= "10";//PageSize
	String pageNum 		= "1";//Page넘버
	
	int totalCnt = 0;//총글수
	int bottomCount =10;//Bottom Count
	
	//req.setAttribute("totalCnt", totalCnt);
	//req.setAttribute("paramVO", inVO);
	UserVO param = (UserVO)request.getAttribute("paramVO");
	if(null != param){
		searchDiv =StringUtil.nvl(param.getSearchDiv(),"");//검색구분
		searchWord=StringUtil.nvl(param.getSearchWord(),"");//검색어
		pageNum =StringUtil.nvl(param.getPageNum(),"1");//pageNum
		pageSize = StringUtil.nvl(param.getPageSize(),"10");//pageSize
	}
	
	int o_page_size = Integer.parseInt(pageSize);
	int o_page_num = Integer.parseInt(pageNum);
	
	String iTotalCnt= (null == request.getAttribute("totalCnt"))?"0":request.getAttribute("totalCnt").toString();
	totalCnt = Integer.parseInt(iTotalCnt);
	
	log.debug("searchDiv="+searchDiv);
	log.debug("searchWord="+searchWord);
	log.debug("pageNum="+pageNum);
	log.debug("pageSize="+pageSize);
	
    //code조회:검색조건
    CodeVO vo01=new CodeVO();
    vo01.setMstId("USER_CATEGORY");
    CodeDAO codeDao=new CodeDAO();
	List<DTO> list = codeDao.do_selectList(vo01);
    //--code조회	
    
    String  msg = StringUtil.nvl(request.getAttribute("message"),"");
    if(""!=msg){
    	//message:초기화 
    	request.setAttribute("message","");
    
    	out.println("<script>alert('" +msg+ "');</script>");
    }
    
%>  
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<%=HS2JConst.context%>/js/bootstrap.min.js"></script>
</head>
<body>
<!--====================================================
                       HOME-P
======================================================-->
    <div id="home-p" class="home-p pages-head2 text-center">
      <div class="container">
		<p>회원관리를 위한</p>
		<h1  class="wow fadeInUp" data-wow-delay="0.1s">User 관리</h1>
      </div><!--/end container-->
    </div>  

	
<section id="cart" class="cart">
   <div class="container" style="padding-top: 30px; padding-bottom: 30px;">
    <!-- Form area -->
    <form name = "frm" id = "frm" action="<%=HS2JConst.context%>/user.do" method = "post">
    	<input type="hidden"  name="workDiv" id="workDiv" />
    	<input type="hidden"  name="pageNum" id="pageNum" />
    	<input type="hidden"  name="userId"  id="userId"/>

    	<!-- Search Area -->
 		<div class="container-fluid text-right">
			<%= StringUtil.makeSelectBox(list,searchDiv, "searchDiv", true) %>
	    	<input type="text" name=searchWord id="searchWord"  value="<%=searchWord %>" maxlength="20" size="20" />
	    	&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button"  class="btn btn-default btn-sm" value="조회" id="retrieve_btn"  />  
			<input type="button"  class="btn btn-default btn-sm" value="등록"  id="save_btn" />  	
			<input type="button"  class="btn btn-default btn-sm" value="메인" id="move_btn" />  
	   	 </div>
	    	<!--// Search Area -->
    	<!-- Table list -->
    
    	<!--check no	ID	이름	비번	사용여부	이메일	등록일  -->
    	<p>
    	<div class="table-responsive container-fluid text-left">
    	<table  id="listTable" class="table table-hover table-condensed">
    		<thead>
    			<tr>
    			    
    			    <th style="width:5%" class="text-center">NO</th>
    			    <th style="width:13%" class="text-center">계정</th>
    			    <th style="width:10%" class="text-center">이름</th>
    			    <th style="width:13%" class="text-center">아이디</th>
    			    <th style="width:13%" class="text-center">이메일</th>
    			    <th style="width:10%" class="text-center">핸드폰</th>
    			    <th style="width:15%" class="text-center">전문 분야</th>
    			    <th style="width:15%" class="text-center">경력 사항</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:choose>
    			    <c:when test="${list.size()>0}">
    					<c:forEach  var="userVO"  items="${list}">
    						<tr>
    						   
    						    <td class="text-center"><c:out value="${userVO.num}"/></td>
    						    <td class="text-center"><c:out value="${userVO.userAccount}"/></td>
    							<td class="text-center"><c:out value="${userVO.userName}"/></td>
    							<td class="text-center"><c:out value="${userVO.userId}"/></td>
    							<td class="text-center"><c:out value="${userVO.userEmail}"/></td>
    							<td class="text-center"><c:out value="${userVO.userPNumber}"/></td>
    							<td class="text-center"><c:out value="${userVO.userSpecialism}"/></td>
    							<td class="text-center"><c:out value="${userVO.userWork}"/></td>
    						</tr>
    					</c:forEach>  
    				</c:when>
    				<c:otherwise>
    					<tr>
    						<td colspan="99">등록된 게시글이 없습니다.</td>
    					</tr>
    				</c:otherwise>
    			</c:choose>
    		</tbody> 
    	</table> 
    	<!-- // Table list -->
    </div>
    	<!-- paging -->
    	<div>
    		<%=StringUtil.renderPaging(totalCnt, o_page_num, o_page_size, bottomCount, "/user.do", "ctrSelectPage") %>
    	</div>
    </form>
  </div>
    <!-- // Form area -->
</section>
<script type="text/javascript">

	//Page이동
	function ctrSelectPage(url,page_num){
		//alert(url+"|"+page_num);
		var frm = document.frm;
		frm.workDiv.value = "1000";
		frm.pageNum.value = page_num;
		frm.action="/HS2J/"+url;
		frm.submit();
	}
	
	/*조회*/
	function ctrSelectList(){
		var frm = document.frm;
		frm.workDiv.value = "1000";
		frm.action="/HS2J/user.do";
		frm.submit();
	}	
	
	/*등록화면으로 이동*/
	function ctrSave(){
		var frm = document.frm;
		frm.workDiv.value = "2100";
		frm.action="/HS2J/user.do";
		frm.submit();		
		
	}
	
	//메인화면으로 이동
	function ctrMove(){
		var frm = document.frm;
		frm.workDiv.value = "2200";
		frm.action="/HS2J/user.do";
		frm.submit();
		
	}
	$(document).ready(function(){
		//alert('document');
		   console.log('${message}');
		   if("" != '${message}'){
		      alert('${message}');
		   }
		//Grid Click
		$("#listTable>tbody").on("click","tr",function(){
			//alert("tr");
			var curTR = $(this);
			var curTD = curTR.children();
			var userId= curTD.eq(3).text();
			alert("userId="+userId);
			var frm = document.frm;
			frm.workDiv.value = "1100";
			frm.userId.value = userId;
			frm.action="/HS2J/user.do";
			frm.submit();
			
			
		});
		   $("#move_btn").click(function(){
			   
		    	  ctrMove();      
		}); 
		   $("#save_btn").click(function(){
				   ctrSave();      
		}); 
		   $("#retrieve_btn").click(function(){
			   ctrSelectList();      
		}); 
		
	});//--document
</script>
</body>
</html>