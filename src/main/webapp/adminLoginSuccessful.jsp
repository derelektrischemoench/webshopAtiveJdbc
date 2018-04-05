<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8"%>
<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Your login has been successful</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <h3>Do admin stuff:</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-sm">
            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/AdminArtistsOverview" />">
                <button type="submit"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                    Artists
                </button>
            </a>
        </div>
        <div class="col-sm">
            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminCustomersOverview" />">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                    Customers
                </button>
            </a>
        </div>
        <div class="col-sm">
            <a href="<c:out value="${pageContext.request.contextPath}/adminLogin/adminOrdersOverview" />">
                <button type="submit"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                    Orders
                </button>
            </a>
        </div>
    </div>


</div>

<script>
    var successMessage = $('.createAdminAccount').firstElementChild();
    successMessage.addClass('timeOver');
</script>


<jsp:include page="include/footinclude.jsp"/>