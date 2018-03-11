<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<c:out value="${pageContext.request.getContextPath()}"/>

<!--<div class="container-fluid main">

<div class="slider row" data-slick='{"slidesToShow": 1, "slidesToScroll": 1}'>
<div><h3>1</h3></div>
<div><h3>2</h3></div>
<div><h3>3</h3></div>
<div><h3>4</h3></div>
<div><h3>5</h3></div>
<div><h3>6</h3></div>
</div>
</div>-->

<div class="container-fluid main">
    <div class="slider row" data-slick='{"slidesToShow": 1, "slidesToScroll": 1}'>
        <c:forEach items="${mostRecentRecords}" var="record">
            <div>
                <h3>New in stock</h3>
                record name: <c:out value="${record.get('title')}"/>
                <c:out value="${record.get('img_file_path')}"/>
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
