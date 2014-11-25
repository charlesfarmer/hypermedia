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

    <script type="text/javascript"
            src="http://code.jquery.com/jquery-latest.min.js">
    </script>
    <script type="text/javascript"
            src="js/bootstrap/bootstrap.min.js">
    </script>
  </head>

  <body onload="javascript:viewTemplateIndex('view-template-index-form');">
    <form id="view-template-index-form" method="post" action="viewTemplateIndex.do">
    </form>
  </body>
</html>
