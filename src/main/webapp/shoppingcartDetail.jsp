<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:include page="include/headinclude.jsp"/>

<div class="container main">
    <div class="row align-items-baseline">
        <div class="col-sm"><h1>Items in your shoppingcart:</h1></div>
    </div>

    <div class="row">
        <div class="col-sm">
            <c:choose>
                <c:when test="${fn:length(recordsInShoppingcart) gt 0}">
                    <c:set var="total" value="${0}"/>
                    <c:forEach items="${recordsInShoppingcart}" var="record">
                        <div class="shoppingCartRecord mdl-card mdl-shadow--2dp itemCard shoppingCartItem mx-auto"
                             style="background-image: url(' ${ record.get('img_file_path') } ')">
                            <div class="mdl-card__actions mdl-card--border justify-content-end"
                                 style="background: rgba(0,0,0,.5)">

                                <div class="d-flex flex-row justify-content-between">
                                    <h6 class="mdl-card__title-text"><mytaglib:getArtistForRecord
                                            inputArtist="${record.get('artist_id')}"/> <br>
                                        <c:out value="${record.get('title')}"/>
                                    </h6>
                                    <p class="price" style="color: #fff">Price: <c:out
                                            value="${record.getFloat('price')} "/> €</p>
                                </div>

                                <div class="d-flex flex-row">
                                    <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                                       href="shoppingCartDetail/deleteFromShoppingCartConfirm?recordId=${record.get('id')}">
                                        Delete from shoppingcart
                                    </a>
                                </div>
                            </div>
                        </div>

                        <c:set var="total" value="${total + record.getFloat('price')}"></c:set>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2>Your shoppingcart is empty</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <hr>
        </div>
    </div>
    <c:if test="${fn:length(recordsInShoppingcart) gt 0}">
        <div class="row">
            <div class="col-sm flex-column justify-content-end">
                <fmt:formatNumber var="totalFormatted" type="number" minFractionDigits="2" maxFractionDigits="2"
                                  value="${total}"/>
                <p class="mdl-typography--headline" style="text-align: end">Total: <c:out
                        value="${totalFormatted} €"/></p>
            </div>
        </div>
        <div class="d-flex justify-content-end">
            <a href="<c:out value="${pageContext.request.contextPath}/shoppingCartDetail/checkout" />">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                    <i class="material-icons">payment</i>Proceed to checkout
                </button>
            </a>
        </div>
    </c:if>
</div>