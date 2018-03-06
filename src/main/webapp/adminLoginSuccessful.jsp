<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


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
            <a href="/webshop/adminLogin/addRecord">
                <button class='btn btn-outline-primary'>Add Record</button>
            </a>
        </div>
    </div>


</div>

<script>
    var successMessage = $('.createAdminAccount').firstElementChild();
    successMessage.addClass('timeOver');
</script>


<jsp:include page="include/footinclude.jsp"/>