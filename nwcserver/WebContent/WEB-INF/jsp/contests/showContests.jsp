<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="contest" uri="/tlds/ContestTags"%>

<spring:url value="/resources/" var="resources" />

<html>
    <script type="text/javascript">
        var successCaption = "<spring:message code="success.caption"/>";
        var errorLabel = "<spring:message code="error.caption" />";
        var contestId = 0;
    </script>

    <div class="pull-right" style="margin-top: 10px">
        <security:authorize access="hasRole('ROLE_TEACHER')">
            <div class="radio-inline">
                <label>
                    <input type="radio" name="radioOption" id="optionsRadios1" value="all" checked
                           onclick="updateContestsList()">
                    <spring:message code="contest.radio.All"/>
                </label>
            </div>
            <div class="radio-inline">
                <label>
                    <input type="radio" name="radioOption" id="optionsRadios2" value="false"
                           onclick="updateContestsList()">
                    <spring:message code="contest.radio.unhidden" />
                </label>
            </div>
            <div class="radio-inline">
                <label class="hidden-radio">
                    <input type="radio" name="radioOption" id="optionsRadios3" value="true"
                           onclick="updateContestsList()">
                    <spring:message code="contest.radio.hidden"/>
                </label>
            </div>
        </security:authorize>

        <div class="radio-inline">
            <label>
                <select id="selectOption" class="form-control" onchange="updateContestsList()">
                    <option value="all"><spring:message code="contest.status.all" /></option>
                    <option value="going"><spring:message code="listContests.status.going" /></option>
                    <option value="release"><spring:message code="listContests.status.release" /></option>
                    <option value="preparing"><spring:message code="listContests.status.preparing" /></option>
                    <option value="archive"><spring:message code="listContests.status.archive" /></option>
                </select>
            </label>
        </div>
    </div>

    <c:url var="dataUrl" value="/contestListJson.do?hidden=all&status=all" />
    <table id="contestTable" class="table" data-toggle="table"
           data-url="${dataUrl}" data-method="get" data-cache="false"
           data-search="true" data-clear-search="true" data-search-align="left"
           data-pagination="true" data-show-pagination-switch="true" data-sort-name="starts"
           data-sort-order="desc" data-row-style="rowStyle" data-maintain-selected="true">
        <thead>
        <tr>
            <th data-field="starts" data-align="center" data-sortable="true"  data-width="100"
                data-formatter="dateFormatter">
                <spring:message code="listContests.contests.tableHeader.starts" />
            </th>
            <th data-field="title" data-align="center">
                <spring:message code="listContests.contests.tableHeader.title" />
            </th>
            <th data-field="status" data-align="center" data-sortable="true" data-width="100"
                data-formatter="statusFormatter">
                <spring:message code="listContests.contests.tableHeader.status" />
            </th>
        </tr>
        </thead>
    </table>

    <security:authorize access="hasRole('ROLE_TEACHER')">
        <div class="text-center" style="text-align: center; margin-top: 20px">
            <button class="btn btn-primary btn-sm" onclick="window.location.href = 'addContest.do'">
                <spring:message code="contest.createButton.caption" />
            </button>
        </div>
    </security:authorize>

    <div id="contestModal" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-header-info">
                    <div id="title-block">
                        <span id="title-text" class="h4"></span>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
                <div id="mdl-body" class="modal-body" style="height: 400px; width: 600px">
                    <div id="start-date-block">
                        <b><spring:message code="contest.table.starts" />:</b>
                        <span id="start-date"></span>
                    </div>
                    <div id="duration-block">
                        <b><spring:message code="contest.table.duration" />:</b>
                        <span id="duration-hours"></span>
                        <spring:message code="contest.table.duration.hours" />
                        <span id="duration-minutes"></span>
                        <spring:message code="contest.table.duration.minutes" />
                    </div>
                    <div id="type-block">
                        <b><spring:message code="contest.table.type" />:</b>
                        <span id="type"></span>
                    </div>

                    <div id="description-block">
                        <div class="text-center" style="margin: 1px">
                            <b><spring:message code="contest.table.description" /></b>
                        </div>
                        <span id="description"></span>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="pull-right" style="margin-top: 10px">
                        <security:authorize access="hasRole('ROLE_TEACHER')">
                            <div id="edit-group">
                                <span id="now-edit">
                                    <spring:message code="contest.table.edited" />
                                    <span id="edit-username"></span>
                                    <button type="button" class="btn btn-sm btn-info" onclick="refresh(contestId)">
                                        <span class="glyphicon glyphicon-refresh"></span>
                                        <spring:message code="contest.table.refresh" />
                                    </button>
                                </span>
                                <button id="edit-btn" class="btn btn-sm btn-info" onclick="edited(contestId)">
                                    <spring:message code="btn.edit" />
                                </button>
                            </div>
                        </security:authorize>
                        <security:authorize access="hasRole('ROLE_USER')">
                            <button class="open-btn btn btn-sm btn-info form-group"
                                    style="font-weight: 600" onclick="openContest(contestId)">
                                <spring:message code="contest.startButton" />
                            </button>
                        </security:authorize>
                        <security:authorize access="!isAuthenticated()">
                            <button class="open-btn btn btn-sm btn-info form-group"
                                    style="font-weight: 600" onclick="openContest(contestId)">
                                <spring:message code="contest.unauthenticated" />
                            </button>
                        </security:authorize>
                        <button id="archive-btn" class="btn btn-sm btn-info form-group"
                                style="font-weight: 600" onclick="archive()">
                            <spring:message code="home.archive.caption" />
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</html>
