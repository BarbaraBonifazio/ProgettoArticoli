<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">

<jsp:include page="header.jsp" />

<title>Accedi</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">


</head>
<body>
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
				<h2>Bevenuto/a!</h2>
				<h4>Inserisci i tuoi dati per effettuare l'accesso</h4>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="LoginServlet" class="needs-validation" novalidate>

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Username <span class="text-danger">*</span></label> <input
								type="text" name="username" id="username" class="form-control"
								placeholder="Inserire Nome Utente" required>
						</div>
						<div class="invalid-feedback">
				          Il campo risulta vuoto!
				        </div>

						<div class="form-group col-md-6">
							<label>Password <span class="text-danger">*</span></label> <input
								type="password" name="password" id="password"
								class="form-control" placeholder="Inserire Password" required>
						</div>
						<div class="invalid-feedback">
				          Il campo risulta vuoto!
				        </div>
					</div>


					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Accedi</button>



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