<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
    <link rel="stylesheet" href="/css/css.css">
</head>
<body>
<jsp:include page="menu.jsp"/>

<div class="flex-container">
    <div class="columnleft">
        <div>
            <a href="/budget/new" class="button-menu">Dodaj nowy budżet</a>
        </div>
        <c:if test="${not empty sessionScope.currentbudgetid}">
            <div>
                <a href="" class="button-menu">Duplikuj budżet</a>
                <%--<a href="/budget/clone" class="button-menu">Duplikuj budżet</a>--%>
            </div>
        </c:if>
        <hr class="line">
        <p class="text"><strong>TWOJE BUDŻETY</strong></p>
        <div>
            <c:forEach items="${list}" var="budget">
                <div class="text">
                    <a href="/budget/warning?budgetId=${budget.id}" class="button-negative">Usuń</a>
                    <a href="/budget/set?id=${budget.id}" class="button-empty">
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
                    <a class="button-positive"
                       href="/invitation/accept?userId=${invitation.reciver.id}
                       &budgetId=${invitation.budgetId}
                       &invitationId=${invitation.id}">Zaakceptuj</a>
                    <a class="button-negative" href="/invitation/delete?invitationId=${invitation.id}">Odrzuć</a>
                <hr class="thinLine">
                </p>
            </c:forEach>
        </c:if>
        <c:if test="${empty sessionScope.currentbudgetid}">
            <p class="text">Utwórz nowy budżet lub wybierz z listy istniejących</p>
        </c:if>
        <c:if test="${not empty sessionScope.currentbudgetid}">
            <div class="text">
                <h3>
                    <strong>BUDŻET</strong> OD <strong>
                        ${currentBudgetDto.startDate.format(formatterLong)}</strong> DO
                    <strong>${currentBudgetDto.endDate.format(formatterLong)}</strong>
                </h3>

                <form class="text" action="/invitation/send" method="post">
                    <c:if test="${not empty inviteMessage}">
                        <p class="error"><br/>${inviteMessage}</p>
                    </c:if>
                    <input type="hidden" name="currentBudgetStartDate"
                           value="${currentBudgetDto.startDate.format(formatterLong)}">
                    <input type="hidden" name="currentBudgetEndDate"
                           value="${currentBudgetDto.endDate.format(formatterLong)}">
                    <label>Zaproś inne osoby</label>
                    (podaj email) <input name="reciverEmail"/>
                    <input class="button-positive" type="submit" value="Zaproś">
                </form>
                <hr class="thinLine">

                <table class="expences-table">
                    <tr>
                        <td class="text-left">
                            <form class="list" action="" method="post">Padaj stan konta:
                                <input style="width: 4rem" name="balance" type="number" required="" value="" min="0">
                                <input class="button-positive" type="submit" value="OK">
                            </form>
                        </td>
                        <td class="text-center">
                            Stan konta: <strong>3820.00 zł</strong>
                        </td>
                        <td class="text-right">
                            Dziś jest <strong>${currentDate.format(formatterLong)}</strong>
                        </td>
                    </tr>
                </table>

                <hr class="thinLine">

                <div class="block-container">
                    <div class="left-container">
                        <table>
                            <tr>
                                <td class="halfbread-left">Obecne saldo to: <strong>-150 zł</strong></td>
                            </tr>
                            <tr>
                                <td class="halfbread-left">Będziesz na plusie za: <strong>2 dni</strong></td>
                            </tr>
                            <tr>
                                <td class="halfbread-left">Do dyspozycji dziennie: <strong>100 zł</strong></td>
                            </tr>
                            <tr>
                                <td class="halfbread-left">Do dyspozycji tygodniowo: <strong>700 zł</strong></td>
                            </tr>
                            <tr>
                                <td class="halfbread-left">Zaplanowane oszczędności: <strong>${currentBudgetDto.savings}
                                    zł</strong></td>
                            </tr>
                            <tr>
                                <td class="halfbread-left">Do końca weekendu możesz wydać: <strong>200 zł</strong></td>
                            </tr>
                        </table>
                    </div>
                    <div class="calendar-container">
                        <ul class="weekdays">
                            <li>Pn</li>
                            <li>Wt</li>
                            <li>Śr</li>
                            <li>Cz</li>
                            <li>Pt</li>
                            <li>So</li>
                            <li>Nd</li>
                        </ul>
                        <ul class="days">
                            <c:forEach items="${calendar.days}" var="day">
                                <c:choose>
                                    <c:when test="${day.shortNumber==''}">
                                        <c:set var="dayClass" value="day-empty"></c:set>
                                    </c:when>
                                    <c:when test="${day.type=='n'}">
                                        <c:set var="dayClass" value="day-normal"></c:set>
                                    </c:when>
                                    <c:when test="${day.type=='p'}">
                                        <c:set var="dayClass" value="day-planned"></c:set>
                                    </c:when>
                                    <c:when test="${day.type=='e'}">
                                        <c:set var="dayClass" value="day-expired"></c:set>
                                    </c:when>
                                    <c:when test="${day.today}">
                                        <c:set var="todayClass" value="day-today"></c:set>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${day.shortNumber==''}">
                                        <c:set value="#00000000" var="dayBackground"></c:set>
                                    </c:when>
                                    <c:when test="${day.today}">
                                        <c:set value="#c7deff" var="dayBackground"></c:set>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="#edf2f5" var="dayBackground"></c:set>
                                    </c:otherwise>
                                </c:choose>

                                <li class="day ${dayClass}" style="background-color:${dayBackground}">
                                        ${day.shortNumber}
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <c:if test="${not empty calendar.expiredExpences}">
                    <ul class="text-expired">
                        Upłynął już termin na opłacenie:
                        <c:forEach items="${calendar.expiredExpences}" var="expence">
                            <li class="text-expired">&squf; ${expence.expenceDescription}</li>
                        </c:forEach>
                    </ul>
                    <hr class="thinLine">
                </c:if>

            </div>
            <div class="columnleft">
                <p class="list"><strong>WPŁYWY:</strong><br/></p>
                <hr class="thinLine">
                <form class="list" action="/income/add" method="post">
                    <c:if test="${not empty incomeMessage}">
                        <p class="error"><br/>${incomeMessage}</p>
                    </c:if>
                    <input style="width: 75%" name="income" type="number" required value="" min="0"/>
                    <input class="button-add" type="submit" value="+">
                </form>

                <table>
                    <c:forEach items="${currentBudgetDto.incomes}" var="income">
                        <tr class="incomes">
                            <td>
                                <a href="/income/delete?incomeId=${income.id}" class="button-delete">-</a>
                            </td>
                            <td class="incomes">
                                    ${income.incomeAmmount} zł (${income.user.userName})
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <c:if test="${not empty currentBudgetDto.savings && not empty currentBudgetDto.expences}">
                    <p class="list"><strong>OSZCZĘDNOŚCI</strong><br></p>
                    <form class="list" action="/budget/set-savings" method="post">
                        <c:if test="${not empty savingsMessage}">
                            <p class="error"><br/>${savingsMessage}</p>
                        </c:if>
                        <input style="width: 75%" name="savings" type="number" required="" value="" min="0">
                        <input class="button-add" type="submit" value="+">
                    </form>
                </c:if>
            </div>

            <div class="columnright">
                <p class="list"><strong>WYDATKI</strong></p>
                <hr class="thinLine">

                <jsp:include page="/expence/new"/>
                <strong class="error">${expenceMessage}</strong>

                <jsp:include page="/expence/unexpected"/>
                <strong class="error">${unexpectedExpenceMessage}</strong>

                <table class="expences-table">
                    <hr class="thinLine">
                    <tbody>
                    <tr class="expences-table">
                        <th>Usuń</th>
                        <th>Opis</th>
                        <th>Kwota</th>
                        <th>Data</th>
                        <th>Stały</th>
                        <th>Opłacony</th>
                    </tr>
                    <c:forEach items="${currentBudgetDto.expences}" var="expence">
                        <tr>
                            <td><a href="/expence/delete?expenceId=${expence.id}" class="button-delete">-</a></td>
                            <td class="expences-description">${expence.expenceDescription}</td>
                            <td>${expence.expenceAmmount} zł</td>
                            <td>${expence.payDate.format(formatterShortDays)}</td>
                            <td>
                                <input type="checkbox" class="checkbox"
                                       onchange="location='/expence/type-switch?expenceId=${expence.id}'"
                                       <c:if test="${expence.type == 'a'}">checked="checked"</c:if>
                                />
                            </td>
                            <td>
                                <input type="checkbox" class="checkbox"
                                    <%--onchange="this.form.submit()"--%>
                                       onchange="location='/expence/payed-switch?expenceId=${expence.id}'"
                                       <c:if test="${expence.payed}">checked="checked"</c:if>
                                />

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </c:if>
    </div>
</div>
</body>
</html>
