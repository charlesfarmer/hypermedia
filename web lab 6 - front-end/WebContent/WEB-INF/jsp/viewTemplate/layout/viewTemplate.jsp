<div id="template-page">
  <div id="template-header">
    <jsp:include page="/WEB-INF/jsp/viewTemplate/layout/viewHeader.jsp" flush="true">
      <jsp:param name="param1" value="asdf"/>
    </jsp:include>
    <div id="template-body"><jsp:include page="/WEB-INF/jsp/viewTemplate/layout/viewBody.jsp" flush="true"/></div>
    <div id="template-footer"><jsp:include page="/WEB-INF/jsp/viewTemplate/layout/viewFooter.jsp" flush="true"/></div>
  </div>
</div>
