<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<div>
		<h1>Wereldwijnen</h1>
		<img src='<c:url value="/images/intro.jpg"/>' alt='logo' id='logo'>
	</div>
	<ul>
		<c:forEach var='land' items='${landen}' varStatus='status'>
			<li><c:url value='/soorten.htm' var='index'>
					<c:param name='id' value="${land.id}" />
				</c:url> <a href="<c:out value='${index}'/>"><img
					src="images/${land.id}.png" alt="${land.naam}"></a></li>
		</c:forEach>
	</ul>
</nav>