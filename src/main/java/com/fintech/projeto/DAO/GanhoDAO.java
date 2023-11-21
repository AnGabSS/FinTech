package com.fintech.projeto.DAO;

import java.util.List;

import com.fintech.projeto.beans.Ganho;
import com.fintech.projeto.beans.Usuario;
import com.fintech.projeto.exception.DBException;

public interface GanhoDAO {
	void cadastrar(Usuario usuario, Ganho ganho) throws DBException;
	void atualizar(Usuario usuario, Ganho ganho) throws DBException;
	void remover(String cod) throws DBException;
	Ganho buscar (String cod);
	List<Ganho> listar();
}
