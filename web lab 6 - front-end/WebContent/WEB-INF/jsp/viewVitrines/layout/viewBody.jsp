<%@page import="ca.qc.collegeahuntsic.weblab6.servlet.action.CreateVitrineServlet"%>
<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.UpdateVitrineServlet"%>
<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.ViewMembreServlet"%>
<%@ page import="ca.qc.collegeahuntsic.weblab6.dto.MembreDTO" %>
<%@ page import="ca.qc.collegeahuntsic.weblab6.dto.VitrineDTO" %>

<%@ taglib prefix="message" uri="application-message" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<div id="body">
  <div id="view-vitrines-title"><div id="view-vitrines-title-text"><message:say key="viewVitrines.body.titre.displayMessage"/><c:out value="${ requestScope.membre.username }"></c:out></div></div>
  <div id="view-vitrines-vitrines">
  
  <!--  -->
  <!--  MESSAGES DE CREATION -->
  <!--  -->
  <c:choose>
    <c:when test="${ not empty requestScope.createSuccessful }">
      <c:choose>
        <c:when test="${ requestScope.createSuccessful.booleanValue() }">
          <div id="create-vitrine-success" class="alert alert-success" >
            <span class="glyphicon glyphicon-ok"></span>
            <message:say key="viewVitrine.create.success.displayMessage"/>
          </div>
        </c:when>
        <c:otherwise>
          <div id="create-vitrine-failed" class="alert alert-danger" >
            <span class="glyphicon glyphicon-exclamation-sign"></span>
            <message:say key="viewVitrine.create.failed.displayMessage"/>
          </div>
        </c:otherwise>
      </c:choose>
    </c:when>
  </c:choose>
  
  <!--  -->
  <!--  MESSAGES DE SUPPRESSION -->
  <!--  -->
  <c:choose>
    <c:when test="${ not empty requestScope.deleteSuccessful }">
      <c:choose>
        <c:when test="${ requestScope.deleteSuccessful.booleanValue() }">
          <div id="delete-vitrine-success" class="alert alert-success" >
            <span class="glyphicon glyphicon-ok"></span>
            <c:out value="${ requestScope.deletedVitrineTitle } "></c:out><message:say key="viewVitrine.delete.success.displayMessage"/>
          </div>
        </c:when>
        <c:otherwise>
          <div id="delete-vitrine-failed" class="alert alert-danger" >
            <span class="glyphicon glyphicon-exclamation-sign"></span>
            <message:say key="viewVitrine.delete.failed.displayMessage"/>
          </div>
        </c:otherwise>
      </c:choose>
    </c:when>
  </c:choose>
  <!--  -->
  <!--  MESSAGES DE MODIFICATION -->
  <!--  -->
  <div id="edit-failed-title-blank" class="alert alert-danger" style="display: none;">
    <span class="glyphicon glyphicon-exclamation-sign"></span>
    <message:say key="viewVitrine.edit.failed.emptyTitle.displayMessage"/>
  </div>
  <c:choose>
	<c:when test="${ not empty requestScope.updateSuccessful }">
	  <c:choose>
	    <c:when test="${ requestScope.updateSuccessful.booleanValue() }">
	      <div id="edit-vitrine-success" class="alert alert-success" >
	        <span class="glyphicon glyphicon-ok"></span>
	        <message:say key="viewVitrine.edit.success.displayMessage"/>
	      </div>
	    </c:when>
	    <c:otherwise>
	      <div id="edit-vitrine-failed" class="alert alert-danger" >
	        <span class="glyphicon glyphicon-exclamation-sign"></span>
	        <message:say key="viewVitrine.edit.failed.displayMessage"/>
	      </div>
	    </c:otherwise>
	  </c:choose>
	</c:when>
  </c:choose>
  
  
  <!--  -->
  <!--  FORMULAIRE DE MODIFICATION -->
  <!--  -->
  <div id="edit-vitrine" style="display:none;">
      <div class="edit-vitrine-title" id="edit-vitrine-title"><message:say key="viewVitrine.edit.title.displayMessage"/></div>
      <form id="form-edit-vitrine" action="updateVitrine.do" method="post" onsubmit="javascript: return checkTitleLength('new-title');">
        <div id="nouveau-titre" class="form-field"><input class="form-text form-control" name="<%= UpdateVitrineServlet.VITRINE_NEW_TITLE_PARAMETER_NAME %>" id="new-title" placeholder="Nouveau titre"></div>
        <div id="apply-change" class="form-field"><input class="form-field-btn btn btn-default" type="submit" value="<message:say key='viewVitrines.body.btnSave.displayName'/>"></div>
        <input type="hidden" id="edit-vitrine-id" name="<%= UpdateVitrineServlet.UPDATE_VITRINE_PARAMETER_NAME %>" value="">
      </form>
  </div>
  
  <!--  -->
  <!--  DIV DE SUPPRESSION -->
  <!--  -->
  <div id="delete-vitrine" style="display:none;">
    <div class="delete-vitrine-title"><message:say key="viewVitrine.delete.title.displayMessage"/></div>
    <div class="delete-vitrine-disclaimer"><message:say key="viewVitrine.delete.disclaimer.displayMessage"/></div>
    <div class="yes-no-btn-div">
      <div class="btn-supprimer"></div>
      <div class="btn-annuler"></div>
    </div>
  </div>
  
  <!--  -->
  <!--  FORMULAIRE DE SUPPRESSION -->
  <!--  -->
  <form id="delete-vitrine-form" action="deleteVitrine.do" method="post" style="display:none;">
    <input id="delete-vitrine-id" type="hidden" name="<%= UpdateVitrineServlet.UPDATE_VITRINE_PARAMETER_NAME %>" value=""><input>
    <input type="hidden" name="<%= ViewMembreServlet.MEMBRE_ID_ATTRIBUTE_NAME %>" value="${ requestScope.membre.idMembre }"><input>
  </form>
  
  <!--  -->
  <!--  NOUVELLE VITRINE ET VITRINES -->
  <!--  -->
    <c:if test="${ requestScope.membre.idMembre eq sessionScope.membre.idMembre }">
      <div id="view-vitrines-nouvelle-vitrine">
        <div class="container">
          <div class="table-cell"><div id="new-vitrines-message"><message:say key="viewVitrines.body.nouvelleVitrine.displayMessage"/></div></div>
          <div class="table-cell">
            <form id="new-vitrine-form" action="createVitrine.do" method="post" onsubmit="javascript: return checkTitleLength('new-vitrine-title');">
              <div><input class="new-vitrine-name form-control" placeholder="<message:say key="viewVitrine.new.vitrine.displayMessage"/>" name="<%= CreateVitrineServlet.VITRINE_TITLE_PARAMETER_NAME %>" id="new-vitrine-title" type="text"/></div>
            </form>
          </div>
          <div class="table-cell">
            <div id="new-vitrine-button">
              <div id="img-new-vitrines"><img alt="Nouvelle vitrine" src='<message:say key="viewVitrine.body.nouvelleVitrine.img"/>'></div>
            </div>
          </div>
        </div>
      </div>
    </c:if>
    <div id="view-vitrines-liste-vitrines">
      <c:choose>
        <c:when test="${ requestScope.vitrines.size() > 0 }">
          <div class="vitrine-summary-header">
            <!-- <div class="vitrine-summary-lastEdited"><message:say key="viewVitrines.body.dernierChangement.displayName"/><div class="vitrine-element"><c:out value=""></c:out></div></div> -->
            <div class="vitrine-summary-dateAdded"><message:say key="viewVitrines.body.dateAjout.displayName"/></div>
            <div class="vitrine-summary-title"><message:say key="viewVitrines.body.titre.displayName"/></div>
            <div class="vitrine-summary-toolbox"><message:say key="viewVitrines.body.actions.displayName"/></div>
          </div>
          <c:forEach items="${ requestScope.vitrines }" var="vitrine" varStatus="i">
            <div class="vitrine-summary">
            <!-- <div class="vitrine-summary-lastEdited"><message:say key="viewVitrines.body.dernierChangement.displayName"/><div class="vitrine-element"><c:out value=""></c:out></div></div> -->
              <div class="vitrine-summary-dateAdded">
                <div class="vitrine-element"><fmt:formatDate value="${ vitrine.dateAdded }" pattern="yyyy/MM/dd"/></div>
              </div>
              <div class="vitrine-summary-title">
                <div class="vitrine-element"><c:out value="${ vitrine.title }"></c:out></div>
              </div>
              <div class="vitrine-summary-toolbox">
                <div class="vitrine-table-row">
                <form action="viewLignesProduit.do?idMembre=<c:out value="${ requestScope.membre.idMembre }"></c:out>idVitrine=<c:out value="${ vitrine.idVitrine }"></c:out>">
                  <div class="img-toolbox voir-vitrine"><img id='voir-<c:out value="${ vitrine.idVitrine }"></c:out>' alt="Voir vitrine" src='<message:say key="viewVitrine.body.voirVitrine.img"/>'></div>
                </form>
                  <c:if test="${ requestScope.membre.idMembre eq sessionScope.membre.idMembre }">
                    <div class="img-toolbox modifier-vitrine"><img id='modifier-<c:out value="${ vitrine.idVitrine }"></c:out>' alt="Modifier vitrine" src='<message:say key="viewVitrine.body.modifierVitrine.img"/>'></div>
                    <div class="img-toolbox supprimer-vitrine"><img id='supprimer-<c:out value="${ vitrine.idVitrine }"></c:out>' alt="Supprimer vitrine" src='<message:say key="viewVitrine.body.supprimerVitrine.img"/>'></div>
                  </c:if>
                </div>
              </div>
            </div>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <div class="vitrine-summary"><div id="empty-vitrines"><message:say key="viewVitrines.body.emptyVitrines.displayMessage"/><c:out value="${ requestScope.membre.username }"></c:out></div></div>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</div>