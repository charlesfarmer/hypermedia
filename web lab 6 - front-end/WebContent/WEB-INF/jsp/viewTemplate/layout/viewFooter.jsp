<%@ page import="java.util.Calendar"%>
<%@ taglib uri="application-message"
           prefix="message" %>
           
<div id="footer">
  <div id="retour-a-l'accueil">
    <a href="viewTemplate.do"><message:say key="global.returnHome.displayMessage"/></a>
  </div>
  <div id="go-to-top">
    <a href="javascript:scroll(0,0);"><message:say key="global.returnTop.displayMessage"/></a>
  </div>
  <br/>
  <div id="legal-footer">
    <message:say key="global.allRightsReserved.displayMessage"/> - (<%= new java.util.GregorianCalendar().get(Calendar.YEAR) %>)
  </div>
</div>
