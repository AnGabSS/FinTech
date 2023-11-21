package com.fintech.projeto.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.fintech.projeto.DAO.impl.OracleInvestimentoDAO;
import com.fintech.projeto.beans.*;

@WebServlet("/InvestimentoServlet")
public class InvestimentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OracleInvestimentoDAO investimentoDAO;

    @Override
    public void init() {
        investimentoDAO = new OracleInvestimentoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try {
            String action = request.getParameter("action");

            if (action != null) {
                switch (action) {
                    case "cadastrar":
                        cadastrarInvestimento(request, response);
                        break;
                    case "buscar":
                        buscarInvestimento(request, response);
                        break;
                    case "editar":
                        editarInvestimento(request, response);
                        break;
                    case "atualizar":
                        atualizarInvestimento(request, response);
                        break;
                    case "remover":
                        removerInvestimento(request, response);
                        break;
                    default:
                        enviarRespostaErro(response, "Ação desconhecida.");
                }
            } else {
                enviarRespostaErro(response, "Ação não fornecida.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro ao processar a requisição: " + e.getMessage());
        }
    }

    private void cadastrarInvestimento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String cpf = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String cotasParam = request.getParameter("cotas");
            String descricao = request.getParameter("descricao");

            if (cpf != null && nome != null && cotasParam != null && descricao != null) {
                double cotas = Double.parseDouble(cotasParam);

                if (isValidCPF(cpf)) {
                    Usuario usuario = new Usuario();
                    usuario.setCPF(cpf);

                    Investimento investimento = new Investimento(nome, cotas, descricao);

                    investimentoDAO.cadastrar(usuario, investimento);

                    enviarRespostaSucesso(response, "Investimento cadastrado com sucesso.");
                } else {
                    enviarRespostaErro(response, "CPF inválido.");
                }
            } else {
                enviarRespostaErro(response, "Parâmetros incompletos para cadastrarInvestimento.");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro na conversão de número: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro ao cadastrar investimento: " + e.getMessage());
        }
    }

    private void buscarInvestimento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String cod = request.getParameter("cod");

            if (cod != null) {
                Investimento investimento = investimentoDAO.buscar(cod);

                if (investimento != null) {
                   
                    enviarRespostaSucesso(response, convertInvestimentoToJson(investimento));
                } else {
                    enviarRespostaErro(response, "Investimento não encontrado.");
                }
            } else {
                enviarRespostaErro(response, "Código do investimento não fornecido.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro ao buscar investimento: " + e.getMessage());
        }
    }

    private void editarInvestimento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String cod = request.getParameter("cod");

            if (cod != null) {
                Investimento investimento = investimentoDAO.buscar(cod);

                if (investimento != null) {
                   
                    enviarRespostaSucesso(response, convertInvestimentoToJson(investimento));
                } else {
                    enviarRespostaErro(response, "Investimento não encontrado.");
                }
            } else {
                enviarRespostaErro(response, "Código do investimento não fornecido.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro ao buscar investimento para edição: " + e.getMessage());
        }
    }

    private void atualizarInvestimento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
          
            String cod = request.getParameter("cod");
            String nome = request.getParameter("nome");
            String cotasParam = request.getParameter("cotas");
            String descricao = request.getParameter("descricao");

            if (cod != null && nome != null && cotasParam != null && descricao != null) {
                double cotas = Double.parseDouble(cotasParam);

                Investimento investimento = new Investimento(cod, cotas, descricao);

                investimentoDAO.atualizar(null, investimento);

               
                enviarRespostaSucesso(response, "Investimento atualizado com sucesso.");
            } else {
                enviarRespostaErro(response, "Parâmetros incompletos para atualizarInvestimento.");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro na conversão de número: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro ao atualizar investimento: " + e.getMessage());
        }
    }

    private void removerInvestimento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String cod = request.getParameter("cod");

            if (cod != null) {
                investimentoDAO.remover(cod);

        
                enviarRespostaSucesso(response, "Investimento removido com sucesso.");
            } else {
                enviarRespostaErro(response, "Código do investimento não fornecido.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            enviarRespostaErro(response, "Erro ao remover investimento: " + e.getMessage());
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

    private String convertInvestimentoToJson(Investimento investimento) {
        
        return investimento.toJson();
    }

    private boolean isValidCPF(String cpf) {
        
        return cpf != null && cpf.length() == 11;
    }
}
