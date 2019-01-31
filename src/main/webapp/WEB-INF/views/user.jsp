<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
    <style>
        <%@include file="css/css.css" %>
    </style>
    <link rel="stylesheet" href="css/css.css">
</head>
<body style="text-align: center">
<jsp:include page="menu.jsp"/>

<div class="flex-container">
    <div class="columnleft">
        <div>
            <a href="/budget/new" class="button">Dodaj nowy budżet</a>
        </div>
        <p class="text"><strong>TWOJE BUDŻETY</strong></p>
        <div>
            <c:forEach items="${list}" var="budget">
                <div class="text">
                    <a href="/budget/set?id=${budget.id}" class="list">
                        <strong>${budget.startDate.format(formatterShort)}</strong><br/></a>
                    <hr class="thinLine">
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="columnright">
        <c:if test="${empty sessionScope.currentbudgetid}">
            <p class="text">Utwórz nowy budżet lub wybierz z listy istniejących</p>
        </c:if>
        <c:if test="${not empty sessionScope.currentbudgetid}">
            <p class="text"><strong>BUDŻET</strong> OD <strong>
                    ${sessionScope.currentbudgetstartdate.format(formatterLong)}</strong> DO
                <strong>${sessionScope.currentbudgetenddate.format(formatterLong)}</strong>
            </p>
        </c:if>
    </div>
</div>
</body>
</html>
