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
	<c:set var='land' value='${soort.land}' />
	<h2>Soorten uit ${land.naam}</h2>
	<c:if test='${not empty land}'>
		<c:forEach var='soort' items='${land.soorten}' varStatus='status'>
			<li><c:url value='/landen/persoort.htm' var='persoort'>
					<c:param name='idsoort' value="${soort.id}" />
				</c:url> <a href="<c:out value='${persoort}'/>">${soort.naam}</a></li>
		</c:forEach>
	</c:if>
	<h2>Wijnen uit ${soort.naam}</h2>
	<c:if test='${not empty soort}'>
		<c:forEach var='wijn' items='${soort.wijnen}' varStatus='status'>
			<li><c:url value='/soorten/wijntoevoegen.htm' var='toevoegen'>
					<c:param name='idwijn' value="${wijn.id}" />
				</c:url><a href="<c:out value='${toevoegen}'/>">${wijn.jaar}</a> 
				<c:forEach
					begin='1' end='${wijn.beoordeling}'>
					&#9733;
				</c:forEach></li>
		</c:forEach>
	</c:if>
</body>
</html>