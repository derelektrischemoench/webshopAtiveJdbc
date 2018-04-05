<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>All Customers with orders:</h1>
        </div>
    </div>

    <table class="table hover table-hover adminOrderTable">
        <thead>
        <tr>
            <th scope="col">Last Name</th>
            <th scope="col">First Name</th>
            <th scope="col">Street</th>
            <th scope="col">House no</th>
            <th scope="col">zip</th>
            <th scope="col">city</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allAccounts}" var="customer">
            <tr>
                <td>
                    <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminCustomersOverview/showOrdersPerCustomer?customerId=${customer.get('id')}" />">
                        <c:out value="${customer.get('last_name')}"/>
                    </a>
                </td>
                <td>
                    <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminCustomersOverview/showOrdersPerCustomer?customerId=${customer.get('id')}" />">
                        <c:out value="${customer.get('first_name')}"/>
                    </a>
                </td>
                <td>
                    <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminCustomersOverview/showOrdersPerCustomer?customerId=${customer.get('id')}" />">
                        <c:out value="${customer.get('street')}"/>
                    </a>
                </td>
                <td>
                    <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminCustomersOverview/showOrdersPerCustomer?customerId=${customer.get('id')}" />">
                        <c:out value="${customer.get('house_number')}"/>
                    </a>
                </td>
                <td>
                    <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminCustomersOverview/showOrdersPerCustomer?customerId=${customer.get('id')}" />">
                        <c:out value="${customer.get('zip')}"/>
                    </a>
                </td>
                <td>
                    <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminCustomersOverview/showOrdersPerCustomer?customerId=${customer.get('id')}" />">
                        <c:out value="${customer.get('city')}"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="include/footinclude.jsp"/>