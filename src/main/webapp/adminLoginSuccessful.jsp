<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount">
    <div class="row">
        <div class="col-sm-12">
            <h1>Your login has been successful</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            Do admin stuff:
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6 col-md-3">
            <a href="">
                <button class="btn btn-outline-primary">Add records</button>
            </a>
            <a href="">
            <button class="btn btn-outline-primary">Kick users</button>
        </a><a href="">
            <button class="btn btn-outline-primary">???</button>
        </a>
            <a href="">
                <button class="btn btn-outline-primary">Profit</button>
            </a>
        </div>
    </div>


</div>

<script>
    var successMessage = $('.createAdminAccount').firstElementChild();
    successMessage.addClass('timeOver');
</script>


<jsp:include page="include/footinclude.jsp"/>