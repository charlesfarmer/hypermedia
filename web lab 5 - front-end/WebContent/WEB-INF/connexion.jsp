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
<title>LABORATOIRE 5</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="connexion">
		<form method="post" action="connexion?connexion=true">
			<c:choose>
			    <c:when test="${ sessionScope.client != null }">
			    	<h4><c:out value="${ sessionScope.client.email }" /></h4>
			    	<strong><a href="connexion?deconnexion=true">Déconnexion</a></strong>
			    </c:when>
			    <c:otherwise>
			    	<h2>Connexion</h2> <br>
					Votre courriel <input type="email" name="email">
					<c:if test="${ requestScope['clientInconnu'] }">
						<span style='color:red;'>Courriel/Mot de passe invalide</span>
					</c:if>
					<br>
					Mot de passe <input type="password" name="password"> <br>
					<input type="submit" class="btn" value="Connexion">
			    </c:otherwise>
			</c:choose>
		</form>
		<form method="post" action="connexion?nouveauCompte=true">
			<h2>Créer un nouveau compte </h2><br>
			Entrez votre courriel <input type="email" name="nouveauEmail">
			<c:if test="${ requestScope['clientExistant'] }">
				<span style='color:red;'>Courriel déjà utilisé. Veuillez essayer de nouveau.</span>
			</c:if>
			<br><br>
			Mot de passe <input type="password" name="password1"> <br>
			Répétez mot de passe <input type="password" name="password2"> <br>
			<input type="submit" class="btn" value="Créer votre compte gratuit!">
		</form>
		<br><br>
	</div>
		<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>