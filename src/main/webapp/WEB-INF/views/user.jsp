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
                <hr class="thinLine">
                </p>
            </c:forEach>
        </c:if>
        <c:if test="${empty sessionScope.currentbudgetid}">
            <p class="text">Utwórz nowy budżet lub wybierz z listy istniejących</p>
        </c:if>
        <c:if test="${not empty sessionScope.currentbudgetid}">
            <div class="text">
                <h4>
                    <strong>BUDŻET</strong> OD <strong>
                        ${sessionScope.currentbudgetstartdate.format(formatterLong)}</strong> DO
                    <strong>${sessionScope.currentbudgetenddate.format(formatterLong)}</strong>
                </h4>
                <form class="text" action="/invitation/send" method="post">
                    <c:if test="${not empty inviteMessage}">
                        <p style="color: cornflowerblue"><br/>${inviteMessage}</p>
                    </c:if>
                    <label>Zaproś inne osoby</label>
                    (podaj email) <input name="reciverEmail"/>
                    <input class="button" type="submit" value="Zaproś">
                </form>
                <hr class="thinLine">
                    <%--fake tabela--%>
                <table class="tg">
                    <tr>
                        <th class="tg-wo29"><span style="font-weight:bold">Dziś jest 15-02-2019</span></th>
                        <th class="tg-wo29"></th>
                        <th class="tg-vt7q">Stan konta:</th>
                        <th class="tg-wtex">3820,00 zł</th>
                    </tr>
                    <tr>
                        <td class="tg-wo29">Do dyspozycji dziennie</td>
                        <td class="tg-wo29"><span style="font-weight:bold">100 zł</span></td>
                        <td class="tg-vt7q">Do dyspozycji tygodniowo:</td>
                        <td class="tg-wtex">700,00 zł</td>
                    </tr>
                    <tr>
                        <td class="tg-wo29"></td>
                        <td class="tg-wo29"></td>
                        <td class="tg-vt7q">Obecne saldo to:</td>
                        <td class="tg-coyn">-150,00 zł</td>
                    </tr>
                    <tr>
                        <td class="tg-wo29">Będziesz na plusie za:</td>
                        <td class="tg-wo29">2 dni</td>
                        <td class="tg-wo29">Do końca weekendu możesz wydać:</td>
                        <td class="tg-vt7q"><span style="font-weight:bold;color:rgb(1, 51, 0)">200,00 zł</span></td>
                    </tr>
                </table>
                <hr class="thinLine"><br/>
                    <%--koniec fake tabel--%>
            </div>
            <div>
                <div class="columnleft">
                    <p class="list"><strong>WPŁYWY:</strong><br/></p>
                    <form class="list" action="/income/add" method="post">
                        <c:if test="${not empty incomeMessage}">
                            <p style="color: cornflowerblue"><br/>${incomeMessage}</p>
                        </c:if>
                        <input style="width: 80%" name="income" type="number" required value="" min="0"/>
                        <input class="button-blue" type="submit" value="+">
                    </form>
                    <c:forEach items="${incomes}" var="income">
                        <div class="list">
                            <a href="/income/delete?incomeId=${income.id}"
                               class="button-red">-</a> ${income.incomeAmmount} zł (${income.user.userName})
                        </div>
                    </c:forEach>
                </div>
                <div class="columnright">
                    <p class="list"><strong>WYDATKI:</strong></p>
                </div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
