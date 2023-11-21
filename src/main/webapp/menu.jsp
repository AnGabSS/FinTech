<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
            integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
            crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

    <meta charset="UTF-8">
    <title>Insert title here</title>
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
                            <a class="nav-link active" aria-current="page" href="./home.html"><i
                                    class="fa-solid fa-house m-1"></i>Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="perfil.jsp"><i
                                    class="fa-solid fa-user m-1"></i>Perfil</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                Todos os serviços
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#"> Contas </a></li>
                                <li><a class="dropdown-item" href="./serviços.jsp"> Serviços contratados</a></li>
                                <li><a class="dropdown-item" href="./objetivos.jsp"> +Objetivos</a></li>
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
                    </ul>
                </div>
            </div>
        </nav>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>