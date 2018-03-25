<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>


<jsp:include page="include/headinclude.jsp"/>

<div class="container notIndex">
    <div class="row">
        <div class="col-sm-12">
            <h1>Available Records for <c:out value="${artist.get('artist_name')}"/>:</h1>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row justify-content-center">
        <c:forEach items="${artistsRecords}" var="record">
            <div class="col-8">
                <div class="demo-card-square mdl-card mdl-shadow--2dp itemCard mx-auto">
                    <div class="titleImage">
                        <div class="mdl-card__title mdl-card--expand">
                            <h4 class="mdl-card__title-text">Record Name: <br> <c:out value="${record.get('title')}"/></h4>

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
                        href="${pageContext.request.contextPath}/adminLogin/adminArtistsOverview/adminRecordsOverview/editRecord?artistId=${artist.get('id')}&recordId=${record.get('id')}">
                            Edit record
                        </a>
                        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            Delete record
                        </a>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminArtistsOverview/adminRecordsOverview/addRecord"></c:out>?artist_id=<c:out value="${artist.get('id')}" />">
                <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
                    <i class="material-icons">add</i>
                </button>
                <span>Add
                record for <c:out value="${artist.get('artist_name')}"></c:out></span>

            </a>

        </div>
    </div>

</div>

