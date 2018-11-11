<%@page import="com.hs2j.User.vo.UserVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.hs2j.item.vo.ItemUserCodeFileReqVO"%>
<%@page import="com.hs2j.item.dao.ItemDAO"%>
<%@page import="com.hs2j.item.vo.ItemVO"%>
<%@page import="com.hs2j.comm.DTO"%>
<%@page import="java.util.List"%>
<%@page import="com.hs2j.code.dao.CodeDAO"%>
<%@page import="com.hs2j.code.vo.CodeVO"%>
<%@page import="com.hs2j.comm.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새 요청</title>
<%
	final Logger LOG = Logger.getLogger(this.getClass());

	String royalty = (String) request.getParameter("itemRoyalty");
	String repaydate = (String) request.getParameter("itemRepaydate");
	String investment = (String) request.getParameter("itemInvestment");
	String totalMoney = (String) request.getParameter("totalMoney");
	String itemSeq = (String) request.getParameter("itemSeq");
	
	//코드조회
	CodeVO vo01 = new CodeVO();
	CodeDAO dao01 = new CodeDAO();
	vo01.setMstId("REQ_CATEGORY");
	List<DTO> list =  dao01.do_selectList(vo01);
	
	//아이템 번호 : 임시 코드
	ItemUserCodeFileReqVO ivo = new ItemUserCodeFileReqVO();
	ivo.setItemSeq(itemSeq);
	ItemDAO idao = new ItemDAO();
	List itemList = idao.do_selectOne(ivo);
	ItemUserCodeFileReqVO retVO = (ItemUserCodeFileReqVO) itemList.get(0);
	//LOG.debug("req_new.jsp retVO:" + retVO.toString());

	UserVO member = new UserVO();
	String userId = "";
	if(null != session.getAttribute("member")){
		member = (UserVO)session.getAttribute("member");
		userId = member.getUserId();
		LOG.debug("현재 로그인 유저 ID:" + member.getUserId() + "/ 계정유형 : " + member.getUserAccount());
	}else{
		LOG.debug("로그인 안함");
	}
	
	String reqCategory = "";
	int reqCategoryInt = 0;
	
	if(member.getUserAccount().equals("20")){
		reqCategory = "창업참가";
		reqCategoryInt = 20;
	}else if (member.getUserAccount().equals("30") || member.getUserAccount().equals("40")){
		reqCategory = "투자";
		reqCategoryInt = 30;
	}
	request.setAttribute("reqCategory", reqCategory);
%>

</head>
<body>
<h2>새 요청</h2>

<form id = "reqAddArea" name="reqAddArea"> 
<!-- 화면상 hidden area -->
<input type="hidden" name="workDiv" id="workDiv"  maxlength="20" value="2000" />
<input type="hidden" name="item" id="item"  maxlength="20" value = "3" />
<input type="hidden" name="category" id="category"  maxlength="20" value = "10" />
<input type="hidden" name="sender" id="sender"  maxlength="20" value = "<%=userId%>"/>
<!-- 화면상 hidden area -->

<div>
<h3>[<%= retVO.getItemTitle() %>]아이템에 대한 [<%=reqCategory%>]요청</h3>
</div>
<div>
<label>메시지</label><p>
<textarea id = "reqMessage" name = "reqMessage" rows="10" cols="50" maxlength="4000" placeholder="요청할 메시지를 입력하세요"></textarea>
</div>
	<p>
	<!--  if category = 투자요청이면 여기 표시  -->
	<table>
	<c:choose>
	   <c:when test="${reqCategory=='투자'}">
			<tr>
				<td style="display:none"><%=userId %></td>
				<td>로열티 </td>
				<td><%=royalty %></td>
			</tr>
			<tr>
				<td>로열티 상환 일 </td>
				<td><%=repaydate %></td>
			</tr>
			<tr>
				<td>목표 투자 금액 </td>
				<td><%=investment %></td>
			</tr>
			<tr>
				<td>현 투자 금액 </td>
				<td><%=totalMoney %></td>
			</tr>
			<tr>
				<td>투자 금액 </td>
				<td><input type="text" name="money" id="money" maxlength="20" /> 원</td>
			</tr>
		</c:when>
		<c:when test="${reqCategory=='창업참가'}">
			<tr>
				<td style="display:none"><%=userId %></td>
			</tr>
		</c:when>
	</c:choose>
	</table>
	
	<input type="button" value="요청" id="saveReqBtn" class="btn"  />
	<input type="button" value="취소" id="cancelReqbtn" class="btn" />
	
</form>

<!-- script -->
<script type = "text/javascript">

function saveReq(){
	
	
}
$(document).ready(function(){
	
	$("#saveReqBtn").click(function(){
		$("#workDiv").val('2000');
		
		$.ajax({
			type:"GET",
			url:"/HS2J/req.do",
			dataType:"html",
			data:$("#reqAddArea").serialize(),
			success: function(data){
				var saveMessage = data.split("|");
			
				if(saveMessage[0] == "1"){
					alert(saveMessage[1]);
				} else if (saveMessage[0] == "1"){
					alert(saveMessage[1]);
				}
				
				alert("요청을 보냈습니다");
			},
			complete:function(data){},
			error:function(xhr,status,error){}
		});//ajax
	
	});//saveReqBtn
	
	$("#cancelReqbtn").click(function(){
		self.close();
	});
});//document.ready

</script>
<!--// script -->
</body>
</html>