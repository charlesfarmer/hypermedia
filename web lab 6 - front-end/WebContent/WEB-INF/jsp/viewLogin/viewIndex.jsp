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
    <link rel="stylesheet" href="css/viewLogin/stylesheet.css" type="text/css"/>
    <script type="text/javascript" src="js/viewLogin/viewLogin.js"></script>
    <%@ include file="/WEB-INF/jsp/layout/includeCommon.jsp" %>
  </head>
  <body>
  	<div id="index-login"><jsp:include page="/WEB-INF/jsp/viewLogin/layout/viewLogin.jsp" flush="true"/></div>
  </body>
</html>