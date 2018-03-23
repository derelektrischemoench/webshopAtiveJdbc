<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>


<jsp:include page="include/headinclude.jsp"/>

<div class="container adminLogin main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Login</h1>
        </div>
    </div>
    <form action="adminLogin" method="post" accept-charset="UTF-8">


        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="login__username" name="login__username">
            <label class="mdl-textfield__label" for="login__username">Username</label>
            <span class="mdl-textfield__error">${errorMsg}</span>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="login__password" name="login__password">
            <label class="mdl-textfield__label" for="login__password">Password</label>
            <!--<span class="mdl-textfield__error">${errorMsg}</span>-->
        </div>

        <button type="submit"
                class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Submit
        </button>


    </form>

    <div class="row">
        <div class="col-sm-12">
            <a href="createAdminAccount">Create admin account</a>
        </div>
    </div>
</div>


<jsp:include page="include/footinclude.jsp"/>
