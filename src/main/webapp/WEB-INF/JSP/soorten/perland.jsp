<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Soorten per land' />
</head>
<body>
	<header>
		<v:menu />
	</header>
	<h2>Soorten uit ${land.naam}</h2>
	<c:if test='${not empty land}'>
		<c:forEach var='soort' items='${land.soorten}' varStatus='status'>
			<li><c:url value='/landen/persoort.htm'
					var='index'>
					<c:param name='idsoort' value="${soort.id}" />
				</c:url> <a href="<c:out value='${index}'/>">${soort.naam}</a></li>
		</c:forEach>
	</c:if>
</body>
</html>