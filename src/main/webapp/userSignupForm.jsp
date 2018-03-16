<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container adminLogin main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Register new account</h1>
        </div>
    </div>

    <form action="registerUser" method="post">
        <div class="form-group">
            <label for="registerUser__userName">Username</label>
            <input type="text" class="form-control" id="registerUser__userName" aria-describedby="emailHelp"
                   placeholder="Enter username" name="registerUser__userName">

        </div>
        <div class="form-group">
            <label for="registerUser__email">Email</label>
            <input type="text" class="form-control" id="registerUser__email" aria-describedby="emailHelp"
                   placeholder="Email" name="registerUser__email">

        </div>
        <div class="form-group">
            <label for="registerUser__password1">Password</label>
            <input type="password" class="form-control" id="registerUser__password1" placeholder="Password"
                   name="registerUser__password1">
            <span class="passwordErrorMessage">${errorMsg}</span>

        </div>
        <div class="form-group">
            <label for="registerUser__password2">Password, repeat</label>
            <input type="password" class="form-control" id="registerUser__password2" placeholder="Password"
                   name="registerUser__password2">
        </div>


        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
