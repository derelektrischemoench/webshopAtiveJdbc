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
                <img src="<c:out value="${pageContext.request.contextPath}" />/20180308190817IMG_20171031_164220.jpg " alt="">

                <c:forEach items="${artists}" var="artist">
                    <li>
                        img path: <c:out value="${artist.get('artist_img_path')}" />
                        <c:out value="${artist.get('first_name')}" />
                        <c:out value="${artist.get('last_name')}" />



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

<!--TODO: WHERE THE FUCK SHOULD IMAGES GO??? fuck this shit-->
<!-- TODO: -->
<jsp:include page="include/footinclude.jsp"/>