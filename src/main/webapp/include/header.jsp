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
        <div class="form-group">
            <label for="customerLogin__username">Username</label>
            <input type="text" class="form-control" id="customerLogin__username" aria-describedby="emailHelp"
                   placeholder="Enter username" name="customerLogin__username">
            <span class="passwordErrorMessage">${errorMsg}</span>

        </div>
        <div class="form-group">
            <label for="customerLogin__password">Password</label>
            <input type="password" class="form-control" id="customerLogin__password" placeholder="Password"
                   name="customerLogin__password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
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
