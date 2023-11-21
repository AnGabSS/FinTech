<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt- br">
<head>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

  <link rel="stylesheet" href="resources/css/styles.css">

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Páginas do fintech</title>
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="bootstrap-grid.css">
</head>

<body>

<div class="container-fluid mt-3">
  <nav class="navbar navbar-expand-lg">
    <div class="container">
      <a class="navbar-brand logo-text" href="#"><i class="fa-solid fa-business-time logo-icon"></i>Projeto
        fintech</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
              aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa-solid fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <h4 class="my-2">Olá, Fulano!</h4>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="./home.html"><i
                    class="fa-solid fa-house m-1"></i>Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./perfil.jsp"><i
                    class="fa-solid fa-user m-1"></i>Perfil</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
              Todos os serviços
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#"> Contas </a></li>
              <li><a class="dropdown-item active" href="./serviços.jsp"> Serviços contratados</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="#">Visão geral</a>
              </li>
            </ul>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="#"><i
                    class="fa-solid fa-sliders m-1"></i>Configurações</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="login.html"><i
                    class="fa-solid fa-circle-xmark m-1"></i>Sair da conta</a>
          </li>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</div>

<div class="container-fluid text-center">
  <h2 class="m-5 p-2" style="background-color: rgb(13, 110, 253);">Seus Serviços</h2>
  <div class="container my-5">
    <table class="table table-striped table-houver text">
      <thead class="table-dark">
      <tr>
        <th>Nome</th>
        <th>Valor</th>
        <th>Descrição</th>
      </tr>
      </thead>

      <tbody>
	       <c:forEach var="g" items="${ganhos}">
	    <tr>
	        <td>${g.nome}</td>
	        <td>${g.valor}</td>
	        <td>${g.descricao}</td>
	        <td><fmt:formatDate value="${g.dtIsercao.time}" pattern="dd/MM/yyyy"/></td>
	        <c:url value="servico" var="linkG">
	            <c:param name="acao" value="abrir-form-edicao"/>
	            <c:param name="codigo" value="${g.cod}"/>
	        </c:url>
	        <a href="${linkG}" class="btn btn-primary">Editar ganho</a>
	        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="codigoExcluirG.value = ${g.cod}">Excluir</button>
	    </tr>
			</c:forEach>

            <c:forEach var="d" items="${despesa}">
      	<tr>
      		<td>${d.nome}</td>
      		<td>${d.valor}</td>
      		<td>${d.descricao}</td>
      		<td><fmt:formatDate value="$d.dtIsercao.time" pattern="dd/MM/yyyy"/></td>
      		<c:url value="servico" var="linkD">
    			<c:param name="acao" value="abrir-form-edicao"/>
    			<c:param name="codigo" value="${d.cod}"/>
			</c:url>

			<a href="${linkD}" class="btn btn-primary">Editar Despesa</a>
      		<button type="button" class="btn btn-danger" data-toggle="modal" data-target="codigoExcluirD.value = ${d.cod}">Excluir</button>
      	</tr>
      
      </c:forEach>
      
      
      
      </tbody>

    </table>
			

  </div>

</div>

<div class="modal fade" id="excluirModalG" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="ModalLabel">Confirmação</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="close">
						<span aria-hidden="true">&times;</span>
					</button>
			</div>
			<div class="modal-content">
				<p>Deseja realmente excluir o gasto?</p>
			</div>
			<div class="modal-footer">
				<form action="ganhoServlet" method="post">
					<input type="hidden" name="acao" value="excluir">
	      		<input type="hidden" name="codigo" id="codigoExcluirG">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		        <button type="submit" class="btn btn-danger">Excluir</button>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="excluirModalD" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="ModalLabel">Confirmação</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="close">
						<span aria-hidden="true">&times;</span>
					</button>
			</div>
			<div class="modal-content">
				<p>Deseja realmente excluir a Despesa?</p>
			</div>
			<div class="modal-footer">
				<form action="ganhoServlet" method="post">
					<input type="hidden" name="acao" value="excluir">
	      		<input type="hidden" name="codigo" id="codigoExcluirD">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		        <button type="submit" class="btn btn-danger">Excluir</button>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="resources/js/bootstrap.bundle.min.js"></script>

</body>

</html>
