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
    
    <jsp:include page="menu.jsp" />


        <h1>Lista de Tarefas</h1>

        <input type="text" id="taskInput" placeholder="Digite uma tarefa">
        <button id="addTaskBtn">Adicionar Tarefa</button>
        <form action="Objetivo" method="post" id="taskForm">
            <ul id="taskList"></ul>
        </form>
        <button id="saveTasksBtn">Salvar Tarefas</button>


        <script src="resources/js/addTarefa.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>

    </html>