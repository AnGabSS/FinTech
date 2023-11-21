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

<div class="container">
    <h1 class="text-center m-5" style="background-color: rgb(13, 110, 253);">Formulário de Edição de Serviços</h1>

    <form id="servicoForm" method="post">
    <input type="hidden" name="acao" value="editar">
        <div class="col-sm-12 col-md-12 p-4">
            <h2>Preencha os Dados do Serviço</h2>

            <div class="row align-items-end">
                <div class="col-sm-12 col-md-6 my-3">
                    <label for="nomeDespesa" class="form-label">Nome do Serviço</label>
                    <input type="text" class="form-control" id="nomeDespesa" name="nomeDespesa" placeholder="Ex: Item">
                </div>
                <div class="col-sm-12 col-md-6 my-3">
                    <label for="valorDespesa" class="form-label">Valor</label>
                    <input type="text" class="form-control" id="valorDespesa" name="valorDespesa" placeholder="Ex: R$ 25,00">
                </div>
            </div>

            <div class="row align-items-end">
                <div class="col-sm-12 col-md-6 my-3">
                    <label for="username" class="form-label">Nome/Pessoa</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Ex: Fulano">
                </div>
            </div>

            <div class="row align-items-end">
                <div class="col-sm-12 col-md-6 my-3">
                    <legend class="col-form-label col-sm-2 pt-0">Escolha</legend>
                    <div class="col-sm-10">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="tipoServico" id="ganho" value="Ganho"
                                   checked>
                            <label class="form-check-label" for="ganho">
                                Ganho
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="tipoServico" id="despesa" value="Despesa">
                            <label class="form-check-label" for="despesa">
                                Despesa
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="d-flex justify-content-end">
                <button class="btn btn-primary" type="submit" onclick="submitForm()">Adicionar Serviço</button>
            </div>
        </div>
    </form>
</div>

<script src="resources/js/bootstrap.bundle.min.js"></script>
<script>
    function submitForm() {
        var form = document.getElementById("servicoForm");
        var tipoServico = document.querySelector('input[name="tipoServico"]:checked');

        var nomeDespesa = document.getElementById("nomeDespesa").value.trim();
        var valorDespesa = document.getElementById("valorDespesa").value.trim();

        if (!nomeDespesa || !valorDespesa || !tipoServico) {
            alert("Preencha todos os campos obrigatórios.");
            return;
        }

        if (tipoServico.value === "Ganho") {
            form.action = "GanhoServlet";
        } else if (tipoServico.value === "Despesa") {
            form.action = "DespesaServlet";
        }

        form.submit();
    }
</script>

</body>

</html>
