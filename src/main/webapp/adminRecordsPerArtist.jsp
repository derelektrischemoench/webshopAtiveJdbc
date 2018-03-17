<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container-fluid notIndex">
    <div class="row">
        <div class="col-sm-12">
            <h1>Available Records for <c:out value="${artist.get('artist_name')}"/>:</h1>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <c:forEach items="${artistsRecords}" var="record">
            <div class="col-sm-6">
                <div class="demo-card-square mdl-card mdl-shadow--2dp itemCard mx-auto">
                    <div class="titleImage">
                        <div class="mdl-card__title mdl-card--expand">
                            <h2 class="mdl-card__title-text">Record Name: <br> <c:out value="${record.get('title')}"/></h2>

                        </div>
                        <div class="imagepart"
                             style="background-image: url(' ${ record.get('img_file_path') } ')"></div>
                    </div>

                    <div class="mdl-card__supporting-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Aenan convallis.
                    </div>
                    <div class="mdl-card__actions mdl-card--border">
                        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            View Updates
                        </a>
                    </div>
                </div>


            </div>
        </c:forEach>
    </div>


    <!-- TODO: list available records-->

    <div class="row">
        <div class="col-sm-12">
            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminArtistsOverview/adminRecordsOverview/addRecord"></c:out>?artist_id=<c:out value="${artist.get('id')}" />">Add
                record for <c:out value="${artist.get('artist_name')}"></c:out> </a>

        </div>
    </div>

</div>

