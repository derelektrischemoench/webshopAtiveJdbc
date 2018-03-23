<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Add Artist</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-8 offset-sm-2">
            <form action="<c:out value="${pageContext.request.contextPath}/adminLogin/AdminArtistsOverview/addArtist" />" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
                <!-- IMPORTANT ! DO NOT CHANGE FORM NAMES OR VALS-->
                <div class="form-group">
                    <label for="artistAlias">Artist alias</label><br>
                    <input type="text" name="artistAlias" id="artistAlias" />
                </div>
                <div class="form-group">
                    <label for="artistFirstname">Artist first name</label><br>
                    <input type="text" name="artistFirstname" id="artistFirstname" />
                </div>
                <div class="form-group">
                    <label for="artistLastname">Artist last name</label><br>
                    <input type="text" name="artistLastname" id="artistLastname" />
                </div>
                <div class="form-group">
                    <label for="artistLabel">Artist label</label><br>
                    <input type="text" name="artistLabel" id="artistLabel" />
                </div>

                <div class="form-group">
                    <label for="artistImage">Upload artist image</label><br>
                    <input id="artistImage" name="artistImage" type="file" size="50" accept="image/*" class="btn btn-outline-secondary">
                </div>



                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>


<jsp:include page="include/footinclude.jsp"/>