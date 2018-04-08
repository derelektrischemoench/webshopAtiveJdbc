<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>Contact us </h1>
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-sm-6 ">
            <form action="<c:out value="${pageContext.request.contextPath}/contactUs" />"
                  method="post"
                  class="d-flex flex-column justify-content-center recordCrudForm">

                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="contact__emailAddress"
                           name="contact__emailAddress">
                    <label class="mdl-textfield__label" for="contact__emailAddress">Your emailAdress</label>
                </div>

                <div class="mdl-textfield mdl-js-textfield">
                    <textarea class="mdl-textfield__input" type="text" rows="3" id="sample5" name="contact__message"></textarea>
                    <label class="mdl-textfield__label" for="sample5">Your message</label>
                </div>

                <button type="submit"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                    Get in touch
                </button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="include/footinclude.jsp"/>

<jsp:include page="include/footinclude.jsp"/>