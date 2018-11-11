<%@page import="com.hs2j.bic.dao.BicDAO"%>
<%@page import="com.hs2j.comm.DTO"%>
<%@page import="com.hs2j.code.dao.CodeDAO"%>
<%@page import="com.hs2j.code.vo.CodeVO"%>
<%@page import="com.hs2j.comm.StringUtil"%>
<%@page import="com.hs2j.bic.vo.BicVO"%>
<%@page import="com.hs2j.comm.HS2JConst"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 전체 보육센터 리스트 + 지도 --%>
<%
	Logger log = Logger.getLogger(this.getClass());

	request.setCharacterEncoding("UTF-8");
	
	String searchDiv = "";			//검색 구분(도시)
	String searchWord = "";			//검색어
	String fLat = "";
	String fLon = "";
	
	BicVO param = (BicVO)request.getAttribute("paramVO");
	
	if(null != param){
		searchDiv = StringUtil.nvl(param.getSearchDiv(), "");
		searchWord = StringUtil.nvl(param.getSearchWord(), "");
	}
	
	searchDiv = (String)request.getAttribute("searchDiv");
	fLat = (String)request.getAttribute("fLat");
	fLon = (String)request.getAttribute("fLon");
	
    log.debug("searchDiv="+(String)request.getAttribute("searchDiv"));
    log.debug("searchWord="+(String)request.getAttribute("searchWord"));
    
    //code조회 : 검색조건
    CodeVO vo01 = new CodeVO();
    vo01.setMstId("BIC_CITY");
    CodeDAO codeDao = new CodeDAO();
    List<DTO> tList = codeDao.do_selectList(vo01);
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
<!--====================================================
                       HOME-P
======================================================-->
    <div id="home-p" class="home-p pages-head1 text-center">
      <div class="container">
        <h1 class="wow fadeInUp" data-wow-delay="0.1s">창업 보육 센터</h1>
        <p>경기도 창업 보육 센터 정보를 지원하고 있습니다.</p>
      </div><!--/end container-->
    </div>   
    
    <section id="single-news-p1" class="single-news-p1">
		<div class="container">
			<form name = "bicfrm" id = "bicfrm" action = "<%=HS2JConst.context%>/bic.do" method="get">
				<input type="hidden"  name="workDiv"  id="workDiv" />
				<input type="hidden"  name="bicSeq"  id="bicSeq" />
				
				<!-- 필터 -->
	         	<div style="display: block; text-align: center; padding:30px;">
			        <select class="selDo" style="width: 156px; height: 50px; margin: 5px;">
	  					<option>경기도</option>
					</select>		        	
			        <%= StringUtil.makeSelectBox2(tList, searchDiv, "searchDiv", true) %>
			        <input type="text" class="form-control input-lg" name="searchWord" id="searchWord" value="<%=searchWord%>" placeholder="검색어" style="display: inline-block; width: 255px; height: 50px; margin: 5px;"/>
			        <input type="button" class="btn btn-primary btn-lg" value="검색" id="retrieve_btn" style="height: 50px; margin: 5px;" onclick="javaScript:ctrSelectList();" />   
				</div>
			</form>	
         	<!-- 필터링 -->
			
			<!-- 리스트와 지도 -->
			<div class="form-inline">
				<div class="col-md-4">
					<ul class="list-group" style="width:400px; height:600px; overflow-y:scroll; -webkit-overflow-scrolling: touch;" >	
						<%List<BicVO> list = (List)request.getAttribute("list"); 
						if(list != null) {
						%>
						<%for(int i=0; i<list.size(); i++){ %>
						<li class="list-group-item">
							<a class="active" onclick="javaScript:showMap(<%=list.get(i).getBicLatitude()%>,<%=list.get(i).getBicLongitude()%>);">
								<dl>
									<dt><%=list.get(i).getBicName()%></dt>
									<dd><%=list.get(i).getBicAddress()%></dd>
									<dd><%=list.get(i).getBicRoadNm()%></dd>
									<dd><%=list.get(i).getBicPhone()%></dd>
									<dd style="display:none;"><%=list.get(i).getBicLatitude()%></dd>
									<dd style="display:none;"><%=list.get(i).getBicLongitude()%></dd>
								</dl>
							</a>
						</li>
						<%} 
						}%>
					</ul>
				</div> 
			
				<div class="col-md-8" style="padding: 50px;">
					<div id="googleMap" style="width:700px; height:600px;"></div>
				</div>
			</div>
		</div>
	</section>
	
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBgZyS-S5fB_Ok1qOmTKRZTf6OFFu9-zoo"></script>
<script>
/*조회*/
function ctrSelectList(){			//필터링 후 리스트
	var bicfrm = document.bicfrm;
	bicfrm.action="/HS2J/bic.do"
	bicfrm.submit();
}	

function showMap(latitude, longitude) {				//지도 첫화면
	var lat =<%=fLat%>;
	var lon =<%=fLon%>;
	
	if((latitude != null) && (longitude != null)) {
		lat = latitude;
		lon = longitude;
	}
	
	var mapProp = {
		center:new google.maps.LatLng(lat, lon),
		zoom:16,
		mapTypeId:google.maps.MapTypeId.ROADMAP
	};

	var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
	
	var marker = new google.maps.Marker({
	    position:new google.maps.LatLng(lat, lon),
	    map: map,
	  });
	
	marker.setMap(map);
}
google.maps.event.addDomListener(window, 'load', showMap);

</script>
</body>
</html>