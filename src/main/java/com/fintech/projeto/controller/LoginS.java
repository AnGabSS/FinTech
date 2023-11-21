package com.fintech.projeto.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.fintech.projeto.beans.Usuario;

import java.io.IOException;

@WebServlet("/login")
public class LoginS extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginS() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        if ("admin".equals(cpf) && "12345".equals(senha)) {
            HttpSession session = request.getSession();


            Usuario usuario = new Usuario();
            usuario.setCPF(cpf);
            usuario.setNome("Nome do Usuário");

            session.setAttribute("usuario", usuario);

            response.sendRedirect("home.html");
        } else {
            response.sendRedirect("login.html");
        }
    }
}
