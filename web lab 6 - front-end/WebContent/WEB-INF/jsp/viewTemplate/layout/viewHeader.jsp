<%@ page import="ca.qc.collegeahuntsic.weblab6.dto.MembreDTO" %>
<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.LoginMembreServlet" %>

<%@ taglib prefix="message" uri="application-message" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<div id="header">
  <a name="top"></a>
  <nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="viewTemplate.do"><img id="nav-bar-logo" src="img/company-logo.png"></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <c:choose>
          <c:when test="${ not empty param.viewVitrines }">
            <li class="active"><a href="viewVitrines.do"><message:say key="global.vitrinesPage.displayMessage"/></a></li>
          </c:when>
          <c:otherwise>
            <li><a href="viewVitrines.do"><message:say key="global.vitrinesPage.displayMessage"/></a></li>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${ not empty param.viewMarchands }">
            <li class="active"><a href="viewMarchands.do"><message:say key="global.marchandsPage.displayMessage"/></a></li>
          </c:when>
          <c:otherwise>
            <li><a href="viewMarchands.do"><message:say key="global.marchandsPage.displayMessage"/></a></li>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${ not empty param.viewFavorites }">
            <li class="active"><a href="viewFavorites.do"><message:say key="global.favoritesPage.displayMessage"/></a></li>
          </c:when>
          <c:otherwise>
            <li><a href="viewFavorites.do"><message:say key="global.favoritesPage.displayMessage"/></a></li>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${ not empty param.viewStatistiques }">
            <li class="active"><a href="viewStatistiques.do"><message:say key="global.statistiquesPage.displayMessage"/></a></li>
          </c:when>
          <c:otherwise>
            <li><a href="viewStatistiques.do"><message:say key="global.statistiquesPage.displayMessage"/></a></li>
          </c:otherwise>
        </c:choose>
        
        <!-- 
        <c:choose>
          <c:when test="${ not empty param.param1 }">
            ...
          </c:when>
          <c:otherwise>
            ...
          </c:otherwise>
        </c:choose>
         -->
         
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-user"></span>
            <% MembreDTO membre = ((MembreDTO) request.getSession().getAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME)); %>
            <%= membre.getUsername() %>
            <span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li>
              <a href="viewMembre.do?idMembre=<c:out value="${ sessionScope.membre.idMembre }"></c:out>"><message:say key="global.membrePage.displayMessage"/></a>
            </li>
            <li class="divider"></li>
            <li>
              <a href="deconnexion.do"><message:say key="global.logout.displayMessage"/></a>
            </li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>
