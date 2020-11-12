<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest'}"><c:redirect url="LogoutServlet"/></c:if>
<%@page import="it.gestionearticoli.model.articolo.Articolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="header.jsp" />
<title>Modifica</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica Articolo</h5>
				<a class="text-right" href="${pageContext.request.contextPath}/ListArticoliServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="ExecuteUpdateArticoliServlet" 
								name="myForm" onsubmit="return validateForm()" action="/action_page.php">

					<div class="form-row">


						<Input type="hidden" name="idarticoloPerUpdate"
							id="articoloPerUpdate" class="form-control"
							value="${requestScope.articoloPerUpdate.id}">



						<div class="form-group col-md-6">
							<label>Codice <span class="text-danger">*</span></label> <input
								type="text" name="codice" id="codice" class="form-control"
								placeholder="Inserire il codice" required
								value="${requestScope.articoloPerUpdate.codice}">
						</div>

						<div class="form-group col-md-6">
							<label>Descrizione <span class="text-danger">*</span></label> <input
								type="text" name="descrizione" id="descrizione"
								class="form-control" placeholder="Inserire la descrizione"
								required value="${requestScope.articoloPerUpdate.descrizione}">
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Prezzo <span class="text-danger">*</span></label> <input
								type="number" class="form-control" name="prezzo" id="prezzo"
								placeholder="Inserire prezzo" required
								value="${requestScope.articoloPerUpdate.prezzo}">
						</div>

						<div class="form-group col-md-6">
							<label>Categoria</label> <select id="listaCategorieAttribute"
								name="idDaInviareAExecuteUpdate" class="form-control">
								<option value="">- Seleziona Categoria -</option>
								<c:forEach items="${requestScope.listaCategorieAttribute}"
									var="categoria">
									<option value="${categoria.idCategoria}"><c:out
											value="${categoria.descrizione}" /></option>

								</c:forEach>
							</select>
						</div>
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>


				</form>

				<script>
				function validateForm() {
					  var x = document.forms["myForm"]["prezzo"].value;

					  if (x < 0) {
					    alert("Il prezzo non può essere un numero negativo!");
					    return false;
					  }
					  
					  else if (isNaN(document.forms["myForm"]["prezzo"].value)) {
						  
						    alert("Il campo Prezzo deve essere un numero!");
						    return false;
					  }  
					} 
				</script>

				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="footer.jsp" />

</body>
</html>