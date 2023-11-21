package com.fintech.projeto.DAO.impl;

import com.fintech.projeto.DAO.ObjetivoDAO;
import com.fintech.projeto.beans.*;
import com.fintech.projeto.connection.*;
import com.fintech.projeto.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleObjetivoDAO implements ObjetivoDAO {
	private static Connection conexao;
	
	public List<Objetivo> listar() {
		 
	    List<Objetivo> lista = new ArrayList<Objetivo>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	      conexao = ConexaoDB.obterConexao();
	    stmt = conexao.prepareStatement("SELECT * FROM T_SIP_OBJETIVOS");
	    rs = stmt.executeQuery();
	  
	    while (rs.next()) {
	    String nome = rs.getString("NM_OBJETIVO");
	    String dtPrevisao = rs.getString("DT_PREVISAO");
	    int statusInt = rs.getInt("STATUS");
	    boolean status = false;
	    if(statusInt != 0) {
	    	status = true;
	    }
	    double valorNecessario = rs.getDouble("NR_VALOR_NESC");
	    String descricao = rs.getString("DS_OBJETIVO");
	        

	    	Objetivo objetivo = new Objetivo(nome, valorNecessario, dtPrevisao, status, descricao);
	        
	        lista.add(objetivo);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        stmt.close();
	        rs.close();
	        conexao.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    return lista;
	  }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void cadastrar(Usuario usuario, Objetivo objetivo) {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "INSERT INTO T_SIP_OBJETIVOS(CD_OBJETIVO, CPF, NM_OBJETIVO, NR_VALOR_NESC, DS_OBJETIVO, DT_PREVISAO, STATUS) VALUES(SQ_OBJETIVO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getCPF());
			stmt.setString(2, objetivo.getNome());
			stmt.setDouble(3, objetivo.getValorNecessario());
			stmt.setString(4,objetivo.getDescricao());
			stmt.setString(5, objetivo.getDtPrevisao());
			stmt.setBoolean(6, objetivo.getStatus());
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close(); 
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Usuario usuario, Objetivo objetivo) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "UPDATE T_SIP_OBJETIVOS SET NM_OBJETIVO = ?, NR_VALOR_NESC = ?, DS_OBJETIVO = ?, DT_PREVISAO = ? STATUS = ? WHERE CD_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, objetivo.getNome());
			stmt.setDouble(2, objetivo.getValorNecessario());
			stmt.setString(3, objetivo.getDescricao());
			stmt.setString(4, objetivo.getDtPrevisao());
			stmt.setBoolean(5, objetivo.getStatus());
			stmt.setString(6, objetivo.getCod());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close(); 
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void remover(String cod) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "DELETE * FROM T_SIP_OBJETIVOS WHERE CD_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cod);
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close(); 
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Objetivo buscar(String cod) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Objetivo resultado = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "SELECT * FROM T_SIP_OBJETIVOS WHERE CD_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cod);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				cod = rs.getString("CD_OBJETIVO");
				String nome = rs.getString("NM_OBJETIVO");
				double valorNesc = rs.getDouble("NR_VALOR_NESC");
				String descricao = rs.getString("DS_OBJETIVO");
				String dtPrevisao = rs.getString("DT_PREVISAO");
				boolean status = rs.getBoolean("STATUS");
				
				resultado = new Objetivo(cod, nome, valorNesc, dtPrevisao, status, descricao);
				return resultado;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close(); 
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

}
