<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Główna</title>
    <style><%@include file="css/css.css"%></style>
    <link rel="stylesheet" href="css/css.css">
</head>
<body>
<jsp:include page="menu.jsp"/>

<div class="home-page-background">
    <p class="boxes-right">
        <strong>Witaj w budget app!</strong>
    </p>
    <p class="boxes-right">
        Dowiedz się, jak tworzyć budżety w naszej aplikacji
    </p>
    <p class="boxes-right">
        Dowiedz się wiecej o planowniu własnych finansów
    </p>
</div>

</body>
</html>
