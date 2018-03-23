<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>

<jsp:include page="include/headinclude.jsp"/>
<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Add record for ${artist.get('artist_name')}</h1>
            <h2></h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-10">
            <form action="<c:out value="${pageContext.request.contextPath}/adminLogin/adminArtistsOverview/adminRecordsOverview/addRecord" />"
                  method="post"
                  enctype="multipart/form-data" accept-charset="UTF-8">

                <input id="artistId" type="hidden" name="createRecord__artistId" value="${artist.get('id')}">


                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="createRecord__recordName"
                           name="createRecord__recordName">
                    <label class="mdl-textfield__label" for="createRecord__recordName">Name of the record:</label>
                </div>

                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="createRecord__recordLabel"
                           name="createRecord__recordLabel">
                    <label class="mdl-textfield__label" for="createRecord__recordLabel">Record published by
                        label:</label>
                </div>

                <!--<div class="form-group">
                    <label for="recordImage">Upload record image</label><br>
                    <input id="recordImage" name="createRecord__recordImage" type="file" size="50" accept="image/*"
                           class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                </div> -->

                <div class="mdl-textfield mdl-js-textfield mdl-textfield--file">
                    <input class="mdl-textfield__input" placeholder="No file chosen" type="text" id="recordImage" readonly/>
                    <div class="mdl-button mdl-button--icon mdl-button--file">
                        <i class="material-icons">attach_file</i>
                        <input type="file" name="createRecord__recordImage" id="ID"
                               onchange="document.getElementById('recordImage').value=this.files[0].name;"/>
                    </div>
                </div>


                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="number" step="0.01" id="createRecord__price"
                           name="createRecord__price">
                    <label class="mdl-textfield__label" for="createRecord__price">Price</label>
                </div>


                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>


<jsp:include page="include/footinclude.jsp"/>