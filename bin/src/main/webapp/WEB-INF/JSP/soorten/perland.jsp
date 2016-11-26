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
		<c:forEach items='${land.soorten}' var='soort'>
					${soort.naam}
		</c:forEach>
	</c:if>
</body>
</html>