<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Budget delete warning</title>
    <style>
        <%@include file="css/css.css" %>
    </style>
    <link rel="stylesheet" href="css/css.css">
</head>
<body>
<jsp:include page="menu.jsp"/>
<p class="text-bold">Czy na pewno chcesz usunąć budżet od
    ${budgetToDelete.startDate.format(formatterLong)}
    do ${budgetEndDate.format(formatterLong)}?
</p>

<c:if test="${fn:length(budgetToDelete.users) > 1}">
    Pozostali użytkownicy (
    <c:forEach items="${budgetToDelete.users}" var="user">
        <c:if test="${user.userName != sessionScope.name}">
            &squf; ${user.userName}
        </c:if>
    </c:forEach>
    ) <br/> także utracą do niego dostęp.
</c:if>
<hr class="thinLine">
<a href="/budget/delete?budgetId=${budgetToDelete.id}" class="button-negative">Usuń</a>
<a href="/user" class="button-positive">Anuluj</a>
<hr class="line">
</body>
</html>
