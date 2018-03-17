<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container-fluid main searchResults">
    <div class="row">
        <div class="col-sm-12">
            <h2>
                Found the following artists:
            </h2>
        </div>
    </div>

    <div class="row">
        <c:forEach items="${artists}" var="artist">
            <div class="col-sm-6">
                <div class="demo-card-square mdl-card mdl-shadow--2dp itemCard__searchResult mx-auto">
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
        <div class="col-sm-12">
            <h2>
                Results in records:
            </h2>
        </div>
    </div>

    <div class="row">
            <c:forEach items="${records}" var="record">
                <div class="demo-card-square mdl-card mdl-shadow--2dp itemCard__searchResultRecord mx-auto">
                    <div class="titleImage" style="background-image: url(' ${ record.get('img_file_path') } ')">
                        <div class="mdl-card__title mdl-card--expand">
                            <h2 class="mdl-card__title-text">Record Name: <br> <c:out
                                    value="${record.get('title')}"/>
                            </h2>
                            <div class="mdl-card__actions mdl-card--border">
                        <a class="mdl-button mdl-button--colored mdl-js-button mdl-button--raised mdl-js-ripple-effect"
                           href="/addTo...?recordId=${record.get('id')}">+ cart
                        </a>
                    </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
</div>