<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<div>
		<h1>Wereldwijnen</h1>
		<img src='<c:url value="/images/intro.jpg"/>' alt='logo' id='logo'>
	</div>
	<ul>
		<li><a href="<c:url value='/artikels/zoekenopnummer.htm'/>">Artikel
				zoeken op nummer</a></li>
		<li><a href="<c:url value='/artikels/toevoegen.htm'/>">Artikel
				toevoegen </a></li>
		<li><a href="<c:url value='/artikels/zoekenopnaam.htm'/>">Artikels
				zoeken op naam</a></li>
		<li><a href="<c:url value='/artikels/prijsverhoging.htm'/>">Prijsverhoging
		</a></li>
		<li><a href="<c:url value='/artikels/kortingen.htm'/>">Artikelkortingen
		</a></li>
		<li><a href="<c:url value='/artikels/perartikelgroep.htm'/>">Artikels
				per artikelgroep</a></li>
		<li><a href="<c:url value='/artikels.htm'/>">Artikellijst</a></li>
	</ul>
</nav>