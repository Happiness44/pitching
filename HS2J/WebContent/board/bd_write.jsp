<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="com.hs2j.board.vo.BoardVO"%>
<%@page import="com.hs2j.comm.DTO"%>
<%@page import="java.util.List"%>
<%@page import="com.hs2j.code.dao.CodeDAO"%>
<%@page import="com.hs2j.code.vo.CodeVO"%>
<%@page import="com.hs2j.comm.StringUtil"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hs2j.comm.HS2JConst"%>
<%-- 글보기--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%
	Logger log = Logger.getLogger(this.getClass());

	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type"); 	
	
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
	String addType = "";
	String title = "";
	String sentence = "";
	
	//code조회:검색조건
	CodeVO vo01 = new CodeVO();
	CodeDAO codeDao=new CodeDAO();
	
	List<DTO> list = null;
	
	if(type.equals("2100")){
		title = "PITCHING";
		sentence = "공지사항";
		num = "1000";
		addType = "2000";
		vo01.setMstId("BD_WITHIN_CATEGORY");
		list = codeDao.do_selectList(vo01);
	}else if(type.equals("2200")){
		title = "PITCHING";
		sentence = "창업뉴스";
		num = "1010";
		addType = "2010";
		vo01.setMstId("BD_WITHIN_CATEGORY");
		list = codeDao.do_selectList(vo01);
		list.remove(0);
	}else if(type.equals("2300")){
		title = "PITCHING";
		sentence = "창업후기";
		num = "1020";
		addType = "2020";
		vo01.setMstId("ITEM_CATEGORY");
		list = codeDao.do_selectList(vo01);
	}
	
	//category처리 
	String category = "";
	BoardVO vo = null;
	if(null != request.getAttribute("vo")){
		vo = (BoardVO)request.getAttribute("vo");
		category = StringUtil.nvl(vo.getBdCategory(),"");
	}else{
		category = "1";
		vo =new BoardVO();
	}
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
    <div class="row title-bar" style="padding:0">
    	<div class="col-md-12">
    		<h1 class="wow fadeInUp"><%=title %></h1>
    		<div class="heading-border"></div>
    		<p class="wow fadeInUp" data-wow-delay="0.4s" style="padding:0"><%=sentence %></p> 
    	</div>
    </div>
	<!-- div container -->
	<div class="container" style="padding-top: 30px; padding-bottom: 30px;">
		<form name = "boardfrmEdit" id = "boardfrmEdit" class="form-horizontal" action = "<%=HS2JConst.context%>/board.do" method="post" >
			<input type="hidden"  name="workDiv"  id="workDiv" />
			<input type="hidden"  name="bdRegPerson"  id="bdRegPerson" value="<%=member.getUserId()%>"/>
			<div class="form-group" <%if(type.equals("2100")) {%> style="display:none" <%}%>>
				<div class="col-sm-12">
					<%= StringUtil.makeSelectBox(list, category, "bdWithinCategory", false) %>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12">
					<input type="text" class="form-control" name="bdTitle" id="bdTitle" placeholder="title" value="<c:out value='${vo.bdTitle}'/>">
				</div>
			</div>

			<div class="form-group">
    			<div class="col-sm-12">
	    			<textarea class="form-control" rows="20" name="bdContents" id="bdContents" placeholder="content"><c:out value='${vo.bdContents}'/></textarea>
    			</div>
    		</div>
			
			<div class="form-group col-md-12 text-center">
				<a class="btn btn-general btn-green" id="doOk" onclick="javascript:ctrAdd(<%=addType%>)">확인</a>
	      		<a class="btn btn-general btn-green" id="doReturn" onclick="javascript:ctrSelectList(<%=num%>)">되돌아가기</a>
			</div>
		</form>
	</div>
<script type="text/javascript">
/*조회*/
function ctrSelectList(num){
	var boardfrmEdit = document.boardfrmEdit;
	boardfrmEdit.workDiv.value = num;
	boardfrmEdit.action="/HS2J/board.do";
	boardfrmEdit.submit();
}

/*등록*/
function ctrAdd(addType){
	if( false == confirm("저장 하시겠습니까?")) return;
	
	var frm = document.boardfrmEdit;
	boardfrmEdit.workDiv.value = addType;
	boardfrmEdit.bdContents.value = $('#bdContents').val().replace(/\n/g, "<br>")
	boardfrmEdit.action="/HS2J/board.do";
	boardfrmEdit.submit();
}

function checkValide(){
	if(""== $("#title").val()){
		alert("제목을 입력 하세요.");
		$("#title").focus();
	    return false;	
	}		
	return true;
}
</script>
</body>
</html>