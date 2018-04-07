<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


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
        <c:set var="recordPriceTotal" value="0" scope="page"/>
        <c:forEach items="${recordAndAmount}" var="recordAndAmount">
            <c:set var="recordAmount" value="${recordAndAmount.value}" />
            <c:set var="recordPrice" value="${recordAndAmount.key.get('price')}" />

            <tr>
                <td>
                    <mytaglib:getArtistForRecord inputArtist="${recordAndAmount.key.get('artist_id')}"/>
                     - <c:out value="${recordAndAmount.key.get('title')}"/>
                </td>
                <td><c:out value="${recordPrice}"/> €</td>
                <td><c:out value="${recordAmount}"/></td>
                <td>
                    <c:set var="recordProduct" value="${recordPrice * recordAmount}" />
                    <fmt:formatNumber type = "number" groupingUsed = "false" value = "${recordProduct}" /> €
                </td>
            </tr>

        </c:forEach>
        <tr></tr>

            <tr>
                <td>Total</td>
                <td></td>
                <td></td>
                <td><c:out value="${recordPriceProduct}" /></td>
            </tr>
        </tbody>
    </table>
</div>

<jsp:include page="include/footinclude.jsp"/>