<%@page import="ca.qc.collegeahuntsic.weblab5.listener.MagasinSessionListener"%>
<br><br>
<div>
	<span><a href="${pageContext.request.contextPath}/index">Retour à la page d'accueil</a></span>
	<br>
		Nombre d'usager en ligne:
		<%= MagasinSessionListener.getNbActiveSession() %>
</div>