<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid footer">
    <div class="row">
        <div class="col-sm-12 footercol">
            <p>
                <c:if test="${cookie.username.value eq 'admin'}">
                    <a href="adminLogout">Logout</a>
                </c:if>
                <c:if test="${cookie.username.value ne 'admin'}">
                    <a href="adminLogin">Admin login</a>
                </c:if>

                <c:out value="${cookie.username.value}"></c:out>

            </p>
        </div>
    </div>
</div>

</body>
</html>
