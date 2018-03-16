<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Available Records for <c:out value="${artist.get('artist_name')}"/>:</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <c:forEach items="${artistsRecords}" var="record">
                    <li>
                        Record Name: <c:out value="${record.get('title')}" />
                    </li>
                </c:forEach>
        </div>
    </div>


    <!-- TODO: list available records-->

    <div class="row">
        <div class="col-sm-12">
            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminArtistsOverview/adminRecordsOverview/addRecord"></c:out>?artist_id=<c:out value="${artist.get('id')}" />">Add
                record for <c:out value="${artist.get('artist_name')}"></c:out> </a>

        </div>
    </div>

</div>

<jsp:include page="include/footinclude.jsp"/>