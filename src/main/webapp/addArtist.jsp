<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h1>Add Artist</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-8 offset-sm-2">
            <form action="<c:out value="${pageContext.request.contextPath}/adminLogin/addArtist" />" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="recordImage">Upload artist image</label><br>
                    <input id="recordImage" name="createArtist__artistImage" type="file" size="50" accept="image/*" class="btn btn-outline-secondary">
                </div>

                <div class="form-group">
                    <label for="artistName">Artist name:</label>
                    <input id="artistName" type="text" name="createArtist__artistName">
                </div>

                <div class="form-group">
                    <label for="artistFirstName">First name:</label>
                    <input id="artistFirstName" type="text" name="createArtist__artistFirstName">
                </div>

                <div class="form-group">
                    <label for="artistLastName">Last name:</label>
                    <input id="artistLastName" type="text" name="createArtist__artistLastName">
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>


<jsp:include page="include/footinclude.jsp"/>