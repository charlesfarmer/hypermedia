<%@ taglib uri="application-message"
           prefix="message" %>
           
<%@ page import="ca.qc.collegeahuntsic.weblab6.dto.MembreDTO" %>

<div id="login-body">
  <form id="login-membre-form" method="post" action="loginMembre.do">
    <label for="login-username"><message:say key="viewLogin.layout.viewBody.username.displayName"/></label><input name="<%= MembreDTO.USERNAME_COLUMN_NAME %>" id="login-username" type="text"/>
    <label for="login-password"><message:say key="viewLogin.layout.viewBody.password.displayName"/></label><input name="<%= MembreDTO.PASSWORD_COLUMN_NAME %>" id="login-password" type="text"/>
    <input type="submit" value="<message:say key="viewLogin.layout.viewBody.formButton.displayName"/>"></input>
  </form>
</div>