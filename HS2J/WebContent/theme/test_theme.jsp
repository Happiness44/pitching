<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.hs2j.User.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  Taglib -->    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix= "decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	//cache control
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	if(request.getProtocol().equals("HTTP/1.1")) {
	response.setHeader("Cache-Control", "no-cache");
	}
	//로그인처리
		UserVO member = null;
		String userId = null;
		
		if(null != session.getAttribute("member")){
			member = (UserVO)session.getAttribute("member");
			LOG.debug("현재 로그인 유저 ID:" + member.getUserId() + "/ 계정유형 : " + member.getUserAccount());
		} 
	
	if(null != member){
		userId = member.getUserId();
	}

%>
	 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<!-- stylesheets -->
    <link rel="shortcut icon" href="/HS2J/theme/bootstrapTheme/img/favicon.ico">
    <!-- Global Stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <link href="/HS2J/theme/bootstrapTheme/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/HS2J/theme/bootstrapTheme/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/HS2J/theme/bootstrapTheme/css/animate/animate.min.css">
    <link rel="stylesheet" href="/HS2J/theme/bootstrapTheme/css/owl-carousel/owl.carousel.min.css">
    <link rel="stylesheet" href="/HS2J/theme/bootstrapTheme/css/owl-carousel/owl.theme.default.min.css">
    <link rel="stylesheet" href="/HS2J/theme/bootstrapTheme/css/style.css">
    <!-- Core Stylesheets -->
    <link rel="stylesheet" href="/HS2J/theme/bootstrapTheme/css/about.css">

    <!--Global JavaScript -->
    <script src="/HS2J/theme/bootstrapTheme/js/jquery/jquery.min.js"></script>
    <script src="/HS2J/theme/bootstrapTheme/js/popper/popper.min.js"></script>
    <script src="/HS2J/theme/bootstrapTheme/js/bootstrap/bootstrap.min.js"></script>
    <script src="/HS2J/theme/bootstrapTheme/js/wow/wow.min.js"></script>
    <script src="/HS2J/theme/bootstrapTheme/js/owl-carousel/owl.carousel.min.js"></script>
    <!-- Plugin JavaScript -->
    <script src="/HS2J/theme/bootstrapTheme/js/jquery-easing/jquery.easing.min.js"></script>
    <script src="/HS2J/theme/bootstrapTheme/js/custom.js"></script>

    <!-- jQuery CDN -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

	<!-- paging by javascript -->    
	<script type="text/javascript" src="/HS2J/js/jquery.simplePagination.js"></script>
	<link type="text/css" rel="stylesheet" href="/HS2J/css/simplePagination.css"/>
	
<script type="text/javascript">
	//Login 여부 검증 javascript
	function myReq(){
		if("null" == "<%=userId%>"){
				alert("로그인하지 않으면 이용할 수 없습니다. 로그인을 해주세요.");
				window.location.href="/HS2J/main/main_bootstrap.jsp";
				return false;
		}else {
			window.location.href="/HS2J/my_page/req/req_list.jsp";
		}
	}//myReq
	
	
	
	function myOngoingItem(){
		if("null" == "<%=userId%>"){
			alert("로그인하지 않으면 이용할 수 없습니다. 로그인을 해주세요.");
			window.location.href="/HS2J/main/main_bootstrap.jsp";
			return false;
		}else {
		window.location.href="http://localhost:8080/HS2J/item.do?workDiv=1000&itemRegPerson="+"<%=userId%>"+"&itemCompyn=10";
		}
	}//myOngoingItem
	
	function myCompItem(){
		if("null" == "<%=userId%>"){
			alert("로그인하지 않으면 이용할 수 없습니다. 로그인을 해주세요.");
			window.location.href="/HS2J/main/main_bootstrap.jsp";
			return false;
		}else {
		window.location.href="http://localhost:8080/HS2J/item.do?workDiv=1000&itemRegPerson="+"<%=userId%>"+"&itemCompyn=20";
		}
	}//myOngoingItem
	
	function myMsg(){
		if("null" == "<%=userId%>"){
			alert("로그인하지 않으면 이용할 수 없습니다. 로그인을 해주세요.");
			window.location.href="/HS2J/main/main_bootstrap.jsp";
			return false;
		}else {
		window.location.href="/HS2J/my_page/msg/msg_list.jsp";
		}
	}//myMsg
	
	</script>
    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>피칭</title>
</head>
<body id="page-top">

<!--====================================================
                         HEADER
======================================================-->

    <header>

      <!-- Top Navbar  -->
      <div class="top-menubar">
        <div class="topmenu">
          <div class="container">
            <div class="row">
              <div class="col-md-5">
                <ul class="list-inline top-contacts">
                  <li>
                    <i class="fa fa-envelope"></i> Email: <a href="mailto:admin@pitching.com"><b>admin@pitching.com</b></a>
                  </li>
                  <li>
                    <i class="fa fa-phone"></i> Hotline: <b>(+02) 123 4567</b>
                  </li>
                </ul>
              </div> 
              <div class="col-md-7">
                <ul class="list-inline top-data">
                  <!--  로그인 처리 -->
                  <jsp:include page="/main/main.jsp"></jsp:include>
                   
                </ul>
              </div>
            </div>
          </div>
        </div> 
      </div>  
      
      <!-- Navbar -->
      <nav class="navbar navbar-expand-lg navbar-light" id="mainNav" data-toggle="affix">
        <div class="container">
          <a class="navbar-brand smooth-scroll" href="/HS2J/main/main_bootstrap.jsp">
            <img src="/HS2J/theme/bootstrapTheme/img/pitchingLogo.png" alt="logo">
          </a> 
          <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"> 
                <span class="navbar-toggler-icon"></span>
          </button>  
          <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item" ><a class="nav-link smooth-scroll" href="/HS2J/main/main_bootstrap.jsp">Home</a></li>
                <li class="nav-item" ><a class="nav-link smooth-scroll" href="/HS2J/board.do?workDiv=1000">공지사항</a></li> 
                
                <li class="nav-item dropdown" >
                  <a class="nav-link dropdown-toggle smooth-scroll" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">창업 아이템</a> 
                  <div class="dropdown-menu dropdown-cust" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item"  href="/HS2J/item.do?workDiv=1000&pageNum=&itemSeq=&searchDiv=&itemBranch=&itemCount=&itemInvestment=&itemStartDate=&itemEndDate=&itemTitle=&itemRegPerson=&itemCompyn=10&itemRegion=">아이템 보기</a>
                    <a class="dropdown-item" href="/HS2J/item/item_stats.jsp">아이템 동향</a>
                  </div>
                </li>
                
                <li class="nav-item dropdown" >
                  <a class="nav-link dropdown-toggle smooth-scroll" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">창업 정보</a> 
                  <div class="dropdown-menu dropdown-cust" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="/HS2J/board.do?workDiv=1010">창업 뉴스</a>
                    <a class="dropdown-item" href="/HS2J/space.do?workDiv=1000">창업 공간</a>
                    <a class="dropdown-item" href="/HS2J/bic.do?workDiv=&bicSeq=&searchDiv=&searchWord=">창업 보육 센터</a>
                  </div>
                </li>

                <li class="nav-item" ><a class="nav-link smooth-scroll" href="/HS2J/board.do?workDiv=1020">창업 후기</a></li> 

                
                <li class="nav-item dropdown" >
                  <a class="nav-link dropdown-toggle smooth-scroll" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">마이페이지</a> 
                  <div class="dropdown-menu dropdown-cust" aria-labelledby="navbarDropdownMenuLink"> 
                    <a class="dropdown-item"  target="_empty" href="javascript:myReq();">나의 요청/제안</a> 
                    <a class="dropdown-item"  target="_empty" href="javascript:myOngoingItem();">나의 진행중 아이템</a> 
                    <a class="dropdown-item"  target="_empty" href="javascript:myCompItem();">나의 완료 아이템</a>                     
                    <a class="dropdown-item"  target="_empty" href="javascript:myMsg();">쪽지함</a> 
                  </div>
                </li>
                
                <li>
                  <i class="search fa fa-search search-btn"></i>
                  <div class="search-open">
                    <div class="input-group animated fadeInUp">
                      <input type="text" class="form-control" placeholder="Search" aria-describedby="basic-addon2">
                      <span class="input-group-addon" id="basic-addon2">Go</span>
                    </div>
                  </div>
                </li> 
                <li>
                  <div class="top-menubar-nav">
                    <div class="topmenu ">
                      <div class="container">
                        <div class="row">
                          <div class="col-md-9">
                            <ul class="list-inline top-contacts">
                              <li>
                                <i class="fa fa-envelope"></i> Email: <a href="mailto:info@htmlstream.com">info@htmlstream.com</a>
                              </li>
                              <li>
                                <i class="fa fa-phone"></i> Hotline: (1) 396 4587 99
                              </li>
                            </ul>
                          </div> 
                          <div class="col-md-3">
                            <ul class="list-inline top-data">
                              <li><a href="#" target="_empty"><i class="fa top-social fa-facebook"></i></a></li>
                              <li><a href="#" target="_empty"><i class="fa top-social fa-twitter"></i></a></li>
                              <li><a href="#" target="_empty"><i class="fa top-social fa-google-plus"></i></a></li> 
                              <li><a href="#" class="log-top" data-toggle="modal" data-target="#login-modal">Login</a></li>  
                            </ul>
                          </div>
                        </div>
                      </div>
                    </div> 
                  </div>
                </li>
            </ul>  
          </div>
        </div>
      </nav>
    </header> 

<!--====================================================
                    LOGIN OR REGISTER
======================================================-->
    <section id="login">
      <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header" align="center">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span class="fa fa-times" aria-hidden="true"></span>
                      </button>
                  </div>
                  <div id="div-forms">
                      <form id="login-form">
                          <h3 class="text-center">Login</h3>
                          <div class="modal-body">
                              <label for="username">Username</label> 
                              <input id="login_username" class="form-control" type="text" placeholder="Enter username " required>
                              <label for="username">Password</label> 
                              <input id="login_password" class="form-control" type="password" placeholder="Enter password" required>
                              <div class="checkbox">
                                  <label>
                                      <input type="checkbox"> Remember me
                                  </label>
                              </div>
                          </div>
                          <div class="modal-footer text-center">
                              <div>
                                  <button type="submit" class="btn btn-general btn-white">Login</button>
                              </div>
                              <div>
                                  <button id="login_register_btn" type="button" class="btn btn-link">Register</button>
                              </div>
                          </div>
                      </form>
                      <form id="register-form" style="display:none;">
                          <h3 class="text-center">Register</h3>
                          <div class="modal-body"> 
                              <label for="username">Username</label> 
                              <input id="register_username" class="form-control" type="text" placeholder="Enter username" required>
                              <label for="register_email">E-mailId</label> 
                              <input id="register_email" class="form-control" type="text" placeholder="Enter eMail" required>
                              <label for="register_password">Password</label> 
                              <input id="register_password" class="form-control" type="password" placeholder="Password" required>
                          </div>
                          <div class="modal-footer">
                              <div>
                                  <button type="submit" class="btn btn-general btn-white">Register</button>
                              </div>
                              <div>
                                  <button id="register_login_btn" type="button" class="btn btn-link">Log In</button>
                              </div>
                          </div>
                      </form>
                  </div>
              </div>
          </div>
      </div>
    </section>      




<!-- body -->
<decorator:body>

</decorator:body>
<!-- body -->
<!--====================================================
                      FOOTER
======================================================-->
    <footer>
        <div id="footer-s1" class="footer-s1">
            <div class="footer">
                <div class="container">
                    <div class="row">
                        <!-- About Us -->
                        <div class="col-md-3 col-sm-6 ">
                           <div class="heading-footer">
                            <h2>사이트 소개</h2></div>
                            <address class="address-details-f">
                        		피칭(pitching)은 
                        		<br>창업자가 아이템 
                        		<br>공유를 통해 공동   
                        		<br>창업자를 만날 수 있고,
                        		<br>투자자에게 
                        		<br>창업자의 아이디어에  
                        		<br>투자할 수 있는
                        		<br>기회를 제공하는 
                        		<br>플랫폼입니다.
                        	</address>
                        </div>
                        <!-- End About Us -->
                        
                       
                        <!-- Latest Tweets -->
                        <div class="col-md-3 col-sm-6">
                            <div class="heading-footer">
                                <h2>연락처</h2></div>
                            <address class="address-details-f">
                   			             서울특별시 마포구
                                <br> 월드컵북로 21 풍성빌딩 2층
                                <br> Phone: 010 1234 5678
                                <br> Fax: 800 123 3456
                                <br> Email: <a href="mailto:admin@pitching.com" class="">admin@pitching.com</a>
                            </address>
                        </div>
                        <!-- End Latest Tweets -->
                        
                        <!-- Recent Blog Entries -->
                        <div class="col-md-3 col-sm-6 ">
                            <div class="heading-footer">
                                <h2>사이트 제작자</h2></div>
                            <ul class="list-unstyled thumb-list">
                                <li>
                                    <div class="overflow-h">
                                        <a href="https://www.youtube.com/watch?v=Ib674A1yMtg" target="_blank">김혜진</a>
                                        <small>decaffeine12@gmail.com</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="overflow-h">
                                        <a href="https://www.youtube.com/watch?v=d1D1SJ-KqaQ" target="_blank">신지인</a>
                                        <small>hamo0oy@naver.com</small>
                                    </div>
                                </li>
                                 <li>
                                    <div class="overflow-h">
                                        <a href="https://www.youtube.com/watch?v=KijN77CgQV4" target="_blank">안소현</a>
                                        <small>thgus1127@naver.com</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="overflow-h">
                                        <a href="https://www.youtube.com/watch?v=_Tg_2ClFk3w&start=17" target="_blank">전아름</a>
                                        <small>areumj0507@google.com</small>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!-- End Recent Blog Entries -->
                        
                        
                    </div>
                </div>
                <!--/container -->
            </div>
        </div>
        <div id="footer-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div id="footer-copyrights">
                            <p>Copyrights &copy; 2018 All Rights Reserved by SIST/HS2J. <a href="#"> 개인정보 보호 정책</a> </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a href="#home-p" id="back-to-top" class="btn btn-sm btn-green btn-back-to-top smooth-scrolls hidden-sm hidden-xs" title="home" role="button">
            <i class="fa fa-angle-up"></i>
        </a>
    </footer>
</html>