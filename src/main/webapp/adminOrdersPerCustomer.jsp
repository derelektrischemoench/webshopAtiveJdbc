<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Orders of <span class="colorBlue">${customer.get('user_name')}</span></h1>
        </div>
    </div>

    <table class="table hover table-hover adminOrderTable">
        <thead>
        <tr>
            <th scope="col">Order id</th>
            <th scope="col">Order date</th>
            <th scope="col">Recipient first name</th>
            <th scope="col">Recipient last name</th>
            <th scope="col">Recipient means of payment</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td><c:out value="${order.get('id')}"/></td>
                <td>
                    <c:set var="orderDate" scope="session" value="${order.get('date')}"/>
                    <c:out value="${fn:substring(orderDate, 0, 10)}"/>
                </td>
                <td>
                    <c:out value="${order.get('first_name')}"/>
                </td>
                <td>
                    <c:out value="${order.get('last_name')}"/>
                </td>
                <td>
                    <c:out value="${order.get('means_of_payment')}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>