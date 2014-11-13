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
	<div class="connexion">
		<jsp:include page="header.jsp"></jsp:include>
		
		<c:import url="/produits.xml" var="produits" charEncoding="UTF-8" />
		<x:parse xml="${produits}" var="output" />
		
		<c:set var="subTotal" value="${ 0 }"></c:set>
		
		<table border="1">
		<tr>
			<th>Aperçu</th>
			<th>Item</th>
			<th>Quantité</th>
			<th>Prix</th>
		</tr>
		
		<c:forEach items="${sessionScope['panier']}" var="ligne">
			<tr>
			
			<x:forEach select="$output/magasin/produit" var="produit">
				<c:set var="prodId"><x:out select="$produit/code"/></c:set>
				
				
				<c:if test="${ prodId == ligne.produitBean.idProduit }">
					<td>
						<img height='150' src="PICS/<x:out select="$produit/image"/>"/>
					</td>
					<td>
						<x:out select="$produit/nom" />
					</td>
					<td>
						<c:out value="${ ligne.quantite }" />
					</td>
					<td>
						<c:set var="tempPrix"><c:out value="${ (ligne.quantite * ligne.produitBean.stockBean.prix) - ligne.produitBean.stockBean.rabais }"/></c:set>
						<c:out value="${ tempPrix }"/>
						<c:set var="subTotal" value="${ subTotal + tempPrix }"/>
					</td>
				</c:if>
				
			</x:forEach>
			
			
			</tr>
		</c:forEach>
		
		</table>
		<h3>
		Sous-total: <c:out value="${ subTotal }"/><br/>
		<a href="confirmation">CONFIRMER</a> la commande
		</h3>
		<jsp:include page="footer.jsp"></jsp:include>
		</div>
</body>
</html>