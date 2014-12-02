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
    <%@ include file="/WEB-INF/jsp/layout/includeCommon.jsp" %>
    <link rel="stylesheet" href="css/viewTemplate/stylesheet.css" type="text/css"/>
    <link rel="stylesheet" href="css/viewVitrines/stylesheet.css" type="text/css"/>
    <link rel="stylesheet" href="css/core/stylesheet.css" type="text/css"/>
    <script type="text/javascript" src="js/viewTemplate/viewTemplate.js"></script>
    <script type="text/javascript" src="js/viewVitrines/viewVitrines.js"></script>
  </head>
  <body>
    <div id="index-template"><jsp:include page="/WEB-INF/jsp/viewVitrines/layout/viewVitrines.jsp" flush="true"/></div>
  </body>
</html>