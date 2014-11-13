<style><jsp:include page="CSS/bootstrap.css"></jsp:include>
img{
	height:150px;
}
td{
	padding:10px;
}

</style>
<div class="navbar navbar-fluid navbar-top">
	<div class="navbar-inner">
		<ul class="nav">
			<li><a href="${pageContext.request.contextPath}/index">ACCUEIL</a></li>
			<li><a href="${pageContext.request.contextPath}/panier">PANIER</a></li>
			<li><a href="${pageContext.request.contextPath}/profil">PROFIL</a></li>
			<li><a href="${pageContext.request.contextPath}/connexion">CONNEXION</a></li>
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<c:if test="${ sessionScope.client.email eq 'root@magasin' }">
			<li><a href="${pageContext.request.contextPath}/administration">ADMINISTRATION</a></li>
			</c:if>
		</ul>
	</div>
</div>

<br><br>