<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:include page="include/headinclude.jsp"/>


<div class="container notIndex">
    <c:choose>
        <c:when test="${fn:length(records) gt 0}"> <!-- Record results-->
            <div class="row">
                <div class="col-sm-12">
                    <h1>Found the following results for: <c:out value="${searchQuery}"/> in Records:</h1>
                </div>
            </div>

            <div class="row">
                <c:forEach items="${records}" var="record">
                    <div class="col-sm-6">
                        <div class="demo-card-square mdl-card mdl-shadow--2dp itemCard mx-auto">
                            <div class="titleImage">
                                <div class="mdl-card__title mdl-card--expand">
                                    <h2 class="mdl-card__title-text">Record Name: <br> <c:out
                                            value="${record.get('title')}"/>
                                    </h2>

                                </div>
                                <div class="imagepart"
                                     style="background-image: url(' ${ record.get('img_file_path') } ')"></div>
                            </div>

                            <div class="mdl-card__supporting-text">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                Aenan convallis.
                            </div>
                            <div class="mdl-card__actions mdl-card--border">
                                <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                                   href="">
                                    Add to cart
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-sm-12">
                    <h1>No results in Records</h1>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    <hr>
</div>
<div class="container-fluid">


    <div class="row">
        <c:choose>
            <c:when test="${fn:length(artists) gt 0}">
                <c:forEach items="${artists}" var="artist">
                    <div class="col-sm-6">
                        <div class="demo-card-square mdl-card mdl-shadow--2dp itemCard mx-auto">
                            <div class="titleImage">
                                <div class="mdl-card__title mdl-card--expand">
                                    <h2 class="mdl-card__title-text">Artist Name: <br> <c:out
                                            value="${artist.get('artist_name')}"/></h2>

                                </div>
                                <div class="imagepart"
                                     style="background-image: url(' ${ record.get('img_file_path') } ')"></div>
                            </div>

                            <div class="mdl-card__supporting-text">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                Aenan convallis.
                            </div>
                            <div class="mdl-card__actions mdl-card--border">
                                <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                                   href="">
                                    Add to cart
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-sm-12">
                        <h1>No results in Artists</h1>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>