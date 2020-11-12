<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}"><c:redirect url="LogoutServlet"/></c:if>
<%@page import="it.gestionearticoli.model.articolo.Articolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="header.jsp" />
<title>Lista Categorie</title>

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


		<div class='card'>
			<div class='card-header'>
				<h3>Lista delle categorie corrispondenti alla tua ricerca</h3>
			</div>
			<div class='card-body'>
				<c:if
					test="${sessionScope.utente.ruolo != 'guest'}">
					<a class="btn btn-primary " href="PrepareInsertCategoriaServlet">Add
						New</a>
				</c:if>


				<div class='table-responsive text-center'>
					<table class='table table-striped '>
						<thead>
							<tr>
								
								<th><h4>Descrizione</h4></th>
							</tr>
						</thead>


						<tbody>
							<c:forEach items="${requestScope.listaCatPerSearch}"
								var="categoria">
								<tr>
									
									<td>${categoria.descrizione}</td>
									<td><a class="btn  btn-sm btn-outline-secondary"
										href="ListArticoliPerCategoriaServlet?idPerFindByCategoria=${categoria.idCategoria}">Visualizza</a>

										<c:if
											test="${sessionScope.utente.ruolo != 'guest'}">
											<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
												href="PrepareUpdateCategoriaServlet?idPerExecuteUpdateCat=${categoria.idCategoria}">Edit</a>
										</c:if> <c:if test="${sessionScope.utente.ruolo == 'admin'}">
											<a class="btn btn-outline-danger btn-sm"
												href="ConfirmDeleteCategoriaServlet?idPerExecuteDeleteCat=${categoria.idCategoria}">Delete</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
					
				<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/home.jsp"
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