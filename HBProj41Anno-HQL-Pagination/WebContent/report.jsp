<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${policiesList ne null && !empty policiesList}">
		<table border="1" align="center">
			<tr>
				<td>SerialNo</td>
				<td>Policy ID</td>
				<td>Policy Name</td>
				<td>Policy Type</td>
				<td>Company</td>
				<td>Tenure</td>
			</tr>
			<c:forEach var="dto" items="${policiesList}">
				<tr bgcolor="yellow">
					<td>${dto.serialNo}</td>
					<td>${dto.policyId}</td>
					<td>${dto.policyName}</td>
					<td>${dto.policyType}</td>
					<td>${dto.company}</td>
					<td>${dto.tenure}</td>
				</tr>
			</c:forEach>
		</table>
		
		<center>
			<c:if test="${pageNo>1}">
				<b><a href="controller?pageNo=${pageNo-1}&s1=link">Previous</a></b>&nbsp;&nbsp;
			</c:if>
			<c:forEach var="i" begin="1" end="${pagesCount}" step="1">
				<b><a href="controller?pageNo=${i}&s1=link">[ ${i} ]</a></b>&nbsp;&nbsp;
			</c:forEach>
			<c:if test="${pageNo<pagesCount}">
				<b><a href="controller?pageNo=${pageNo+1}&s1=link">Next</a></b>&nbsp;&nbsp;
			</c:if>
		</center>
		
	</c:when>
	
	<c:otherwise>
		<h1 style="color: red;text-align: center;">No Records Found</h1>
	</c:otherwise>
	
</c:choose>
<br>
<h2 style="text-align: center;"><a href="index.html">Home</a></h2>
