<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logowanie</title>
    <style>
        <%@include file="css/css.css" %>
    </style>
    <link rel="stylesheet" href="css/css.css">
</head>
<body style="text-align: center">
<jsp:include page="menu.jsp"/>
<div>
    <h3 class="text">Zaloguj się</h3>
    <div class="text">
        <c:if test="${param['error'] != null}">
        <span class="error">Błędny login lub hasło!</span>
        </c:if>
        <form:form modelAttribute="userDto" method="post">
            <p>Email: <form:input path="email" required="true" name="login"/></p>
            <p>Hasło: <form:password path="password" required="true" name="password"/>
                <form:errors path="password" cssClass="error"/></p>
            <p><input type="submit" value="Zaloguj"></p>
        </form:form>
    </div>
</div>
<hr class="line">
<br/>
<div>
    <h3 class="text">lub, jeśli nie masz konta:</h3><br/>
    <a href="/user" class="button">zarejetruj się</a>
</div>
</body>
</html>
