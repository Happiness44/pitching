<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="com.hs2j.comm.DTO"%>
<%@page import="java.util.List"%>
<%@page import="com.hs2j.code.dao.CodeDAO"%>
<%@page import="com.hs2j.code.vo.CodeVO"%>
<%@page import="com.hs2j.comm.StringUtil"%>
<%@page import="com.hs2j.board.vo.BoardVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.hs2j.comm.HS2JConst"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<%-- 전체 글 리스트를 보여주는 화면 / 창업 후기 - 필터링(제목/작성자/분류)  --%>
<%-- https://coderanch.com/t/594720/languages/Show-Hide-text-box-select --%>
<%
	Logger log = Logger.getLogger(this.getClass());

	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	log.debug("type="+type);
	
	UserVO member = null;
	String userId = null;
	String userAccount = null;
	
	if(null != session.getAttribute("member")){
		member = (UserVO)session.getAttribute("member");
		userId = member.getUserId();
		userAccount = member.getUserAccount();
		log.debug("현재 로그인 유저 ID:" + userId + "/ 계정유형 : " + userAccount);
	}else{
		log.debug("로그인을 하지 않았습니다.");
	}
	
	String num = "";
	String pageType = "";
	String title = "";				//bootstrap에 사용될 제목
	String sentence = "";			//bootstrap에 사용될 문구			
	
	if(type.equals("2100")){		//2100 공지사항
		num = "1000";
		sentence = "피칭 사용자들을 위한";	
		title = "공지사항";
		pageType = "ctrSelectPageNt";
	}else if(type.equals("2200")){	//2200 창업뉴스
		num = "1010";
		sentence = "예비 창업인들을 위한";
		title = "창업뉴스";
		pageType = "ctrSelectPageNw";
	}else if(type.equals("2300")){	//2300 창업후기
		num = "1020";
		sentence = "창업인들을 위한 공간";
		title = "창업후기";
		pageType = "ctrSelectPageHg";
	}
	
	String searchDiv = "";			//검색 구분(10:제목  /20:작성자 /30:분류)
	String searchWord = "";			//검색어
	String pageSize = "10";			//PageSize
	String pageNum = "1";			//PageNum
	
	int totalCnt = 0;				//총 글수
	int bottomCount = 10;			//Bottom Count;
	
	BoardVO param = (BoardVO)request.getAttribute("paramVO");
    if(null != param){
        searchDiv  = StringUtil.nvl(param.getSearchDiv(),"");		//검색구분
        searchWord = StringUtil.nvl(param.getSearchWord(),"");		//검색어
        pageSize   = StringUtil.nvl(param.getPageSize(),"10");		//PageSize
        pageNum    = StringUtil.nvl(param.getPageNum(),"1");		//Page넘버    	
    }
    
    int o_page_size = Integer.parseInt(pageSize);
    int o_page_num = Integer.parseInt(pageNum);
    
    String iTotalCnt = 
        	(null == request.getAttribute("totalCnt"))?"0":request.getAttribute("totalCnt").toString();
	totalCnt  = Integer.parseInt(iTotalCnt);

    log.debug("searchDiv="+searchDiv);
    log.debug("searchWord="+searchWord);
    log.debug("pageSize="+pageSize);
    log.debug("pageNum="+pageNum);
    log.debug("totalCnt="+totalCnt);
	
    //code조회:검색조건
    CodeVO vo01 = new CodeVO();
    vo01.setMstId("BD_FILTER");
    CodeDAO codeDao=new CodeDAO();
	List<DTO> tList = codeDao.do_selectList(vo01);
	//--code조회	    
%>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 부트스트랩 -->
<link href="<%=HS2JConst.context%>/css/bootstrap.min.css" rel="stylesheet">

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
		<p><%=sentence%></p>
		<h1  class="wow fadeInUp" data-wow-delay="0.1s"><%=title%></h1>
      </div><!--/end container-->
    </div>  
    
	<section id="cart" class="cart">
		<div class="container" style="padding-top: 30px; padding-bottom: 30px;">
			<form name = "boardfrm" id = "boardfrm" action = "<%=HS2JConst.context%>/board.do" method="get">
				<input type="hidden"  name="workDiv"  id="workDiv" />
				<input type="hidden"  name="bdSeq"  id="bdSeq" />
				<input type="hidden"  name="pageNum" id="pageNum" />
			
		 <div class="table-responsive">	
			<table class="table table-hover table-condensed" id="listTable">
          		<thead>
           			 <tr>
           			 	<th style="display:none">순번</th>
              			<th style="width:15%" class="text-center">분류</th>
              			<th style="width:50%" class="text-left">글 제목</th>
              			<th style="width:15%" class="text-center">작성자</th>
              			<th style="width:30%" class="text-right">작성일</th>
            		</tr>
          		</thead>
				<tbody>
	            	<c:choose>
						<c:when test="${list.size()>0}">
	    					<c:forEach  var="BCSelectListVO"  items="${list}">
	    						<tr>
	    							<td style="display:none"><c:out value="${BCSelectListVO.bdSeq}"/></td>
									<td class="text-center"><c:out value="${BCSelectListVO.cdDtlNm}"/></td>
									<td class="text-left"><c:out value="${BCSelectListVO.bdTitle}"/></td>
									<td class="text-center"><c:out value="${BCSelectListVO.bdRegPerson}"/></td>
									<td class="text-right"><c:out value="${BCSelectListVO.bdRegDate}"/></td>
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
	</div>
		<!-- Grid 영역 / Table list -->
         <!-- 글쓰기(관리자는 모든 게시판의 글을 쓸 수 있음 / 일반 회원은 창업후기만 작성 가능 ) -->
		<%if(member != null){ 
			if(title.equals("공지사항") && member.getUserAccount().equals("10") || title.equals("창업뉴스") && member.getUserAccount().equals("10") || title.equals("창업후기")) {%>		
	         <div class ="row">
    	        <div class ="col-md-12 text-right">
        	       <a class="btn btn-general btn-green" id="doWrite" onclick="javaScript:ctrSave(<%=type%>);">글쓰기</a>
        	    </div>   
        	 </div>
        <%} 
		}%>
         <!--// 글쓰기 -->
      
         <!-- Paging -->
         <div>   
            <%=StringUtil.renderPaging(totalCnt, o_page_num, o_page_size, bottomCount, "board.do", pageType) %>
          </div>
          <!-- Paging -->
      
         <!-- 창업후기의 경우 필터링 생김 -->
         <table style="margin:auto;">
         	<tr>
         		<td>
	         	<%if(type.equals("2300")) {%>
		        <%= StringUtil.makeSelectBox(tList, searchDiv, "searchDiv", true) %>
		        </td>
		        <td>
		        <input type="text" class="form-control input-sm" name=searchWord id="searchWord" value="<%=searchWord%>"  placeholder="검색어"/>
		        </td>
		        <td>
		        <input type="button"  class="btn btn-default btn-sm"  value="검색" id="retrieve_btn" onclick="javaScript:ctrSelectList(<%=num%>);" />   
	         	<%} %>
         		</td>
         	</tr>
         </table>
         <!-- 필터링 -->
			</form>
		</div>
	</section>
<script type="text/javascript">
/*page 이동*/
function ctrSelectPageNt(url, page_num){	//공지사항
	var boardfrm = document.boardfrm;
	boardfrm.workDiv.value = "1000";
	boardfrm.pageNum.value = page_num;
	boardfrm.action="/HS2J/"+url;
	boardfrm.submit();
}

function ctrSelectPageNw(url, page_num){	//창업뉴스
	var boardfrm = document.boardfrm;
	boardfrm.workDiv.value = "1010";
	boardfrm.pageNum.value = page_num;
	boardfrm.action="/HS2J/"+url;
	boardfrm.submit();
}

function ctrSelectPageHg(url, page_num){	//창업후기
	var boardfrm = document.boardfrm;
	boardfrm.workDiv.value = "1020";
	boardfrm.pageNum.value = page_num;
	boardfrm.action="/HS2J/"+url;
	boardfrm.submit();
}

/*조회*/
function ctrSelectList(num){				//필터링 후 창업후기
	var boardfrm = document.boardfrm;
	boardfrm.workDiv.value = num;
	boardfrm.action="/HS2J/board.do";
	boardfrm.submit();
}	

/*등록화면으로 이동*/
function ctrSave(type){
	var boardfrm = document.boardfrm;
	boardfrm.workDiv.value = type;
	boardfrm.action="/HS2J/board.do";
	boardfrm.submit();		
}

$(document).ready(function(){	
	if("" != '${message}'){
		alert('${message}');
	}
	
	//Grid Click
	$("#listTable>tbody").on("click","tr",function(){
		//alert("tr");
		var curTR = $(this);
		var curTD = curTR.children();
		var bdSeq = curTD.eq(0).text();
		
		var boardfrm = document.boardfrm;
		boardfrm.workDiv.value = "1100";
		boardfrm.bdSeq.value = bdSeq;
		
		boardfrm.action="/HS2J/board.do";
		
		boardfrm.submit();
	});
});
</script>
</body>
</html>