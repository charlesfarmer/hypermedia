<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Laboratoire 5</title>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>

		<h1>Bienvenue sur le meilleur site de vente de produits!</h1>
		
		<h3>Produit vedette</h3>
		
		<c:import url="/produits.xml" var="produits" charEncoding="UTF-8"/>
		<x:parse xml="${produits}" var="output" />
		<table border="1">
			<x:forEach select="$output/magasin/produit" var="produit">
				<c:set var="prodId"><x:out select="$produit/code"/></c:set>
				<c:if test="${ applicationScope['vedette'] == prodId }">
					<tr style='background:#FFA0A0'>
						<td>
							<x:out select="$produit/code" />
						</td>
						<td>
							<x:out select="$produit/nom" />
						</td>		
						<td>
							<x:out select="$produit/description" />
						</td>
						<td>
							<img height='150' src="PICS/<x:out select="$produit/image"/>"/>
						</td>
						<td>
							<a class="btn" href="panier?id=<x:out select="$produit/code"/>">AJOUTER</a>
						</td>
					</tr>
				</c:if>
			</x:forEach>
		</table>
		
		<c:import url="/produits.xml" var="produits" charEncoding="UTF-8"/>
		<x:parse xml="${produits}" var="output" />
		<table border="1">
			<x:forEach select="$output/magasin/produit" var="produit">
			<c:set var="prodId"><x:out select="$produit/code"/></c:set>
				<c:if test="${ applicationScope['vedette'] != prodId }">
					<tr>
						<td>
							<x:out select="$produit/code" />
						</td>
						<td>
							<x:out select="$produit/nom" />
						</td>		
						<td>
							<x:out select="$produit/description" />
						</td>
						<td>
							<img height='150' src="PICS/<x:out select="$produit/image"/>"/>
						</td>
						<td>
							<a class="btn" href="panier?id=<x:out select="$produit/code"/>">AJOUTER</a>
						</td>
					</tr>	
				</c:if>
			</x:forEach>
		</table>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
