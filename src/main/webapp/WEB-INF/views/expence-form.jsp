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
        <title>Expence Form</title>
    </head>
<body>
<form:form modelAttribute="newExpence" action="/expence/new" method="post">
    <fieldset class="expences-form">
        <legend class="text-gray-small">PLANOWANE:</legend>
        Opis <form:input path="expenceDescription" required="true" style="width: 33%"/>
        Kwota <form:input path="expenceAmmount" required="true" type="number" min="0.00" step="0.1" style="width: 12%"/>
        Data <form:input path="payDate" type="date" style="width: 8rem"/>
        <form:input path="type" type="hidden" value="o"/>
        <input class="button-add" type="submit" value="+">
    </fieldset>
</form:form>
</body>
</html>
