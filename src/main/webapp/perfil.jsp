<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14/11/2023
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="resources/css/styles.css">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Páginas do fintech</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
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
                        <a class="nav-link active" href="./perfil.html"><i
                                class="fa-solid fa-user m-1"></i>Perfil</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="./serviços.html" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Todos os serviços
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#"> Contas </a></li>
                            <li><a class="dropdown-item nav-link" href="./serviços.jsp?acao=listar" > Serviços contratados</a></li>
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
                </form>
            </div>
        </div>
    </nav>
</div>
<div class="container-fluid d-flex justify-content-center">
    <div class="card" style="width: 18rem;">
        <img src="resources/images/tl (1).webp" class="card-img-top" alt="...">
        <div class="card-body">
            <h5 class="card-title">E-mail</h5>
            <p class="card-text">***********@hotmail.com</p>
        </div>
        <div class="container">
            <p>-------------------------------------------------</p>

            <div class="container-fluid d-flex mb-4">
                <a href="#" class="m-3 ">Alterar senha</a>
            </div>
        </div>
    </div>
</div>

<script src="resources/js/bootstrap.bundle.min.js"></script>
</body>

</html>
