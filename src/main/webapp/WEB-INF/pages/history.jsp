<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
		<title>Model states history</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/history.css"/>" />
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<script>var frequencyData = <c:out value="${frequency}"/></script>
		<script>var generationData = <c:out value="${generation}"/></script>
		<script>var consumptionData = <c:out value="${consumption}"/></script>
    	<script src="<c:url value="/resources/js/charts.js"/>"></script>
    </head>
    <body>
        <div class="all">
            <div class="roof">
                <div class="EPSsw">
                    <h2 class="EPS">Dispatcher</h2>
                </div>
                <form class="out" method="post" action="<c:url value="/logout"/>">
                    <input type="submit" class="input" value="sign out"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <p class="cms">data on: ${date}</p>
            </div>
			<div class="menu">
                <ul>
                	<li><a href="${modelUrl}">model</a></li>
                </ul>
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
