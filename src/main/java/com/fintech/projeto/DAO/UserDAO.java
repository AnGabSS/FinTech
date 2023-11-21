package com.fintech.projeto.DAO;

import java.util.List;

import com.fintech.projeto.beans.Usuario;
import com.fintech.projeto.exception.DBException;

public interface UserDAO {
	void cadastrar(Usuario user) throws DBException;
	void atualizar(Usuario user) throws DBException;
	void remover(String CPF) throws DBException;
	Usuario buscar (String CPF);
	List<Usuario> listar();
}
