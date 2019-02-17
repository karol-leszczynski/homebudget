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
<body>
<jsp:include page="menu.jsp"/>

<div class="flex-container">
    <div class="columnleft">
        <div>
            <a href="/budget/new" class="button-menu">Dodaj nowy budżet</a>
        </div>
        <c:if test="${not empty sessionScope.currentbudgetid}">
            <div>
                <a href="/budget/clone" class="button-menu">Duplikuj budżet</a>
            </div>
        </c:if>
        <hr class="line">
        <p class="text"><strong>TWOJE BUDŻETY</strong></p>
        <div>
            <c:forEach items="${list}" var="budget">
                <div class="text">
                    <a href="/budget/delete?budgetId=${budget.id}" class="button-negative">Usuń</a>
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
                <h3>
                    <strong>BUDŻET</strong> OD <strong>
                        ${sessionScope.currentbudgetstartdate.format(formatterLong)}</strong> DO
                    <strong>${sessionScope.currentbudgetenddate.format(formatterLong)}</strong>
                </h3>
                <form class="text" action="/invitation/send" method="post">
                    <c:if test="${not empty inviteMessage}">
                        <p class="error"><br/>${inviteMessage}</p>
                    </c:if>
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
                                <td class="halfbread-left">Zaplanowane oszczędności: <strong>1500 zł</strong></td>
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
                            <li></li>
                            <li>1</li>
                            <li>2</li>
                            <li>3</li>
                            <li>4</li>
                            <li>5</li>
                            <li>6</li>
                            <li>7</li>
                            <li>8</li>
                            <li>9</li>
                            <li>10</li>
                            <li>11</li>
                            <li>12</li>
                            <li>13</li>
                            <li>14</li>
                            <li>15</li>
                            <li>16</li>
                            <li>17</li>
                            <li>18</li>
                            <li>19</li>
                            <li>20</li>
                            <li>21</li>
                            <li>22</li>
                            <li>23</li>
                            <li>24</li>
                            <li>25</li>
                            <li>26</li>
                            <li>27</li>
                            <li>28</li>
                            <li>29</li>
                            <li>30</li>
                            <li>31</li>
                        </ul>
                    </div>
                </div>

                <ul class="text-expired">
                    Upłynął już termin na opłacenie:
                    <li class="text-expired">&squf; Kocie żarcie</li>
                    <li class="text-expired">&squf; Paliwo</li>
                </ul>
                <hr class="thinLine">

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
                    <c:forEach items="${incomes}" var="income">
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

                <p class="list"><strong>OSZCZĘDNOŚCI</strong><br></p>
                <form class="list" action="" method="post">
                    <input style="width: 75%" name="savings" type="number" required="" value="" min="0">
                    <input class="button-add" type="submit" value="+">
                </form>

            </div>

            <div class="columnright">
                <p class="list"><strong>WYDATKI</strong></p>
                <hr class="thinLine">
                <form action="" method="post">
                    <fieldset class="expences-form">
                        <legend class="text-gray-small">PLANOWANE:</legend>
                        Opis <input style="width: 33%" name="ExpenceDescription" type="text" maxlength="50" required>
                        Kwota <input style="width: 12%" name="ExpenceAmmount" type="number" required="" value="" min="0">
                        Data <input style="width: 8rem" name="ExpenceDate" type="date" max="" min="">
                        <input class="button-add" type="submit" value="+">
                    </fieldset>
                </form>
                <form action="" method="post">
                    <fieldset class="expences-form">
                        <legend class="text-gray-small">NIEPRZEWIDZIANE:</legend>
                        Opis <input style="width: 60%" name="ExpenceDescription" type="text" maxlength="50" required>
                        Kwota <input style="width: 18%" name="ExpenceAmmount" type="number" required="" value="" min="0">
                        <input class="button-add" type="submit" value="+">
                    </fieldset>
                </form>
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
                    <tr>
                        <td><a class="button-delete">-</a></td>
                        <td class="expences-description">Paliwo sdfsdfsdfsdfsdfsdfsssdfsdfsd</td>
                        <td>200 zł</td>
                        <td>10-01</td>
                        <td><form class="list"><input type="checkbox" class="checkbox"></form></td>
                        <td><form class="list"><input type="checkbox" class="checkbox"></form></td>
                    </tr>
                    <tr>
                        <td><a class="button-delete">-</a></td>
                        <td class="expences-description">Kocie żarcie</td>
                        <td>200 zł</td>
                        <td>10-01</td>
                        <td><form class="list"><input type="checkbox" class="checkbox"></form></td>
                        <td><form class="list"><input type="checkbox" class="checkbox"></form></td>
                    </tr>
                    </tbody></table>
            </div>

        </c:if>
    </div>
</div>
</body>
</html>
