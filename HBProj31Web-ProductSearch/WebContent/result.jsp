<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
</head>
<body>
	<h1 style="color:red;text-align: center">Result Page</h1>
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
	<a href="search.html">Home</a>
</body>
</html>