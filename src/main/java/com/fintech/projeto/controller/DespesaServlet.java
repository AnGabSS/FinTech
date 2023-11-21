package com.fintech.projeto.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fintech.projeto.beans.Despesa;
import com.fintech.projeto.beans.Usuario;
import com.fintech.projeto.DAO.DespesaDAO;
import com.fintech.projeto.DAO.UserDAO;
import com.fintech.projeto.exception.DBException;
import com.fintech.projeto.factory.DAOFactory;

@WebServlet("/despesaServlet")
public class DespesaServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DespesaDAO dao;
    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = DAOFactory.getDespesaDAO();
        userDao = DAOFactory.getUserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "listar":
                listarDespesas(request, response);
                break;
            case "abrir-form-edicao":
                abrirFormularioEdicao(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação desconhecida");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        
if(acao != null) {
        switch (acao) {
            case "cadastrar":
                cadastrarDespesa(request, response);
                break;
            case "editar":
                editarDespesa(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação desconhecida");
        }
    } else {
    	System.out.println("Ação nula");
    }
 }

    private void listarDespesas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Despesa> lista = dao.listar();
        request.setAttribute("despesa", lista);
        request.getRequestDispatcher("servicos.jsp").forward(request, response);
    }

    private void abrirFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cod = request.getParameter("codigo");
        Despesa despesa = dao.buscar(cod);
        request.setAttribute("despesa", despesa);
        request.getRequestDispatcher("edicao-servico.jsp").forward(request, response);
    }

    private void editarDespesa(HttpServletRequest request, HttpServletResponse response)
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

            double preco = Double.parseDouble(valorStr);
            Usuario usuario = userDao.buscar(cpfUser);
            Despesa despesa = new Despesa(cod, nome, preco, descricao);

            dao.atualizar(usuario, despesa);

            request.setAttribute("msg", "Item atualizado!");
        } catch (NumberFormatException | DBException | NullPointerException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }
        request.getRequestDispatcher("serviço.jsp").forward(request, response);
    }

    private void cadastrarDespesa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nome = request.getParameter("nomeDespesa");
            String descricao = request.getParameter("descricao");
            String valorStr = request.getParameter("valorDespesa");
            String cpfUser = request.getParameter("cpf");

            if (nome == null || descricao == null || valorStr == null || cpfUser == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros ausentes");
                return;
            }

            double preco = Double.parseDouble(valorStr);
            Usuario usuario = userDao.buscar(cpfUser);
            Despesa despesa = new Despesa(nome, preco, descricao);

            dao.cadastrar(usuario, despesa);

            request.setAttribute("msg", "Item cadastrado!");
        } catch (NumberFormatException | DBException | NullPointerException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }
        request.getRequestDispatcher("serviços.jsp").forward(request, response);
    }
}
