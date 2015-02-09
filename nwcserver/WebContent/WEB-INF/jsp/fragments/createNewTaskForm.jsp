<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="contest" uri="/tlds/ContestTags" %> 
<contest:taskModalForm taskModelName="contest.tasks[${taskIndex}]" 
                       taskId="${taskIndex}" formUrl="${formJsonUrl}" contestId="${contestId}"/>