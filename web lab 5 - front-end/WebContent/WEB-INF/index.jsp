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
		<title>Laboratoire 5</title>
	</head>
	<body>
		<a href="/connexion">Se connecter</a> <br>
		<a href="/panier">Visionner votre panier d'achat</a> <br>
		<h1>Bienvenue sur le meilleur site de vente de produits!</h1>
	
		<c:import url="/produits.xml" var="produits" />
		<x:parse xml="${produits}" var="output" />
		<table border="1">
			<x:forEach select="$output/magasin/produit" var="produit">
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
					<a href="/panier?id=<x:out select="$produit/code"/>">AJOUTER</a>
				</td>
			</tr>
			</x:forEach>
		</table>
	</body>
</html>