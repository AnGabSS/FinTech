package com.fintech.projeto.factory;

import com.fintech.projeto.DAO.*;
import com.fintech.projeto.DAO.impl.*;

public class DAOFactory {

	public static UserDAO getUserDAO(){
		return new OracleUserDAO();
	}
	
	public static DespesaDAO getDespesaDAO() {
		return new OracleDespesaDAO();
	}
	
	public static GanhoDAO getGanhoDAO() {
		return new OracleGanhoDAO();
		}
	
	public static ObjetivoDAO getObjetivoDAO() {
		return new OracleObjetivoDAO();
	}
	
	public static InvestimentoDAO getInvestimento() {
		return new OracleInvestimentoDAO();
	}
	
	}