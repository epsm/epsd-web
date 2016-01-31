<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html>
<html>
    <head>
        <title>sign up</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/registration.css"/>" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="all">
        	<div class="header">
	            <h2 class="EPS">Electric Power System Dispatcher</h2>
			</div> 
	        <div class="data_input">
	            <sf:form method="post" commandName="request">
	                <table>
	                    <tr>
	                    	<td><label for="username">username</label></td>
	                        <td><sf:input id="username" path="userName"/></td>
	                        <td><sf:errors class="input_errors" path="userName"/></td>
	                    </tr>
	                    <tr>
	                    	<td><label for="email_r">email</label></td>
	                        <td><sf:input id="email_r" path="email"/></td>
	                        <td><sf:errors class="input_errors" path="email"/></td>
	                    </tr>
	                    <tr>
	                    	<td><label for="password_r">password</label></td>
	                        <td><sf:password id="password_r" path="password"/></td>
	                        <td><sf:errors class="input_errors" path="password"/></td>
	                    </tr>
	                    <tr>
	                    	<td></td>
	                        <td>
	                        	<input type="submit" class="s_in" value="sign up" name="singup" />
	                        	<a href="<c:url value="/login"/>">login page</a>
	                        </td>
	                    </tr>    
	                </table>
	            </sf:form>
	        </div>
	        <div class="basement">
	            <a href="https://github.com/epsm">project on GitHub</a>
	        </div>
        </div>
    </body>
</html>

