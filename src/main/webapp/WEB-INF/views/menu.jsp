<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <c:if test="${empty sessionScope.user}">
        <a href="/login" class="button">Zaloguj</a>
        <a href="/register" class="button">Zarejestruj</a>
    </c:if>
    <c:if test="${not empty sessionScope.username}">
        <a href="/user" class="button">Twoje Budżety</a>
        <a href="/login/logout" class="button">Wyloguj się</a>
        <p class="text">Witaj ${sessionScope.username}!</p>
    </c:if>
</div>
<hr class="line">
</body>
</html>