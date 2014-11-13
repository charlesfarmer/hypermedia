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
	<form method="post" action="connexion">
		<h2>Connexion</h2> <br>
		Entrez votre courriel <input type="email" name="email"> <br>
		Entrez votre mot de passe <input type="password" name="password"> <br>
		<input type="submit" value="Connexion">
		<h2>Créer un nouveau compte </h2><br>
		Entrez votre courriel <input type="email" name="email"> <br>
		Ce courriel vous servira de nom d'utilisateur <br>
		Entrez votre mot de passe <input type="password" name="password"> <br>
		Entrez votre mot de passe à nouveau <input type="password" name="password"> <br>
		<input type="submit" value="Créer votre compte gratuit!">
	</form>
	<br><br>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>