<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container-fluid">
    <div class="row logo">
        <div class="col-xs-2">
            <div>to</div>
        </div>
        <div class="col-xs-10">
            <p><span>t</span>ight </p>
            <p><span>s</span>hit</p>
            <p><span>r</span>ecords</p>
        </div>
    </div>

    <div class="slider row" data-slick='{"slidesToShow": 1, "slidesToScroll": 1}'>
        <div><h3>1</h3></div>
        <div><h3>2</h3></div>
        <div><h3>3</h3></div>
        <div><h3>4</h3></div>
        <div><h3>5</h3></div>
        <div><h3>6</h3></div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.slider').slick();
    })
</script>


<jsp:include page="include/footinclude.jsp"/>
