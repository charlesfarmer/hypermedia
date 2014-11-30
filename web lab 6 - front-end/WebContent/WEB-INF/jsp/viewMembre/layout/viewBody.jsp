<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.UpdateMembreServlet"%>
<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.LoginMembreServlet" %>
<%@ page import="ca.qc.collegeahuntsic.weblab6.dto.MembreDTO" %>

<%@ taglib prefix="message" uri="application-message" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<div id="body">
<div class="panel panel-default" id="profil-membre">
  <div class="panel panel-heading">
    <div class="panel-title membre-titre" id="membre-profil-titre"><message:say key="viewMembre.layout.viewBody.profil.displayMessage"/><c:out value="${ requestScope.membre.username }"></c:out></div>  </div>
  <div id="panel-body" class="panel panel-body">
    <div id="left">
      <div id="info-membre-div">
        <% Boolean isUpdateSuccessful = (Boolean) request.getAttribute(UpdateMembreServlet.UPDATE_STATUS_PARAMETER_NAME);
           if(isUpdateSuccessful != null) {
             if(isUpdateSuccessful != null && isUpdateSuccessful.booleanValue()) { %>
               <div class="alert alert-success">
                 <span class="glyphicon glyphicon-ok"></span>
                 <message:say key="update.membre.success.displayMessage"/>
               </div>
          <% } else { %>
               <div class="alert alert-danger">
                 <span class="glyphicon glyphicon-exclamation-sign"></span>
                 <message:say key="update.membre.failed.displayMessage"/>
               </div>
         <%  } 
           } %>
        <div class="membre-titre" id="profil-membre-username"><message:say key="viewMembre.layout.viewBody.profil.username.displayName"/>
        </div>
        <c:choose>
          <c:when test="${ not empty requestScope.membre.username }">
            <span class="membre-info"><c:out value="${ requestScope.membre.username }"></c:out></span>
          </c:when>
          <c:otherwise>
            <span class="membre-info"><message:say key="viewMembre.layout.viewBody.profil.attributVide.displayMessage"/></span>
          </c:otherwise>
        </c:choose>
        <div class="membre-titre" id="profil-membre-email"><message:say key="viewMembre.layout.viewBody.profil.email.displayName"/>
        </div>
        <c:choose>
          <c:when test="${ not empty requestScope.membre.email }">
            <span class="membre-info"><c:out value="${ requestScope.membre.email }"></c:out></span>
          </c:when>
          <c:otherwise>
            <span class="membre-info"><message:say key="viewMembre.layout.viewBody.profil.attributVide.displayMessage"/></span>
          </c:otherwise>
        </c:choose>
        <br>
        <c:if test="${ requestScope.membre.username eq sessionScope.membre.username }">
          <span class="voir-new-profil" id="voir-new-profil-form"><a href="javascript:showNewProfil();"><message:say key="viewMembre.layout.viewBody.profil.voirNewProfilForm.displayMessage"/></a></span>
        </c:if>
      </div>
      <c:if test="${ requestScope.membre.username eq sessionScope.membre.username }">
      <div style="visibility: hidden;" id="new-profil-membre">
        <form class="input-group" id="new-profil-form" method="post" action="updateMembre.do">
          <input class="form-new-profil form-control" placeholder="<message:say key="viewMembre.layout.viewBody.changeProfil.email.displayMessage"/>" name="<%= MembreDTO.EMAIL_COLUMN_NAME %>" id="profil-membre-new-email" type="text"></input>
          <br/>
          <input class="form-new-profil form-control" placeholder="<message:say key="viewMembre.layout.viewBody.changeProfil.password.displayMessage"/>" name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>" id="profil-membre-new-password" type="password"></input>
          <br/>
          <input class="form-new-profil form-control" placeholder="<message:say key="viewMembre.layout.viewBody.changeProfil.password.displayMessage"/>" name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>2" id="profil-membre-new-password2" type="password"></input>
          <br/>
          <input class="form-old-password form-control" placeholder="<message:say key="viewMembre.layout.viewBody.changeProfil.oldPassword.displayMessage"/>" name="ancien_<%= MembreDTO.PASSWORD_COLUMN_NAME %>" id="profil-membre-ancien-password" type="password"></input>
          <br/>
          <input class="btn btn-default" type="submit" value="<message:say key="viewMembre.layout.viewBody.changeProfil.submit.displayMessage"/>"></input>
        </form>
      </div>
      </c:if>
     </div>
     <div id="right">
     <div class="panel panel-default" id="vitrines-membre">
       <div class="panel panel-heading">
         <div class="panel-title membre-titre" id="membre-vitrines-titre"><message:say key="viewMembre.layout.viewBody.vitrines.displayMessage"/><c:out value="${ requestScope.membre.username }"></c:out></div>
       </div>
     </div>
     <div class="panel panel-default" id="marchands-membre">
       <div class="panel panel-heading">
         <div class="panel-title membre-titre" id="membre-marchands-titre"><message:say key="viewMembre.layout.viewBody.marchands.displayMessage"/><c:out value="${ requestScope.membre.username }"></c:out></div>
       </div>
     </div>
     </div>
  </div>
</div>
</div>
