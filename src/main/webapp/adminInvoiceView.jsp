<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>


<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Invoice:</h1>
        </div>
    </div>

    <table class="table hover table-hover adminOrderTable">
        <thead>
            <tr>
                <th scope="col">Item name</th>
                <th scope="col">Price</th>
                <th scope="col">Quantity</th>
                <th scope="col">Sum</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach items="${recordsInOrder}" var="record">
                    <td>${record.get('title')}</td>
                    <td>${record.get('price')}</td>
                    <td></td>
                    <td></td>
                </c:forEach>
            </tr>
        </tbody>
    </table>
</div>

<jsp:include page="include/footinclude.jsp"/>