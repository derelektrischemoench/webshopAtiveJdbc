<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount">
    <div class="row">
        <div class="col-sm-12">
            <h1>Available Artists:</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6 col-md-3">
            <ul>
                <c:forEach items="${artists}" var="artist">
                    <li>
                        imgs path: <c:out value="${artist.get('artist_img_path')}" />
                        <c:out value="${artist.get('first_name')}" />
                        <c:out value="${artist.get('last_name')}" />
                        <img src="<c:out value="${artist.get('artist_img_path')}" />" alt="jo" />
                        <img src="/uploadFiles/artistImages/kill_processes.png" alt="asdasd"/>

                    </li>
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