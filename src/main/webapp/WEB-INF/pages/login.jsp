<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html>
<html>
    <head>
        <title>sign in</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css"/>" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="all">
			<div class="header">
            	<h2 class="EPS">Electric Power System Dispatcher</h2>
        	</div>        
	        <div class="data_input">
	            <form name ="loginForm" action ="login" method="POST">	                
	                <div>
						<label for="email">email</label>
						<input type="text" id="email" name="email" />
					</div>
					<div>
						<label for="password">password</label>
						<input type="password" id="password" name="password"/>
					</div>
					<div>
						<input type="submit" class="s_in" value="sign in" name="submit" />
						<a href="<c:url value="/registration"/>">registration page</a>
					</div>    
	                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	            </form>
	        </div>
	        <div class="basement">
	            <a href="https://github.com/epsm">project on GitHub</a>
	            <a href="http://dou.ua/forums/topic/16411/">please criticize project on dou.ua</a>
	        </div>	
        </div>
    </body>
</html>

