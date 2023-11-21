package com.fintech.projeto.DAO.impl;

import com.fintech.projeto.DAO.InvestimentoDAO;
import com.fintech.projeto.beans.*;
import com.fintech.projeto.connection.*;
import com.fintech.projeto.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleInvestimentoDAO implements InvestimentoDAO{
	private static Connection conexao;
	
	public List<Investimento> listar() {
		 
	    List<Investimento> lista = new ArrayList<Investimento>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	      conexao = ConexaoDB.obterConexao();
	    stmt = conexao.prepareStatement("SELECT * FROM T_SIP_INVESTIMENTOS");
	    rs = stmt.executeQuery();
	  
	    while (rs.next()) {
	    String nome = rs.getString("NM_INVESTIMENTO");
	    String descricao = rs.getString("DS_INVESTIMENTO");
	    double cotas = rs.getDouble("NR_COTAS");
	    java.sql.Date dataInsercao = rs.getDate("DT_ISERCAO");
	        

	    Investimento investimento = new Investimento(nome, cotas, descricao, dataInsercao);
	        
	        lista.add(investimento);
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
	public void cadastrar(Usuario usuario, Investimento investimento) {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "INSERT INTO T_SIP_INVESTIMENTOS (CD_INVESTIMENTO, CPF, NM_INVESTIMENTO, NR_COTAS, DS_INVESTIMENTO) VALUES (SQ_INVEST.NEXTVAL, ?, ?, ?, ?)";
			String sql1 = "UPDATE T_SIP_USUARIO SET NR_SALDO = ? WHERE CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getCPF());
			stmt.setString(2, investimento.getNome());
			stmt.setDouble(3, investimento.getCotas());
			stmt.setString(4, investimento.getDescricao());
			investimento.addInvestimentoSaldo(usuario);
			stmt.executeUpdate();
			
			stmt = conexao.prepareStatement(sql1);
			stmt.setDouble(1, usuario.getSaldo());
			stmt.setString(2, usuario.getCPF());
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
	public void atualizar(Usuario usuario, Investimento investimento) throws DBException {
		PreparedStatement stmt = null;
		
		try{
			conexao = ConexaoDB.obterConexao();
			String sql = "UPDATE T_SIP_INVESTIMENTOS SET NM_INVESTIMENTO = ?, NR_COTAS = ?, DS_INVESTIMENTO = ? WHERE  CD_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, investimento.getNome());
			stmt.setDouble(2, investimento.getCotas());
			stmt.setString(3, investimento.getDescricao());
			stmt.setString(4, investimento.getCod());
			
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
			String sql = "DELETE FROM T_SIP_INVESTIMENTOS WHERE CD_INVESITMENTO = ?";
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
	public Investimento buscar(String cod) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Investimento resultado = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "SELECT * FROM T_SIP_INVESTIMENTOS WHERE CD_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cod);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				cod = rs.getString("CD_INVESTIMENTO");
				String nome = rs.getString("NM_INVESTIMENTO");
				double cotas = rs.getDouble("NR_COTAS");
				String descricao = rs.getString("DS_INVESTIMENTO");
				java.sql.Date dtIsercao = rs.getDate("DT_ISERCAO");
				
				resultado = new Investimento(cod, nome, cotas, descricao, dtIsercao);
				
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
