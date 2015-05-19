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

</script>
	<div class="main-block">
		<div id="accordion">

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					<%=request.getParameter("status") == null ? "ALL CONTESTS"
					: request.getParameter("status")%>
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="/NWCServer/getContests.do">All Contests</a></li>
					<li><a
						href="<c:url value="/getContestsByStatus.do?status=GOING"/>">
							<spring:message code="contest.going.label" />
					</a></li>
					<li><a
						href="<c:url value="/getContestsByStatus.do?status=PREPARING"/>">
							<spring:message code="contest.preparing.label" />
					</a></li>
					<li><a
						href="<c:url value="/getContestsByStatus.do?status=RELEASE"/>">
							<spring:message code="contest.release.label" />
					</a></li>
					<li><a
						href="<c:url value="/getContestsByStatus.do?status=ARCHIVE"/>">
							<spring:message code="contest.archive.label" />
					</a></li>
				</ul>
			</div>


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
										style="font-weight: 600" onclick="archive()">This
										competition has been archived</button>
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
			<div class="text-center">
				<!-- Pagination STARTO -->
				<div class="text-center">
					<ul class="pagination">
						<!-- "Previous" button -->
						<c:if test="${currentPage gt 1}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${currentPage - 1}"/>
									  </c:url>"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						<!-- Display first page -->
						<c:if test="${currentPage - 3 ge 1}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="id" value="${taskId}"/>
										<c:param name="page" value="1"/>
										</c:url>">
									<c:out value="1" />
							</a></li>
						</c:if>

						<!-- Display there're more pages before first page -->
						<c:if test="${currentPage - 3 gt 1}">
							<li class="disabled"><a>...</a></li>
						</c:if>

						<!-- Display 2 closest pages -->
						<c:forEach begin="1" end="5" var="loop">
							<c:choose>
								<c:when test="${loop eq 3}">
									<li class="active"><a><c:out value="${currentPage}" /></a></li>
								</c:when>
								<c:when
									test="${(currentPage + loop - 3 gt 0)and(currentPage + loop - 3 lt pageCount + 1)}">
									<li><a
										href="<c:url value="/etiam.do">
										<c:param name="id" value="${taskId}"/>
										<c:param name="page" value="${currentPage + loop - 3}"/>
										</c:url>">
											<c:out value="${currentPage + loop - 3}" />
									</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<!-- Display there're more pages before last page -->
						<c:if test="${currentPage + 3 lt pageCount}">
							<li class="disabled"><a>...</a></li>
						</c:if>
						<!-- Display last page -->
						<c:if test="${currentPage + 3 le pageCount}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="id" value="${taskId}"/>
										<c:param name="page" value="${pageCount}"/>
										</c:url>">
									<c:out value="${pageCount}" />
							</a></li>
						</c:if>

						<!-- "Next" button -->
						<c:if test="${currentPage lt pageCount}">
							<li><a
								href="<c:url value="/etiam.do">
										<c:param name="page" value="${currentPage + 1}"/>
									  </c:url>"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
					</ul>
				</div>
				<!-- Pagination FINISH -->
			</div>
		</div>
	</div>
</body>
</html>