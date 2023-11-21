package com.fintech.projeto.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import com.fintech.projeto.beans.*;

import com.fintech.projeto.DAO.impl.OracleObjetivoDAO;

@WebServlet("/Objetivo")
public class ObjetivoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OracleObjetivoDAO objetivoDAO;

    @Override
    public void init() {
        objetivoDAO = new OracleObjetivoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        Usuario usuarioAutenticado = obterUsuario(request);

        if (usuarioAutenticado == null) {
            enviarRespostaErro(response, "Usuário não autenticado.");
            return;
        }

        try {
            String taskCounterParam = request.getParameter("taskCounter");

            if (taskCounterParam != null && !taskCounterParam.isEmpty()) {
                int taskCounter = Integer.parseInt(taskCounterParam);
                System.out.println("taskCounter: " + taskCounter);

                for (int i = 0; i < taskCounter; i++) {
                    String action = request.getParameter("action_" + i);

                    switch (action) {
                        case "cadastrar":
                            cadastrarTarefa(request, usuarioAutenticado, i);
                            break;
                        case "excluir":
                            excluirTarefa(request, i);
                            break;
                        case "editar":
                            editarTarefa(request, usuarioAutenticado, i);
                            break;
                    }
                }

                String saveTasksBtn = request.getParameter("saveTaskBtn");
                if ("true".equals(saveTasksBtn)) {
                    cadastrarTarefa(request, usuarioAutenticado, -1);
                }

                enviarRespostaSucesso(response, "Operações realizadas com sucesso.");

            } else {
                enviarRespostaErro(response, "Parâmetro taskCounter não fornecido ou inválido.");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro na conversão de número: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro ao processar a requisição: " + e.getMessage());
        }
    }

    private void cadastrarTarefa(HttpServletRequest request, Usuario usuario, int i) {
        try {
            String nome = request.getParameter("taskText_" + i);
            String descricao = request.getParameter("textBox_" + i);
            String dtPrevisao = request.getParameter("dateBox_" + i);
            String status = request.getParameter("statusBox_" + i);
            String valorNecessario = request.getParameter("valueBox_" + i);

            if (nome != null && descricao != null && dtPrevisao != null && status != null && valorNecessario != null) {
                objetivoDAO.cadastrar(usuario, new Objetivo(nome, Double.parseDouble(valorNecessario), dtPrevisao, Boolean.parseBoolean(status), descricao));
            } else {
                throw new IllegalArgumentException("Parâmetros incompletos para cadastrarTarefa.");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Erro na conversão de número.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar no banco de dados.");
        }
    }

    private void excluirTarefa(HttpServletRequest request, int i) {
        String cod = request.getParameter("cod_" + i);

        try {
            objetivoDAO.remover(cod);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir no banco de dados: " + e.getMessage());
        }
    }

    private void editarTarefa(HttpServletRequest request, Usuario usuario, int i) {
        try {
            String cod = request.getParameter("cod_" + i);
            String nome = request.getParameter("taskText_" + i);
            String descricao = request.getParameter("textBox_" + i);
            String dtPrevisao = request.getParameter("dateBox_" + i);
            String status = request.getParameter("statusBox_" + i);
            String valorNecessario = request.getParameter("valueBox_" + i);

            objetivoDAO.atualizar(usuario, new Objetivo(cod, nome, Double.parseDouble(valorNecessario), dtPrevisao, Boolean.parseBoolean(status), descricao));

        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Erro na conversão de número.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao editar no banco de dados: " + e.getMessage());
        }
    }

    private void enviarRespostaErro(HttpServletResponse response, String mensagem) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"erro\": \"" + mensagem + "\"}");
        }
    }

    private void enviarRespostaSucesso(HttpServletResponse response, String mensagem) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"mensagem\": \"" + mensagem + "\"}");
        }
    }

    private Usuario obterUsuario(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            return (Usuario) session.getAttribute("usuario");
        }

        return null;
    }
}