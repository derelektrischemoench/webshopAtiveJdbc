<div class="container-fluid header">

    <h2 class="left">WEL<br>COME</h2>
    <h2 class="right">Tight shit records</h2>
    <h4 class="right login">

        <span>login / register</span>

    </h4>
</div>

<div class="customerLoginForm">
    <h3>Login</h3>
    <form action="loginUser" method="post">

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="sample3" name="customerLogin__username">
            <label class="mdl-textfield__label" for="sample3">Username</label>
            <span class="mdl-textfield__error">${errorMsg}</span>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="customerLogin__password" name="customerLogin__password">
            <label class="mdl-textfield__label" for="customerLogin__password">Password</label>
            <span class="mdl-textfield__error">${errorMsg}</span>
        </div>

        <button type="submit"
                class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Submit
        </button>
        <br>

        <a href="registerUser">Don't have an account yet? Click here to sign up</a>

    </form>
</div>

<script>
    var button = $('.login');
    var loginForm = $('.customerLoginForm');

    button.click(function () {
        loginForm.toggleClass('active');
    })
</script>
