package com.fintech.projeto.DAO.impl;

import com.fintech.projeto.DAO.GanhoDAO;
import com.fintech.projeto.beans.*;
import com.fintech.projeto.connection.*;
import com.fintech.projeto.exception.DBException;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleGanhoDAO implements GanhoDAO {
	private static Connection conexao;
	
	public List<Ganho> listar() {
		 
	    List<Ganho> lista = new ArrayList<Ganho>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	      conexao = ConexaoDB.obterConexao();
	    stmt = conexao.prepareStatement("SELECT * FROM T_SIP_GANHOS");
	    rs = stmt.executeQuery();
	  
	    while (rs.next()) {
	    String cod = rs.getString("CD_GANHO");
	    String nome = rs.getString("NM_LUCRO");
	    String descricao = rs.getString("DS_LUCRO");
	    double valor = rs.getDouble("NR_VALOR");
	    Date dtIsercao = rs.getDate("DT_ISERCAO");
	        

	        Ganho ganho = new Ganho(cod, nome, valor, descricao, dtIsercao);
	        
	        lista.add(ganho);
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
	
	public void cadastrar(Usuario usuario, Ganho ganho) {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "INSERT INTO T_SIP_GANHOS(CD_GANHO, CPF, NM_LUCRO, NR_VALOR, DS_LUCRO) VALUES (SQ_LUCRO.NEXTVAL, ?, ?, ?, ?)";
			String sql1 = "UPDATE T_SIP_USUARIO SET NR_SALDO = ? WHERE CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getCPF());
			stmt.setString(2, ganho.getNome());
			stmt.setDouble(3, ganho.getValor());
			stmt.setString(4, ganho.getDescricao());
			stmt.executeUpdate();
			
			stmt.close();
			ganho.addGanhoSaldo(usuario);
			stmt = conexao.prepareStatement(sql1);
			stmt.setDouble(1, usuario.getSaldo());
			stmt.setString(2, usuario.getCPF());
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close(); 
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Usuario usuario, Ganho ganho) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "UPDATE T_SIP_GANHOS SET NM_LUCRO = ?, NR_VALOR = ?, DS_LUCRO = ? WHERE CD_GANHO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, ganho.getNome());
			stmt.setDouble(2, ganho.getValor());
			stmt.setString(3, ganho.getDescricao());
			stmt.setString(4, ganho.getCod());
			
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			String sql = "DELETE * FROM T_SIP_GANHOS WHERE CD_GANHO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cod);
			
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close(); 
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Ganho buscar(String cod) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Ganho resultado = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "SELECT * FROM T_SIP_GANHOS WHERE CD_GANHO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cod);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				cod = rs.getString("CD_GANHO");
				String nome = rs.getString("NM_LUCRO");
				double valor = rs.getDouble("NR_VALOR");
				String descricao = rs.getString("DS_LUCRO");
				
				resultado = new Ganho(cod, nome, valor, descricao);
				return resultado;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close(); 
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}	return resultado;
	}
}
