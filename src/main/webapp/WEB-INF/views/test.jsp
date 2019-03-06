<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
    <%--<style><%@include file="css/css.css"%></style>--%>
    <%--<link rel="stylesheet" href="css/css.css">--%>
    <link rel="stylesheet" href="/css/css.css">
</head>
<body style="text-align: center">
<jsp:include page="menu.jsp"/>
<form action="/test" method="post">
    <input type="date" name="data"/>
    <input type="submit" value="Utwórz"/>
</form>
</body>
</html>