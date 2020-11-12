
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}"><c:redirect url="LogoutServlet"/></c:if>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="header.jsp" />
<title>Search Categoria</title>

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
				<h5>Cerca Categoria</h5>
				<a class="text-right" href="${pageContext.request.contextPath}/home.jsp"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a>
			</div>
			<div class='card-body'>


				<form method="post" action="ExecuteFindByExampleCategoriaServlet" class="needs-validation"
					novalidate>
					

					<div class="form-group col-md-6">
						<label>Descrizione</label> <input
							type="text" name="descrizioneCat" id="descrizione"
							class="form-control" placeholder="Inserire almeno i primi tre caratteri"
							required>
							<div class="invalid-feedback">
					         Il campo risulta vuoto!
					        </div>
							
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>

				</form>

				<script>
				
				(function() {
				  'use strict';
				  window.addEventListener('load', function() {
				   
				    var forms = document.getElementsByClassName('needs-validation');
				    
				    var validation = Array.prototype.filter.call(forms, function(form) {
				      form.addEventListener('submit', function(event) {
					        if (form.checkValidity() === false) {
					          event.preventDefault();
					          event.stopPropagation();
					        }
				        form.classList.add('was-validated');
				        
				      }, false);
				    });
				  }, false);
				})();
				</script>
				
						
				



				<!-- end card-body -->
			</div>

		</div>
		<!-- end container -->
	</main>
	<jsp:include page="footer.jsp" />
</body>
</html>
