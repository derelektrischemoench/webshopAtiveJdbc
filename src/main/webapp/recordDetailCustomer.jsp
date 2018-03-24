<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:include page="include/headinclude.jsp"/>

<div class="container main">
    <div class="row">
        <div class="col-sm">
            <h1>${record.get('title')}</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm">
            <div class="demo-card-square mdl-card mdl-shadow--2dp customerRecordDetailCard">
                <div class="mdl-card__title mdl-card--expand"
                     style="background-image: url(' ${ record.get('img_file_path') }')">
                </div>
            </div>
        </div>
        <div class="col-sm">
            <h4>Tracklist:</h4>
            <ul class='mdl-list'>
                <li class="mdl-list__item">
                    <span class="mdl-list__item-primary-content">
                        Bryan Cranston
                    </span>
                </li>
                <li class="mdl-list__item">asdasd</li>
                <li class="mdl-list__item">asdasd</li>
            </ul>
        </div>
    </div>
</div>


<jsp:include page="include/footinclude.jsp"/>