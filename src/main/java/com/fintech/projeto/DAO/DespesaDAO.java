package com.fintech.projeto.DAO;

import java.util.List;

import com.fintech.projeto.beans.Despesa;
import com.fintech.projeto.beans.Usuario;
import com.fintech.projeto.exception.DBException;

public interface DespesaDAO {
	void cadastrar(Usuario usuario, Despesa despesa) throws DBException;
	void atualizar(Usuario usuario, Despesa despesa) throws DBException;
	void remover(String cod) throws DBException;
	Despesa buscar (String cod);
	List<Despesa> listar();

}
