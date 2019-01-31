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
                    <a href="/budget/delete?budgetId=${budget.id}" class="button">Usuń</a>
                    <a href="/budget/set?id=${budget.id}" class="list">
                        <strong>${budget.startDate.format(formatterShort)}</strong></a>
                    <hr class="thinLine">
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="columnright">
        <c:if test="${not empty invitations}">
            <c:forEach items="${invitations}" var="invitation">
                <p class="text">${invitation.message}
                    <a class="button"
                       href="/invitation/accept?userId=${invitation.reciver.id}
                       &budgetId=${invitation.budgetId}
                       &invitationId=${invitation.id}">Zaakceptuj</a>
                    <a class="button" href="/invitation/delete?invitationId=${invitation.id}">Odrzuć</a>
                <hr class="thinLine"></p>
            </c:forEach>
        </c:if>
        <c:if test="${empty sessionScope.currentbudgetid}">
            <p class="text">Utwórz nowy budżet lub wybierz z listy istniejących</p>
        </c:if>
        <c:if test="${not empty sessionScope.currentbudgetid}">
            <p class="text"><strong>BUDŻET</strong> OD <strong>
                ${sessionScope.currentbudgetstartdate.format(formatterLong)}</strong> DO
            <strong>${sessionScope.currentbudgetenddate.format(formatterLong)}</strong>
            <form class="text" action="/invitation/send" method="post">
            <c:if test="${not empty inviteMessage}">
                <p style="color: cornflowerblue"><br/>${inviteMessage}</p>
            </c:if>
            <label>Zaproś inne osoby</label>
            (podaj email) <input name="reciverEmail"/>
            <input class="button" type="submit" value="Zaproś">
            </form>
            <hr class="thinLine">
            </p>
        </c:if>
    </div>
</div>
</body>
</html>
