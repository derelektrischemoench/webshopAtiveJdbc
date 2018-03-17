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
            <h2>
                New in stock
            </h2>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="slider row index" data-slick='{"slidesToShow": 2, "slidesToScroll": 1}'>
        <c:forEach items="${mostRecentRecords}" var="record">

            <div class="slider-card mdl-card mdl-shadow--2dp"
                 style="background-image: url(' ${ record.get('img_file_path') } ')">
                <div class="mdl-card__title">
                    <h2 class="mdl-card__title-text"><mytaglib:getArtistForRecord
                            inputArtist="${record.get('artist_id')}"/> - <c:out
                            value="${record.get('title')}"/>
                    </h2>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="row">
        <div class="col-sm-12 text-center">
            <a href="asdasd">
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
            <form action="#">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="sample3">
                    <label class="mdl-textfield__label" for="sample3">Search for records</label>
                </div>

                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                    Go
                </button>

            </form>

        </div>
    </div>
</div>


</div>

<script>
    $(document).ready(function () {
        $('.slider').slick();
    })
</script>


<jsp:include page="include/footinclude.jsp"/>
