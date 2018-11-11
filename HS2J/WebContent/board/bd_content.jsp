<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.hs2j.board.vo.BCSelectOneVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hs2j.comm.HS2JConst"%>
<%-- 글보기--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%
	Logger log = Logger.getLogger(this.getClass());

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


	BCSelectOneVO vo = null;
	if(null != request.getAttribute("vo")){
		vo = (BCSelectOneVO)request.getAttribute("vo");
	}else{
		vo = new BCSelectOneVO();
	}

	String num = "";		//리스트
	String mType = "";		//수정 글쓰기
	String lNum = "";		//삭제 후 리스트
	
	String title = "";		//bootstrap을 위한 제목
	String sentence = "";	//bootstrap을 위한 문구
	
	if(vo.getCdDtlNm().equals("공지사항")){
		num = "1000";
		title = "PITCHING";
		sentence = "공지사항";
		mType = "3000";
		lNum = "4000";
	}else if(vo.getCdDtlNm().equals("창업지원사업") || vo.getCdDtlNm().equals("스타트업이슈")){
		num = "1010";
		title = "PITCHING";
		sentence = "창업뉴스";
		mType = "3100";
		lNum = "4100";
	}else{
		num = "1020";
		title = "PITCHING";
		sentence = "창업후기";
		mType = "3200";
		lNum = "4200";
	}
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
    		<p class="wow fadeInUp" data-wow-delay="0.4s" style="padding:0"><%= sentence %></p> 
    	</div>
    </div>

<!-- div container -->
	<div class="container" style="padding-top: 30px; padding-bottom: 30px;">
		<form name = "boardfrmEd" id = "boardfrmEd" class="form-horizontal" action = "<%=HS2JConst.context%>/board.do" method="get">
			<input type="hidden"  name="workDiv"  id="workDiv" />
			<input type="hidden"  name="bdSeq"  id="bdSeq" />
			<input type="hidden"  name="bdTitle"  id="bdTitle" />
			<input type="hidden"  name="bdContents"  id="bdContents" />
			
			<div class="form-group">
				<div class="col-sm-12" >
					<h4 id="bdTitle text-left" style="padding-bottom: 10px;"><ins>${vo.bdTitle}</ins></h4>
				</div>
				<div class="col-sm-12 text-right">
					<h5 id="cdDtlNm" style="padding-bottom: 5px;">${vo.cdDtlNm}</h5>
				</div>
			</div>

			<div class="form-group">
					<p class="col-sm-12" id="bdContents" style="min-height: 300px;">${vo.bdContents}</p>
    		</div>
	
			<div class="form-group text-right">
				<div class="col-xm-12">
      				<a class="btn btn-general btn-green" id="doList" onclick="javascript:ctrSelectList(<%=num%>)">목록</a>
      					<%if(member != null) {
      						if(member.getUserAccount().equals("10") || sentence.equals("창업후기")&&member.getUserId().equals(vo.getBdRegPerson())) {%>
      				<a class="btn btn-general btn-green" id="doModify" onclick="javascript:ctrModify(<%=mType%>)">수정</a>
      				<a class="btn btn-general btn-green" id="doDelete" onclick="javascript:ctrDel(<%=vo.getBdSeq()%>,<%=lNum%>)">삭제</a>
						<% }
      					} %>
				</div>
			</div>
		</form>
	</div>
<script type="text/javascript">
/*조회*/
function ctrSelectList(num){
	var boardfrmEd = document.boardfrmEd;
	boardfrmEd.workDiv.value = num;
	boardfrmEd.action="/HS2J/board.do";
	boardfrmEd.submit();
}

/*수정화면으로 이동*/
function ctrModify(mType){
   if( false == confirm("수정 하시겠습니까?")) return;
   
   var bdSeq = '${vo.bdSeq}';
   var bdTitle = '${vo.bdTitle}';
   var bdContents = '${vo.bdContents}';
   
   var boardfrmEd = document.boardfrmEd;
   boardfrmEd.workDiv.value = mType;
   boardfrmEd.bdSeq.value = bdSeq;
   boardfrmEd.bdTitle.value = bdTitle;
   boardfrmEd.bdContents.value = bdContents;

   boardfrmEd.action="/HS2J/board.do";
   boardfrmEd.submit();   
}

/*삭제*/
function ctrDel(bdSeq, lNum){
	if( false == confirm("삭제 하시겠습니까?")) return;
	var boardfrmEd = document.boardfrmEd;
	boardfrmEd.workDiv.value = lNum;
	boardfrmEd.bdSeq.value = bdSeq;
	boardfrmEd.action="/HS2J/board.do";
	boardfrmEd.submit();	
}
</script>	
</body>
</html>