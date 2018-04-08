<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>
<div class="container main">
    <div class="row">
        <div class="col-sm-12">
            <h1>All contact inquries:</h1>
        </div>
    </div>


    <table class="table hover table-hover adminOrderTable">
        <thead>
        <tr>
            <th scope="col">email</th>
            <th scope="col">content</th>
            <th scope="col"></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contactInquries}" var="contactInquiry">
            <tr>
                <td>${contactInquiry.getString('contact_email')} </td>
                <td> ${contactInquiry.getString('message')}</td>
                <td>


                    <a href="
                        <c:out value="${pageContext.request.contextPath}/adminLogin/adminContactInquiries/delete?id=${contactInquiry.getString('id')}" />
">
                        <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent show-modal">
                            Delete
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<dialog class="mdl-dialog">
    <h4 class="mdl-dialog__title">Allow data collection?</h4>
    <div class="mdl-dialog__content">
      <p>
blablabla
          btn
      </p>
    </div>
    <div class="mdl-dialog__actions">
      <button type="button" class="mdl-button">Agree</button>
      <button type="button" class="mdl-button close">Disagree</button>
    </div>
  </dialog>


<script>
    var dialog = document.querySelector('dialog');
    var showModalButton = $('.show-modal');
    if (!dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }

    showModalButton.each(function () {
        $(this).click(function (e) {
            event.preventDefault();
            var link = $(this).attr("href");
            dialog.show({
                clickOutsideToClose: true,
                controllerAs: 'ctrl',
                locals: {link: link},
                controller: ['$scope', 'name', function ($scope, name) {
                    $scope.name = name;
                }]
            });

        })
    });

    dialog.querySelector('.close').addEventListener('click', function () {
        dialog.close();
    });

    dialog.querySelector('.delete').addEventListener('click', function () {
        deleteFromShoppingcart($(this));
    });


    var deleteFromShoppingcart = function () {

    };
</script>

<jsp:include page="include/footinclude.jsp"/>