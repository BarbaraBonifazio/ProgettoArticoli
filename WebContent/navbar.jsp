<!-- navbar -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.gestionearticoli.model.utente.Utente"%>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="home.jsp">Home
					<span class="sr-only">(current)</span>
			</a></li>
			<!-- <li class="nav-item"><a class="nav-link" href="#">Link</a></li>

			<li class="nav-item"><a class="nav-link disabled" href="#"
				tabindex="-1" aria-disabled="true">Disabled</a></li> -->

			<li class="nav-item dropdown active"><a
				class="nav-link dropdown-toggle" href="#" id="dropdown01"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Cerca</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item" href="home.jsp">Home</a> <a
						class="dropdown-item" href="ListArticoliServlet">Lista
						Articoli</a> <a class="dropdown-item" href="ListCategorieServlet">Lista
						Categorie</a>
					<c:if
						test="${sessionScope.utente.ruolo == 'admin' || sessionScope.utente.ruolo == 'operator'}">
						<a class="dropdown-item" href="insertArticolo.jsp">Inserisci
							nuovo articolo</a>
						<a class="dropdown-item" href="insertCategoria.jsp">Inserisci
							nuova categoria</a>
					</c:if>
				</div></li>

			<li class="nav-item active"><a class="nav-link" href="#">
					Benvenuto/a <c:out value="${sessionScope.utente.nome}"/> <c:out
						value="${sessionScope.utente.cognome}" /> <c:out
						value="${sessionScope.utente.codiceFiscale}" />
			</a></li>

		</ul>
		<c:if
			test="${sessionScope.utente.ruolo == 'admin' || sessionScope.utente.ruolo == 'operator' || sessionScope.utente.ruolo == 'guest'}">
			<ul class="navbar-nav mr-1">
				<li class="nav-item active"><a class="nav-link active"
					href="LogoutServlet"> Logout </a></li>
			</ul>
		</c:if>

		<c:if test="${sessionScope.utente.ruolo == null}">
			<ul class="navbar-nav mr-1">
				<li class="nav-item active"><a class="nav-link active"
					href="${pageContext.request.contextPath}/index.jsp"> Login </a></li>
			</ul>
		</c:if>

		<!-- <form class="form-inline my-2 my-lg-0">
			 <input
				class="form-control mr-sm-2" type="text" placeholder="Search"
				aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form> -->
	</div>
</nav>
