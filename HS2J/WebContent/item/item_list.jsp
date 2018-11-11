<%--
  /**
  * @Class Name : item_list.jsp
  * @Description : 아이템조회화면
  * @Modification Information
  *
  *   수정일                    수정자                      수정내용
  *  ----------    --------    ---------------------------
  *  2018.4.24        SIST01         최초 생성
  *
  * author SIST01
  * since 2018. 4. 24.
  *
  * Copyright (C) 2018 by SIST01  All right reserved.
  */
--%>
<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="java.util.HashMap"%>
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
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   Logger log = Logger.getLogger(this.getClass());
   
   //로그인처리
   UserVO member = null;
   if(null != session.getAttribute("member")){
      member = (UserVO)session.getAttribute("member");
   }else{
      member = new UserVO();
      member.setUserId("");
   }
   
   //paging
   String searchDiv  = "";//검색구분
   String searchWord = "";//검색어
   String pageSize   = "10";//PageSize
   String pageNum    = "1";//Page넘버
   String itemRegPerson = "";
   String searchDiv1  = "";//검색구분
   String searchDiv2  = "";//검색구분
   String itemInvestment = "";
   String itemTitle = "";
   String itemCount = "";
   String itemCompyn = "";
   String itemStartDate = "";
   String itemEndDate = "";
   
   int    totalCnt   = 0;//총글수
    int    bottomCount= 10;//Bottom Count;

   ItemUserCodeFileReqVO param = (ItemUserCodeFileReqVO)request.getAttribute("paramVO");
   if(null != param){
        searchDiv  = StringUtil.nvl(param.getSearchDiv(),"");//검색구분
        pageSize   = StringUtil.nvl(param.getPageSize(),"10");//PageSize
        pageNum    = StringUtil.nvl(param.getPageNum(),"1");//Page넘버   
        searchDiv2  = StringUtil.nvl(param.getItemRegion(),"");
        searchDiv1  = StringUtil.nvl(param.getItemBranch(),"");
        itemRegPerson  = StringUtil.nvl(param.getItemRegPerson(),"");
        itemInvestment  = StringUtil.nvl(param.getItemInvestment(),"");
        itemTitle  = StringUtil.nvl(param.getItemTitle(),"");
        itemCount  = StringUtil.nvl(param.getItemCount(),"");
        itemCompyn  = StringUtil.nvl(param.getItemCompyn(),"");
        itemStartDate = StringUtil.nvl(param.getItemStartDate(),"");
        itemEndDate = StringUtil.nvl(param.getItemEndDate(),"");
    }

    int o_page_size = Integer.parseInt(pageSize);
    int o_page_num = Integer.parseInt(pageNum);
   
    String iTotalCnt = 
              (null == request.getAttribute("totalCnt"))?"0":request.getAttribute("totalCnt").toString();
    totalCnt  = Integer.parseInt(iTotalCnt);
    
   //코드조회 : 검색조건 아이템 분류
   CodeVO vo01 = new CodeVO();
   CodeDAO dao01 = new CodeDAO();
   vo01.setMstId("ITEM_CATEGORY");
   List<DTO> list =  dao01.do_selectList(vo01);

   //모집분야
   CodeVO vo02 = new CodeVO();
   vo02.setMstId("ITEM_BRANCH");
   List<DTO> listbranch =  dao01.do_selectList(vo02);
   
   //지역
   CodeVO vo03 = new CodeVO();
   vo03.setMstId("ITEM_REGION");
   List<DTO> listregion =  dao01.do_selectList(vo03);
    //--코드조회: 검색조건
   
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 부트스트랩 -->
<link href="<%=HS2JConst.context%>/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<%=HS2JConst.context%>/js/bootstrap.min.js"></script>
</head>
<body>
    <div id="home-p" class="home-p pages-head2 text-center">
      <div class="container">
         <p>아이템 제안과 투자를 한 번에</p>
      <h1  class="wow fadeInUp" data-wow-delay="0.1s">아이템</h1>
      </div><!--/end container-->
    </div> 
<!-- div container -->
<div class="container">
    <!--  form area -->
    <form name="itemfrm" id="itemfrm" action="<%=HS2JConst.context%>/item.do" method = "get">
      <input type="hidden" name="workDiv" id = "workDiv" value="1000"/> 
        <input type="hidden"  name="pageNum" id="pageNum" />
      <input type="hidden" name="itemSeq" id = "itemSeq" /> 
      
       <!--  Search area -->
         <div class="container-fluid text-right" style="padding-top:40px;">
               <table id="searchTable" class="table table-striped table-bordered table-hover table-condensed">
                  <tr>
                     <td>아이템 분류</td>
                     <td><%= StringUtil.makeSelectBox(list,searchDiv, "searchDiv", true) %></td>
                     <td>&nbsp;&nbsp;&nbsp;&nbsp;모집 분야</td>
                     <td><%= StringUtil.makeSelectBox(listbranch,searchDiv1, "itemBranch", true) %></td>
                  </tr>
                  <tr>
                     <td>참여자 수 : </td>
                     <td><input type="text" class="form-control input-sm" name=itemCount id="itemCount" value="<%=itemCount %>"  placeholder="검색어" /></td>
                     <td>투자 금액 : </td>
                     <td><input type="text" class="form-control input-sm" name=itemInvestment id="itemInvestment" value="<%=itemInvestment %>"  placeholder="검색어" /></td>
                  </tr>
                  <tr>
                     <td>시작 날짜 : </td>
                     <td><input type="text" id="itemStartDate" name="itemStartDate" value="<%=itemStartDate %>" placeholder="시작 날짜"></td>
                     <td>끝 날짜 : </td>
                     <td><input type="text" id="itemEndDate" name="itemEndDate" value="<%=itemEndDate %>" placeholder="끝 날짜"></td>
                  </tr>
                  <tr>
                     <td>아이템 이름 : </td>
                     <td><input type="text" class="form-control input-sm" name=itemTitle id="itemTitle" value="<%=itemTitle %>"  placeholder="검색어" /></td>
                     <td>아이템 작성자 : </td>
                     <td><input type="text" class="form-control input-sm" name=itemRegPerson id="itemRegPerson" value="<%=itemRegPerson %>"  placeholder="검색어" /></td>
                  </tr><tr>
                     <td>진행 여부 </td>
                  <td><input type = "radio" name="itemCompyn" id="itemCompyn" value="10" id ="item_branch" checked="checked"/>진행 중
                  <input type = "radio" name="itemCompyn" id="itemCompyn" value="20" id ="item_branch"/>진행 완료</td>
                     
                     <td>&nbsp;&nbsp;&nbsp;&nbsp;지역</td>
                     <td><%= StringUtil.makeSelectBox(listregion,searchDiv2, "itemRegion", true) %></td>
                  </tr>
                  <tr>
                     <td><input type="button" class="btn btn-general btn-green" value="조회" id="retrieve_btn" onclick="javascript:itemctrSelectList();"/></td>
                     <c:if test="${null != member.getUserId()}">   
                        <td><input type="button" class="btn btn-general btn-green" value="아이템 등록" id="save_btn" onclick="javascript:itemctrSave();"/></td>
                     </c:if>
                  </tr>
               </table>
         </div>
       <!-- // Search area -->
    
      <!-- table list -->
      <div class="table-responsive container-fluid text-left">
      <!--  <table id="listTable" class="table  table-striped table-bordered table-hover table-condensed"> -->
           <table id="listTable" class="table table-bordered table-hover table-condensed">
              <thead class="bg-primary">
                 <tr>
                    <th style="display:none" class="text-center col-md-1 col-xs-12">번호</th>
                    <th style="width:10%; font-size: small;" class="text-center col-md-1 col-xs-12">아이템 분류</th>
                    <th style="width:10%" class="text-center col-md-1 col-xs-12">모집분야</th>
                    <th style="width:20%" class="text-center col-md-1 col-xs-12">제목</th>
                    <th style="width:10%" class="text-center col-md-1 col-xs-12">지역</th>
                    <th style="width:10%; font-size: small;" class="text-center col-md-1 col-xs-12">참여자 수</th>
                    <th style="width:10%" class="text-center col-md-1 col-xs-12">작성자</th>
                    <th style="width:10%" class="text-center col-md-1 col-xs-12">시작일</th>
                    <th style="width:10%" class="text-center col-md-1 col-xs-12">종료일</th>
                    <th style="width:10%" class="text-center col-md-1 col-xs-12">투자 금액</th>
                 </tr>
              </thead>  
           <tbody>
              <c:choose>
                     <c:when test="${list.size() > 0}"> 
                        <c:forEach var="itemUserCodeFileReqVO" items = "${list}" >
                           <tr>
                                <td style="display:none" ><c:out value = "${itemUserCodeFileReqVO.itemSeq}" /></td>
                                <td style="width:10%; font-size: small;" class="text-center col-md-1 col-xs-12"><c:out value = "${itemUserCodeFileReqVO.itemCategory}" /></td>
                              	<td style="width:10%" class="text-center"><c:out value = "${itemUserCodeFileReqVO.itemBranch}" /></td>
                                <td style="width:20%"><c:out value = "${itemUserCodeFileReqVO.itemTitle}" /></td>
                                <td style="width:10%"><c:out value = "${itemUserCodeFileReqVO.itemRegion}" /></td>
                                <td style="width:10%; font-size: small;" class="text-center col-md-1 col-xs-12"><c:out value = "${itemUserCodeFileReqVO.itemCount}" /></td>
                                <td style="width:10%"><c:out value = "${itemUserCodeFileReqVO.itemRegPerson}" /></td>
                                <td style="width:10%"><c:out value = "${itemUserCodeFileReqVO.itemStartDate}" /></td>
                                <td style="width:10%"><c:out value = "${itemUserCodeFileReqVO.itemEndDate}" /></td>
                                <td style="width:10%"><c:out value = "${itemUserCodeFileReqVO.itemInvestment}" /></td>
                           </tr>                        
                        </c:forEach>
                     </c:when>
                     
                     <c:otherwise>
                        <tr>
                           <td colspan = "9">등록된 아이템이 없습니다.</td>
                        </tr>
                     </c:otherwise>
                     
                  </c:choose>
           </tbody>
        </table>
        </div>  
      <!-- //table list -->
      
      <!-- Paging -->
        <div>
             <%=StringUtil.renderPaging(totalCnt, o_page_num, 10, bottomCount, "/item.do", "itemctrSelectPage") %>
        </div>
        <!-- Paging -->
   
   </form>
   <!--  //form area -->
</div>    
<!--// div container -->

<!-- script -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script src="<%= HS2JConst.context %>/js/bootstrap.min.js"></script>
<script type = "text/javascript">

   $(document).ready(function(){   
      if("" != '${message}'){
         alert('${message}');
      }
      
      //Grid 
      $("#listTable>tbody").on("click","tr",function(){
         var curTR = $(this);
         var curTD = curTR.children();
         var itemSeq = curTD.eq(0).text();
         
         //alert(itemSeq);
         if(itemSeq != "등록된 아이템이 없습니다."){
	         var itemfrm = document.itemfrm;
	         itemfrm.workDiv.value = "1100";
	         itemfrm.itemSeq.value = itemSeq;
	         itemfrm.action = "/HS2J/item.do";
	         itemfrm.submit();
         }
      });
   });
   
   function itemctrSelectPage(url,page_num){
       //alert(url+"|"+page_num);
       var itemfrm = document.itemfrm;
       itemfrm.workDiv.value = "1000";
       itemfrm.pageNum.value = page_num;
       itemfrm.action="/HS2J/"+url;
       itemfrm.submit();      
    }
 
   /* 조회 */
   function itemctrSelectList(){
      var itemfrm = document.itemfrm;
      console.log(itemfrm);
      itemfrm.workDiv.value = "1000";
      itemfrm.action = "/HS2J/item.do";
      itemfrm.submit();
   }
   
   /* 등록화면으로 이동 */
   function itemctrSave(){
      var itemfrm = document.itemfrm;
       itemfrm.workDiv.value = "2100";
       itemfrm.action = "/HS2J/item.do";
       itemfrm.submit();
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
     });
</script>
<!--// script -->


</body>
</html>