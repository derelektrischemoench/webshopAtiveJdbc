<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Invoice no <span class="colorBlue">${order.get("id")}</span> <br> from
                <span class="colorBlue">
                    <mytaglib:dateFormatter dateString="${order.get('date')}"/>
                </span>
            </h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm">
            <h4>Customer data:</h4>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <p>
                ${order.getString("first_name")} ${order.getString("last_name")} <br>
                ${order.getString("address")} ${order.getString("house_number")} <br>
                ${order.getString("zip")} ${order.getString("city")}
            </p>
        </div>
        <div class="col">
            <p>asda</p>
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
            <c:set var="rowSum" value="0" scope="page"/>

            <c:forEach items="${recordAndAmount}" var="recordAndAmount">
                <c:set var="recordAmount" value="${recordAndAmount.value}"/>
                <c:set var="recordPrice" value="${recordAndAmount.key.get('price')}"/>

                <tr>
                    <td>
                        <mytaglib:getArtistForRecord inputArtist="${recordAndAmount.key.get('artist_id')}"/>
                        - <c:out value="${recordAndAmount.key.get('title')}"/>
                    </td>
                    <td><c:out value="${recordPrice}"/> €</td>
                    <td><c:out value="${recordAmount}"/></td>
                    <td style="text-align: right">
                        <c:set var="rowSum" value="${recordPrice * recordAmount}"/>
                        <fmt:formatNumber type="number" groupingUsed="false" value="${rowSum}"/> €
                        <c:set var="recordPriceTotal" value="${recordPriceTotal + rowSum}"/>
                    </td>
                </tr>

            </c:forEach>
            <tr></tr>
            <tr>
                <td class="colorBlue"><strong>Total</strong></td>
                <td></td>
                <td></td>
                <td style="text-align: right" class="colorBlue">
                    <strong>
                        <mytaglib:priceSumFormatter price="${recordPriceTotal}"/> €
                    </strong>
                </td>
            </tr>
            <tr>
                <td style="font-size: 10px">Including 19% taxes:</td>
                <td></td>
                <td></td>
                <td style="text-align: right; font-size: 10px"><mytaglib:germanVatCalc
                        inputAmount="${recordPriceTotal}"/> €
                </td>
            </tr>
            </tbody>
        </table>
</div>

<jsp:include page="include/footinclude.jsp"/>