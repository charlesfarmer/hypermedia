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
	<c:if test="${ sessionScope.client.email eq 'root@magasin' }">
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/administration">ADMINISTRATION</a>
			</li>
		</ul>
	</c:if>
</ul>
</div>
</div>

<br><br>