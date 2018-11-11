<%--
  /**
  * @Class Name : msg_list.jsp
  * @Description : 
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018. 4. 24.     SIST01      최초 생성
  *
  * author SIST01
  * since 2018. 4. 24.
  *
  * Copyright (C) 2018 by SIST01  All right reserved.
  */
--%>
<%@page import="com.hs2j.comm.StringUtil"%>
<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="com.hs2j.comm.DTO"%>
<%@page import="com.hs2j.message.dao.MessageDAO"%>
<%@page import="com.hs2j.message.vo.MessageVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<title>쪽지 목록</title>
</head>
<body>
<link rel = "stylesheet" type = "text/css" href = "/HS2J/css/req_list_style.css" />
<%
Logger LOG = Logger.getLogger(this.getClass());

//cache Control
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
if (request.getProtocol().equals("HTTP/1.1"))
	response.setHeader("Cache-Control", "no-cache");

//로그인처리
	UserVO member = null;
	String userId = null;
	String userAccount = null;
	
	if(null != session.getAttribute("member")){
		member = (UserVO)session.getAttribute("member");
		userId = member.getUserId();
		userAccount = member.getUserAccount();
		LOG.debug("현재 로그인 유저 ID:" + userId + "/ 계정유형 : " + userAccount);
	}else{
		LOG.debug("로그인 안함");
	}

%>
<div class="row title-bar" style="padding:0">
	<div class="col-md-12">
		<h1 class="wow fadeInUp">Pitching</h1>
		<div class="heading-border"></div>
		<p class="wow fadeInUp" data-wow-delay="0.4s" style="padding:0">쪽지 보기</p> 
	</div>
</div>
<!-- hidden area -->
	<form name="hiddenArea" id="hiddenArea">
<!-- 		<input type="hidden" name="currentTab" id="currentTab" /> -->
		<input type="hidden" name="workDiv" id="workDiv" value="1000" /> 
		<input type="hidden" name="seq" id="seq"  />
		<input type="hidden" name="user_id" id="user_id"/>
		<input type="hidden" name="searchWord" id="searchWord" />

		<input type="hidden" name="searchDiv" id="searchDiv" /> 
		<input type="hidden" name="acceptance" id = "acceptance" />
		
		<input type="hidden" name = "pageNum" id = "pageNum" value="1" />
		<input type="hidden" name = "totalCnt" id = "totalCnt"  />
		<input type="hidden" name = "pageSize" id = "pageSize"  />
		
		<input type="hidden" name = "receiveOrSent" id = "receiveOrSent" value="receive" />
		
	</form>
	<!-- hidden area -->
	
<div id="container" class = "container-fluid text-center" >
<div class = "reqContainer" style="margin-top:30px; margin-bottom:30px; ">
	<ul class = "reqTabs">
		<li class = "tab-link current" data-tab="receiveTab" id="receiveTab"> 받은 쪽지  </li>
		<li class = "tab-link" data-tab="sentTab" id="sentTab"> 보낸 쪽지  </li>
	</ul>

	<div id="receiveTabContent" class="tab-content current">
		~~~<%=member.getUserId()%>님의 받은 쪽지 목록~~~
		<table class = "reqTable table table-striped table-bordered table-hover table-condensed"
		style="width: 400px; margin-left: auto; margin-right: auto;">
		<thead class>
			<th> 쪽지 제목 </th>
			<th> 보낸 사람 </th>
			<th> 날짜 </th>
		</thead>
		<tbody id = "receiveContainer">
				
		</tbody>
		</table>
	</div>

	<div id="sentTabContent" class="tab-content">
		~~~<%=member.getUserId()%>님의 보낸 쪽지 목록 ~~~
		<table class = "reqTable table table-striped table-bordered table-hover table-condensed"
		style="width: 400px; margin-left: auto; margin-right: auto;">
		<thead>
			<th> 쪽지 제목  </th>
			<th> 받는 사람 </th>
			<th> 날짜 </th>
		</thead>
		<tbody id = "sentContainer">
				
		</tbody>
		</table>
	</div> 
	
	<div id = "pageArea" class="center-block" style="display:inline-block;"> 
		
	</div>
	</div>
	
	
		
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link type="text/css" rel="stylesheet" href="/HS2J/css/simplePagination.css"/>
<script type="text/javascript" src="/HS2J/js/jquery.simplePagination.js"></script>
<script type="text/javascript">
//null값 처리 함수
function nvl(str, defaultVal) {
    var defaultValue = "";
     
    if (typeof defaultVal != 'undefined') {
        defaultValue = defaultVal;
    }
     
    if (typeof str == "undefined" || str == null || str == '' || str == "undefined") {
        return defaultValue;
    }
     
    return str;
}//nvl

/*개별 뷰 화면으로 이동*/
function msgOneView(msgSeq){
	var hiddenArea = document.hiddenArea;
	hiddenArea.workDiv.value = "1100";
	hiddenArea.seq.value = msgSeq;

	var newWindow = window.open("about:blank","newWindow",
			'scrollbars=no, toolbar=no, resizable=no,width=350, height=450');
	hiddenArea.target = "newWindow";
	hiddenArea.action = "/HS2J/msg.json";
	hiddenArea.submit();
	
}//msgOneView

//받은거 or 보낸거 list 조회
function msgList(pageNum, receiveOrSent){	

	var user_account = $("#user_account").val();
	console.log(user_account);
	
	
		if(receiveOrSent == 'receive'){
			$("#searchDiv").val('20');
		} else if (receiveOrSent == 'sent'){
			$("#searchDiv").val('10');
		}
	
	
	$("#workDiv").val('1000');
	$("#pageNum").val(pageNum);
	console.log("11111 msgList.pageNum:" + pageNum);
	
	$.ajax({
		type:"POST",
		async: false,
		url:"/HS2J/msg.json",
		dataType:"html",
		data:$("#hiddenArea").serialize(),
		success:function(data){//성공

			console.log('data='+data);
			var container = document.getElementById(receiveOrSent + 'Container');

			if(data == '데이터가 없습니다.'){
				var tableText = '<tr><td colspan="3">' + data + '</td></tr>';
				$("#totalCnt").val(0);
				$("#pageSize").val(1);
			}else if ($.parseJSON(data).length == 0){ //보낸 요청이 없어서 데이터가 없음
				var tableText = '<tr><td colspan="3">데이터가 없습니다.</td></tr>';
				$("#totalCnt").val(0);
				$("#pageSize").val(1);	
			}else{
				var list = $.parseJSON(data);
				console.log(list);							
			
				var tableText = "";
								
				for(i = 0; i < list.length ; i++){
					//TODO: 보낸/받는 쪽지에 따라 보낸 사람 or 받는 사람
					console.log('msgList.receiveOrSent' + receiveOrSent);
					if(receiveOrSent == 'receive'){
						var tableText = tableText + '<tr><td><a href="javascript:msgOneView(' + list[i].seq + ');">'
							            + list[i].title + '</td><td>' + list[i].sender + '</td><td>'
							            + list[i].msgDate + '</td><td style="display:none;">' 
						                 + list[i].seq + '</td></tr>';
					}else if(receiveOrSent == 'sent'){
						var tableText = tableText + '<tr><td><a href="javascript:msgOneView(' + list[i].seq + ');">'
			            + list[i].title + '</td><td>' + list[i].receiver + '</td><td>'
			            + list[i].msgDate + '</td><td style="display:none;">' 
		                 + list[i].seq + '</td></tr>';
					}
					
				}
				
				//totalCnt = list[0].totalCnt;
				//pageSize = list[0].pageSize;
				$("#totalCnt").val(list[0].totalCnt);
				console.log('totalCnt:'+$("#totalCnt").val());
				$("#pageSize").val(list[0].pageSize);
				console.log('pageSize:'+$("#pageSize").val());
			}
			container.innerHTML = tableText;
		},
		complete:function(data){//무조건
		},
		error:function(xhr,status,error){//실패시
			console.log(error);
		}
	});//ajax
	}//msgList

	function paging(receiveOrSent){	
		var totalCnt = $("#totalCnt").val();
		var pageSize = $("#pageSize").val();
		var pageNum = $("#pageNum").val();
		var receiveOrSent = $("#receiveOrSent").val();
		
		console.log("pageSize="+pageSize);
		console.log("pageNum="+pageNum);
		console.log("receiveOrSent="+receiveOrSent);
		
	    $('#pageArea').pagination({
	        items: totalCnt,
	        itemsOnPage: pageSize,
	        currentPage : pageNum,
		    cssStyle: 'light-theme',
	        onPageClick: function(pageNum){
	        	msgList(pageNum,receiveOrSent);
	        	console.log('onPageClick:pageNum='+pageNum+',receiveOrSent='+receiveOrSent);
	        }
	    });
	
	}//paging

$(document).ready(function(){
		
		var hiddenArea = document.hiddenArea;
		hiddenArea.user_id.value = "<%=member.getUserId()%>";
		hiddenArea.searchWord.value = "<%=member.getUserId()%>";

		//화면 표시: selectList 및 paging
		msgList(1,'receive'); //첫 화면 표시
		paging('receive');
		
		
		
		$('ul.reqTabs li').click(function(){
			var tab_id = $(this).attr('data-tab');
			
			$('ul.reqTabs li').removeClass('current');
			$('.tab-content').removeClass('current');
			
			if(tab_id == 'receiveTab'){
				$("#receiveOrSent").val('receive');
				msgList(1,'receive');
				paging('receive');
			}else if(tab_id == 'sentTab'){
				$("#receiveOrSent").val('sent');
				msgList(1,'sent');
				paging('sent');
			}
			
			$(this).addClass('current');
			$("#" + tab_id + 'Content').addClass('current');
		})
		
		//Grid Click
		//thead 말고 tbody부터 클릭
		$(".req").on("click","tr",function(){
			var curTR = $(this);
			var curTD = curTR.children();
			var reqSeq = curTD.eq(2).text();
			console.log(reqSeq);
			reqOneView(reqSeq);
			
		});//--Grid Click
		
		
	});//document.ready
	</script>	
</body>
</html>