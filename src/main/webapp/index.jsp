<%@ page import="java.util.Iterator" %>
<%@ page import="model.Record" %>
<%@ page import="model.Artist" %>
<%@ page import="org.javalite.activejdbc.Base" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gafr" uri="customArtistTag" %>
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

    <%%>

    <div class="slider row" data-slick='{"slidesToShow": 1, "slidesToScroll": 1}'>
        <c:forEach items="${mostRecentRecords}" var="record">
            <div>
                <div class="imageFrame">
                    <img class="img-fluid" src="<c:out value="${record.get('img_file_path')}"/>" alt="recordImg"/>
                </div>
                <div class="imageSliderRecordDesc">

                    <span>
                        <!-- TODO: use custom tag-->
                        <gafr:getArtistForRecord inputArtist="asdaksjld" />
                    </span>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.slider').slick();
    })
</script>


<jsp:include page="include/footinclude.jsp"/>
