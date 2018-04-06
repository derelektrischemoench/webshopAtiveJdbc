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
                    <c:set var="iterCount" value="${0}"/>
                    <form action="<c:out value="${pageContext.request.contextPath}/shoppingCartDetail/checkout" />"
                          class="updateShoppingCartAmount">
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
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                            <input class="mdl-textfield__input" type="text"
                                                   pattern="-?[0-9]*(\.[0-9]+)?"
                                                   id="sample4" value="1">
                                            <label class="mdl-textfield__label" for="sample4">Amount <i
                                                    class="material-icons">mode_edit</i></label>
                                            <span class="mdl-textfield__error">Input is not a number!</span>
                                        </div>
                                        <btn class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect show-modal"
                                             data-id="${record.get('id')}">
                                            Delete from shoppingcart
                                        </btn>
                                    </div>

                                </div>
                            </div>

                            <c:set var="total" value="${total + record.getFloat('price')}"></c:set>
                            <c:set var="iterCount" value="${iterCount + 1}"></c:set>
                        </c:forEach>

                        <c:if test="${fn:length(recordsInShoppingcart) gt 0}">
                            <div class="row">
                                <div class="col-sm flex-column justify-content-end">
                                    <fmt:formatNumber var="totalFormatted" type="number" minFractionDigits="2"
                                                      maxFractionDigits="2"
                                                      value="${total}"/>
                                    <p class="mdl-typography--headline" style="text-align: end">Total: <c:out
                                            value="${totalFormatted} €"/></p>
                                </div>
                            </div>
                            <div class="d-flex justify-content-end">
                                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                                        type="submit">
                                    <i class="material-icons">payment</i>Proceed to checkout
                                </button>
                            </div>
                        </c:if>
                    </form>
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

</div>

<!-- DELETE confirm dialog -->

<dialog class="mdl-dialog">
    <h4 class="mdl-dialog__title">You sure you want to delete this?</h4>
    <!--<div class="mdl-dialog__content">
        <p> Are you sure you want to delete this record from the shoppigcart?</p>
    </div>-->
    <input type="hidden" name="fuckthis" class="fuckthis" value=""/>

    <div class="mdl-dialog__actions">

        <input type="submit" class="mdl-button delete"/>

        <button type="button" class="mdl-button close">Nope</button>
    </div>
</dialog>

<script>
    var dialog = document.querySelector('dialog');
    var showModalButton = $('.show-modal');
    if (!dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }

    showModalButton.each(function () {
        $(this).click(function () {
            var dataValue = $(this).attr("data-id");
            $('.fuckthis').val(dataValue);
            dialog.showModal();
        })
    });

    dialog.querySelector('.close').addEventListener('click', function () {
        dialog.close();
    });

    dialog.querySelector('.delete').addEventListener('click', function () {
        deleteFromShoppingcart($(this));
    });


    var deleteFromShoppingcart = function () {
        var recordId = $('.fuckthis').val();
        console.log("recordIdvalue in request: " + recordId);

        $.ajax({
            url: "/webapp/shoppingCartDetail/deleteFromShoppingCart",
            type: "get",

            data: {
                recordId: recordId
            },

            success: function (response) {
                //Do Something
                location.reload();
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        })
    };


</script>