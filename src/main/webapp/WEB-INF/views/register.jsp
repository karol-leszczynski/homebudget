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
    <h3 class="text">Zarejestruj się</h3>
    <div class="text">
        <form:form modelAttribute="newUser" method="post">
            <p>Nazwa użytkownika: <form:input path="userName" required="true"/>
                <form:errors path="userName" cssClass="error"/></p>
            <p>Email: <form:input path="email" required="true"/>
                <form:errors path="email" cssClass="error"/></p>
            <p>Hasło: <form:password path="password" required="true"/>
                <form:errors path="password" cssClass="error"/></p>
            <p>Powtórz hasło: <form:password path="passwordSecondCheck" required="true"/>
                <form:errors path="passwordSecondCheck" cssClass="error"/></p>
            <p><input class="button-menu" type="submit" value="Zarejestruj"></p>
        </form:form>
    </div>
</div>
</body>
</html>
