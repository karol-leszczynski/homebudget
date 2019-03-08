<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
    <link rel="stylesheet" href="/css/css.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Unexpected expence form</title>
</head>
<body>
<form:form modelAttribute="unexpectedExpence" action="/expence/unexpected" method="post">
    <fieldset class="expences-form">
        <legend class="text-gray-small">NIEPRZEWIDZIANE:</legend>
        Opis <form:input path="expenceDescription" required="true" style="width: 60%" type="text" maxlength="50"/>
        Kwota <form:input path="expenceAmmount" required="true" type="number" min="0.00" step="0.1" style="width: 18%"/>
        <form:input path="type" type="hidden" value="n"/>
        <form:input path="payDate" type="hidden" value=""/>
        <input class="button-add" type="submit" value="+">
    </fieldset>
</form:form>
</body>
</html>
