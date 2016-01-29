google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawCharts);

function drawCharts() {
	var frequencyDataTable = new google.visualization.DataTable();
	frequencyDataTable.addColumn('timeofday');
	frequencyDataTable.addColumn('number', 'frequency');
	frequencyDataTable.addRows(frequencyData);
	
	var generationDataTable = new google.visualization.DataTable();
	generationDataTable.addColumn('timeofday');
	generationDataTable.addColumn('number', 'power');
	generationDataTable.addRows(generationData);
				
	var consumptionDataTable = new google.visualization.DataTable();
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