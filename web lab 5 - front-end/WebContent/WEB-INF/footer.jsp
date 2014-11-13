<jsp:useBean id="counter" class="ca.qc.collegeahuntsic.weblab5.listener.MagasinSessionListener"></jsp:useBean>
<div>
		Nombre d'usager en ligne:
		<jsp:getProperty property="nbActiveSession" name="counter"/>
</div>