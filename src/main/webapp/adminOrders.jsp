<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>All orders:</h1>
        </div>
    </div>

    <table class="table hover table-hover adminOrderTable">
        <thead>
        <tr>
            <th scope="col">Order date</th>
            <th scope="col">Customer first name</th>
            <th scope="col">Customer last name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allOrders}" var="order">
            <tr>
                <td>
                    <a href="adminOrdersOverview/adminDetail?orderId=${order.get('id')}">
                        <c:set var = "orderDate" scope="session" value="${order.get('date')}" />
                        <c:out value="${fn:substring(orderDate, 0, 10)}"/>
                    </a>
                </td>
                <td>
                    <a href="adminOrdersOverview/adminDetail?orderId=${order.get('id')}">
                        <c:out value="${order.get('first_name')}"/>
                    </a>
                </td>
                <td>
                    <a href="adminOrdersOverview/adminDetail?orderId=${order.get('id')}">
                        <c:out value="${order.get('last_name')}"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="include/footinclude.jsp"/>