<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container main">
    <div class="row">
        <div class="col-sm-12">

            <h1>Order ${order.get('id')}: <br> <span class="colorBlue"><mytaglib:dateFormatter
                    dateString="${order.get('date')}"/></span></h1>

        </div>
    </div>

    <div class="row">
        <table class="table hover table-hover adminOrderTable">
            <thead>
                <tr>
                    <th scope="col">First name</th>
                    <th scope="col">Last name</th>
                    <th scope="col">Address</th>
                    <th scope="col">No</th>
                    <th scope="col">Zip</th>
                    <th scope="col">City</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${order.get('first_name')}</td>
                    <td>${order.get('last_name')}</td>
                    <td>${order.get('address')}</td>
                    <td>${order.get('house_number')}</td>
                    <td>${order.get('zip')}</td>
                    <td>${order.get('city')}</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="row">
        <div class="col-12">
            <h2>Items in order:</h2>
        </div>
        <div class="col-sm">
            <c:forEach items="${records}" var="record">
                <c:out value="${record.get('title')}" />
            </c:forEach>
        </div>
    </div>


</div>

<jsp:include page="include/footinclude.jsp"/>