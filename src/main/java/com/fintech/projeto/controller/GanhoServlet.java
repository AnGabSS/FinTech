package com.fintech.projeto.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fintech.projeto.beans.Ganho;
import com.fintech.projeto.beans.Usuario;
import com.fintech.projeto.DAO.GanhoDAO;
import com.fintech.projeto.DAO.UserDAO;
import com.fintech.projeto.exception.DBException;
import com.fintech.projeto.factory.DAOFactory;

@WebServlet("/GanhoServlet")
public class GanhoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private GanhoDAO dao;
    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = DAOFactory.getGanhoDAO();
        userDao = DAOFactory.getUserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao != null) {
            switch (acao) {
                case "listar":
                    listarGanhos(request, response);
                    break;
                case "abrir-form-edicao":
                    abrirFormularioEdicao(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação desconhecida");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'acao' ausente");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao != null) {
            switch (acao) {
                case "cadastrar":
                    cadastrarGanho(request, response);
                    break;
                case "editar":
                    editarGanho(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação desconhecida");
            }
        } else {
            System.out.println("Ação nula");
        }
    }

    private void listarGanhos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ganho> lista = dao.listar();
        request.setAttribute("ganho", lista);
        request.getRequestDispatcher("servicos.jsp").forward(request, response);
    }

    private void abrirFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cod = request.getParameter("codigo");
        Ganho ganho = dao.buscar(cod);
        request.setAttribute("ganho", ganho);
        request.getRequestDispatcher("edicao-servico.jsp").forward(request, response);
    }

    private void editarGanho(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String cod = request.getParameter("codigo");
            String nome = request.getParameter("nome");
            String valorStr = request.getParameter("valor");
            String descricao = request.getParameter("descricao");
            String cpfUser = request.getParameter("cpf");

            if (cod == null || nome == null || valorStr == null || descricao == null || cpfUser == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros ausentes");
                return;
            }

            double valor = Double.parseDouble(valorStr);
            Usuario usuario = userDao.buscar(cpfUser);
            Ganho ganho = new Ganho(cod, nome, valor, descricao);

            dao.atualizar(usuario, ganho);

            request.setAttribute("msg", "Item atualizado!");
        } catch (NumberFormatException | DBException | NullPointerException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }
        request.getRequestDispatcher("servico.jsp").forward(request, response);
    }

    private void cadastrarGanho(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nome = request.getParameter("nomeGanho");
            String descricao = request.getParameter("descricao");
            String valorStr = request.getParameter("valorGanho");
            String cpfUser = request.getParameter("cpf");

            if (nome == null || descricao == null || valorStr == null || cpfUser == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros ausentes");
                return;
            }

            double valor = Double.parseDouble(valorStr);
            Usuario usuario = userDao.buscar(cpfUser);
            Ganho ganho = new Ganho(nome, valor, descricao);

            dao.cadastrar(usuario, ganho);

            request.setAttribute("msg", "Item cadastrado!");
        } catch (NumberFormatException | DBException | NullPointerException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }
        request.getRequestDispatcher("servicos.jsp").forward(request, response);
    }
}
