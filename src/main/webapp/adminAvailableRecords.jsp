<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Available records:</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6 col-md-3">
            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminRecordsOverview/addRecord" />">
                <button class='btn btn-outline-primary'>Add Record</button>
            </a>
        </div>
    </div>
</div>

<jsp:include page="include/footinclude.jsp"/>