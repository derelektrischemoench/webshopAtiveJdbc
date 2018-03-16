<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container createAdminAccount main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Create admin account</h1>
        </div>
    </div>
    <form action="createAdminAccount" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">Username</label>
            <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                   placeholder="Enter username" name="adminSignup__username">

        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
                   name="adminSignup__password1">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword2">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password"
                   name="adminSignup__password2">
            <span class="passwordErrorMessage">${errorMsg}</span>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>


<jsp:include page="include/footinclude.jsp"/>