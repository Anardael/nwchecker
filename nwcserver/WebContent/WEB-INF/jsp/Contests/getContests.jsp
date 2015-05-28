<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="contest" uri="/tlds/ContestTags"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<html>
<body>
<script type="text/javascript">
    var successCaption = "<spring:message code="success.caption"/>";
    var successSignUpContest = "<spring:message code="contest.signUpBody"/>";
    var alreadySignUp = "<spring:message code="contest.alreadySubscribed"/>";
    var errorLabel = "<spring:message code="error.caption" />";
    var nowEditingBody = "<spring:message code="contest.editing.now.body"/>";
    var nowEditingUser = "<spring:message code="contest.editing.now.username"/>";
    var contestId = 0;

</script>
	<div class="main-block">
		<div id="accordion">

			<c:forEach items="${contests}" var="contest" varStatus="row">
				<a class="list-group-item" data-toggle="collapse"
					data-parent="#accordion" href="#collapse${row.index}">
					<div class="link-block">
						<div class="start_date-block">
							${fn:substring(contest.starts,0,16)}</div>
						<div class="title-block">
							<span class="h5"><b>${contest.title}</b></span>
						</div>
						<div class="status-block">
							<c:if test="${contest.status=='GOING'}">
								<label class="label label-success contestsStatus"> <spring:message
										code="contest.going.label" /></label>
							</c:if>
							<c:if test="${contest.status=='PREPARING'}">
								<label class="label label-info contestsStatus"> <spring:message
										code="contest.preparing.label" />
								</label>
							</c:if>
							<c:if test="${contest.status=='RELEASE'}">
								<label class="label label-info contestsStatus"> <spring:message
										code="contest.release.label" />
								</label>
							</c:if>
							<c:if test="${contest.status=='ARCHIVE'}">
								<label class="label label-danger contestsStatus"> <spring:message
										code="contest.archive.label" />
								</label>
							</c:if>
							<c:if test="${contest.hidden==true}">
								<label class="label label-default contestsStatus"><spring:message
										code="contest.hidden.label" /></label>
							</c:if>
						</div>
					</div>
				</a>

				<div id="collapse${row.index}"
					class="panel-collapse collapse drop-block">
					<li class="list-group-item list-group-item-info">
						<div class="panel-body">
							<div class="dateDuration">
								<c:if test="${not empty contest.starts }">
									<div class="drop-block-element">
										<b><spring:message code="contest.table.starts" />:</b>
										${fn:substring(contest.starts,0,16)}
									</div>
								</c:if>
								<c:if test="${not empty contest.duration }">
									<div class="drop-block-element">
										<b><spring:message code="contest.table.duration" />:</b>
										${fn:substring(contest.duration,11,16)}
									</div>
								</c:if>
								<c:if test="${not empty contest.typeContest.name }">
									<div class="drop-block-element">
										<b><spring:message code="contest.table.type" />:</b>
										${contest.typeContest.name}
									</div>
								</c:if>
							</div>

							<div class="drop-block-element">
								<div class="text-center" style="margin: 1px">
									<b><spring:message code="contest.table.description" /></b>
								</div>
								<div>${contest.description}</div>
							</div>

							<c:if test="${contest.status=='GOING'}">
								<div class="tasks drop-block-element">
									<div class="text-center">
										<b><spring:message code="contest.taskList" /></b>
									</div>
									<div class="tasks-block">
										<c:forEach items="${contest.tasks}" var="task"
											varStatus="taskRow">
											<contest:taskView taskId="${taskRow.index}"
												contestId="${row.index}"
												task="${contest.tasks[taskRow.index]}" />
											<a class="list-group-item " style="margin: 3px"
												data-toggle="modal"
												data-target="#taskView_${row.index}_${taskRow.index}"
												href="#"> <span>${task.title}</span>
											</a>
										</c:forEach>
									</div>
								</div>
							</c:if>

							<div class="edit text-center drop-block-element"
								style="margin-top: 10px">
								<security:authorize access="hasRole('ROLE_TEACHER')">
									<c:set var="user" value="${nowContestEdits[contest.id]}" />
									<c:if test="${not empty user}">
										<label class="label label-warning contestsStatus"> <spring:message
												code="contest.editing.now.label" />: ${user}
										</label>
									</c:if>
									<c:set value="index${contest.id}index" var="contestIndex" />
									<c:if test="${fn:contains(editContestIndexes,contestIndex)}">
										<button class="btnEditContest btn btn-sm btn-info form-group"
											onclick="edited(${contest.id})">
											<spring:message code="btn.edit" />
										</button>
									</c:if>
								</security:authorize>
								<c:if test="${(contest.status=='GOING')}">
									<security:authorize access="hasRole('ROLE_USER')">
										<button class="btn btn-sm btn-info form-group"
											style="font-weight: 600" onclick="openContest(${contest.id})">
											<spring:message code="contest.startButton" />
										</button>
									</security:authorize>
									<security:authorize access="!isAuthenticated()">
										<button class="btn btn-sm btn-info form-group"
											style="font-weight: 600" onclick="openContest(${contest.id})">
											<spring:message code="contest.unauthenticated" />
										</button>
									</security:authorize>
								</c:if>
								<c:if test="${(contest.status=='ARCHIVE')}">
									<button class="btn btn-sm btn-info form-group"
										style="font-weight: 600" onclick="archive()"><spring:message code="home.archive.caption" /></button>
								</c:if>
							</div>
						</div>
					</li>
				</div>
			</c:forEach>


			<security:authorize access="hasRole('ROLE_TEACHER')">
				<div class="col-sm-6 col-sm-offset-3"
					style="text-align: center; margin-top: 20px">
					<button class="btn btn-primary btn-sm"
						onclick="window.location.href = 'addContest.do'">
						<spring:message code="contest.createButton.caption" />
					</button>
				</div>
			</security:authorize>
		</div>
	</div>

<br>
<br>
<br>

    <div class="main-block">
        <c:url var="dataUrl" value="/contestListJson.do" />
        <table id="contestTable" class="table" data-toggle="table"
               data-url="${dataUrl}" data-method="get" data-cache="false"
               data-search="true" data-clear-search="true" data-pagination="true"
               data-show-pagination-switch="true" data-sort-name="starts" data-sort-order="desc">
            <thead style="background-color: lightskyblue">
            <tr>
                <th data-field="starts" data-align="center" data-sortable="true"  data-width="100">
                    Start date
                </th>
                <th data-field="title" data-align="center">
                    Title
                </th>
                <th data-field="status" data-align="center" data-sortable="true" data-width="100">
                    Status
                </th>
            </tr>
            </thead>
        </table>
    </div>


    <div id="contestModal" class="modal">
        <c:set var="ID" value="111"/>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-header-info">
                    HEADER ${ID}
                </div>
                <div id="mdl-body" class="modal-body" style="height: 400px; width: 600px">
                    MODAL BODY
                </div>
                <div class="modal-footer">
                    MODAL FOOTER
                </div>
            </div>
        </div>
    </div>



</body>
</html>
