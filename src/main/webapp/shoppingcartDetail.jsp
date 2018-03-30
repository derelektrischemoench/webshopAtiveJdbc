<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>


<jsp:include page="include/headinclude.jsp"/>

<div class="container main">
    <div class="row align-items-baseline">
        <div class="col-sm"><h1>Items in your shoppingcart:</h1></div>
    </div>

    <div class="row">
        <div class="col-sm">
            <c:forEach items="${recordsInShoppingcart}" var="record">
                <div class="demo-card-square mdl-card mdl-shadow--2dp itemCard shoppingCartItem mx-auto">
                    <div class="titleImage">
                        <div class="mdl-card__title mdl-card--expand">
                            <h6 class="mdl-card__title-text"><mytaglib:getArtistForRecord inputArtist="${record.get('artist_id')}"/> <br>
                                <c:out value="${record.get('title')}"/>
                            </h6>

                        </div>
                        <div class="imagepart"
                             style="background-image: url(' ${ record.get('img_file_path') } ')"></div>
                    </div>
                    <div class="mdl-card__actions mdl-card--border">
                        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            Delete from shoppingcart
                        </a>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="include/footinclude.jsp"/>