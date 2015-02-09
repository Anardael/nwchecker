<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ tag description="userListt" pageEncoding="UTF-8"%>
<%@attribute name="contest" %>
<%@attribute name="contestId" %>
<div id="userListModal" class="modal fade">
    <div class="modal-dialog "style="height: 50%">
        <div class="modal-content">
            <div class="modal-header modal-header-info">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><spring:message code="contest.userList.header"/></h4>
            </div>
            <div class="modal-body">
                <table data-toggle="table"
                       data-url="getContestUsersList.do?contestId=${contestId}"
                       data-classes="table table-hover"
                       data-click-to-select="true"
                       >
                    <thead>
                        <tr>
                            <th data-field="choosed" data-checkbox="true" ></th>
                            <th data-field="id" data-sortable="true" class="idField">id</th>
                            <th data-field="name" data-sortable="true"><spring:message code="contest.userList.displayName"/></th>
                            <th data-field="" data-sortable="true"><spring:message code="contest.userList.fullName"/></th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button  type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="btn.close"/></button>
                <button id="submitUserListButton" type="button" class="btn btn-primary"><spring:message code="btn.save"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->