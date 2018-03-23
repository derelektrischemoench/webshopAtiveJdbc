<%@ page import="java.util.Iterator" %>
<%@ page import="model.Record" %>
<%@ page import="model.Artist" %>
<%@ page import="org.javalite.activejdbc.Base" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container-fluid main">
    <div class="row">
        <div class="col-sm-12">
            <h1>All available records:</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
             <c:forEach items="${foundRecords}" var="record">
                 <c:out value="${record.get('title')}"/>
             </c:forEach>
        </div>
    </div>
</div>


<jsp:include page="include/footinclude.jsp"/>
