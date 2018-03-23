<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>


<jsp:include page="include/headinclude.jsp"/>
<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Successfully added artist</h1>
            <span class="highlight">${artistName}</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <a href="adminLogin/AdminArtistsOverview">
                <button class="btn btn-outline-primary">
                    Back
                </button>
            </a>

        </div>
    </div>

</div>


<jsp:include page="include/footinclude.jsp"/>