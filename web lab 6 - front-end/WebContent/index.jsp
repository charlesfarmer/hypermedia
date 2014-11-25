<%@ taglib uri="application-config"
           prefix="config" %>

<%@ taglib uri="application-message"
           prefix="message" %>

<!DOCTYPE html PUBLIC
               "-//W3C//DTD XHTML 1.0 Strict//EN"
               "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
  <head>
  
    <base href="<config:webappProtocol/>://<config:webappServerName/>:<config:webappPortNumber/><config:webappPath/>/"/>
    
    <title><message:say key="global.welcome.displayMessage"/></title>
    
    <script type="text/javascript" src=""></script>
    
    <%@ include file="/WEB-INF/jsp/layout/jsCommon.jsp" %>
    
  </head>

  <body>
  	<h1>Ca marche!</h1>
    <p>Dis bonjour à papa</p>
  </body>
</html>
