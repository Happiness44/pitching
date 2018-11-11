<%@page import="com.hs2j.comm.DTO"%>
<%@page import="java.util.List"%>
<%@page import="com.hs2j.code.dao.CodeDAO"%>
<%@page import="com.hs2j.code.vo.CodeVO"%>
<%@page import="com.hs2j.comm.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새 요청</title>
<%
//코드조회
CodeVO vo01 = new CodeVO();
CodeDAO dao01 = new CodeDAO();
vo01.setMstId("REQ_CATEGORY");
List<DTO> list =  dao01.do_selectList(vo01);

%>
</head>
	<body>
		<h2>투자하기</h2>
		
		<div> <!-- 화면상 hidden area -->
		<input type="hidden" name="item" id="item"  maxlength="20" />
		<input type="hidden" name="category" id="category"  maxlength="20" />
		<input type="hidden" name="sender" id="sender"  maxlength="20" />
		</div>
		<!-- 화면상 hidden area -->
		<div>
		<h3>[푸드테크 창업]아이템에 대한 [투자]요청</h3>
		</div>
		
		<!--  if category = 투자요청이면 여기 표시 -->
		<div>
			<label>계좌번호</label> 123  <br>
			<label>로열티</label> 123 <br>
			<label>로열티 상환 날짜</label>123 <br>
			<label>목표 투자 금액</label> 123 <br>
			<label>현 투자 금액</label>123 <br>
			<label>투자하실 금액: </label><input type="text" name="money" id="money" maxlength="20" />원
		</div>
		<!-- // 투자요청이면 여기 표시 -->
		<!-- button area -->
		<div>
		   <input type="button" value="요청" id="request_btn" class="btn"  /> 
		   <input type="button" value="취소" id="cancel_btn" class="btn" /> 
		</div>
		<!-- button area -->
	</body>
</html>