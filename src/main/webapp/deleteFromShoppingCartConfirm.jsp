<%@ page import="java.util.Iterator" %>
<%@ page import="model.Record" %>
<%@ page import="model.Artist" %>
<%@ page import="org.javalite.activejdbc.Base" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mytaglib" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:include page="/include/headinclude.jsp"/>


<div class="container main">
    <div class="row">
        <div class="col-sm">
            <h2>
                Confirm deletion
            </h2>
        </div>
    </div>

    <div class="row">
        <div class="col-sm">
            <h3>Are you sure you want to delete this item from your shoppingcart?</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <a href="">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                    Si, claro
                </button>

            </a>
        </div>
        <div class="col-sm">
            <a href="">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">
                    No, no es bueno
                </button>
            </a>
        </div>
    </div>
</div>