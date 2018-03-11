<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h1>Add record for ${artistName}</h1>
            <h2></h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-10">
            <form action="<c:out value="${pageContext.request.contextPath}/adminLogin/adminArtistsOverview/adminRecordsOverview/addRecord" />"
                  method="post"
                  enctype="multipart/form-data">

                <input id="artistName" type="hidden" name="createRecord__artistName" value="${artistName}">


                <div class="form-group">
                    <label for="recordName">Name of the record:</label><br>
                    <input id="recordName" type="text" name="createRecord__recordName">
                </div>

                <div class="form-group">
                    <label for="recordLabel">Record published by label::</label><br>
                    <input id="recordLabel" type="text" name="createRecord__recordLabel">
                </div>

                <div class="form-group">
                    <label for="recordImage">Upload record image</label><br>
                    <input id="recordImage" name="createRecord__recordImage" type="file" size="50" accept="image/*"
                           class="btn btn-outline-secondary">
                </div>


                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>


<jsp:include page="include/footinclude.jsp"/>