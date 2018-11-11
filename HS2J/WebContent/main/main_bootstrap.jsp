<%--
  /**
  * @Class Name : file.jsp
  * @Description : 
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018. 5. 2.     SIST01      최초 생성
  *
  * author SIST01
  * since 2018. 5. 2.
  *
  * Copyright (C) 2018 by SIST01  All right reserved.
  */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--====================================================
                         HOME
======================================================-->
    <section id="home">
      <div id="carousel" class="carousel slide carousel-fade" data-ride="carousel"> 
        <!-- Carousel items -->
        <div class="carousel-inner">
            <div class="carousel-item active slides">
              <div class="overlay"></div>
              <div class="slide-1"></div>
                <div class="hero ">
                  <hgroup class="wow fadeInUp">
                      <h1>창업 시작은 <span ><a href="" class="typewrite" data-period="2000" data-type='[ "피칭","PITCHING"]'>
                        <span class="wrap"></span></a></span> </h1>        
                      <h3>함께 시작할 공동창업자와의 만남과 투자유치를 한 곳에서!</h3>
                  </hgroup>
                  <button class="btn btn-general btn-green wow fadeInUp" role="button"><a href="/HS2J/main/user_register.jsp">회원가입</a></button>
                </div>           
            </div> 
        </div> 
      </div> 
    </section> 

<!--====================================================
                        ABOUT
======================================================-->
    <section id="about" class="about">
      <div class="container">
        <div class="row title-bar">
          <div class="col-md-12">
            <h1 class="wow fadeInUp">PITCHING : 새롭게 시작하는 창업자와 투자자를 위한 사이트</h1>
            <div class="heading-border"></div>
            <p class="wow fadeInUp" data-wow-delay="0.4s">프랜차이즈보다는 기존에 존재하지 않는 새로운 창업 아이템<br/>
															창업자를 위한 아이템, 창업공간 등 정보 제공<br/>												
															투자자에게 창업자의 아이디어에 투자 기회 부여</p>
            <div class="title-but"><button class="btn btn-general btn-green" role="button"><a href="/HS2J/item/item_list.jsp">아이템 보기</a></button></div>
          </div>
        </div>
      </div>  
      <!-- About right side withBG parallax -->
      <div class="container-fluid">
        <div class="row"> 
          <div class="col-md-4 bg-starship">
            <div class="about-content-box wow fadeInUp" data-wow-delay="0.3s">
              <i class="fa fa-snowflake-o"></i>
              <h5>공동창업 및 투자유치</h5>
              <p class="desc">다양한 직군의 창업자들과 공동 창업 / 투자자들로부터 투자 유치를 동시에</p>
            </div>
          </div> 
          <div class="col-md-4 bg-chathams">
            <div class="about-content-box wow fadeInUp" data-wow-delay="0.5s">
              <i class="fa fa-circle-o-notch"></i>
              <h5>후기</h5>
              <p class="desc">창업자에게 생생하게 듣기</p>
            </div>
          </div> 
          <div class="col-md-4 bg-matisse">
            <div class="about-content-box wow fadeInUp" data-wow-delay="0.7s">
              <i class="fa fa-hourglass-o"></i>
              <h5>창업 정보</h5>
              <p class="desc">창업 관련 최신 뉴스 및 공간 정보 제공</p>
            </div>
          </div> 
        </div> 
      </div>       
    </section> 

<h2>  </h2>
<hr/>
</body>
</html>