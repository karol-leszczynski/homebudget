<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Menu</title>
    <style>
        <%@include file="css/css.css" %>
    </style>
    <link rel="stylesheet" href="css/css.css">
</head>
<body>
<div>
    <a href="/" class="button">Home</a>
    <sec:authorize access="!isAuthenticated()">
        <a href="/login" class="button">Zaloguj</a>
        <a href="/register" class="button">Zarejestruj</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="/user" class="button">Twoje Budżety</a>
        <a href="/logout" class="button">Wyloguj się</a>
        <p class="text">Witaj ${sessionScope.name}!</p>
    </sec:authorize>
</div>
<hr class="line">
</body>
</html>