package com.fintech.projeto.DAO;

import java.util.List;

import com.fintech.projeto.beans.Objetivo;
import com.fintech.projeto.beans.Usuario;
import com.fintech.projeto.exception.DBException;

public interface ObjetivoDAO {

	void cadastrar(Usuario usuario, Objetivo objetivo) throws DBException;
	void atualizar(Usuario usuario, Objetivo objetivo) throws DBException;
	void remover(String cod) throws DBException;
	Objetivo buscar (String cod);
	List<Objetivo> listar();
}
