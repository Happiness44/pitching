<%@page import="com.hs2j.comm.HS2JConst"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- ��Ʈ��Ʈ�� -->
<link href="<%=HS2JConst.context%>/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body style="margin-left:15px; margin-top:15px;">
������ ���۵Ǿ����ϴ�. <div></div>
<input type="button" class="btn btn-general btn-green" value="ȭ�� �ݱ�" id="close_btn" class = "btn"/>

<script type="text/javascript">
	
$(document).ready(function(){
	$('#close_btn').click(function(){
		self.close();
	});
})

</script>
</body>
</html>