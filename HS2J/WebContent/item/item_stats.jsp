<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Stats</title>
</head>
<body>
<%@page import="org.apache.log4j.Logger"%>

<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.hs2j.stat.StatDAO"%>
<%@page import="java.util.Map"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	Map<String, String> itemCountMap = new HashMap<String, String>();

	StatDAO sDao = new StatDAO();
	List<String> list = sDao.getCodeList();

	for(int i = 0 ; i < list.size() ; i++){
		String count = sDao.getItemCountsByList(list.get(i));
		itemCountMap.put(list.get(i), count);
	}
	
	String allLine = "";
	for(int i = 0 ; i < list.size(); i++){
		String oneLine = "['";
		oneLine += sDao.getCodeName(list.get(i)) + "'," + itemCountMap.get(list.get(i)) + "]";
		if(i != list.size()-1){
			oneLine += ",";
		}
		allLine += oneLine;
		}

%>
<!-- javascript -->
 <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
        	<%=allLine%>
	          ]);
	

        // Set chart options
        var options = {'title':'아이템 최신 동향',
                       'width':800,
                       'height':600};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
<div id="home-p" class="home-p pages-head1 text-center">
	<div class="container">
        <h1 class="wow fadeInUp" data-wow-delay="0.1s">분류별 아이템 비율</h1>
        <p>다른 회원들은 무슨 아이템을 주로 등록했는지 살펴보고 최신 트렌드를 알아보세요! </p>
    </div><!--/end container-->
</div> 

<div class="container">
	<div class="row">
    
    <!-- left news details -->
		<div class="col-md-8">
			<div class="single-news-p1-cont">
				<div class="single-news-desc">
					<!-- 차트를 넣자 -->
                  	<div id="chart_div"></div>
                </div>
            </div>
        </div>
	</div>
</div>
</body>
</html>