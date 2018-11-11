<%--
  /**
  * @Class Name : file.jsp
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<%


%>
<script type="text/javascript">



</script>

<title>엮인 쪽지 보기</title>
</head>
<body>
<h2>엮인 쪽지 보기 </h2>

	<c:choose>
							<c:when test="${list.size() > 0}"> 
								<c:forEach var = "messageVO" items = "${list}" >
										<div class = "msgContainer" style="border:2px solid gray;">
										<p>보낸 사람: <c:out value = "${messageVO.sender}" /> 님
										<p>받는 사람: <c:out value = "${messageVO.receiver}" />님
										<p>보낸 시간: <c:out value = "${messageVO.msgDate}" />
										<p>제목: <c:out value = "${messageVO.title}" />
										<p>내용: <c:out value = "${messageVO.contents}" />
										</div><p><p>							
								</c:forEach>
							</c:when>
							
							<c:otherwise>
								<div class = "msgContainer">
									엮인 쪽지가 없습니다.
								</div>
							</c:otherwise>
							
						</c:choose>


</body>
</html>