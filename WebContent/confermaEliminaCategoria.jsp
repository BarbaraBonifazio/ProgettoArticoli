<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest' || sessionScope.utente.ruolo == 'operator'}"><c:redirect url="LogoutServlet"/></c:if>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.gestionearticoli.model.articolo.Articolo"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">

<jsp:include page="header.jsp" />

<title>Conferma</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">
</head>


<body>
	<jsp:include page="navbar.jsp" />

	<main role="main" class="container">
		<div class='card'>
			<div class="text-center">
				<div class='card-header'>
					<h4>Confermi di voler eliminare questa categoria?</h4>
				</div>

				<div class='card-body'>
					<form method="get" action="ExecuteDeleteCategorieServlet"
						novalidate="novalidate">
						<div class="form-row">
							<div class="form-group col-md-6">
								<Input type="hidden" name="idPerExecuteDeleteCat"
									id="idPerExecuteDeleteCat" class="form-control"
									value="${requestScope.categoriaDaEliminare.idCategoria}">
								<a class="btn btn-danger btn-lg" role="button"
									href="ListCategorieServlet">Annulla</a>
							</div>
							<div class="form-group col-md-6">
								<button type="submit" name="submit" value="submit" id="submit"
									class="btn btn-primary btn-lg">Conferma</button>
							</div>
						</div>

					</form>

					<!-- end card-body -->
				</div>
			</div>
		</div>
		<!-- end container -->
	</main>
	<jsp:include page="footer.jsp" />

</body>
</html>