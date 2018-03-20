<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container adminLogin main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Successfully created your account</h1>
        </div>
    </div>
    <div class="row">
        <h3>Username: ${username}</h3>
    </div>
</div>
