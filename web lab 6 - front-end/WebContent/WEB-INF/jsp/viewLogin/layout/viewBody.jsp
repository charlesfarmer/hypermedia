<%@ taglib uri="application-message"
           prefix="message" %>
           
<%@ page import="ca.qc.collegeahuntsic.weblab6.dto.MembreDTO" %>
<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.RegisterMembreServlet" %>
<%@ page import="ca.qc.collegeahuntsic.weblab6.servlet.action.LoginMembreServlet" %>

<div id="body">
  <div id="login-titre"><message:say key="viewLogin.layout.viewBody.titre.login.displayMessage"/></div>
  <div id="login-membre">
  <% Boolean isLoginSuccessful= (Boolean) request.getAttribute(LoginMembreServlet.LOGIN_STATUS_ATTRIBUTE_NAME);
    if(isLoginSuccessful!= null) {
       if(isLoginSuccessful!= null && isLoginSuccessful.booleanValue()) { %>
         <h2><message:say key="login.membre.successful.displayMessage"/></h2>
    <% } else { %>
         <div class="alert alert-danger">
           <span class="glyphicon glyphicon-exclamation-sign"></span>
           <message:say key="login.membre.failed.displayMessage"/>
         </div>
   <%  } 
     } %>
    <form id="login-membre-form" method="post" action="loginMembre.do">
      <label class="login-text" for="login-username"><message:say key="viewLogin.layout.viewBody.username.displayName"/></label>
      <input name="<%= MembreDTO.USERNAME_COLUMN_NAME %>" id="login-username" type="text"/>
      <br/>
      <label class="login-text" for="login-password"><message:say key="viewLogin.layout.viewBody.password.displayName"/></label>
      <input name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>" id="login-password" type="password"/>
      <br/>
      <input type="submit" value="<message:say key="viewLogin.layout.viewBody.buttonLogin.displayName"/>"></input>
    </form>
  </div>
  <div id="login-titre"><message:say key="viewLogin.layout.viewBody.titre.register.displayMessage"/></div>
  <div id="register-membre">
  <% Boolean isRegisterSuccessful= (Boolean) request.getAttribute(RegisterMembreServlet.REGISTER_STATUS_ATTRIBUTE_NAME);
     if(isRegisterSuccessful!= null) {
       if(isRegisterSuccessful!= null && isRegisterSuccessful.booleanValue()) { %>
         <h2><message:say key="register.membre.successful.displayMessage"/></h2>
    <% } else { %>
         <div class="alert alert-danger">
           <span class="glyphicon glyphicon-exclamation-sign"></span>
           <message:say key="register.membre.failed.displayMessage"/>
         </div>
   <%  } 
     } %>
    <form id="register-membre-form" method="post" action="registerMembre.do">
      <label class="login-text" for="register-username"><message:say key="viewLogin.layout.viewBody.username.displayName"/></label>
      <input name="<%= MembreDTO.USERNAME_COLUMN_NAME %>" id="register-username" type="text"/>
      <br/>
      <label class="login-text" for="register-password"><message:say key="viewLogin.layout.viewBody.password.displayName"/></label>
      <input name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>" id="register-password" type="password"/>
      <br/>
      <label class="login-text" for="register-password2"><message:say key="viewLogin.layout.viewBody.password.displayName"/></label>
      <input name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>2" id="register-password2" type="password"/>
      <br/>
      <label class="login-text" for="register-email"><message:say key="viewLogin.layout.viewBody.email.displayName"/></label>
      <input name="<%= MembreDTO.EMAIL_COLUMN_NAME %>" id="register-email" type="text"/>
      <br/>
      <input type="submit" value="<message:say key="viewLogin.layout.viewBody.buttonRegister.displayName"/>"></input>
    </form>
  </div>
</div>