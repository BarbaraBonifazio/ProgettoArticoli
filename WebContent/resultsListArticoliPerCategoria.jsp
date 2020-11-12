<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}"><c:redirect url="LogoutServlet"/></c:if>
<%@page import="it.gestionearticoli.model.articolo.Articolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="header.jsp" />
<title>Visualizza elemento</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>

<body>
	<jsp:include page="navbar.jsp" />

	<main role="main" class="container">
		<Input type="hidden" name="idCategoriaPerResultsListArtPerCat"
			id="idCategoriaPerResultsListArtPerCat" class="form-control"
			value="${requestScope.categoriaPerResultsListArtPerCat.idCategoria}">
		<div class='card'>
			<div class='card-header'>
				<h5>
					Categoria:
					<c:out value="${categoriaPerResultsListArtPerCat.descrizione}" />
				</h5>
			</div>

			<div class='card-body'>
				<div class='table-responsive'>
				<h5>Lista Articoli</h5>
					<table class='table table-striped '>
					
						<thead>
						
							<tr>
								
								<th>Codice</th>
								<th>Descrizione</th>
								<th>Prezzo</th>
								
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${categoriaPerResultsListArtPerCat.listaArticoli}"
								var="articolo">
								<tr>
									
									<td>${articolo.codice}</td>
									<td>${articolo.descrizione}</td>
									<td>${articolo.prezzo}</td>
									
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>


			<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/ListCategorieServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a>
			</div>
		</div>



		<!-- end main container -->
	</main>
	<jsp:include page="footer.jsp" />

</body>
</html>