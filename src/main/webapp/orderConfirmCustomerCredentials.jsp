<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="include/headinclude.jsp"/>

<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Please enter your <br><span class="colorBlue">credentials</span></h1>
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-12">
            <form action=""
                  method="post"
                  class="d-flex flex-column justify-content-center recordCrudForm">

                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="customerCheckout__firstName"
                           name="customerCheckout__firstName">
                    <label class="mdl-textfield__label" for="customerCheckout__firstName">First name:</label>
                </div>

                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="customerCheckout__lastName"
                           name="customerCheckout__lastName">
                    <label class="mdl-textfield__label" for="customerCheckout__lastName">Last name:</label>
                </div>

                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="customerCheckout__emailAddress"
                           name="customerCheckout__emailAddress">
                    <label class="mdl-textfield__label" for="customerCheckout__emailAddress">EmailAddress</label>
                </div>

                <div class="row">
                    <div class="col-sm-10">
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%;">
                            <input class="mdl-textfield__input" type="text" id="customerCheckout__street"
                                   name="customerCheckout__street">
                            <label class="mdl-textfield__label" for="customerCheckout__street">Address</label>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" id="customerCheckout__houseNumber"
                                   name="customerCheckout__houseNumber">
                            <label class="mdl-textfield__label" for="customerCheckout__houseNumber">No</label>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-6">
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%;">
                            <input class="mdl-textfield__input" type="number" id="customerCheckout__zip"
                                   name="customerCheckout__zip">
                            <label class="mdl-textfield__label" for="customerCheckout__zip">Zip</label>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%;">
                            <input class="mdl-textfield__input" type="text" id="customerCheckout__city"
                                   name="customerCheckout__city">
                            <label class="mdl-textfield__label" for="customerCheckout__city">City</label>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm">
                        <div class="mdl-textfield mdl-js-textfield getmdl-select">
                            <input type="text" value="" class="mdl-textfield__input" id="customerCheckout__meansOfPayment" readonly>
                            <input type="hidden" value="" name="customerCheckout__meansOfPayment">
                            <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
                            <label for="customerCheckout__meansOfPayment" class="mdl-textfield__label">Means of payment</label>
                            <ul for="sample2" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
                                <li class="mdl-menu__item" data-val="Wire transfer">Wire transfer</li>
                                <li class="mdl-menu__item" data-val="Paypal">Paypal</li>
                                <li class="mdl-menu__item" data-val="Wads of cash">Check</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <button type="submit"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                    Place order
                </button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="include/footinclude.jsp"/>