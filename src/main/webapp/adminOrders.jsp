<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8"%>
<jsp:include page="include/headinclude.jsp"/>

<jsp:include page="include/headinclude.jsp"/>

<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>All orders:</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <c:forEach items="${allOrders}" var="order">
                <c:out value="${order.get('date')}"/>
                <c:out value="${order.get('first_name')}" />
                <c:out value="${order.get('last_name')}" />
            </c:forEach>
        </div>
        </div>
    </div>
</div>

<jsp:include page="include/footinclude.jsp"/>