
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}"><c:redirect url="LogoutServlet"/></c:if>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="header.jsp" />
<title>Search Articolo</title>

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
				<h5>Cerca articolo</h5>
				<a class="text-right" href="${pageContext.request.contextPath}/home.jsp"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a>
			</div>
			<div class='card-body'>


				<form method="post" action="ExecuteFindByExampleArticoloServlet"
					name="myForm" onsubmit="return validateForm()" action="/action_page.php" novalidate>
					

					<div class="form-group col-md-6">
						<label>Codice</label> <input
							type="text" name="codiceArt" id="codice"
							class="form-control" placeholder="Inserire almeno i primi tre caratteri"
							required>
							
					</div>
					
					<div class="form-group col-md-6">
						<label>Descrizione</label> <input
							type="text" name="descrArt" id="descrizione"
							class="form-control" placeholder="Inserire almeno i primi tre caratteri"
							required>
							
					</div>
					
					<div class="form-group col-md-6">
						<label>Prezzo</label> <input
							type="text" name="prezzoArt" id="prezzo"
							class="form-control" placeholder="Inserire il prezzo"
							required>
							
					</div>
					
					<%-- <div class="form-group col-md-6">
							<label>Categoria</label> <select id="idCat"
								name="idCat" class="form-control" required>
								<option value="">- Seleziona Categoria -</option>
								<c:forEach items="${requestScope.listaCategorieAttribute}"
									var="categoria">
									<option value="${categoria.idCategoria}"><c:out
											value="${categoria.descrizione}" /></option>
								</c:forEach>
							</select>
						</div> --%>

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
