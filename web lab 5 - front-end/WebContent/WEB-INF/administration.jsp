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
	<h3>Changer le produit vedette</h3>
	<form method="post" action="administration">
		Entrez un ID de produit:&nbsp;<input type="text" name="produitVedette">
		<c:if test="${ requestScope['produitInvalide'] != null }">
			<span style="color:red;">ID de produit invalide</span>
		</c:if>
		<c:if test="${ requestScope['produitValide'] != null }">
			<span style="color:red;">Modification réussie</span>
		</c:if>
		<br><br>
		<input type="submit" value="Modifier produit vedette">
	</form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>