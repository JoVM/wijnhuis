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
				<c:set var="totaal"
					value="${totaal = totaal + wijn.key.prijs * wijn.value}"
					scope="page" />
				<tr>
					<td>${wijn.key.soort.land.naam}${wijn.key.soort.naam}
						${wijn.key.jaar}</td>
					<td>${wijn.key.prijs}</td>
					<td>${wijn.value}</td>
					<td>${wijn.key.prijs * wijn.value}</td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td>Totaal:${totaal}&euro;</td>
			</tr>
		</c:if>
	</table>
	<br>
	<form method='post' id='bestelbonbevestigen'>
		<label>Naam<span>${fouten.naam}</span> <input name='naam'
			value='${param.naam}' autofocus></label> <label>Straat<span>${fouten.straat}</span>
			<input name='straat' value='${param.straat}' autofocus required></label>
		<label>Huisnummer<span>${fouten.huisnummer}</span> <input
			name='huisnummer' value='${param.huisnummer}' autofocus required></label>
		<label>Postcode<span>${fouten.postcode}</span> <input
			name='postcode' value='${param.postcode}' autofocus required></label>
		<label>Gemeente<span>${fouten.gemeente}</span> <input
			name='gemeente' value='${param.gemeente}' autofocus required></label>
		<div>
			<label><span>${fouten.leverwijze}</span></label>
		</div>
		<br>
		<div>
			<label><input type='radio' name='leverwijze' value='afhalen'>
				Afhalen </label> <label> <input type='radio' name='leverwijze'
				value='opsturen'> Opsturen
			</label>
		</div>
		<input type='submit' value='Als bestelbon bevestigen'
			id='bevestigknop'>
	</form>
	<label><span>${fouten.error}</span></label>
	<script>
		document.getElementById('bestelbonbevestigen').onsubmit = function() {
			document.getElementById('bevestigknop').disabled = true;
		};
	</script>
</body>
</html>