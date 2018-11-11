<%--
  /**
  * @Class Name : req_list.jsp
  * @Description : 받은/보낸 요청 목록
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018. 4. 23.     SIST01      최초 생성
  *
  * author SIㅇST01
  * since 2018. 4. 23.
  *
  * Copyright (C) 2018 by SIST01  All right reserved.
  */
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>받은/보낸 요청 목록</title>

</head>
<body>
<link rel="stylesheet" type="text/css"	href="/HS2J/css/req_list_style.css" />
<link type="text/css" rel="stylesheet" href="/HS2J/css/simplePagination.css"/>
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="/HS2J/js/jquery.simplePagination.js"></script>
<script src="/HS2J/js/jquery.bpopup.min.js"></script>

<%@page import="com.hs2j.comm.StringUtil"%>
<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="com.hs2j.comm.DTO"%>
<%@page import="com.hs2j.request.dao.RequestDAO"%>
<%@page import="com.hs2j.request.vo.RequestVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());

	//cache Control
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (request.getProtocol().equals("HTTP/1.1"))
		response.setHeader("Cache-Control", "no-cache");
	
	//임시 로그인 처리 (ID: testuser, user_account : 20 창업자)
//	UserVO member = new UserVO();
//	member.setUserId("testuser");
//	member.setUserAccount("20");
	
	
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
		<p class="wow fadeInUp" data-wow-delay="0.4s" style="padding:0">요청 보기</p> 
	</div>
</div>	

<div id="container" class = "container-fluid text-center">
	<!-- hidden area -->
	<form name="hiddenArea" id="hiddenArea">
<!-- 		<input type="hidden" name="currentTab" id="currentTab" /> -->
		<input type="hidden" name="workDiv" id="workDiv" value="1000" /> 
		<input type="hidden" name="user_id" id="user_id"/>
		<input type="hidden" name="user_account" id="user_account" />
		<input type="hidden" name="searchWord" id="searchWord" />

		<input type="hidden" name="searchDiv" id="searchDiv" /> 
		<input type="hidden" name="acceptance" id = "acceptance" />
		
		<input type="hidden" name = "pageNum" id = "pageNum" value="1" />
		<input type="hidden" name = "totalCnt" id = "totalCnt"  />
		<input type="hidden" name = "pageSize" id = "pageSize"  />
		
		<input type="hidden" name = "receiveOrSent" id = "receiveOrSent" value="receive" />
		
	</form>
	<!-- hidden area -->
	
	<div class="reqContainer" style="margin-top:30px; margin-bottom:30px;">
		<ul class="reqTabs">
			<li class="tab-link current" data-tab="receiveTab" id="receiveTab">받은 요청/제안</li>
			<li class="tab-link" data-tab="sentTab" id="sentTab">보낸 요청/제안</li>
		</ul>

		<div id="receiveTabContent" class="tab-content current">
			<%=userId%>님의 받은 요청/투자제안 목록입니다.
			<table class = "req receive table table-striped table-bordered table-hover table-condensed" 
			style="width: 400px; margin-left: auto; margin-right: auto;">
				<thead>
					<th>요청/제안 </th>
					<th>날짜</th>
				</thead>
				<tbody id = "receiveContainer">
				
				</tbody>
			</table>
		</div>

		<div id="sentTabContent" class="tab-content">
			<%=userId%>님의 보낸 요청/투자제안 목록입니다.
			<table class = "req sent table table-striped table-bordered table-hover table-condensed" 
			style="width: 400px; margin-left: auto; margin-right: auto;" >
				<thead>
					<th>요청/제안</th>
					<th>날짜</th>
				</thead>
				<tbody id = "sentContainer">
				
				</tbody>
							
			</table>
		</div>
		
		<div id = "pageArea" class="center-block" style="display:inline-block;"> 
		
		</div>
		
	</div> <!-- reqContainer -->
</div>	
    
	<!-- Layer -->
	<div id = "reqOneView" style="display:none; background-color:white; width: 500px; height: 500px;">
		
	</div></div>
	<!--  // Layer -->			
			
<script type="text/javascript">

	var totalCnt;
	var pageSize;
	var pageNum = 1;
	
	
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
	
	
	function ctrUpdateAcceptance(reqSeq, acceptance){
		console.log("ctrUpdateAcceptance");
		
		var inputData = {
			    "workDiv": "3000", 
			    "seq": reqSeq,
			    "acceptance": acceptance
			};
		
		console.log(inputData);
		
		$.ajax({
			type:"POST",
			url:"/HS2J/req.json",
			dataType:"html",
			data:inputData,
			success:function(data){//성공
				var outMessage = data.split("|");
			
				if(outMessage.length > 0){
		         	alert(outMessage[1]);
		         	console.log(outMessage[0]);
		         	console.log(outMessage[1]);

	         	
		         	if(outMessage[0] == "1") {
		         		$('#reqOneView').bPopup().close();
					}
	        	}
				
				
			},
			complete:function(data){//무조건
			},
			error:function(xhr,status,error){//실패시
				console.log(error);
			}
		});//ajax
		
		
	} //ctrUpdateAcceptance

	//받은거 or 보낸거 list 조회
	function reqList(pageNum, receiveOrSent){	

		var user_account = $("#user_account").val();
		console.log(user_account);
		if(user_account == '10'){
			$("#searchDiv").val('99');
		}
		if(user_account == '20'){
			if(receiveOrSent == 'receive'){
				$("#searchDiv").val('40');
			} else if (receiveOrSent == 'sent'){
				$("#searchDiv").val('20');
			}
		}else if (user_account == '30' || user_account == '40'){
			if(receiveOrSent == 'receive'){
				$("#searchDiv").val('99');
			} else if (receiveOrSent == 'sent'){
				$("#searchDiv").val('10');
			}
		}
		
		
		$("#workDiv").val('1000');
		$("#pageNum").val(pageNum);
		console.log("11111 reqList.pageNum:" + pageNum);
		
		$.ajax({
			type:"POST",
			async: false,
			url:"/HS2J/req.json",
			dataType:"html",
			data:$("#hiddenArea").serialize(),
			success:function(data){//성공

				console.log('data='+data);
				console.log('dataLength='+data.length);
				var container = document.getElementById(receiveOrSent + 'Container');
				var tableText = "";
				
				if(data == '데이터가 없습니다.'){ //투자자라서 받은투자 데이터가 없음
					tableText = '<tr><td colspan="2">' + data + '</td></tr>';
					$("#totalCnt").val(0);
					$("#pageSize").val(1);
				}else if ($.parseJSON(data).length == 0){ //보낸 요청이 없어서 데이터가 없음
					tableText = '<tr><td colspan="2">데이터가 없습니다.</td></tr>';
					$("#totalCnt").val(0);
					$("#pageSize").val(1);
				}else{
					var list = $.parseJSON(data);
				
					tableText = "";
									
					for(i = 0; i < list.length ; i++){
						//reqCategory에 따라 '창업참가/투자요청'
						var joinOrFund = (list[i].reqCategory=="20")?'참가요청':'투자제안';
						
						if(receiveOrSent == 'receive'){
							var tableText = tableText + '<tr><td><a href="javascript:layerPopup();">나의 창업 아이템['
								            + list[i].itemTitle + ']에 [' + list[i].reqSender + ']님의 '
								            + joinOrFund + '</a></td><td>' + list[i].reqDate + '</td><td style="display:none;">' 
							                 + list[i].reqSeq + '</td></tr>';
						}else if(receiveOrSent == 'sent'){
							var tableText = tableText + '<tr><td><a href="javascript:layerPopup();">' + list[i].itemRegPerson + '님의 창업 아이템['
				            + list[i].itemTitle + ']에  나의 ' + joinOrFund + '</a></td><td>' + list[i].reqDate + '</td><td style="display:none;">' 
			                 + list[i].reqSeq + '</td></tr>';
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
	}//reqList
	
	function reqOneView(reqSeq){
		console.log('reqOneView');
			
		var inputData = {
			    "workDiv": "1100", 
			    "seq": reqSeq
			};
		
		console.log(inputData);
		
		$.ajax({
			type:"POST",
			url:"/HS2J/req.json",
			dataType:"html",
			data:inputData,
			success:function(data){//성공
				console.log('data:'+data);
				var list = $.parseJSON(data);
				console.log('list.itemTitle:'+list.itemTitle);
				console.log('list.reqSeq:'+list.reqSeq);
				console.log('list.reqCategory'+list.reqCategory);
				
				var receiveOrSent = $("#receiveOrSent").val();
				console.log(receiveOrSent);
				
				var tableTextAll = "";
				var joinOrFund = (list.reqCategory=='20')?'참가요청':'투자제안';
				var tableTextFundMoney = '<p><p><table class="table bordered"><tr><td>금액</td></tr><tr><td>' + list.reqMoney + '원</td></tr></table>'; //투자금액
				var reqOneView = document.getElementById('reqOneView');
				
					var tableTextInfo = '<div style="margin:10px;">'
					+ '<h4>나의 창업 아이템 [' + list.itemTitle + ']에 [' + list.reqSender + ']님의 ' + joinOrFund + '</h4>'
					+ '<p><p><table class="table bordered"><tr><td rowspan="2"><img src=\"' + nvl(list.userImgFile,"/HS2J/img/my_page/apeach.jpg") + '\" style="width:110px; height:90px;"></td>'
					+'<td>' + list.reqSender + '</td></tr><tr>'
					+  '<td>직장: ' + nvl(list.userWork,"직장정보없음") + '<p>전문정보: ' + nvl(list.userSpecialism,"전문정보없음") + '</td></tr></table><p><p>'
					+ '<table class="table bordered"><tr><td>메시지</td></tr><tr><td>' + list.reqMessage + '</td></tr></table>';
					
					var tableTextButtons = '<input type="button"  class="btn" value="수락" id="accept_btn" onclick="javascript:ctrUpdateAcceptance(' + list.reqSeq + ',10);"/>'
					+ '&nbsp;&nbsp;<input type="button"  class="btn " value="거절" id="reject_btn" onclick="javascript:ctrUpdateAcceptance(' +list.reqSeq + ',20);" />';
					
					if(receiveOrSent == 'receive'){
						if(list.reqCategory=="20"){
							tableTextAll = tableTextInfo + tableTextButtons;
						}else if(list.reqCategory=="10"){
							tableTextAll = tableTextInfo + tableTextFundMoney + tableTextButtons;
						}
					}else if(receiveOrSent == 'sent'){
						if(list.reqCategory=="20"){
							tableTextAll = tableTextInfo;
						}else if(list.reqCategory=="10"){
							tableTextAll = tableTextInfo + tableTextFundMoney;
						}
					}
	
				reqOneView.innerHTML = tableTextAll;
			},
			complete:function(data){//무조건
			},
			error:function(xhr,status,error){//실패시
				console.log(error);
			}
		});//ajax
	}//reqOneView
	
	
	function paging(receiveOrSent){	
			var totalCnt = $("#totalCnt").val();
			var pageSize = $("#pageSize").val();
			var pageNum = $("#pageNum").val();
			
			console.log("pageSize="+pageSize);
			console.log("pageNum="+pageNum);
			console.log("receiveOrSent="+receiveOrSent);
			
		    $('#pageArea').pagination({
		        items: totalCnt,
		        itemsOnPage: pageSize,
		        currentPage : pageNum,
			    cssStyle: 'light-theme',
		        onPageClick: function(pageNum){
		        	reqList(pageNum,receiveOrSent);
		        	console.log('onPageClick:pageNum='+pageNum+',receiveOrSent='+receiveOrSent);
		        }
		    });
		
	}//paging
	
	function layerPopup(e){
		 modalClose: true,
         $('#reqOneView').bPopup();
	}
	
	$(document).ready(function(){
		
		var hiddenArea = document.hiddenArea;
		hiddenArea.user_id.value = "<%=userId%>";
		hiddenArea.searchWord.value = "<%=userId%>";
		hiddenArea.user_account.value = "<%=userAccount%>";

		console.log("user_id="+hiddenArea.user_id.value);
		console.log("user_account="+hiddenArea.user_account.value);
		
		
		//화면 표시: selectList 및 paging
		reqList(1,'receive'); //첫 화면 표시
		paging('receive');
						
		//탭 전환	
		$('ul.reqTabs li').click(function() {
			var tab_id = $(this).attr('data-tab');
			console.log(tab_id);
			
			if(tab_id == 'receiveTab'){
				$("#receiveOrSent").val('receive');
				reqList(1,'receive');
				paging('receive');
			}else if(tab_id == 'sentTab'){
				$("#receiveOrSent").val('sent');
				reqList(1,'sent');
				paging('sent');
			}
			
			$('ul.reqTabs li').removeClass('current');
			$('.tab-content').removeClass('current');

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
		
	});//document.ready;


</script>

</body>
</html>