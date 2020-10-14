<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
</head>
<body>
	<!-- Using JSTL -->
	<h1 style="color:red;text-align: center">Result Page</h1>
	<c:choose>
		<c:when test="${!empty pDTO || pDTO ne null }">
			<table border="1" align="center">
				<tr>
					<td>${pDTO.pid}</td>
					<td>${pDTO.pname}</td>
					<td>${pDTO.price}</td>
					<td>${pDTO.qty}</td>
					<td>${pDTO.category}</td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<h1 style="color: red;text-align: center;">No Product Found</h1>
		</c:otherwise>
	</c:choose>
	<br><br>
	<a href="search.html">Home</a>
	<!-- Using JSP -->
	<%-- <h1 style="color:red;text-align: center">Result Page</h1>
	<%
	 if(request.getAttribute("pDTO")!=null){
	%>
	<table border="1" align="center">
		<tr>
			<td>${pDTO.pid}</td>
			<td>${pDTO.pname}</td>
			<td>${pDTO.price}</td>
			<td>${pDTO.qty}</td>
			<td>${pDTO.category}</td>
		</tr>
	</table>
	<%	 
	 }//if
	 else{
		 %>
		 <h1 style="color: red;text-align: center;">No Product Found</h1>
	<%
	 }
	%>
	<br><br>
	<a href="search.html">Home</a> --%>
</body>
</html>