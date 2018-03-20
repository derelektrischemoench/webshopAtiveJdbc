<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container adminLogin main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Signup for new account</h1>
        </div>
    </div>

    <form action="registerUser" method="post">

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="sample3" name="customerSignup__email">
            <label class="mdl-textfield__label" for="sample3">Email- Address</label>
            <span class="mdl-textfield__error">${errorMsg}</span>
        </div>
        <br>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="customerSignup__password1" name="customerSignup__password1">
            <label class="mdl-textfield__label" for="customerSignup__password1">Password</label>
        </div>
        <br>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="customerSignup__password2" name="customerSignup__password2">
            <label class="mdl-textfield__label" for="customerSignup__password2">Password, repeat</label>
            <span class="mdl-textfield__error">${errorMsg}</span>
        </div>
        <br>


        <button type="submit"
                class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Submit
        </button>
        <br>
    </form>

</div>

