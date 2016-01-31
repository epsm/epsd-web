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
	                <table>
	                    <tr>
	                        <td><label for="email">email</label></td>
	                        <td><input type="text" id="email" name="email" /></td>
	                    </tr>
	                    <tr>
	                        <td><label for="password">password</label></td>
	                        <td><input type="password" id="password" name="password"/></td>
	                    </tr>
	                    <tr>
	       					<td></td>
	                        <td>
	                        	<input type="submit" class="s_in" value="sign in" name="submit" />
	                        	<a href="<c:url value="/registration"/>">registration page</a>
	                        </td>
	                    </tr>    
	                </table>
	                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	            </form>
	        </div>
	        <div class="basement">
	            <a href="https://github.com/epsm">project on GitHub</a>
	        </div>	
        </div>
    </body>
</html>

