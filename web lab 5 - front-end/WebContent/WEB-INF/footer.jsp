<%@page import="ca.qc.collegeahuntsic.weblab5.listener.MagasinSessionListener"%>
<div>
		Nombre d'usager en ligne:
		<%= MagasinSessionListener.getNbActiveSession() %>
</div>