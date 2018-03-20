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
            <input class="mdl-textfield__input" type="text" id="login__username" name="login__username">
            <label class="mdl-textfield__label" for="login__username">Username</label>
            <span class="mdl-textfield__error">${errorMsg}</span>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="login__password" name="login__password">
            <label class="mdl-textfield__label" for="login__password">Password</label>
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

    //helper function for gathering delicious cookies
    function getCookie(name) {
        var dc = document.cookie;
        var prefix = name + "=";
        var begin = dc.indexOf("; " + prefix);
        if (begin == -1) {
            begin = dc.indexOf(prefix);
            if (begin != 0) return null;
        }
        else {
            begin += 2;
            var end = document.cookie.indexOf(";", begin);
            if (end == -1) {
                end = dc.length;
            }
        }
        // because unescape has been deprecated, replaced with decodeURI
        //return unescape(dc.substring(begin + prefix.length, end));
        return decodeURI(dc.substring(begin + prefix.length, end));
    }

    button.click(function () {
        loginForm.toggleClass('active');
        //set initally:

        var cookie = getCookie("menuOpen");
        console.log("value of getCookie: " + cookie);
        if (cookie == null) {
            //no cookie, create:
            setCookie("menuOpen", "true", 30)
        } else if (checkCookie() === true) {
            //menu is open; set to closed
            console.log("cookie says menu is opeb");
            setCookie("menuOpen", "false", 30);
        } else  if (checkCookie() === false) {
            console.log("cookie says menu is closed");
            setCookie("menuOpen", "true", 30)
        }

    });

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    function checkCookie() {
        var menuState = getCookie("menuOpen");
        console.log("menustate: " + menuState);
        if (menuState === "true") {
            return true;
        } else if (menuState === "false"){
            return false;
        }
    }

    $(document).ready(function () {
        //toggle class if the cookie is set
        if (checkCookie() === true) {
            loginForm.toggleClass('active');
        }
    });

</script>
