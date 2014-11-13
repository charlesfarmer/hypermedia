<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<form action="profil" method="post">
	<h2>Le profil de <c:out value='${sessionScope["client"].profil.prenom}'></c:out> </h2>
		<c:out value='${sessionScope["client"].email}' default='non disponible'/> <br>
		<c:out value='${sessionScope["client"].profil.nom}' default='non disponible'/> <br>
		<c:out value='${sessionScope["client"].profil.prenom}' default='non disponible'/> <br>
	</form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>