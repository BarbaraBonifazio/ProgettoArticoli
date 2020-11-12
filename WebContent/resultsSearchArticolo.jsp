<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}"><c:redirect url="LogoutServlet"/></c:if>
<%@page import="it.gestionearticoli.model.articolo.Articolo"%>
<%@page
	import="it.gestionearticoli.web.servlet.categoria.ListCategorieServlet"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="header.jsp" />
<title>Lista Articoli</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="navbar.jsp" />

	<main role="main" class="container">

		<div
			class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none"
			role="alert">
			Aggiungere d-none nelle class per non far apparire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<Input type="hidden" name="listaCategorieAttribute"
			id="listaCategorieAttribute" class="form-control"
			value="${requestScope.listaCategorieAttribute}">
		<div class='card'>
			<div class='card-header'>
				<h5>Lista degli articoli</h5>
			</div>
			<div class='card-body'>
				<c:if
					test="${sessionScope.utente.ruolo != 'guest'}">
					<a class="btn btn-primary "
						href="PrepareInsertArticoloServlet?listaCategorieAttribute?listaArticoliAttribute">Add
						New</a>
				</c:if>
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Codice</th>
								<th>Descrizione</th>
								<th>Prezzo</th>
								<th>Categoria</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.listaArtPerDesc}"
								var="articolo">
								<tr>
									<td>${articolo.id}</td>
									<td>${articolo.codice}</td>
									<td>${articolo.descrizione}</td>
									<td>${articolo.prezzo}</td>
									<td>${articolo.categoria.descrizione}</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/PrepareFindByExampleArticoloServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a>
				</div>

				<!-- end card-body -->
			</div>
		</div>



		<!-- end container -->
	</main>
	<jsp:include page="footer.jsp" />

</body>
</html>