<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Wijn toevoegen' />
</head>
<body>
	<header>
		<v:menu />
	</header>
	<h2>Wijn toevoegen aan mandje</h2>
	<form method='post' id='toevoegform'>
	<table>
<%-- 		<tr><th>Land</th><td>${wijn.soort.land}</td></tr> --%>
<%-- 		<tr><th>Soort</th><td>${wijn.soort}</td></tr> --%>
<%-- 		<tr><th>Jaar</th><td>${wijn.jaar}</td></tr> --%>
<%-- 		<tr><th>Beoordeling</th><td>${wijn.beoordeling}</td></tr> --%>
<%-- 		<tr><th>Prijs</th><td>${wijn.prijs}</td></tr> --%>
	</table>
		<label>Aantal flessen<span>${fouten.naam}</span> <input
			name='naam' value='' autofocus required></label> 
			<input type='submit' value='Toevoegen' id='toevoegknop'>
	</form>
</body>
</html>