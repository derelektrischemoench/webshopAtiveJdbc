<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="row">
        You can add new Records from here
    </div>

    <form action="createRecord" method="post">
        <div class="form-group">
            <label for="recordName">Plattenname</label>
            <input type="text" class="form-control" id="recordName" aria-describedby="emailHelp"
                   placeholder="Enter username" name="createRecord__recordName">

        </div>
        <div class="form-group">
            <label for="artistName">Künstler</label>
            <input type="text" class="form-control" id="artistName" placeholder="Password"
                   name="adminLogin__password">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>



<jsp:include page="include/footinclude.jsp"/>