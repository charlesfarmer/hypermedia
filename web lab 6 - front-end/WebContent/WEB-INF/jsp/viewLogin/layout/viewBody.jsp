<%@ taglib uri="application-message"
           prefix="message" %>
           
<%@ page import="ca.qc.collegeahuntsic.weblab6.dto.MembreDTO" %>
<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.RegisterMembreServlet" %>


<div id="body">
  <form id="login-membre-form" method="post" action="loginMembre.do">
    <label for="login-username"><message:say key="viewLogin.layout.viewBody.username.displayName"/></label><input name="<%= MembreDTO.USERNAME_COLUMN_NAME %>" id="login-username" type="text"/>
    <label for="login-password"><message:say key="viewLogin.layout.viewBody.password.displayName"/></label><input name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>" id="login-password" type="password"/>
    <input type="submit" value="<message:say key="viewLogin.layout.viewBody.formButton.displayName"/>"></input>
  </form>
  
<% Boolean isRegisterSuccessful= (Boolean) request.getAttribute(RegisterMembreServlet.REGISTER_STATUS_ATTRIBUTE_NAME);
   if(isRegisterSuccessful!= null) {
     if(isRegisterSuccessful!= null && isRegisterSuccessful.booleanValue()) { %>
       <h2><message:say key="register.membre.successful.displayMessage"/></h2>
<%   } else { %>
       <h2><message:say key="register.membre.failed.displayMessage"/></h2>
<%   } 
   } %>
  
  <form id="register-membre-form" method="post" action="registerMembre.do">
    <label for="register-username"><message:say key="viewLogin.layout.viewBody.username.displayName"/></label><input name="<%= MembreDTO.USERNAME_COLUMN_NAME %>" id="register-username" type="text"/>
    <label for="register-password"><message:say key="viewLogin.layout.viewBody.password.displayName"/></label><input name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>" id="register-password" type="password"/>
    <label for="register-password2"><message:say key="viewLogin.layout.viewBody.password.displayName"/></label><input name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>2" id="register-password2" type="password"/>
    <input type="submit" value="<message:say key="viewLogin.layout.viewBody.formButton.displayName"/>"></input>
    </form>
</div>