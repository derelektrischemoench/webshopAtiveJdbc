<%@ page import="java.util.Iterator" %>
<%@ page import="model.Record" %>
<%@ page import="model.Artist" %>
<%@ page import="org.javalite.activejdbc.Base" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


<jsp:include page="include/headinclude.jsp"/>

<div class="container main">
    <div class="row">
        <div class="col-sm">
            <h1>All available records:</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <c:forEach items="${allRecordsCustomer}" var="record">
                <div class="slider-card mdl-card mdl-shadow--2dp"
                     style="background-image: url(' ${ record.get('img_file_path') } ')">
                    <div class="mdl-card__title">
                        <h2 class="mdl-card__title-text"><mytaglib:getArtistForRecord
                                inputArtist="${record.get('artist_id')}"/> - <c:out
                                value="${record.get('title')}"/>
                        </h2>
                        <a class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored" href="<c:out value="${pageContext.request.contextPath}/recordDetail?recordId=${record.get('id')}" />" >
                            View
                        </a>
                    </div>
                </div>
            </c:forEach>


        </div>
    </div>
    <div class="row paginationRow">
        <div class="col-sm pagination">
            <a class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
               href="<c:out value="${pageContext.request.contextPath}/mostRecentRecords?pageNo=${newPageno}" />">Next
                Page
            </a>

        </div>
    </div>
</div>

