<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--including head -->
    <head>
        <jsp:include page="../fragments/staticFiles.jsp" />
    </head>
    <body>
        <div class="wrapper container">
            <!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="../fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="news"/>
            </jsp:include>

            <div class="row">
                <aside class="col-md-3">
                    <ul class="list-group submenu">
                        <li class="list-group-item active"><spring:message code="home.info.caption" /></li>
                        <li class="list-group-item"><a href="donec.do"><spring:message code="home.rules.caption" /></a></li>
                        <li class="list-group-item"><a href="vestibulum.do"><spring:message code="home.contacts.caption" /></a></li>
                        <li class="list-group-item"><a href="etiam.do"><spring:message code="home.archive.caption" /></a></li>
                        <li class="list-group-item"><a href="phasellus.do"><spring:message code="home.forum.caption" /></a></li>
                    </ul>
                </aside>
                
                <section class="col-md-9">
                    <div class="jumbotron" style="text-align:center; font-size: large" >
                        <blockquote>
                            <p>
                                <spring:message code="news.quote"/>
                                <c:url var="news" value="/news.do"/>
                            </p>               
                        </blockquote>
                    </div>
                                            
                    <div class="form-group col-sm-12" style="margin:auto">                                  
          			   <ul class="col-sm-offset-2 col-sm-8 ">
            	         <li class="list-group-item list-group-item-heading list-group-item-info" style="text-align:left; font-size: large">
              		         <p>
              		         	<spring:message code="news.contests"/>
              		         </p>
              		         <p>
              		         	 ${contest.title} 
              		         	 <br>  
              		         	 <br> 
              		             ${contest.starts} 
              		         </p>  
              			 </li>                      		    
                       </ul>                      			  	                    				                 					                          
                    </div> 
                                                              	
                    <div class="form-group col-sm-12" style="margin:auto">
          				<ul class="col-sm-offset-2 col-sm-8 ">
            				<li class="list-group-item list-group-item-heading list-group-item-info" style="text-align:center; font-size: large">
                           		<spring:message code="news.result"/> ${title} 
                            </li>                              	  		  				   
  		  					<table class="table table-condensed"> 
  		  					  <thead>
    							<tr>
      								<th><spring:message code="contest.results.tableHeader.place"/></th>
     							    <th><spring:message code="contest.results.tableHeader.displayName"/></th>
    							    <th><spring:message code="contest.results.tableHeader.tasksPassedCount"/></th>
      								<th><spring:message code="contest.results.tableHeader.timePenalty"/></th>
    							</tr>
  							 </thead>		
          	        		 <c:forEach items="${result}" var="contest">
          	        			<tr>
          			    			<td> ${contest.rank}</td> 
          				 			<td>${contest.displayName}</td>
          				 			<td>${contest.tasksPassedCount}</td>
          				 			<td>${contest.timePenalty}</td>
          						</tr>          		     				      			
          					 </c:forEach>
          				 	</table>
          			</div>	  						  		 			       		  				                                                  
                </section>
            </div>
        </div>
        <jsp:include page="../fragments/footer.jsp"/>
    </body>
</html>