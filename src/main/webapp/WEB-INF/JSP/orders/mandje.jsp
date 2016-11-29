<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='Mandje' />
<body>
	<header>
		<v:menu />
	</header>
	<h2>Mandje</h2>
	<table>
		<tr>
			<th>Wijn</th>
			<th>Prijs</th>
			<th>Aantal</th>
			<th>Te betalen</th>
		</tr>
		<c:if test="${not empty wijnenInMandje}">
			<c:forEach var='wijn' items='${wijnenInMandje}'>
				<tr>
					<td>${wijn.key.soort.land.naam} ${wijn.key.soort.naam} ${wijn.key.jaar}</td>
					<td>${wijn.key.prijs}</td>
					<td>${wijn.value}</td>
					<td>${totaal}</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>