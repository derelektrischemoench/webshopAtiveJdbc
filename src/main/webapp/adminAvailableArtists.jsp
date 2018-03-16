<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Available Artists:</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <ul>
                <c:forEach items="${artists}" var="artist">
                    <li>
                        <!--imgs path: <c:out value="${artist.get('artist_img_path')}" />-->
                        Artist name: <c:out value="${artist.get('artist_name')}" />
                    </li>
                    <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminArtistsOverview/adminRecordsOverview" />?artistId=${artist.get('id')}">show records of artist</a>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6 col-md-3">
            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/AdminArtistsOverview/addArtist" />">
                <button class='btn btn-outline-primary'>Add Artist</button>
            </a>
        </div>
    </div>


</div>

<jsp:include page="include/footinclude.jsp"/>