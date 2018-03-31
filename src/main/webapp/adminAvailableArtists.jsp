<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Available Artists:</h1>
        </div>
    </div>

    <div class="row d-flex">
        <c:forEach items="${artists}" var="artist">
            <div class="col-6">
                <div class="demo-card-square mdl-card mdl-shadow--2dp itemCard mx-auto">
                    <div class="titleImage">
                        <div class="mdl-card__title mdl-card--expand">
                            <h2 class="mdl-card__title-text">Artist Name: <br> <c:out
                                    value="${artist.get('artist_name')}"/>
                            </h2>
                        </div>
                        <div class="imagepart">
                            here be image
                        </div>
                    </div>

                    <div class="mdl-card__supporting-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Aenan convallis.
                    </div>
                    <div class="mdl-card__actions mdl-card--border">
                        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                           href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminArtistsOverview/adminRecordsOverview" />?artistId=${artist.get('id')}">show
                            records of artist
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="row">
        <div class="col-sm-6 col-md-3">

            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/AdminArtistsOverview/addArtist" />">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                    Add Artist
                </button>
            </a>
        </div>
    </div>
</div>
