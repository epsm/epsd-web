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
			google.charts.setOnLoadCallback(drawCharts);

			function drawCharts() {
				var frequencyDataTable = new google.visualization.DataTable();
				var frequencyData = ${frequencyChartData}
				frequencyDataTable.addColumn('timeofday');
				frequencyDataTable.addColumn('number', 'frequency');
				frequencyDataTable.addRows(frequencyData);
				
				var generationDataTable = new google.visualization.DataTable();
				var generationData = ${generationChartData}
				generationDataTable.addColumn('timeofday');
				generationDataTable.addColumn('number', 'power');
				generationDataTable.addRows(generationData);
				
				var consumptionDataTable = new google.visualization.DataTable();
				var consumptionData = ${consumptionChartData}
				consumptionDataTable.addColumn('timeofday');
				consumptionDataTable.addColumn('number', 'power');
				consumptionDataTable.addRows(consumptionData);
	
				var frequencyChartOptions = {
					hAxis: {
						title: 'time'
					},
					vAxis: {
						title: 'frequency, Hz'
					},
					backgroundColor: '#f1f8e9',
					curveType: 'function',
					legend: {position: 'none'}
				};
				
				var generationChartOptions = {
					hAxis: {
						title: 'time'
					},
					vAxis: {
						title: 'total generation, MW'
					},
					backgroundColor: '#f1f8e9',
					curveType: 'function',
					legend: {position: 'none'}
				};
				
				var consumptionChartOptions = {
					hAxis: {
						title: 'time'
					},
					vAxis: {
						title: 'total consumption, MW'
					},
					backgroundColor: '#f1f8e9',
					curveType: 'function',
					legend: {position: 'none'}
				};
	
				var frequencyChart = new google.visualization.LineChart(document.getElementById('frequency_chart_div'));
				frequencyChart.draw(frequencyDataTable, frequencyChartOptions);
				var generationChart = new google.visualization.LineChart(document.getElementById('generation_chart_div'));
				generationChart.draw(generationDataTable, generationChartOptions);
				var consumptionChart = new google.visualization.LineChart(document.getElementById('consumption_chart_div'));
				consumptionChart.draw(consumptionDataTable, consumptionChartOptions);
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
                <p class="cms">data on: ${date}</p>
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
          		<div id="generation_chart_div"/>
            </div>
            <div class="name">total consumption</div> 
            <div class="single_schedule">
     			<div id="consumption_chart_div"/>
            </div>
        </div>    
    </body>
</html>
