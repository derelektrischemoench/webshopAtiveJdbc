<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="container-fluid header">
    <div class="row justify-content-between headerInner">
        <div class="col-3">
            <h2>WEL<br>COME</h2>
            <h5>Tight shit records</h5>
        </div>
        <div class="col-5 justify-content-end d-flex flex-column justify-content-between">
            <div class="row justify-content-end">
                <c:if test="${cookie.username.value eq null}">
                    <div class="col-auto">
                        <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent login align-self-end">
                            Login
                        </button>
                    </div>
                </c:if>
                <c:if test="${cookie.username.value ne null and sessionScope.isAdmin ne true}">
                        <span class="mdl-badge shoppingCart__numItemsBadge"
                              data-badge="<mytaglib:getAmountShoppingCartItems shoppingCartId="${cookie.shoppingCartId.value}" />">
                            <div class="col-auto">
                                <div class="demo-card-square mdl-card mdl-shadow--2dp itemCard shoppingCart">
                                    <div class="mdl-card__title mdl-card--expand">
                                        <h6 class="mdl-card__title-text justify-content-between"><p>Shopping cart: </p>
                                            <p><i class="material-icons">expand_more</i></p>
                                        </h6>
                                    </div>
                                    <div class="mdl-card__supporting-text" style="padding-top: 0">
                                        <mytaglib:getShoppingcartContents session="${pageContext.session}"/>
                                        <div class="mdl-card__actions mdl-card--border">
                                            <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                                               href="shoppingCartDetail?shoppingCartId=${cookie.shoppingCartId.value}"
                                               data-upgraded=",MaterialButton,MaterialRipple">
                                                View cart
                                                <span class="mdl-button__ripple-container">
                                                    <span class="mdl-ripple"></span>
                                                </span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </span>
                </c:if>
            </div>
            <div class="row justify-content-end">
                <div class="col-auto">
                    <a href="contactUs">
                        <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent align-self-end">
                            contact us
                        </button>
                    </a>
                </div>
            </div>

            <div class="row justify-content-end">
                <div class="col-sm">
                    <a href="<c:out value="${pageContext.request.contextPath}/termsOfService" />">Terms of service</a>
                </div>
                <div class="col-sm">
                    <a href="<c:out value="${pageContext.request.contextPath}/someOtherStuff" />">
                        Some other shit that no one will ever read
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="customerLoginForm">
    <h3>Login</h3>
    <form action="loginUser" method="post">
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="login__username" name="login__username">
            <label class="mdl-textfield__label" for="login__username">Username</label>
            <span class="mdl-textfield__error" style="visibility:
            <c:out value="${requestScope.visibility}"/> ">
                ${requestScope.uem}
            </span>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="login__password" name="login__password">
            <label class="mdl-textfield__label" for="login__password">Password</label>
            <span class="mdl-textfield__error" style="visibility: <c:out value="${requestScope.visibility}" />">
                ${requestScope.pem}
            </span>
        </div>
        <button type="submit"
                class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Submit
        </button>
        <br>

        <a href="registerUser">Don't have an account yet? Click here to sign up</a>

    </form>
</div>

<c:set var="req" value="${pageContext.request}"/>
<c:set var="baseURL" value="${fn:replace(req.requestURL, req.requestURI, '')}"/>
<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"/>
<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="pageUrl" value="${ baseURL }${ requestPath }${ not empty params?'?'+=params:'' }"/>
<div class="breadcrumbs">
    <mytaglib:breadCrumbler requestPath="${pageUrl}" />
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
        //console.log("value of getCookie: " + cookie);
        if (cookie == null) {
            //no cookie, create:
            setCookie("menuOpen", "true", 30)
        } else if (checkCookie() === true) {
            //menu is open; set to closed
            console.log("cookie says menu is opeb");
            setCookie("menuOpen", "false", 30);
        } else if (checkCookie() === false) {
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
        } else if (menuState === "false") {
            return false;
        }
    }

    $(document).ready(function () {
        //toggle class if the cookie is set
        if (checkCookie() === true) {
            loginForm.toggleClass('active');
        }
    });

    //shopping card expand
    var shoppingCart = $('.shoppingCart');
    var arrow = $('span.ion-chevron-down');
    shoppingCart.click(function () {
        $(this).toggleClass('visible');
        arrow.toggleClass('upsideDown');
    })

</script>
