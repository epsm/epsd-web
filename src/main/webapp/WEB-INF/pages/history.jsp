<!DOCTYPE html>
<html>
    <head>
        <title>Model states history</title>
        <link rel="stylesheet" type="text/css" href="model_states_history.css" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="all">
            <div class="roof">
                <div class="EPSsw">
                    <h2 class="EPS">Electric Power System</h2>
                </div>
                <form class="out" method="post" action="${pageContext.request.contextPath}/login.html">
                    <input type="submit" class="input" value="sign out" name="sign out" />
                </form>
                <p class="cms">characteristics of the power system for the last day</p>
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
                <div class="schedule"></div>
            </div>
            <div class="name">total generation</div> 
            <div class="single_schedule">
                <div class="schedule"></div>
            </div>
            <div class="name">total consumption</div> 
            <div class="single_schedule">
                <div class="schedule"></div>
            </div>
            <div class="name">generations by power stations</div>
            <div class="multiple_schedules">
                <div class="name">station_1</div>
                <div class="schedule"></div>
                <div class="name">station_2</div>
                <div class="schedule"></div>
                <div class="name">station_3</div>
                <div class="schedule"></div>
            </div>
            <div class="name">consumption by consumers</div>
            <div class="multiple_schedules">
				<div class="name">consumer_1</div>
				<div class="schedule"></div>
				<div class="name">consumer_2</div>
				<div class="schedule"></div>
				<div class="name">consumer_3</div>
				<div class="schedule"></div>
				<div class="name">consumer_4</div>
				<div class="schedule"></div>
            </div>
        </div>    
    </body>
</html>
