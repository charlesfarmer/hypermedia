<%@ page import="java.util.Calendar"%>
<%@ taglib uri="application-message"
           prefix="message" %>

<div id="footer-wrapper">    
  <div id="footer">
    <div id="footer-navigation">
        <a href="javascript:scrollToTop();"><message:say key="global.returnTop.displayMessage"/></a>
        <message:say key="global.footerSeparator.displayMessage"/>
        <a href="viewTemplate.do"><message:say key="global.returnHome.displayMessage"/></a>
    </div>
    <div id="legal-footer">
      <message:say key="global.allRightsReserved.displayMessage"/> - (<%= new java.util.GregorianCalendar().get(Calendar.YEAR) %>)
    </div>
  </div>
</div>