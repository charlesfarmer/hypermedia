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
	<h2>Modification du profil</h2>
	<c:if test="${ requestScope['modificationsReussies'] != null }">
			<span style="color:red; size=2em;">Modification Réussies!</span>
	</c:if> <br><br>
	<form method="post" action="modification?miseajour=true">
		Nouveau Prenom <input type="text" name="nouveauPrenom"> <br>
		Nouveau Nom <input type="text" name="nouveauNom"> <br>
		<br>
		<br>
		Entrez votre mot de passe actuel <input type="password" name="password">
		<c:if test="${ requestScope['passwordInvalide'] != null }">
			<span style="color:red">Votre mot de passe est invalide</span>
		</c:if> <br><br>
		<input type="submit" value="Modifier mes informations">
	</form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>