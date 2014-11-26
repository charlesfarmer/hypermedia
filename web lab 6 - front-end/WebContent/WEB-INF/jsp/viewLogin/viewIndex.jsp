<%@ taglib uri="application-config"
           prefix="config" %>

<%@ taglib uri="application-message"
           prefix="message" %>
           
<%@ page import="ca.qc.collegeahuntsic.weblab6.dto.MembreDTO" %>

<!DOCTYPE html PUBLIC
               "-//W3C//DTD XHTML 1.0 Strict//EN"
               "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
  <head>
    <base href="<config:webappProtocol/>://<config:webappServerName/>:<config:webappPortNumber/><config:webappPath/>/"/>
    <title><message:say key="global.welcome.displayMessage"/></title>
    <%@ include file="/WEB-INF/jsp/layout/jsCommon.jsp" %>
  </head>
  <body>
  	<form id="login-membre-form" method="post" action="loginMembre.do">
  	  <label>nom<input name="<%= MembreDTO.USERNAME_COLUMN_NAME %>" id="test" type="text"/></label><br></br>
  	  <label>mot de passe<input name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>" id="test2" type="text"/></label><br></br>
  	  <input type="submit" value="Envoyer"></input>
  	</form>
  </body>
</html>
