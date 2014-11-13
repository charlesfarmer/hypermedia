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
	Panier de <c:out value="${sessionScope['client'].profilBean.prenom}"></c:out><c:if test="${ sessionScope['client'] == null }">invité</c:if>
	<br/><br/>
	
	<c:import url="/produits.xml" var="produits" />
	<x:parse xml="${produits}" var="output" />
	<table border="1">
	<tr>
		<th>Aperçu</th>
		<th>Item</th>
		<th>Quantité</th>
		<th>Retirer du panier</th>
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
				<td align="center">
					<form action="panier">
						<input type="hidden" name="modId" value="<x:out select='$produit/code' />"/>
						<input type="text" name="modQ" size="5" value ='<c:out value="${ ligne.quantite }" />' />
						<br/>
						<input type="submit" name="modSub" value="Modifier"/>
						
					</form>
					
				</td>
				<td>
					<a href="panier?del=<x:out select="$produit/code"/>">RETIRER</a>
				</td>
			</c:if>
			
		</x:forEach>
		
		
		</tr>
	</c:forEach>
	</table>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>