<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container main">
    <div class="row">
        <div class="col-sm">
            <h1>${record.get('title')}</h1>
        </div>
    </div>
    <div class="row align-items-start">
        <div class="col-sm">
            <div class="demo-card-square mdl-card mdl-shadow--2dp customerRecordDetailCard">
                <div class="mdl-card__title mdl-card--expand"
                     style="background-image: url(' ${ record.get('img_file_path') }')">
                </div>
            </div>
        </div>
        <div class="col-sm">
            <h4 style="margin-top: 0">Tracklist:</h4>
            <ul class='mdl-list'>
                <c:forEach items="${tracksOfRecord}" var="track">
                    <li class="mdl-list__item">
                    <span class="mdl-list__item-primary-content">
                            ${track.get('name')}
                    </span>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-sm">
            <span>Price</span>
            <h3>${record.get('price')} €</h3>

            <c:choose>
                <c:when test="${cookie.username eq null}">
                    Please login to add this record to your cart
                </c:when>
                <c:otherwise>
                    <button type="submit"
                            class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent addToCart">
                        Add to cart
                    </button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div aria-live="assertive" aria-atomic="true" aria-relevant="text" class="mdl-snackbar mdl-js-snackbar">
        <div class="mdl-snackbar__text"></div>
        <button type="button" class="mdl-snackbar__action"></button>
    </div>
</div>

<script>
    //ajax function to add a record to the cart to prevent multiple additions via requests
    //if finished this should reload the cart and display a message on bottom confirming the addition
    // of the record
    $(document).ready(function () {
        //refreshShoppingCartContents();
    });

    $(".addToCart").click(function () {
        addToCart();

    });

    var addToCart = function () {
        $.get("/webapp/recordDetail/addToCart?recordId=${record.get('id')}", function () {
            showSnackbar();
            //refreshShoppingCartContents();
        });

    };

    var showSnackbar = function () {
        var notification = document.querySelector('.mdl-js-snackbar');
        var data = {
            message: 'Record added to cart',
            actionHandler: function (event) {
            },
            actionText: 'Undo',
            timeout: 10000
        };
        notification.MaterialSnackbar.showSnackbar(data);
    };

    /* TODO: fix this some time:
    var refreshShoppingCartContents = function () {
        console.log("called ajaxhelper in js");
        var shoppingCart = $('shoppingCart');
        var cookieVal = Cookies.get("shoppingCartId");
        var shoppingcartId = cookieVal;
        var shoppingCartContents = $(".shoppingcartcontents");
        targetUrl = "http://localhost:8080/webapp/getShoppingCartContentsAjaxAdapter?shoppingCartId=" + shoppingcartId;

        //TODO: this works the first time after server restart, not afterwards
        $.get(targetUrl, function (data) {
            //console.log("get done; data: " + data);
            var resultJson = jsonQ(data);
            var ArtistName = resultJson.find('artist_name').value().toString();
            //var recordName = resultJson.find('title').value().toString();
            //var recordPrice = resultJson.find('price').value().toString();
            console.log(ArtistName);
        });

        str = "asdasd";
        str.insertAfter(shoppingCartContents);

        (ArtistName + " " + recordName + " " + recordPrice).insertAfter(shoppingCartContents);
        //shoppingCartContents.insertAfter("asdasdasd");


        //console.log("Response of the ajax calldddd: " + shoppingcartAjaxResult);

    };
*/
</script>

