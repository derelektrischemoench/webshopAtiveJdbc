<%@ page import="java.util.Iterator" %>
<%@ page import="model.Record" %>
<%@ page import="model.Artist" %>
<%@ page import="org.javalite.activejdbc.Base" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


<c:set var="vis" value="${visibility}" scope="request" />
<c:set var="pem" value="${passwordErrorMsg}" scope="request" />
<c:set var="uem" value="${usernameErrorMsg}" scope="request" />

<jsp:include page="include/headinclude.jsp">
    <jsp:param name="visibility" value="${vis}" />
    <jsp:param name="passwordErrorMsg" value="${pem}" />
    <jsp:param name="usernameErrorMsg" value="${uem}" />
</jsp:include>


<div class="container-fluid main">
    <div class="row">
        <div class="col-sm-12">
            <h2>
                New in stock
            </h2>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <h2>${signinSuccessMessage}</h2>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="slider row index" data-slick='{"slidesToShow": 2, "slidesToScroll": 1}'>
        <mytaglib:getTenMostRecentRecordsForSlider />
    </div>

    <div class="row">
        <div class="col-sm-12 text-center">
            <a href="mostRecentRecords?pageNo=0">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                    <span>See more tight new shit</span><span class="ion-arrow-right-b"></span>
                </button>
            </a>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row search">
        <div class="col-sm-12">
            <form action="searchRecords" method="post">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="sample3" name="index__searchRecord">
                    <label class="mdl-textfield__label" for="sample3">Search for records / Artists</label>
                </div>

                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                    Go
                </button>

            </form>

        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.slider').slick();
    })
</script>


<jsp:include page="include/footinclude.jsp"/>
