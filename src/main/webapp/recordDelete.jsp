<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>


<jsp:include page="/include/headinclude.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>Are you sure you want to delete <br>
                <span class="colorBlue"><mytaglib:getRecordNameById
                        inputRecordId="<%= request.getParameter("recordId")%>"/>
                </span>?</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm d-flex justify-content-around">
            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminArtistsOverview/adminRecordsOverview/deleteRecordFinally" />?recordId=<%= request.getParameter("recordId")%>">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                    Si, fliegen
                </button>
            </a>
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                No, señor
            </button>
        </div>
    </div>
</div>