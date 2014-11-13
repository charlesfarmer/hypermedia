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
	<a href="index">Retour à la page principale</a>
	
	Panier de <c:out value="${sessionScope['client'].nom}"></c:out>
	
	<c:import url="/produits.xml" var="produits" />
	<x:parse xml="${produits}" var="output" />
	<table>
	<c:forEach items="${sessionScope['panier']}" var="ligne">
		<tr>
		
		<x:forEach select="$output/magasin/produit" var="produit">
			<x:set var="prodId" select="$produit/code"/>
		
			<c:if test="${ prodId == ligne.produitBean.idProduit }">
				<c:out value="ÇA MARCHE BORDEL "></c:out>
			</c:if>
			<td><c:out value="${ligne.produitBean.idProduit}"></c:out></td>
			<c:out value="${ prodId }"/>
		</x:forEach>
		
		
		</tr>
	</c:forEach>
	</table>
</body>
</html>