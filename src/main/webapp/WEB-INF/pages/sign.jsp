<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Sing in</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/sign.css"/>" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="all">
        <div class="login">
            <h2 class="EPS">Electric Power System Dispatcher</h2>
            <form name ="loginForm" action ="history" method="POST">
                <table>
                    <tr>
                        <td class="center"><label for="email">email</label></td>
                        <td class="center"><label for="password">password</label></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="text" id="email" name="email"/></td>
                        <td><input type="password" id="password" name="password"/></td>
                        <td><input type="submit" class="s_in" value="signin" name="sing in" /></td>
                    </tr>
                </table>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form> 
        </div> 
        <div class="basement">
            <a href="https://github.com/epsm">project on GitHub</a>
        </div>
        <div class="registration">
            <sf:form method="post" commandName="request">
                <td><sf:errors path="*"/></td>
                <table>
                    <tr>
                    	<td><label for="username">username</label></td>
                        <td><sf:input id="username" path="userName"/></td>
                        <td><sf:errors path="userName"/></td>
                    </tr>
                    <tr>
                    	<td><label for="email_r">email</label></td>
                        <td><sf:input id="email_r" path="email"/></td>
                        <td><sf:errors path="email"/></td>
                    </tr>
                    <tr>
                    	<td><label for="password_r">password</label></td>
                        <td><sf:password id="password_r" path="password"/></td>
                        <td><sf:errors path="password"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" class="s_in" value="singup" name="sign up" /></td>
                        <td></td>
                    </tr>    
                </table>
            </sf:form>
        </div>
        </div>
    </body>
</html>

