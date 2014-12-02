<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.UpdateMembreServlet"%>
<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.LoginMembreServlet" %>
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
    <div id="view-vitrines-nouvelle-vitrine">
      <div class="vitrine-table-row">
        <div id="img-new-vitrines"><img alt="Nouvelle vitrine" src='<message:say key="viewVitrine.body.nouvelleVitrine.img"/>'></div>
        <div id="new-vitrines-message"><message:say key="viewVitrines.body.nouvelleVitrine.displayMessage"/></div>
      </div>
      <div class="vitrine-table-row">
      </div>
    </div>
    <div id="view-vitrines-liste-vitrines">
      <c:choose>
        <c:when test="${ requestScope.vitrines.size() > 0 }">
          <div class="vitrine-summary">
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
                  <div class="img-toolbox"><img alt="Voir vitrine" src='<message:say key="viewVitrine.body.voirVitrine.img"/>'></div>
                  <div class="img-toolbox"><img alt="Modifier vitrine" src='<message:say key="viewVitrine.body.modifierVitrine.img"/>'></div>
                  <div class="img-toolbox"><img alt="Supprimer vitrine" src='<message:say key="viewVitrine.body.supprimerVitrine.img"/>'></div>
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