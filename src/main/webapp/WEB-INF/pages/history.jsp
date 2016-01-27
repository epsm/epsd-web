<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
		<title>Model states history</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/history.css" />
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    	<script type="text/javascript">
			google.charts.load('current', {packages: ['corechart', 'line']});
			google.charts.setOnLoadCallback(drawBackgroundColor);

			function drawBackgroundColor() {
				var data = new google.visualization.DataTable();
				var chartData = ${frequencyChartData}
				data.addColumn('timeofday');
				data.addColumn('number', 'frequency');
	
				data.addRows(chartData);
	
				var options = {
					hAxis: {
						title: 'time'
					},
					vAxis: {
						title: 'frequency'
					},
					backgroundColor: '#f1f8e9',
					curveType: 'function',
					legend: {position: 'none'}
				};
	
				var chart = new google.visualization.LineChart(document.getElementById('frequency_chart_div'));
				chart.draw(data, options);
			}
    	</script>
    </head>
    <body>
        <div class="all">
            <div class="roof">
                <div class="EPSsw">
                    <h2 class="EPS">Electric Power System Dispatcher</h2>
                </div>
                <form class="out" method="post" action="${pageContext.request.contextPath}/login.html">
                    <input type="submit" class="input" value="sign out" name="sign out" />
                </form>
                <p class="cms">data on ${date}</p>
            </div>
			<div class="menu">
                <ul>
                	<li><a href="${modelUrl}">model</a></li>
                </ul>
            </div>   
			<div class="basement">
				<a href="https://github.com/epsm">project on GitHub</a>
			</div>
            <div class="name">frequency in system</div>  
            <div class="single_schedule">
                <div id="frequency_chart_div"/>
            </div>
            <div class="name">total generation</div> 
            <div class="single_schedule">
                <div class="schedule"></div>
            </div>
            <div class="name">total consumption</div> 
            <div class="single_schedule">
                <div class="schedule"></div>
            </div>
        </div>    
    </body>
</html>
