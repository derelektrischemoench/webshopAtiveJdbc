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


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contactInquries}" var="contactInquiry">
            <tr>
                <td>${contactInquiry.getString('contact_email')} </td>
                <td> ${contactInquiry.getString('message')}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>


<jsp:include page="include/footinclude.jsp"/>