<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h1>Add records</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-8 offset-sm-2">
            <form action="<c:out value="${pageContext.request.contextPath}/adminLogin/addRecord" />" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="recordImage">Upload record image</label><br>
                    <input id="recordImage"name="createRecord__recordImage" type="file" size="50" accept="image/*" class="btn btn-outline-secondary">
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>


<jsp:include page="include/footinclude.jsp"/>