<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<c:choose>
	    <c:when test="${ sessionScope.client != null }">
	        <h2>Le profil de <c:out value='${sessionScope["client"].profilBean.nom}'></c:out> </h2>
			Courriel/Nom d'utilisateur: <c:out value='${sessionScope["client"].email}' default='non disponible'/> <br><br>
			Prenom: <c:out value='${sessionScope["client"].profilBean.prenom}' default='non disponible'/> <br>
			Nom: <c:out value='${sessionScope["client"].profilBean.nom}' default='non disponible'/> <br><br>
			<button onclick="location.href='profil/modification'"/>Modifier mon profil</button>
	    </c:when>
	    <c:otherwise>
	        <h2>Vous n'êtes présentement pas connecté</h2><br>
	        <a href="${pageContext.request.contextPath}/connexion">Cliquez ici</a> pour vous connecter ou pour vous créer un compte gratuit !
	    </c:otherwise>
	</c:choose>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>