package com.fintech.projeto.DAO;

import java.util.List;

import com.fintech.projeto.beans.Investimento;
import com.fintech.projeto.beans.Usuario;
import com.fintech.projeto.exception.DBException;

public interface InvestimentoDAO {

	void cadastrar(Usuario usuario, Investimento investimento) throws DBException;
	void atualizar(Usuario usuario, Investimento investimento) throws DBException;
	void remover(String cod) throws DBException;
	Investimento buscar (String cod);
	List<Investimento> listar();
}
