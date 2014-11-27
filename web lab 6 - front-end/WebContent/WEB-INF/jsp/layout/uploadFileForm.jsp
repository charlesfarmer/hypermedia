<%@ taglib uri="application-message"
           prefix="message" %>

<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.FileUploadServlet" %>

<% Boolean isUploadSuccessful = (Boolean) request.getAttribute(FileUploadServlet.UPLOAD_STATUS_ATTRIBUTE_NAME);
   if(isUploadSuccessful != null) {
     if(isUploadSuccessful != null && isUploadSuccessful.booleanValue()) { %>
       <h2><message:say key="upload.file.successful.displayMessage"/></h2>
<%   } else { %>
       <h2><message:say key="upload.file.failed.displayMessage"/></h2>
<%   } 
   } %>

<div id="upload-file">
  <form id="upload-file-form" method="post" enctype="multipart/form-data" action="uploadFile.do">
    <input name="file-chooser" id="file-chooser" type="file" accept=".jpg|.png|.gif|.bmp" size="50">
    <br/>
    <input name="submit-file" id="submit-file" type="submit" value="T&#233;l&#233;verser">
  </form>
</div>