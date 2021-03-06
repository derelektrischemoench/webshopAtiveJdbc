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
                <th scope="col">Means of payment</th>
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
                <td>${order.get('means_of_payment')}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="row">
        <div class="col-12">
            <h2>Items in order:</h2>
        </div>
        <div class="col-sm">
            <ul class="mdl-list">

                <table class="table hover table-hover adminOrderTable">
                    <thead>
                    <tr>
                        <th scope="col">Artist</th>
                        <th scope="col">Record</th>
                        <th scope="col">Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${recordAndAmount}" var="recordAndAmount">
                        <tr>
                            <td>
                                <mytaglib:getArtistForRecord inputArtist="${recordAndAmount.key.get('artist_id')}"/>
                            </td>
                            <td><c:out value="${recordAndAmount.key.get('title')}"/></td>
                            <td><c:out value="${recordAndAmount.value}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </ul>

        </div>
    </div>


</div>

<jsp:include page="include/footinclude.jsp"/>