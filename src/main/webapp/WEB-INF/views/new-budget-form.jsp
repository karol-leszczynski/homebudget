<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
    <style><%@include file="css/css.css"%></style>
    <link rel="stylesheet" href="css/css.css">
</head>
<body style="text-align: center">
<jsp:include page="menu.jsp"/>
<div>
    <div class="text">
        <form:form modelAttribute="newBudget" method="post">
            <p><strong>Podaj datę rozpoczęcia budżetu</strong><br/>
                (optymalnym dniem jest ten, w którym na twoje konto wpływa wypłata)<br/>
                <form:input type="date" path="stringDate" required="true"/>
            <p><input type="submit" value="Utwórz"></p>
        </form:form>
    </div>
</div>
</body>
</html>